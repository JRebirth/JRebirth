/**
Copyright JRebirth.org © 2011 
sebastien.bordes@jrebirth.org

This software is a computer program whose purpose is to [describe
functionalities and technical features of your software].

This software is governed by the CeCILL-C license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the CeCILL-C
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL-C license and that you accept its terms.
 */
package org.jrebirth.core.application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.GlobalFacade;
import org.jrebirth.core.facade.impl.GlobalFacadeImpl;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.util.ClassUtility;

/**
 * 
 * The class <strong>AbstractApplication</strong>.
 * 
 * The class to extend if you want to build an application using JRebirth CSM MVC (Command-Service-Message-Model-View Controller).
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 333 $ $Author: sbordes $
 * @since $Date: 2012-02-07 23:18:59 +0100 (mar., 07 févr. 2012) $
 * 
 * @param <P> The root node of the stage, must extends Pane
 */
public abstract class AbstractApplication<P extends Pane> extends Application implements JRebirthApplication {

    /** The Global Facade object that handle other sub facade. */
    private transient GlobalFacade facade;

    /** The application primary stage. */
    private transient Stage stage;

    /** The application scene. */
    private transient Scene scene;

    /** The logger status. */
    private boolean loggerEnabled = true;

    /** The eventTracker status. */
    private boolean eventTrackerEnabled = true;

    /**
     * {@inheritDoc}
     */
    // @Override
    @Override
    public final void init() throws CoreException {
        try {
            super.init();
            // Build the global facade at startup
            this.facade = new GlobalFacadeImpl(this);
        } catch (final Exception e) {
            throw new CoreException(e);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void start(final Stage primaryStage) {

        try {

            // Attach the primary stage for later customization
            this.stage = primaryStage;
            // Customize the primary stage
            initializeStage(this.stage);

            this.scene = buildScene(primaryStage);
            // Customize the default scene previously created
            initializeScene(this.scene);

            // Launch the first UI view
            launchFirstView();

            // Attach the scene
            primaryStage.setScene(this.scene);
            primaryStage.show();

        } catch (final CoreException ce) {
            getFacade().getLogger().error("Error while starting application : " + ce.getMessage());
        }
    }

    /**
     * Customize the primary Stage.
     * 
     * @param stage the primary stage to customize
     */
    private void initializeStage(final Stage stage) {

        stage.setTitle(getApplicationTitle());

        customizeStage(stage);
    }

    /**
     * Customize the primary stage.
     * 
     * @param stage the primary stage to customize
     */
    protected abstract void customizeStage(final Stage stage);

    /**
     * Initialize the default scene.
     * 
     * @param scene the default scene to initialize
     */
    private void initializeScene(final Scene scene) {

        final Stage currentStage = this.stage;

        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    // event.consume();
                    getFacade().getLogger().error("primary filtered");
                }
            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    // event.consume();
                    getFacade().getLogger().error("primary handled");
                }
            }
        });

        // Manage F11 button to switch full screen
        scene.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.F11) {
                    currentStage.setFullScreen(!currentStage.isFullScreen());
                    event.consume();
                    // getFacade().getLogger().error("f11 filtered and consumed");
                } else if (event.getCode() == KeyCode.F10) {
                    currentStage.setIconified(!currentStage.isIconified());
                    event.consume();
                    // getFacade().getLogger().error("f10 filtered and consumed");
                } else {
                    // getFacade().getLogger().error(event.getCode() + " filtered");
                }

            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.F11) {
                    // getFacade().getLogger().error("f11 handler");
                } else {
                    // getFacade().getLogger().error(event.getCode() + " handled");
                }

            }
        });

        customizeScene(scene);
    }

    /**
     * Customize the default scene.
     * 
     * @param scene the scene to customize
     */
    protected abstract void customizeScene(final Scene scene);

    /**
     * Launch the first view by adding it into the root node.
     * 
     * @throws CoreException if the first class was not found
     */
    @SuppressWarnings("unchecked")
    protected final void launchFirstView() throws CoreException {

        final Class<? extends Model> first = getFirstModelClass();

        if (first == null) {
            throw new CoreException("No First Model Class defined.");
        }
        final Node firstNode = getFacade().getUiFacade().retrieve(first).getView().getRootNode();
        ((P) this.scene.getRoot()).getChildren().add(firstNode);

        firstNode.requestFocus();
    }

    /**
     * Return the application title.
     * 
     * @return the application title
     */
    protected abstract String getApplicationTitle();

    /**
     * Initialize the properties of the scene.
     * 
     * 800x600 with transparent background and a Region as Parent Node
     * 
     * @param primaryStage the primary javafx stage
     * 
     * @return the scene built
     * 
     * @throws CoreException if build fails
     */
    protected final Scene buildScene(final Stage primaryStage) throws CoreException {
        return SceneBuilder.create().root(buildRootPane()).width(JRebirthApplication.DEFAULT_SCENE_WIDTH).height(JRebirthApplication.DEFAULT_SCENE_HEIGHT)
                .fill(JRebirthApplication.DEFAULT_SCENE_BG_COLOR).build();
    }

    /**
     * Build dynamically the root pane.
     * 
     * @return the root pane
     * @throws CoreException if build fails
     */
    @SuppressWarnings("unchecked")
    protected P buildRootPane() throws CoreException {
        return (P) ClassUtility.buildGenericType(this.getClass(), 0);
    }

    /**
     * This method must be implemented to declare which UI Model to display first.
     * 
     * @return the class of the first UI Model to launch
     */
    protected abstract Class<? extends Model> getFirstModelClass();

    /**
     * @param loggerEnabled The loggerEnabled to set.
     */
    public final void setLoggerEnabled(final boolean loggerEnabled) {
        this.loggerEnabled = loggerEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isLoggerEnabled() {
        return this.loggerEnabled;
    }

    /**
     * @param eventTrackerEnabled The eventTrackerEnabled to set.
     */
    public final void setEventTrackerEnabled(final boolean eventTrackerEnabled) {
        this.eventTrackerEnabled = eventTrackerEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isEventTrackerEnabled() {
        return this.eventTrackerEnabled;
    }

    /**
     * {@inheritDoc}
     */
    // @Override
    @Override
    public final void stop() throws CoreException {
        try {
            super.stop();
            this.facade.stop();
            this.facade = null;

        } catch (final Exception e) {
            throw new CoreException(e);
        }
    }

    /**
     * @return Returns the facade.
     */
    public final GlobalFacade getFacade() {
        return this.facade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stage getStage() {
        return this.stage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Scene getScene() {
        return this.scene;
    }
}
