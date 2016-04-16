package org.jrebirth.af.core.application;

import java.util.Collections;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.resource.ResourceItem;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;

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
    protected void preInit() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void postInit() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void preStop() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void postStop() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> firstModelClass() {
        throw new CoreRuntimeException(OVERRIDE_FIRST_MODEL_CLASS);
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
    protected List<? extends ResourceItem<?, ?, ?>> getResourceToPreload() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> preBootWaveList() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> postBootWaveList() {
        return Collections.emptyList();
    }

}
