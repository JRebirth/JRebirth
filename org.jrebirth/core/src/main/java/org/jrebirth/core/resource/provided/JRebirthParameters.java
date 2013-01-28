package org.jrebirth.core.resource.provided;

import org.jrebirth.core.resource.parameter.ParameterItemBase;

/**
 * The class <strong>JRebirthParameters</strong>.
 * 
 * Parameters used by JRebirth Application Framework itself
 * 
 * @author Sébastien Bordes
 */
public interface JRebirthParameters {

    /**************************************************************************************/
    /** __________________________Application Core Parameters.___________________________ */
    /**************************************************************************************/

    /** The application stage width. */
    ParameterItemBase<Integer> THREAD_POOL_SIZE = ParameterItemBase.build("threadPoolSize", 4);

    /**************************************************************************************/
    /** _________________________Application Stage Parameters.___________________________ */
    /**************************************************************************************/

    /** The application stage width. */
    ParameterItemBase<Integer> APPLICATION_STAGE_WIDTH = ParameterItemBase.build("stageWidth", 800);

    /** The application stage height. */
    ParameterItemBase<Integer> APPLICATION_STAGE_HEIGHT = ParameterItemBase.build("stageHeight", 600);

    /** The application stage height. */
    // ParameterItemBase<WebColor> APPLICATION_STAGE_BG_COLOR = ParameterItemBase.build(new WebColor("FFFFFF", 0), 600);
}
