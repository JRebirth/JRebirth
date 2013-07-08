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
package org.jrebirth.core.resource;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.jrebirth.core.resource.color.ColorItem;
import org.jrebirth.core.resource.color.ColorItemBase;
import org.jrebirth.core.resource.color.ColorParams;
import org.jrebirth.core.resource.font.FontItemBase;
import org.jrebirth.core.resource.font.FontParams;
import org.jrebirth.core.resource.image.ImageItemBase;
import org.jrebirth.core.resource.image.ImageParams;
import org.jrebirth.core.resource.parameter.ObjectParameter;
import org.jrebirth.core.resource.parameter.ParameterItemBase;
import org.jrebirth.core.resource.style.StyleSheetItemBase;
import org.jrebirth.core.resource.style.StyleSheetParams;

/**
 * The class <strong>Resources</strong> is the first access point when you want to declare a resource for your application.
 * 
 * @author Sébastien Bordes
 */
public class Resources {

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

    private static Map<String, ColorItem> colorMap = new Hashtable<>();

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
     * @param value the parameterized object value
     * 
     * @return a new fresh color item object
     */
    public static <O extends Object> ParameterItemBase<O> create(final String name, final O value) {
        return create(new ObjectParameter<O>(name, value));
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

}
