package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: alert.
 */
public enum MaterialDesignIconsAlert implements IconFont {

    /** add_alert. */
    add_alert("\ue003"),

    /** error. */
    error("\ue000"),

    /** error_outline. */
    error_outline("\ue001"),

    /** warning. */
    warning("\ue002");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsAlert(final String charCode) {
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
