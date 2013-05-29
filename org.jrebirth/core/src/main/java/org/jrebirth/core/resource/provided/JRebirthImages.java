package org.jrebirth.core.resource.provided;

import org.jrebirth.core.resource.image.ImageItem;
import org.jrebirth.core.resource.image.ImageItemBase;
import org.jrebirth.core.resource.image.LocalImage;

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

    /** The application stage width. */
    ImageItem NOT_AVAILABLE = ImageItemBase.build(new LocalImage("image/NotAvailable.png"));

}
