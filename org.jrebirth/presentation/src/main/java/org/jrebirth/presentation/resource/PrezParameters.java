package org.jrebirth.presentation.resource;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.resource.parameter.ObjectParameter;
import org.jrebirth.core.resource.parameter.ParameterItem;

/**
 * The interface <strong>PrezParameters</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface PrezParameters {

    /** The presentation XML file. */
    ParameterItem<String> XML_FILE_LOCATION = create(new ObjectParameter<String>("xmlFileLocation", "presentation/Presentation"));
}
