package org.jrebirth.core.application;

import java.util.Collections;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.resource.font.FontItem;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;

/**
 * 
 * The Default class <strong>DefaultApplication</strong> is the base class of a JRebirth Application.
 * 
 * This the class to extend if you want to build an application using JRebirth WCS-MVC (Wave-Command-Service-Model-View-Controller) without override all abstract methods.
 * 
 * @author Sébastien Bordes
 * 
 * @param <P> The root node of the stage, must extends Pane to allow children management
 */
public class DefaultApplication<P extends Pane> extends AbstractApplication<P> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getFirstModelClass() {
        throw new CoreRuntimeException("You must override getFirstModelClass without calling super.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeStage(final Stage stage) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeScene(final Scene scene) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FontItem> getFontToPreload() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> getPreBootWaveList() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> getPostBootWaveList() {
        return Collections.emptyList();
    }

}
