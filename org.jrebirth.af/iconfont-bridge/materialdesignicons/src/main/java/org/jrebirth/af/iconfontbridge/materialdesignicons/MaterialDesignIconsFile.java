package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: file.
 */
public enum MaterialDesignIconsFile implements IconFont {

    /** attachment. */
    attachment("\ue2bc"),

    /** cloud. */
    cloud("\ue2bd"),

    /** cloud_circle. */
    cloud_circle("\ue2be"),

    /** cloud_done. */
    cloud_done("\ue2bf"),

    /** cloud_download. */
    cloud_download("\ue2c0"),

    /** cloud_off. */
    cloud_off("\ue2c1"),

    /** cloud_queue. */
    cloud_queue("\ue2c2"),

    /** cloud_upload. */
    cloud_upload("\ue2c3"),

    /** create_new_folder. */
    create_new_folder("\ue2cc"),

    /** file_download. */
    file_download("\ue2c4"),

    /** file_upload. */
    file_upload("\ue2c6"),

    /** folder. */
    folder("\ue2c7"),

    /** folder_open. */
    folder_open("\ue2c8"),

    /** folder_shared. */
    folder_shared("\ue2c9");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsFile(final String charCode) {
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
