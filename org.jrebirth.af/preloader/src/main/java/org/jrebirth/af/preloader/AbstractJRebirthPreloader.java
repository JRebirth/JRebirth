package org.jrebirth.af.preloader;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The class <strong>AbstractJRebirthPreloader</strong> is the base class that receives progress notifications from JRebirth engine .
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractJRebirthPreloader extends Preloader {

    /** The Progress Bar. */
    protected ProgressBar progressBar;

    /** The preloader Stage. */
    protected Stage preloaderStage;

    /** The text that will display message received. */
    protected Text messageText;

    /** Flag that indicates if the application is initialized. */
    protected boolean jrebirthInitialized = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleProgressNotification(final ProgressNotification pn) {
        if (this.jrebirthInitialized && pn.getProgress() >= 0.0 && pn.getProgress() <= 1.0) {
            this.progressBar.setProgress(pn.getProgress());
        } else {
            this.messageText.setText(getMessageFromCode((int) pn.getProgress()));
        }
    }

    /**
     * Gets the message from code.
     *
     * @param messageCode the message code
     *
     * @return the message from code
     */
    private String getMessageFromCode(final int messageCode) {
        String res = "";
        switch (messageCode) {
            case 100:
                res = "Initializing";
                break;
            case 200:
                res = "";// Provisioned for custom pre-init task
                break;
            case 300:
                res = "";// Provisioned for custom pre-init task
                break;
            case 400:
                res = "Loading Messages Properties";
                break;
            case 500:
                res = "Loading Parameters Properties";
                break;
            case 600:
                res = "Preparing Core Engine";
                break;
            case 700:
                res = "Preloading Resources";
                break;
            case 800:
                res = "Preloading Modules";
                break;
            case 900:
                res = "";// Provisioned for custom post-init task
                break;
            case 1000:
                res = "Starting";
                break;
            default:
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleStateChangeNotification(final StateChangeNotification event) {
        switch (event.getType()) {
            case BEFORE_LOAD:
                break;
            case BEFORE_INIT:
                this.jrebirthInitialized = true;
                break;
            case BEFORE_START:
                hideStage();
                break;
            default:
        }
    }

    /**
     * Perform actions before the application start.
     *
     * @throws InterruptedException
     */
    protected abstract void hideStage();

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        // Store the preloader stage to reuse it later
        this.preloaderStage = stage;

        // Configure the stage
        stage.centerOnScreen();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(createPreloaderScene());

        // Let's start the show
        stage.show();
    }

    /**
     * Creates the preloader scene.
     *
     * @return the scene
     */
    protected abstract Scene createPreloaderScene();

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleApplicationNotification(final PreloaderNotification n) {
        if (n instanceof MessageNotification) {
            this.messageText.setText(((MessageNotification) n).getMessage());
        } else if (n instanceof ProgressNotification) {
            handleProgressNotification((ProgressNotification) n);
        }
    }
}
