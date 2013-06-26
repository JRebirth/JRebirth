package org.jrebirth.core.resource.provided;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.resource.color.WebColor;
import org.jrebirth.core.resource.image.ImageExtension;
import org.jrebirth.core.resource.image.LocalImage;
import org.jrebirth.core.resource.parameter.ParameterItem;
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

    /** Developer provides more information when dynamic API is broken (Wave Contrcat). */
    ParameterItemBase<Boolean> DEVELOPER_MODE = create("developerMode", true);

    /** Fir Close Retry Delay in milliseconds, time to wait when application try to close the first time. */
    ParameterItemBase<Integer> CLOSE_RETRY_DELAY_FIRST = create("closeRetryDelayFirst", 4000);

    /** Close Retry Delay in milliseconds, time to wait when application try to close all other time. */
    ParameterItemBase<Integer> CLOSE_RETRY_DELAY_OTHER = create("closeRetryDelayOther", 1000);

    /** Pool size of JRebirth Thread Pool. */
    ParameterItemBase<Integer> THREAD_POOL_SIZE = create("threadPoolSize", 4);

    /** Fonts default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItemBase<String> FONT_FOLDER = create("fontsFolder", "fonts");

    /** Images default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItemBase<String> IMAGE_FOLDER = create("imagesFolder", "images");

    /** Styles default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItemBase<String> STYLE_FOLDER = create("stylesFolder", "styles");

    /**
     * .
     */
    ParameterItemBase<String> DEFAULT_CSS = create("defaultCSS", "{}");

    /**
     * The <code>TRUE_TYPE_FONT_EXTENSION</code> field is used to define the font file extension .
     */
    ParameterItemBase<String> TRUE_TYPE_FONT_EXTENSION = create("trueTypeFontExtension", ".ttf");

    /**
     * The <code>WAVE_HANDLER_PREFIX</code> field is used to add a prefix to custom wave handler method of JRebirth components. They will be named like this : doMyAction(Wave) after being renamed in
     * camel case.
     */
    ParameterItemBase<String> WAVE_HANDLER_PREFIX = create("waveHandlerPrefix", "DO_");

    /**
     * The <code>NOT_AVAILABLE_IMAGE</code> field is used to define the image to use when an image is missing.
     */
    ParameterItemBase<LocalImage> NOT_AVAILABLE_IMAGE = create("notAvailableImage", new LocalImage("NotAvailableImage", ImageExtension.PNG));

    /**
     * The <code>APPLICATION_NAME</code> field is used to define title of the application displayed by stage window.<br />
     * The default value contains a <b>{}</b> that will be replaced by application class simple name.
     */
    ParameterItemBase<String> APPLICATION_NAME = create("applicationName", "{} powered by JRebirth");

    /**
     * The <code>APPLICATION_VERSION</code> field is used to define the application version.
     */
    ParameterItem<String> APPLICATION_VERSION = create("applicationVersion", "0.0.0");

    /**************************************************************************************/
    /** _________________________Application Stage Parameters.___________________________ */
    /**************************************************************************************/

    /** The application stage width. */
    ParameterItemBase<Integer> APPLICATION_STAGE_WIDTH = create("applicationStageWidth", 800);

    /** The application stage height. */
    ParameterItemBase<Integer> APPLICATION_STAGE_HEIGHT = create("applicationStageHeight", 600);

    /** The application stage height. */
    ParameterItemBase<WebColor> APPLICATION_STAGE_BG_COLOR = create("applicationStageBgColor", new WebColor("FFFFFF", 1.0));
}
