package org.jrebirth.core.resource.provided;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.resource.color.WebColor;
import org.jrebirth.core.resource.image.ImageExtension;
import org.jrebirth.core.resource.image.LocalImage;
import org.jrebirth.core.resource.parameter.ParameterItem;

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
    ParameterItem<Boolean> DEVELOPER_MODE = create("developerMode", true);

    /** Fir Close Retry Delay in milliseconds, time to wait when application try to close the first time. */
    ParameterItem<Integer> CLOSE_RETRY_DELAY_FIRST = create("closeRetryDelayFirst", 4000);

    /** Close Retry Delay in milliseconds, time to wait when application try to close all other time. */
    ParameterItem<Integer> CLOSE_RETRY_DELAY_OTHER = create("closeRetryDelayOther", 1000);

    /** Pool size of JRebirth Thread Pool. */
    ParameterItem<Integer> THREAD_POOL_SIZE = create("threadPoolSize", 4);

    /** Fonts default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<String> FONT_FOLDER = create("fontsFolder", "fonts");

    /** Images default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<String> IMAGE_FOLDER = create("imagesFolder", "images");

    /** Styles default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<String> STYLE_FOLDER = create("stylesFolder", "styles");

    /**
     * .
     */
    ParameterItem<String> DEFAULT_CSS = create("defaultCSS", "{}");

    /**
     * The <code>TRUE_TYPE_FONT_EXTENSION</code> field is used to define the font file extension .
     */
    ParameterItem<String> TRUE_TYPE_FONT_EXTENSION = create("trueTypeFontExtension", ".ttf");

    /**
     * The <code>WAVE_HANDLER_PREFIX</code> field is used to add a prefix to custom wave handler method of JRebirth components. They will be named like this : doMyAction(Wave) after being renamed in
     * camel case.
     */
    ParameterItem<String> WAVE_HANDLER_PREFIX = create("waveHandlerPrefix", "DO_");

    /**
     * The <code>NOT_AVAILABLE_IMAGE</code> field is used to define the image to use when an image is missing.
     */
    ParameterItem<LocalImage> NOT_AVAILABLE_IMAGE = create("notAvailableImage", new LocalImage("NotAvailableImage", ImageExtension.PNG));

    /**
     * The <code>APPLICATION_NAME</code> field is used to define title of the application displayed by stage window.<br />
     * The default value contains a <b>{}</b> that will be replaced by application class simple name.
     */
    ParameterItem<String> APPLICATION_NAME = create("applicationName", "{} powered by JRebirth");

    /**
     * The <code>APPLICATION_VERSION</code> field is used to define the application version.
     */
    ParameterItem<String> APPLICATION_VERSION = create("applicationVersion", "0.0.0");

    /**************************************************************************************/
    /** _________________________Application Stage Parameters.___________________________ */
    /**************************************************************************************/

    /** The application stage width. */
    ParameterItem<Integer> APPLICATION_STAGE_WIDTH = create("applicationStageWidth", 800);

    /** The application stage height. */
    ParameterItem<Integer> APPLICATION_STAGE_HEIGHT = create("applicationStageHeight", 600);

    /** The application stage height. */
    ParameterItem<WebColor> APPLICATION_STAGE_BG_COLOR = create("applicationStageBgColor", new WebColor("FFFFFF", 1.0));
}
