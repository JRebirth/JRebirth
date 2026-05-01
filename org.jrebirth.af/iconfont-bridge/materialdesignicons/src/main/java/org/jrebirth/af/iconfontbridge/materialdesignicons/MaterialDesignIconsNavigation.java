package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: navigation.
 */
public enum MaterialDesignIconsNavigation implements IconFont {

    /** apps. */
    apps("\ue5c3"),

    /** arrow_back. */
    arrow_back("\ue5c4"),

    /** arrow_downward. */
    arrow_downward("\ue5db"),

    /** arrow_drop_down. */
    arrow_drop_down("\ue5c5"),

    /** arrow_drop_down_circle. */
    arrow_drop_down_circle("\ue5c6"),

    /** arrow_drop_up. */
    arrow_drop_up("\ue5c7"),

    /** arrow_forward. */
    arrow_forward("\ue5c8"),

    /** arrow_upward. */
    arrow_upward("\ue5d8"),

    /** cancel. */
    cancel("\ue5c9"),

    /** check. */
    check("\ue5ca"),

    /** chevron_left. */
    chevron_left("\ue5cb"),

    /** chevron_right. */
    chevron_right("\ue5cc"),

    /** close. */
    close("\ue5cd"),

    /** expand_less. */
    expand_less("\ue5ce"),

    /** expand_more. */
    expand_more("\ue5cf"),

    /** first_page. */
    first_page("\ue5dc"),

    /** fullscreen. */
    fullscreen("\ue5d0"),

    /** fullscreen_exit. */
    fullscreen_exit("\ue5d1"),

    /** last_page. */
    last_page("\ue5dd"),

    /** menu. */
    menu("\ue5d2"),

    /** more_horiz. */
    more_horiz("\ue5d3"),

    /** more_vert. */
    more_vert("\ue5d4"),

    /** refresh. */
    refresh("\ue5d5"),

    /** subdirectory_arrow_left. */
    subdirectory_arrow_left("\ue5d9"),

    /** subdirectory_arrow_right. */
    subdirectory_arrow_right("\ue5da"),

    /** unfold_less. */
    unfold_less("\ue5d6"),

    /** unfold_more. */
    unfold_more("\ue5d7");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsNavigation(final String charCode) {
        this.charCode = charCode;
    }

    @Override
    public String charCode() {
        return this.charCode;
    }

    @Override
    public FontItem item() {
        return MaterialDesignIcons.item();
    }
}
