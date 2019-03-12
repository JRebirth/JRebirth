package org.jrebirth.af.tooling.ecore_maven_plugin;

import java.io.File;

import org.jrebirth.tooling.ecore2fx.Ecore2FXGenerator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Goal which
 *
 */
@Mojo(name = "ecore2fx", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class Ecore2FXMojo extends AbstractMojo {

    /**
     * Location of the model file.
     */
    @Parameter(defaultValue = "Model.ecore", property = "jrebirth.ecoreFile", required = true)
    private File ecoreFile;

    /**
     * Specify output directory where the Java files are generated.
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources")
    private File outputDirectory;

    @Parameter(defaultValue = "false")
    private boolean generateWaveItems;

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() throws MojoExecutionException {
        final Ecore2FXGenerator g = new Ecore2FXGenerator();
        g.setGenerateWaveItems(this.generateWaveItems);
        g.generate(this.outputDirectory, this.ecoreFile, generateWaveItems);
    }

}
