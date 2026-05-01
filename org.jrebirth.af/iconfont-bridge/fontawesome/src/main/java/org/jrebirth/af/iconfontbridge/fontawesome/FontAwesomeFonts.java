package org.jrebirth.af.iconfontbridge.fontawesome;

import org.jrebirth.af.api.resource.font.FontExtension;
import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Font Awesome 6 font resources metadata.
 */
public final class FontAwesomeFonts {

    /** Font Awesome package version. */
    public static final String VERSION = "6.7.2";

    /** Solid 900 font resource. */
    public static final FontItem SOLID_900 = IconFont.buildItem("fa-solid-900", FontAwesome.class, FontExtension.TTF);

    /** Regular 400 font resource. */
    public static final FontItem REGULAR_400 = IconFont.buildItem("fa-regular-400", FontAwesome.class, FontExtension.TTF);

    /** Brands 400 font resource. */
    public static final FontItem BRANDS_400 = IconFont.buildItem("fa-brands-400", FontAwesome.class, FontExtension.TTF);

    /** v4 compatibility font resource. */
    public static final FontItem V4_COMPATIBILITY = IconFont.buildItem("fa-v4compatibility", FontAwesome.class, FontExtension.TTF);

    private FontAwesomeFonts() {
        // Utility class
    }
}
