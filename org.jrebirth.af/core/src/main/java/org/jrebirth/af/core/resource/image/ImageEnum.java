package org.jrebirth.af.core.resource.image;

import javafx.scene.image.Image;

import org.jrebirth.af.core.resource.image.ImageItemBase.Absolute;
import org.jrebirth.af.core.resource.image.ImageItemBase.Relative;
import org.jrebirth.af.core.resource.image.ImageItemBase.Web;
import org.jrebirth.af.core.resource.provided.parameter.CoreParameters;

/**
 * The interface <strong>ImageEnum</strong> should be inherited by any Enumeration that want to manage {@link Image}.
 *
 * Take care of the value used for ({@link CoreParameters#IMAGE_FOLDER}) which will be prepend to the image path.
 *
 * @author Sébastien Bordes
 */
public interface ImageEnum extends Web, Relative, Absolute {

}
