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
package org.jrebirth.af.rest;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.resource.parameter.ParameterItem;

/**
 * The class <strong>RestParameters</strong>.
 *
 * Parameters used by Rest Services base implementation.
 *
 * @author Sébastien Bordes
 */
public interface RestParameters {

    /**************************************************************************************/
    /** _________________________________Rest Parameters.________________________________ */
    /**************************************************************************************/

	// http://localhost:8080/JRebirth

    /** The default address of the remote rest server to use. */
    ParameterItem<String> DEFAULT_REST_SERVER_PROTOCOL = create("defaultRestServerProtocol", "http");
    
    /** The default address of the remote rest server to use. */
    ParameterItem<String> DEFAULT_REST_SERVER_ADDRESS = create("defaultRestServerAddress", "localhost");
    
    /** The default address of the remote rest server to use. */
    ParameterItem<String> DEFAULT_REST_SERVER_PORT = create("defaultRestServerPort", "8080");
    
    /** The default address of the remote rest server to use. */
    ParameterItem<String> DEFAULT_REST_SERVER_PATH = create("defaultRestServerPath", "JRebirth");
    

}
