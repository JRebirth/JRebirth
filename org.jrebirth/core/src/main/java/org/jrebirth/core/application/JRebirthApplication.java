package org.jrebirth.core.application;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The class <strong>JRebirthApplication</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface JRebirthApplication {

    /** The default scene width used. */
    int DEFAULT_SCENE_WIDTH = 800;

    /** The default scene height used. */
    int DEFAULT_SCENE_HEIGHT = 600;

    /** The default scene height used. */
    Color DEFAULT_SCENE_BG_COLOR = Color.TRANSPARENT;

    /**
     * Return the logger status.
     * 
     * @return true if logging is enabled, false otherwise
     */
    boolean isLoggerEnabled();

    /**
     * Return the track event status.
     * 
     * @return true if event tracking is enable, false otherwise
     */
    boolean isEventTrackerEnabled();

    /**
     * @return Returns the stage.
     */
    Stage getStage();

    /**
     * @return Returns the scene.
     */
    Scene getScene();

}
