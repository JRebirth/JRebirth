package org.jrebirth.sample.resources;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.resource.style.StyleSheet;
import org.jrebirth.core.resource.style.StyleSheetItem;

/**
 * The SampleStyles interface providing all style sheets.
 */
public interface SampleStyles {

    /** The application main style sheet. */
    StyleSheetItem MAIN = create(new StyleSheet("sample"));

}
