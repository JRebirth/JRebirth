package org.jrebirth.core.resource;

import java.util.concurrent.atomic.AtomicInteger;

import org.jrebirth.core.resource.color.ColorItemBase;
import org.jrebirth.core.resource.color.ColorParams;
import org.jrebirth.core.resource.font.FontItemBase;
import org.jrebirth.core.resource.font.FontParams;
import org.jrebirth.core.resource.image.ImageItemBase;
import org.jrebirth.core.resource.image.ImageParams;
import org.jrebirth.core.resource.parameter.ObjectParameter;
import org.jrebirth.core.resource.parameter.ParameterItemBase;

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
    public static ColorItemBase create(final ColorParams colorParams) {
        final ColorItemBase colorItem = new ColorItemBase(colorParams);

        // Ensure that the uid will be unique at runtime
        colorItem.setUid(colorIdGenerator.incrementAndGet());

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

}
