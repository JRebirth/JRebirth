package org.jrebirth.preloader;

import javafx.application.Application;
import javafx.application.Preloader.PreloaderNotification;

public class MessageNotification implements PreloaderNotification {

    private final String message;
    private Application application;

    /**
     * @param message
     */
    public MessageNotification(final String message, final Application application) {
        super();
        this.message = message;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return Returns the application.
     */
    public Application getApplication() {
        return this.application;
    }

}
