package org.jrebirth.af.core.resource.style;

import org.jrebirth.af.api.resource.style.StyleSheetItem;

public interface StyleSheetEnum extends StyleSheetItem {

    /**
     *
     * TODO To complete.
     *
     * @param path the style sheet local path
     * @param name the style sheet name
     */
    default void ss(final String path, final String name) {
        set(new StyleSheet(path, name));
    }

    /**
     *
     * TODO To complete.
     *
     * @param name the style sheet file name
     */
    default void ss(final String name) {
        set(new StyleSheet(name));
    }

}
