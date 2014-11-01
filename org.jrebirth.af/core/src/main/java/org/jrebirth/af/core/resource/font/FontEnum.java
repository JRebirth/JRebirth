package org.jrebirth.af.core.resource.font;

import javafx.scene.text.Font;

import org.jrebirth.af.core.resource.color.ColorItemReal.Gray;
import org.jrebirth.af.core.resource.color.ColorItemReal.HSB;
import org.jrebirth.af.core.resource.color.ColorItemReal.RGB01;
import org.jrebirth.af.core.resource.color.ColorItemReal.RGB255;
import org.jrebirth.af.core.resource.color.ColorItemReal.Web;

/**
 * The interface <strong>FontEnum</strong> should be inherited by any Enumeration that want to manage {@link Font}.
 *
 * @author Sébastien Bordes
 */
public interface FontEnum extends RGB255, RGB01, HSB, Web, Gray {

}
