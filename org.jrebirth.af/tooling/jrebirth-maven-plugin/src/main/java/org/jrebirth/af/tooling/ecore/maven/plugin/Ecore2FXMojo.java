package org.jrebirth.af.tooling.ecore.maven.plugin;

import java.io.File;

import org.jrebirth.tooling.ecore2fx.Ecore2FXGenerator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Generates JavaFX-friendly model types from an EMF {@code .ecore} file using {@link Ecore2FXGenerator}.
 *
 * <p>For the text {@code .model} DSL, use goal {@code text2fx} ({@link Text2fxMojo}).</p>
 */
@Mojo(name = "ecore2fx", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class Ecore2FXMojo extends AbstractMojo {

    enum OutputKind {
        generated, src, custom
    }

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    /**
     * Ecore model file (XMI), relative to the project base directory unless absolute.
     */
    @Parameter(defaultValue = "Model.ecore", property = "jrebirth.ecoreFile", required = true)
    private File ecoreFile;

    /**
     * {@code generated} → {@code target/generated-sources}; {@code src} → main source directory;
     * {@code custom} → {@link #outputDirectory}.
     */
    @Parameter(defaultValue = "generated")
    private OutputKind outputKind;

    /**
     * Used when {@code outputKind} is {@code custom}.
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources")
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException {
        final File model = this.ecoreFile.isAbsolute() ? this.ecoreFile : new File(this.project.getBasedir(), this.ecoreFile.getPath());
        if (!model.isFile()) {
            throw new MojoExecutionException("Ecore file not found: " + model.getAbsolutePath());
        }

        final File out = resolveOutputFolder();
        getLog().info("Ecore2FX: model=" + model.getAbsolutePath() + " -> " + out.getAbsolutePath());

        try {
            new Ecore2FXGenerator().generate(out, model);
        } catch (final RuntimeException e) {
            throw new MojoExecutionException("Ecore2FX generation failed: " + e.getMessage(), e);
        }
    }

    private File resolveOutputFolder() {
        return switch (this.outputKind) {
            case generated -> new File(this.project.getBuild().getDirectory(), "generated-sources");
            case src -> new File(this.project.getBuild().getSourceDirectory());
            case custom -> this.outputDirectory;
        };
    }

}
