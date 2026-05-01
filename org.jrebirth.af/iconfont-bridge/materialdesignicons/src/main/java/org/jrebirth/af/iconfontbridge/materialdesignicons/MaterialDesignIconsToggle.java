package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: toggle.
 */
public enum MaterialDesignIconsToggle implements IconFont {

    /** check_box. */
    check_box("\ue834"),

    /** check_box_outline_blank. */
    check_box_outline_blank("\ue835"),

    /** indeterminate_check_box. */
    indeterminate_check_box("\ue909"),

    /** radio_button_checked. */
    radio_button_checked("\ue837"),

    /** radio_button_unchecked. */
    radio_button_unchecked("\ue836"),

    /** star. */
    star("\ue838"),

    /** star_border. */
    star_border("\ue83a"),

    /** star_half. */
    star_half("\ue839");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsToggle(final String charCode) {
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
