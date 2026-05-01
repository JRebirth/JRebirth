package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontExtension;
import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons metadata and shared font item.
 *
 * Generated from material-design-icons-3.0.1 categories.
 */
public final class MaterialDesignIcons {

    /** Source version used to generate this icon set. */
    public static final String VERSION = "3.0.1";

    /** Number of generated icons. */
    public static final int ICON_COUNT = 933;

    /** Number of generated category enums. */
    public static final int CATEGORY_COUNT = 16;

    /** The font item giving access to font resource. */
    private static final FontItem ITEM = IconFont.buildItem("MaterialIcons-Regular", MaterialDesignIconsAction.class, FontExtension.TTF);

    private MaterialDesignIcons() {
        // Utility class
    }

    static FontItem item() {
        return ITEM;
    }
}
