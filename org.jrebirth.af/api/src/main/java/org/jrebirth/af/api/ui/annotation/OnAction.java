/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.api.ui.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javafx.event.ActionEvent;
import javafx.event.Event;

import org.jrebirth.af.api.ui.annotation.type.Action;

/**
 * This annotation is used to automatically attached an action event handler to a property node.
 *
 * @author Sébastien Bordes
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnAction {

    /**
     * Define the event type to manage.
     *
     * The default value is ActionType.Action, no other type can be defined
     */
    Action[] value() default Action.Action;

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
    Class<? extends Event> apiEventClass() default ActionEvent.class;
}
