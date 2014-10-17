package org.jrebirth.af.presentation.resources;


import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.core.resource.parameter.ObjectParameter;
import org.jrebirth.af.core.resource.parameter.ParameterItem;

/**
 * The interface <strong>PrezParameters</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface PrezParameters {

    /** The presentation XML file. */
    ParameterItem<String> XML_FILE_LOCATION = create(new ObjectParameter<String>("xmlFileLocation", "presentation/Presentation"));
}
