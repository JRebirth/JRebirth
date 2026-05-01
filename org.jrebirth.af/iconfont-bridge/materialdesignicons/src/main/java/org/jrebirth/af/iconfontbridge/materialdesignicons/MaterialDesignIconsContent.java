package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: content.
 */
public enum MaterialDesignIconsContent implements IconFont {

    /** add. */
    add("\ue145"),

    /** add_box. */
    add_box("\ue146"),

    /** add_circle. */
    add_circle("\ue147"),

    /** add_circle_outline. */
    add_circle_outline("\ue148"),

    /** archive. */
    archive("\ue149"),

    /** backspace. */
    backspace("\ue14a"),

    /** block. */
    block("\ue14b"),

    /** clear. */
    clear("\ue14c"),

    /** content_copy. */
    content_copy("\ue14d"),

    /** content_cut. */
    content_cut("\ue14e"),

    /** content_paste. */
    content_paste("\ue14f"),

    /** create. */
    create("\ue150"),

    /** delete_sweep. */
    delete_sweep("\ue16c"),

    /** drafts. */
    drafts("\ue151"),

    /** filter_list. */
    filter_list("\ue152"),

    /** flag. */
    flag("\ue153"),

    /** font_download. */
    font_download("\ue167"),

    /** forward. */
    forward("\ue154"),

    /** gesture. */
    gesture("\ue155"),

    /** inbox. */
    inbox("\ue156"),

    /** link. */
    link("\ue157"),

    /** low_priority. */
    low_priority("\ue16d"),

    /** mail. */
    mail("\ue158"),

    /** markunread. */
    markunread("\ue159"),

    /** move_to_inbox. */
    move_to_inbox("\ue168"),

    /** next_week. */
    next_week("\ue16a"),

    /** redo. */
    redo("\ue15a"),

    /** remove. */
    remove("\ue15b"),

    /** remove_circle. */
    remove_circle("\ue15c"),

    /** remove_circle_outline. */
    remove_circle_outline("\ue15d"),

    /** reply. */
    reply("\ue15e"),

    /** reply_all. */
    reply_all("\ue15f"),

    /** report. */
    report("\ue160"),

    /** save. */
    save("\ue161"),

    /** select_all. */
    select_all("\ue162"),

    /** send. */
    send("\ue163"),

    /** sort. */
    sort("\ue164"),

    /** text_format. */
    text_format("\ue165"),

    /** unarchive. */
    unarchive("\ue169"),

    /** undo. */
    undo("\ue166"),

    /** weekend. */
    weekend("\ue16b");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsContent(final String charCode) {
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
