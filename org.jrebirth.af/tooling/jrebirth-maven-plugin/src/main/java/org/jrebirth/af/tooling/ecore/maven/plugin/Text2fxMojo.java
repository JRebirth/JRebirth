package org.jrebirth.af.tooling.ecore.maven.plugin;

import java.io.File;
import java.nio.file.Path;

import org.jrebirth.af.tooling.text2fx.Text2fxGenerator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Generates JavaFX property beans from the JRebirth {@code .model} text DSL (not Ecore).
 */
@Mojo(name = "text2fx", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class Text2fxMojo extends AbstractMojo {

    enum OutputKind {
        generated, src, custom
    }

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    /**
     * Path to the {@code .model} file (relative to the project base directory unless absolute).
     */
    @Parameter(property = "jrebirth.text2fx.modelFile", required = true)
    private File modelFile;

    /**
     * Only {@code javafx} is supported.
     */
    @Parameter(defaultValue = "javafx")
    private String generator;

    /**
     * When {@code false}, boolean getters use {@code isXxx()}; when {@code true}, {@code getXxx()}.
     */
    @Parameter(defaultValue = "true")
    private boolean javaFxAccessorPrefixes;

    @Parameter(defaultValue = "generated")
    private OutputKind outputKind;

    @Parameter(defaultValue = "${project.build.directory}/generated-sources")
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException {
        if (!"javafx".equalsIgnoreCase(this.generator.trim())) {
            throw new MojoExecutionException("Unsupported text2fx generator: '" + this.generator + "' (only javafx is supported)");
        }

        final File model = this.modelFile.isAbsolute() ? this.modelFile : new File(this.project.getBasedir(), this.modelFile.getPath());
        if (!model.isFile()) {
            throw new MojoExecutionException("Model file not found: " + model.getAbsolutePath());
        }

        final File out = resolveOutputFolder();
        getLog().info("Text2FX: model=" + model.getAbsolutePath() + " -> " + out.getAbsolutePath());

        try {
            new Text2fxGenerator().generate(model.toPath(), out.toPath(), this.javaFxAccessorPrefixes);
        } catch (final Exception e) {
            throw new MojoExecutionException("Text2FX generation failed: " + e.getMessage(), e);
        }

        registerCompileSourceRoot(out);
    }

    private File resolveOutputFolder() {
        return switch (this.outputKind) {
            case generated -> new File(this.project.getBuild().getDirectory(), "generated-sources");
            case src -> new File(this.project.getBuild().getSourceDirectory());
            case custom -> this.outputDirectory;
        };
    }

    private void registerCompileSourceRoot(final File out) {
        final Path outNorm = out.toPath().toAbsolutePath().normalize();
        final Path srcNorm = Path.of(this.project.getBuild().getSourceDirectory()).toAbsolutePath().normalize();
        if (!outNorm.equals(srcNorm)) {
            this.project.addCompileSourceRoot(out.getAbsolutePath());
        }
    }

}
