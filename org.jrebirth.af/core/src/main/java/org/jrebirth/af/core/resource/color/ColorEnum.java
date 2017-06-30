package org.jrebirth.af.core.resource.color;

import javafx.scene.paint.Color;

import org.jrebirth.af.core.resource.color.ColorItemBase.Gray;
import org.jrebirth.af.core.resource.color.ColorItemBase.HSB;
import org.jrebirth.af.core.resource.color.ColorItemBase.RGB01;
import org.jrebirth.af.core.resource.color.ColorItemBase.RGB255;
import org.jrebirth.af.core.resource.color.ColorItemBase.Web;

/**
 * The interface <strong>ColorEnum</strong> should be inherited by any Enumeration that want to manage {@link Color}.
 *
 * @author Sébastien Bordes
 */
public interface ColorEnum extends RGB255, RGB01, HSB, Web, Gray {

}
