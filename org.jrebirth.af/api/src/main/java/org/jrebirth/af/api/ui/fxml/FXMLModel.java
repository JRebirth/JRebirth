package org.jrebirth.af.api.ui.fxml;

import org.jrebirth.af.api.ui.Model;

public interface FXMLModel extends Model {

    /** The key part prefix used to attach the fxml path to this class. */
    String KEYPART_FXML_PREFIX = "fxml:";

    /** The key part prefix used to attach the resource bundle path to this class. */
    String KEYPART_RB_PREFIX = "rb:";
    
}
