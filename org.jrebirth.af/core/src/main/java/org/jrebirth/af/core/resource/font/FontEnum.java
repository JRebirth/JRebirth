package org.jrebirth.af.core.resource.font;

import javafx.scene.text.Font;

import org.jrebirth.af.core.resource.font.FontItemReal.Family;
import org.jrebirth.af.core.resource.font.FontItemReal.Real;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;

/**
 * The interface <strong>FontEnum</strong> should be inherited by any Enumeration that want to manage {@link Font}.
 *
 * Take care of the value used for ({@link JRebirthParameters#FONT_FOLDER}) which will be prepend to the font path.
 *
 * @author Sébastien Bordes
 */
public interface FontEnum extends Real, Family {

}
