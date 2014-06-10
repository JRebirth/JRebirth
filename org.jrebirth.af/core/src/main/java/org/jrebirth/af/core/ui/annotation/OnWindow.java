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
package org.jrebirth.af.core.ui.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javafx.event.Event;
import javafx.stage.WindowEvent;

import org.jrebirth.af.core.ui.annotation.type.Window;

/**
 * This annotation is used to automatically attached a window event handler to a property node.
 *
 * @author Sébastien Bordes
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnWindow {

    /**
     * Define the event type to manage.
     *
     * The default value is WindowType.Any
     */
    Window[] value() default Window.Any;

    /**
     * Define a unique name used to avoid sharing same handler.
     *
     * The default value is null, same event handler will be used for multiple annotation
     */
    String name() default "";

    /**
     * Define the JavaFX api event class.
     *
     * Must not be changed !!!
     */
    Class<? extends Event> apiEventClass() default WindowEvent.class;
}
