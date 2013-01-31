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
package org.jrebirth.core.facade;

/**
 * The interface <strong>JRebirthEvent</strong>.
 * 
 * An Event is stored for each important action into the application. Events are useful to understand and debug complex interactions.
 * 
 * @author Sébastien Bordes
 */
public interface JRebirthEvent {

    /**
     * @return Returns the sequence number.
     */
    int getSequence();

    /**
     * @param sequence The sequence to set.
     */
    void setSequence(int sequence);

    /**
     * @return Returns the eventType.
     */
    JRebirthEventType getEventType();

    /**
     * @param eventType The eventType to set.
     */
    void setEventType(JRebirthEventType eventType);

    /**
     * @return Returns the source class.
     */
    Class<?> getSource();

    /**
     * @param source The source class to set.
     */
    void setSource(Class<?> source);

    /**
     * @return Returns the target class.
     */
    Class<?> getTarget();

    /**
     * @param target The data to set.
     */
    void setTarget(Class<?> target);

    /**
     * @return Returns the data.
     */
    String getEventData();

    /**
     * @param data The data to set.
     */
    void setEventData(String data);

}
