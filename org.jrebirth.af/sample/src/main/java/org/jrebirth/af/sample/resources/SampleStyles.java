package org.jrebirth.af.sample.resources;


import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.core.resource.style.StyleSheet;
import org.jrebirth.af.core.resource.style.StyleSheetItem;

/**
 * The SampleStyles interface providing all style sheets.
 */
public interface SampleStyles {

    /** The application main style sheet. */
    StyleSheetItem MAIN = create(new StyleSheet("sample"));

}
