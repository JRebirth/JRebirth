package org.jrebirth.core.resource.provided;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.resource.image.LocalImage;
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
    ParameterItemBase<Boolean> DEVELOPER_MODE = create("developerMode", true);

    /** Close Retry Delay in milliseconds. */
    ParameterItemBase<Integer> CLOSE_RETRY_DELAY_FIRST = create("closeRetryDelayFirst", 4000);

    /** Close Retry Delay in milliseconds. */
    ParameterItemBase<Integer> CLOSE_RETRY_DELAY_OTHER = create("closeRetryDelayOther", 1000);

    /** . */
    ParameterItemBase<Integer> THREAD_POOL_SIZE = create("threadPoolSize", 4);

    /** Font default folder . */
    ParameterItemBase<String> DEFAULT_FONT_FOLDER = create("defaultFontFolder", "font");

    /**
     * The <code>TRUE_TYPE_FONT_EXTENSION</code> field is used to define the font file extension .
     */
    ParameterItemBase<String> TRUE_TYPE_FONT_EXTENSION = create("trueTypeFontExtension", ".ttf");

    /**
     * The <code>WAVE_HANDLER_PREFIX</code> field is used to add a prefix to custom wave handler method of JRebirth components.
     */
    ParameterItemBase<String> WAVE_HANDLER_PREFIX = create("waveHandlerPrefix", "DO_");

    /**
     * The <code>NOT_AVAILABLE_IMAGE</code> field is used to define the image to use when an image is missing.
     */
    ParameterItemBase<LocalImage> NOT_AVAILABLE_IMAGE = create("notAvailableImage", new LocalImage("image/NotAvailableImage.png"));

    /**************************************************************************************/
    /** _________________________Application Stage Parameters.___________________________ */
    /**************************************************************************************/

    /** The application stage width. */
    ParameterItemBase<Integer> APPLICATION_STAGE_WIDTH = create("applicationStageWidth", 800);

    /** The application stage height. */
    ParameterItemBase<Integer> APPLICATION_STAGE_HEIGHT = create("applicationStageHeight", 600);

    /** The application stage height. */
    // ParameterItemBase<WebColor> APPLICATION_STAGE_BG_COLOR = create(new WebColor("FFFFFF", 0), 600);
}
