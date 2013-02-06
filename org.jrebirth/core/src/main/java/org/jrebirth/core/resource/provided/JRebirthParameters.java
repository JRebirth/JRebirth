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
    ParameterItemBase<Boolean> DEVELOPER_MODE = ParameterItemBase.build("developerMode", true);

    /** Close Retry Delay in milliseconds. */
    ParameterItemBase<Integer> CLOSE_RETRY_DELAY_FIRST = ParameterItemBase.build("closeRetryDelayFirst", 4000);

    /** Close Retry Delay in milliseconds. */
    ParameterItemBase<Integer> CLOSE_RETRY_DELAY_OTHER = ParameterItemBase.build("closeRetryDelayOther", 1000);

    /** . */
    ParameterItemBase<Integer> THREAD_POOL_SIZE = ParameterItemBase.build("threadPoolSize", 4);

    /** Font default folder . */
    ParameterItemBase<String> DEFAULT_FONT_FOLDER = ParameterItemBase.build("defaultFontFolder", "font");

    /**
     * The <code>TRUE_TYPE_FONT_EXTENSION</code> field is used to define the font file extension .
     */
    ParameterItemBase<String> TRUE_TYPE_FONT_EXTENSION = ParameterItemBase.build("trueTypeFontExtension", ".ttf");

    /**************************************************************************************/
    /** _________________________Application Stage Parameters.___________________________ */
    /**************************************************************************************/

    /** The application stage width. */
    ParameterItemBase<Integer> APPLICATION_STAGE_WIDTH = ParameterItemBase.build("applicationStageWidth", 800);

    /** The application stage height. */
    ParameterItemBase<Integer> APPLICATION_STAGE_HEIGHT = ParameterItemBase.build("applicationStageHeight", 600);

    /** The application stage height. */
    // ParameterItemBase<WebColor> APPLICATION_STAGE_BG_COLOR = ParameterItemBase.build(new WebColor("FFFFFF", 0), 600);
}
