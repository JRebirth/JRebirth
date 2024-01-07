/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.preloader;


import javafx.application.Application;
import javafx.application.Preloader;

/**
 * The Class MessageNotification.
 */
public class MessageNotification implements Preloader.PreloaderNotification {

    /** The message. */
    private final String message;

    /** The application. */
    private final Application application;

    /**
     * Instantiates a new message notification.
     *
     * @param message the message
     * @param application the application that triggers this message
     */
    public MessageNotification(final String message, final Application application) {
        super();
        this.message = message;
        this.application = application;
    }

    /**
     * Gets the message.
     *
     * @return Returns the message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets the application.
     *
     * @return Returns the application.
     */
    public Application getApplication() {
        return this.application;
    }

}
