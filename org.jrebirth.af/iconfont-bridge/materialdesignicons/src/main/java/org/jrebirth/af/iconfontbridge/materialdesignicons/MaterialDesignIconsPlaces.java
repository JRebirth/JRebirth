package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: places.
 */
public enum MaterialDesignIconsPlaces implements IconFont {

    /** ac_unit. */
    ac_unit("\ueb3b"),

    /** airport_shuttle. */
    airport_shuttle("\ueb3c"),

    /** all_inclusive. */
    all_inclusive("\ueb3d"),

    /** beach_access. */
    beach_access("\ueb3e"),

    /** business_center. */
    business_center("\ueb3f"),

    /** casino. */
    casino("\ueb40"),

    /** child_care. */
    child_care("\ueb41"),

    /** child_friendly. */
    child_friendly("\ueb42"),

    /** fitness_center. */
    fitness_center("\ueb43"),

    /** free_breakfast. */
    free_breakfast("\ueb44"),

    /** golf_course. */
    golf_course("\ueb45"),

    /** hot_tub. */
    hot_tub("\ueb46"),

    /** kitchen. */
    kitchen("\ueb47"),

    /** pool. */
    pool("\ueb48"),

    /** room_service. */
    room_service("\ueb49"),

    /** rv_hookup. */
    rv_hookup("\ue642"),

    /** smoke_free. */
    smoke_free("\ueb4a"),

    /** smoking_rooms. */
    smoking_rooms("\ueb4b"),

    /** spa. */
    spa("\ueb4c");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsPlaces(final String charCode) {
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
