package org.jrebirth.af.showcase.todos.resource;

import org.jrebirth.af.api.resource.font.FontExtension;
import org.jrebirth.af.core.resource.font.FontEnum;

/**
 * The TodosFonts enumeration providing all fonts.
 */
public enum TodosFonts implements FontEnum {

    // @formatter:off

    /** The font used to display delete icon. */
    DELETE {{ real(TodosFontNames.fontello, 16.0, FontExtension.TTF); }};

}
