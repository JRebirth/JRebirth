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
 * Goal which
 *
 */
@Mojo(name = "ecore2fx", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class Ecore2FXMojo extends AbstractMojo {

    private enum OutputKind {
        generated, src, custom
    }

    /**
     * Location of the model file.
     */
    @Parameter(defaultValue = "Model.ecore", property = "jrebirth.ecoreFile", required = true)
    private File ecoreFile;

    @Parameter(defaultValue = "generated")
    private OutputKind outputKind;

    /**
     * Specify output directory where the Java files are generated.
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources")
    private File outputDirectory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() throws MojoExecutionException {
        final Ecore2FXGenerator g = new Ecore2FXGenerator();

        g.generate(getOutputFolder(), this.ecoreFile);
    }

    private File getOutputFolder() {
        final MavenProject mavenProject = (MavenProject) getPluginContext().get("project");

        switch (outputKind) {
            case generated:
                return new File(mavenProject.getBuild().getDirectory() + "/generated-sources");
            case src:
                return new File(mavenProject.getBuild().getSourceDirectory());
            default:
                return outputDirectory;
        }
    }

}
