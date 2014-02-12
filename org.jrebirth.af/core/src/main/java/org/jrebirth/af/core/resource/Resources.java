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

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.jrebirth.af.core.resource.color.ColorItem;
import org.jrebirth.af.core.resource.color.ColorItemBase;
import org.jrebirth.af.core.resource.color.ColorParams;
import org.jrebirth.af.core.resource.color.ResourceParams;
import org.jrebirth.af.core.resource.font.FontItemBase;
import org.jrebirth.af.core.resource.font.FontParams;
import org.jrebirth.af.core.resource.fxml.FXMLItemBase;
import org.jrebirth.af.core.resource.fxml.FXMLParams;
import org.jrebirth.af.core.resource.i18n.Message;
import org.jrebirth.af.core.resource.i18n.MessageItemBase;
import org.jrebirth.af.core.resource.image.ImageItemBase;
import org.jrebirth.af.core.resource.image.ImageParams;
import org.jrebirth.af.core.resource.parameter.ObjectParameter;
import org.jrebirth.af.core.resource.parameter.ParameterItemBase;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;
import org.jrebirth.af.core.resource.style.StyleSheetItemBase;
import org.jrebirth.af.core.resource.style.StyleSheetParams;

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

    /** The dynamic color map. */
    private static Map<String, ColorItem> colorMap = new Hashtable<>();

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
    public static <O extends Object> ParameterItemBase<O> create(final ObjectParameter<O> parameterParams) {
        final ParameterItemBase<O> parameterItem = new ParameterItemBase<O>(parameterParams);

        // Ensure that the uid will be unique at runtime
        parameterItem.setUid(parameterIdGenerator.incrementAndGet());

        return parameterItem;
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
    public static <O extends Object> ParameterItemBase<O> create(final String name, final O defaultValue) {
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
        final ColorItemBase colorItem = new ColorItemBase(colorParams);

        // Ensure that the uid will be unique at runtime
        colorItem.setUid(colorIdGenerator.incrementAndGet());

        return colorItem;
    }

    /**
     * Build a color item.
     * 
     * @param colorParams the primitive values for the color
     * @param dynamicKey the dynamic key used to register this color
     * 
     * @return a new fresh color item object
     */
    public static ColorItem create(final ColorParams colorParams, final String dynamicKey) {
        colorParams.setDynamicKey(dynamicKey);
        final ColorItem colorItem = create(colorParams);

        if (!colorMap.containsKey(dynamicKey)) {
            colorMap.put(dynamicKey, colorItem);
        }
        return colorItem;
    }

    /*************************************************************************/
    /** _______________________________FONT___________________________________ */
    /*************************************************************************/

    /**
     * Build a font item.
     * 
     * @param fontParams the primitive values for the font
     * 
     * @return a new fresh font item object
     */
    public static FontItemBase create(final FontParams fontParams) {
        final FontItemBase fontItem = new FontItemBase(fontParams);

        // Ensure that the uid will be unique at runtime
        fontItem.setUid(fontIdGenerator.incrementAndGet());

        return fontItem;
    }

    /*************************************************************************/
    /** ______________________________IMAGE___________________________________ */
    /*************************************************************************/

    /**
     * Build an image item.
     * 
     * @param imageParams the primitive values for the image
     * 
     * @return a new fresh image item object
     */
    public static ImageItemBase create(final ImageParams imageParams) {

        final ImageItemBase imageItem = new ImageItemBase(imageParams);

        // Ensure that the uid will be unique at runtime
        imageItem.setUid(imageIdGenerator.incrementAndGet());

        return imageItem;
    }

    /*************************************************************************/
    /** ____________________________STYLE SHEET_____________________________ */
    /*************************************************************************/

    /**
     * Build a style sheet item.
     * 
     * @param styleSheetParams the primitive values for the style sheet
     * 
     * @return a new fresh file
     */
    public static StyleSheetItemBase create(final StyleSheetParams styleSheetParams) {

        final StyleSheetItemBase styleSheetItem = new StyleSheetItemBase(styleSheetParams);

        // Ensure that the uid will be unique at runtime
        styleSheetItem.setUid(styleSheetIdGenerator.incrementAndGet());

        return styleSheetItem;
    }

    /*************************************************************************/
    /** ________________________________FXML________________________________ */
    /*************************************************************************/

    /**
     * Build a fxml item.
     * 
     * @param fxmlParams the primitive values for the fxml resource
     * 
     * @return a new fresh FXML item
     */
    public static FXMLItemBase create(final FXMLParams fxmlParams) {

        final FXMLItemBase fxmlItem = new FXMLItemBase(fxmlParams);

        // Ensure that the uid will be unique at runtime
        fxmlItem.setUid(fxmlIdGenerator.incrementAndGet());

        return fxmlItem;
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
    public static MessageItemBase create(final Message messageParams) {

        final MessageItemBase messageItem = new MessageItemBase(messageParams);

        // Ensure that the uid will be unique at runtime
        messageItem.setUid(messageIdGenerator.incrementAndGet());

        return messageItem;
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
        return !(params instanceof ObjectParameter && JRebirthParameters.AUTO_REFRESH_NAME.equals(((ObjectParameter<?>) params).name()));
    }

}
