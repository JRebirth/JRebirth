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
package org.jrebirth.af.api.ui.fxml;

import org.jrebirth.af.api.ui.Model;

/**
 * The Interface FXMLModel implemented by Model loading a fxml file.
 */
public interface FXMLModel extends Model {

    /** The key part prefix used to attach the fxml path to this class. */
    String KEYPART_FXML_PREFIX = "fxml:";

    /** The key part prefix used to attach the resource bundle path to this class. */
    String KEYPART_RB_PREFIX = "rb:";

}
