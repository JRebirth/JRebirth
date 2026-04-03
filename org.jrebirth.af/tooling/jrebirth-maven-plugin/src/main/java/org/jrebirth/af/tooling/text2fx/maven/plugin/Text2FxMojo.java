package org.jrebirth.af.tooling.text2fx.maven.plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.jrebirth.af.tooling.text2fx.generator.JavaFXGenerator;
import org.jrebirth.af.tooling.text2fx.generator.JavaGenerator;
import org.jrebirth.af.tooling.text2fx.model.Model;
import org.jrebirth.af.tooling.text2fx.parser.ModelParser;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Generates Java sources from the text DSL (.model): plain POJOs or JavaFX beans.
 */
@Mojo(name = "text2fx", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class Text2FxMojo extends AbstractMojo {

    private enum OutputKind {
        generated, src, custom
    }

    public enum GeneratorKind {
        /** Classic JavaBean getters/setters (void setters). */
        pojo,
        /** JavaFX properties + fluent or prefixed accessors. */
        javafx
    }

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    /**
     * Input model file (DSL).
     */
    @Parameter(property = "jrebirth.text2fx.modelFile", required = true)
    private File modelFile;

    /**
     * {@code pojo} or {@code javafx}.
     */
    @Parameter(defaultValue = "javafx", property = "jrebirth.text2fx.generator")
    private GeneratorKind generator;

    /**
     * When {@link #generator} is {@code javafx}: use {@code getX}/{@code setX}/{@code isX} instead of fluent {@code x()}/{@code x(value)}.
     */
    @Parameter(defaultValue = "false", property = "jrebirth.text2fx.javaFxAccessorPrefixes")
    private boolean javaFxAccessorPrefixes;

    @Parameter(defaultValue = "generated")
    private OutputKind outputKind;

    @Parameter(defaultValue = "${project.build.directory}/generated-sources/text2fx")
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException {
        if (!modelFile.isFile()) {
            throw new MojoExecutionException("Text2FX model file not found: " + modelFile.getAbsolutePath());
        }

        final File out = resolveOutputFolder();
        out.mkdirs();

        final Model model;
        try {
            model = new ModelParser().parse(Path.of(modelFile.toURI()));
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to read model file: " + modelFile, e);
        } catch (IllegalArgumentException e) {
            throw new MojoExecutionException("Invalid model: " + e.getMessage(), e);
        }

        final Path outputRoot = Path.of(out.toURI());
        try {
            switch (generator) {
                case pojo -> new JavaGenerator().generate(model, outputRoot);
                case javafx -> new JavaFXGenerator(javaFxAccessorPrefixes).generate(model, outputRoot);
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Code generation failed", e);
        }

        project.addCompileSourceRoot(out.getAbsolutePath());
        getLog().info("Text2FX generated sources into " + out.getAbsolutePath() + " (" + generator
                + (generator == GeneratorKind.javafx ? ", javaFxAccessorPrefixes=" + javaFxAccessorPrefixes : "") + ")");
    }

    private File resolveOutputFolder() {
        switch (outputKind) {
            case generated:
                return new File(project.getBuild().getDirectory(), "generated-sources/text2fx");
            case src:
                return new File(project.getBuild().getSourceDirectory());
            default:
                return outputDirectory;
        }
    }
}
