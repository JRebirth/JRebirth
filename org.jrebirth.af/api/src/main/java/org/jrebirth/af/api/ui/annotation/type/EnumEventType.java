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
package org.jrebirth.af.api.ui.annotation.type;

import javafx.event.EventType;

/**
 * The class <strong>EnumEventType</strong>.
 *
 * @author Sébastien Bordes
 */
public interface EnumEventType {

    /**
     * Return the javaFX event type.
     *
     * @return the eventType
     */
    EventType<?> eventType();

    /**
     * Return the enum name.
     *
     * @return the enum name
     */
    String name();

}
