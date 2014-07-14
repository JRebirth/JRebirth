package org.jrebirth.af.component.workbench;

import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import org.jrebirth.af.component.command.StartUpCommand;
import org.jrebirth.af.component.resources.ComponentStyles;
import org.jrebirth.af.component.workbench.ui.TabDemoModel;
import org.jrebirth.af.core.application.DefaultApplication;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveBase;

/**
 * The class <strong>WorkbenchApplication</strong>.
 *
 * @author
 */
public final class WorkbenchApplication extends DefaultApplication<StackPane> {

    /**
     * Application launcher.
     *
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(WorkbenchApplication.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getFirstModelClass() {
        return TabDemoModel.class;
    }

    @Override
    protected void customizeScene(final Scene scene) {
        super.customizeScene(scene);

        addCSS(scene, ComponentStyles.DEFAULT);
    }

    @Override
    public List<Wave> getPreBootWaveList() {

        return Collections.singletonList(WaveBase.callCommand(StartUpCommand.class));
    }

}
