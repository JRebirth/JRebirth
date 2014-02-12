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
package org.jrebirth.af.core.facade;

/**
 * The enumeration <strong>JRebirthEventType</strong>.
 * 
 * Used to track the type of all JRebirth events
 * 
 * @author Sébastien Bordes
 */
public enum JRebirthEventType {

    /** No Event type specified. */
    NONE,
    /** Create Application class. */
    CREATE_APPLICATION,
    /** Create Global Facade class. */
    CREATE_GLOBAL_FACADE,
    /** Create Notifier class. */
    CREATE_NOTIFIER,
    /** Create Command Facade class. */
    CREATE_COMMAND_FACADE,
    /** Create Service Facade class. */
    CREATE_SERVICE_FACADE,
    /** Create UI Facade class. */
    CREATE_UI_FACADE,
    /** Create Command class. */
    CREATE_COMMAND,
    /** Create Service class. */
    CREATE_SERVICE,
    /** Create Model class. */
    CREATE_MODEL,
    /** Create View class. */
    CREATE_VIEW,
    /** Create Controller class. */
    CREATE_CONTROLLER,
    /** Create Wave class. */
    CREATE_WAVE,

    /** */
    ACCESS_COMMAND,
    /** */
    ACCESS_SERVICE,
    /** */
    ACCESS_MODEL,
    /** */
    ACCESS_VIEW,
    /** */
    ACCESS_CONTROLLER,

    /** */
    DESTROY_APPLICATION,
    /** */
    DESTROY_GLOBAL_FACADE,
    /** */
    DESTROY_NOTIFIER,
    /** */
    DESTROY_COMMAND,
    /** */
    DESTROY_SERVICE,
    /** */
    DESTROY_MODEL,
    /** */
    DESTROY_VIEW,
    /** */
    DESTROY_CONTROLLER,
    /** */
    DESTROY_WAVE,

}
