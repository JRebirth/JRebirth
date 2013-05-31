package org.jrebirth.core.resource.provided;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.resource.image.ImageItem;

/**
 * The class <strong>JRebirthImages</strong>.
 * 
 * Images used by JRebirth Application Framework itself
 * 
 * @author Sébastien Bordes
 */
public interface JRebirthImages {

    /**************************************************************************************/
    /** _____________________________Application Core Images.____________________________ */
    /**************************************************************************************/

    /** The Not/Available default image. */
    ImageItem NOT_AVAILABLE = create(JRebirthParameters.NOT_AVAILABLE_IMAGE.get());

}
