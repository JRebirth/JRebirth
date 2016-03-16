/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.resource;

import java.util.concurrent.atomic.AtomicInteger;

import org.jrebirth.af.api.resource.ResourceParams;
import org.jrebirth.af.api.resource.color.ColorItem;
import org.jrebirth.af.api.resource.color.ColorParams;
import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.api.resource.font.FontParams;
import org.jrebirth.af.api.resource.fxml.FXMLItem;
import org.jrebirth.af.api.resource.fxml.FXMLParams;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.api.resource.image.ImageItem;
import org.jrebirth.af.api.resource.image.ImageParams;
import org.jrebirth.af.api.resource.parameter.ParameterItem;
import org.jrebirth.af.api.resource.style.StyleSheetItem;
import org.jrebirth.af.api.resource.style.StyleSheetParams;
import org.jrebirth.af.core.resource.color.ColorItemBase;
import org.jrebirth.af.core.resource.font.FontItemBase;
import org.jrebirth.af.core.resource.fxml.FXMLItemBase;
import org.jrebirth.af.core.resource.i18n.Message;
import org.jrebirth.af.core.resource.i18n.MessageItemBase;
import org.jrebirth.af.core.resource.image.ImageItemBase;
import org.jrebirth.af.core.resource.parameter.ObjectParameter;
import org.jrebirth.af.core.resource.parameter.ParameterItemBase;
import org.jrebirth.af.core.resource.provided.parameter.CoreParameters;
import org.jrebirth.af.core.resource.style.StyleSheetItemBase;

/**
 * The class <strong>Resources</strong> is the first access point when you want to declare a resource for your application.
 *
 * @author Sébastien Bordes
 */
public final class Resources {

    /** The class path separator. */
    public static final String PATH_SEP = "/";

    /** The generator of unique id for parameters. */
    private static AtomicInteger parameterIdGenerator = new AtomicInteger();

    /** The generator of unique id for colors. */
    private static AtomicInteger colorIdGenerator = new AtomicInteger();

    /** The generator of unique id for fonts. */
    private static AtomicInteger fontIdGenerator = new AtomicInteger();

    /** The generator of unique id for images. */
    private static AtomicInteger imageIdGenerator = new AtomicInteger();

    /** The generator of unique id for style sheets. */
    private static AtomicInteger styleSheetIdGenerator = new AtomicInteger();

    /** The generator of unique id for fxml files. */
    private static AtomicInteger fxmlIdGenerator = new AtomicInteger();

    /** The generator of unique id for message items. */
    private static AtomicInteger messageIdGenerator = new AtomicInteger();

    /**
     * Private Constructor.
     */
    private Resources() {
        super();
    }

    /*************************************************************************/
    /** __________________________PARAMETER___________________________________ */
    /*************************************************************************/

    /**
     * Build a parameter item.
     *
     * @param <O> the type of parameterized object
     *
     * @param parameterParams the primitive values for the color
     *
     * @return a new fresh color item object
     */
    public static <O extends Object> ParameterItem<O> create(final ObjectParameter<O> parameterParams) {
        // Ensure that the uid will be unique at runtime
        return (ParameterItem<O>) ParameterItemBase.create(parameterParams.object()).uid(parameterIdGenerator.incrementAndGet()).set(parameterParams); // FIXME
    }

    /**
     * Build a parameter item.
     *
     * @param <O> the type of parameterized object
     *
     * @param name the parameter unique name
     * @param defaultValue the default object value
     *
     * @return a new fresh color item object
     */
    public static <O extends Object> ParameterItem<O> create(final String name, final O defaultValue) {
        return create(new ObjectParameter<O>(name, defaultValue));
    }

    /*************************************************************************/
    /** ______________________________COLOR___________________________________ */
    /*************************************************************************/

    /**
     * Build a color item.
     *
     * @param colorParams the primitive values for the color
     *
     * @return a new fresh color item object
     */
    public static ColorItem create(final ColorParams colorParams) {
        return ColorItemBase.create().uid(colorIdGenerator.incrementAndGet()).set(colorParams);
    }

    /*************************************************************************/
    /** _______________________________FONT___________________________________ */
    /*************************************************************************/

    /**
     * Build a font item.
     *
     * Take care of the value used for ({@link CoreParameters#FONT_FOLDER}) which will be prepend to the font path.
     *
     * @param fontParams the primitive values for the font
     *
     * @return a new fresh font item object
     */
    public static FontItem create(final FontParams fontParams) {
        // Ensure that the uid will be unique at runtime
        return FontItemBase.create().uid(fontIdGenerator.incrementAndGet()).set(fontParams);
    }

    /*************************************************************************/
    /** ______________________________IMAGE___________________________________ */
    /*************************************************************************/

    /**
     * Build an image item.
     *
     * Take care of the value used for ({@link CoreParameters#IMAGE_FOLDER}) which will be prepend to the image path.
     *
     * @param imageParams the primitive values for the image
     *
     * @return a new fresh image item object
     */
    public static ImageItem create(final ImageParams imageParams) {
        // Ensure that the uid will be unique at runtime
        return ImageItemBase.create().uid(imageIdGenerator.incrementAndGet()).set(imageParams);
    }

    /*************************************************************************/
    /** ____________________________STYLE SHEET_____________________________ */
    /*************************************************************************/

    /**
     * Build a style sheet item.
     *
     * Take care of the value used for ({@link CoreParameters#STYLE_FOLDER}) which will be prepend to the style sheet path.
     *
     * @param styleSheetParams the primitive values for the style sheet
     *
     * @return a new fresh file
     */
    public static StyleSheetItem create(final StyleSheetParams styleSheetParams) {
        // Ensure that the uid will be unique at runtime
        return StyleSheetItemBase.create().uid(styleSheetIdGenerator.incrementAndGet()).set(styleSheetParams);
    }

    /*************************************************************************/
    /** ________________________________FXML________________________________ */
    /*************************************************************************/

    /**
     * Build a Singleton FXML item.
     *
     * Each call to FXMLItem.get() will return the same FXML Component instance
     *
     * @param fxmlParams the primitive values for the fxml resource
     *
     * @return a new fresh FXML item
     */
    public static FXMLItem create(final FXMLParams fxmlParams) {
        // Ensure that the uid will be unique at runtime
        return FXMLItemBase.create(true).uid(fxmlIdGenerator.incrementAndGet()).set(fxmlParams);
    }

    /**
     * Build a FXML item with according singleton parameter.
     *
     * @param fxmlParams the primitive values for the FXML resource
     * @param isSingleton the flag that indicates if the FXMLCOmponent will be the same (true) or another instance (false) after each call to FXMLItem.get()
     *
     * @return a new fresh FXML item
     */
    public static FXMLItem create(final FXMLParams fxmlParams, final boolean isSingleton) {
        // Ensure that the uid will be unique at runtime
        return FXMLItemBase.create(isSingleton).uid(fxmlIdGenerator.incrementAndGet()).set(fxmlParams);
    }

    /*************************************************************************/
    /** ______________________________Message_______________________________ */
    /*************************************************************************/

    /**
     * Build a Message item.
     *
     * @param messageParams the key of the i18n message
     *
     * @return a new fresh Message item
     */
    public static MessageItem create(final Message messageParams) {
        // Ensure that the uid will be unique at runtime
        return MessageItemBase.create().uid(messageIdGenerator.incrementAndGet()).set(messageParams);
    }

    /**
     * This utility method allow to avoid doing something if the parameter given is tahe AutoRefresh one. <br />
     * Because this parameter is used to control how other parameters can be updated.
     *
     * @param params the ResourceParams to check
     *
     * @return true if the resource params is not the auto refresh parameter
     */
    public static boolean isNotAutoRefreshParam(final ResourceParams params) {
        return !(params instanceof ObjectParameter && CoreParameters.AUTO_REFRESH_NAME.equals(((ObjectParameter<?>) params).name()));
    }

}
