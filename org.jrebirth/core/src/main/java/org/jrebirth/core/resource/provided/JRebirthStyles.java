package org.jrebirth.core.resource.provided;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.resource.style.StyleSheetItem;

/**
 * The class <strong>JRebirthStyles</strong>.
 * 
 * Images used by JRebirth Application Framework itself
 * 
 * @author Sébastien Bordes
 */
public interface JRebirthStyles {

    /**************************************************************************************/
    /** __________________________Application StyleSheet Images._________________________ */
    /**************************************************************************************/

    /** The default Style Sheet used if none have been atatched. */
    StyleSheetItem DEFAULT = create(JRebirthParameters.DEFAULT_CSS.get());

}
