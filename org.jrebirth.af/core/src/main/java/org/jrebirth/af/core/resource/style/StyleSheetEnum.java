package org.jrebirth.af.core.resource.style;

import java.net.URL;

import org.jrebirth.af.api.resource.style.StyleSheetItem;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;

/**
 * The interface <strong>StyleSheetEnum</strong> should be inherited by any Enumeration that want to manage stylesheet {@link URL}.
 *
 * Take care of the value used for ({@link JRebirthParameters#STYLE_FOLDER}) which will be prepend to the style sheet path.
 *
 * @author Sébastien Bordes
 */
public interface StyleSheetEnum extends StyleSheetItem {

    /**
     * Build and register a {@link StyleSheet}.
     *
     * @param path the style sheet local path
     * @param name the style sheet name
     */
    default void ss(final String path, final String name) {
        set(new StyleSheet(path, name));
    }

    /**
     * Build and register a {@link StyleSheet}.
     *
     * @param name the style sheet file name
     */
    default void ss(final String name) {
        set(new StyleSheet(name));
    }

}
