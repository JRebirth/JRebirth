package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: editor.
 */
public enum MaterialDesignIconsEditor implements IconFont {

    /** attach_file. */
    attach_file("\ue226"),

    /** attach_money. */
    attach_money("\ue227"),

    /** border_all. */
    border_all("\ue228"),

    /** border_bottom. */
    border_bottom("\ue229"),

    /** border_clear. */
    border_clear("\ue22a"),

    /** border_color. */
    border_color("\ue22b"),

    /** border_horizontal. */
    border_horizontal("\ue22c"),

    /** border_inner. */
    border_inner("\ue22d"),

    /** border_left. */
    border_left("\ue22e"),

    /** border_outer. */
    border_outer("\ue22f"),

    /** border_right. */
    border_right("\ue230"),

    /** border_style. */
    border_style("\ue231"),

    /** border_top. */
    border_top("\ue232"),

    /** border_vertical. */
    border_vertical("\ue233"),

    /** bubble_chart. */
    bubble_chart("\ue6dd"),

    /** drag_handle. */
    drag_handle("\ue25d"),

    /** format_align_center. */
    format_align_center("\ue234"),

    /** format_align_justify. */
    format_align_justify("\ue235"),

    /** format_align_left. */
    format_align_left("\ue236"),

    /** format_align_right. */
    format_align_right("\ue237"),

    /** format_bold. */
    format_bold("\ue238"),

    /** format_clear. */
    format_clear("\ue239"),

    /** format_color_fill. */
    format_color_fill("\ue23a"),

    /** format_color_reset. */
    format_color_reset("\ue23b"),

    /** format_color_text. */
    format_color_text("\ue23c"),

    /** format_indent_decrease. */
    format_indent_decrease("\ue23d"),

    /** format_indent_increase. */
    format_indent_increase("\ue23e"),

    /** format_italic. */
    format_italic("\ue23f"),

    /** format_line_spacing. */
    format_line_spacing("\ue240"),

    /** format_list_bulleted. */
    format_list_bulleted("\ue241"),

    /** format_list_numbered. */
    format_list_numbered("\ue242"),

    /** format_paint. */
    format_paint("\ue243"),

    /** format_quote. */
    format_quote("\ue244"),

    /** format_shapes. */
    format_shapes("\ue25e"),

    /** format_size. */
    format_size("\ue245"),

    /** format_strikethrough. */
    format_strikethrough("\ue246"),

    /** format_textdirection_l_to_r. */
    format_textdirection_l_to_r("\ue247"),

    /** format_textdirection_r_to_l. */
    format_textdirection_r_to_l("\ue248"),

    /** format_underlined. */
    format_underlined("\ue249"),

    /** functions. */
    functions("\ue24a"),

    /** highlight. */
    highlight("\ue25f"),

    /** insert_chart. */
    insert_chart("\ue24b"),

    /** insert_comment. */
    insert_comment("\ue24c"),

    /** insert_drive_file. */
    insert_drive_file("\ue24d"),

    /** insert_emoticon. */
    insert_emoticon("\ue24e"),

    /** insert_invitation. */
    insert_invitation("\ue24f"),

    /** insert_link. */
    insert_link("\ue250"),

    /** insert_photo. */
    insert_photo("\ue251"),

    /** linear_scale. */
    linear_scale("\ue260"),

    /** merge_type. */
    merge_type("\ue252"),

    /** mode_comment. */
    mode_comment("\ue253"),

    /** mode_edit. */
    mode_edit("\ue254"),

    /** monetization_on. */
    monetization_on("\ue263"),

    /** money_off. */
    money_off("\ue25c"),

    /** multiline_chart. */
    multiline_chart("\ue6df"),

    /** pie_chart. */
    pie_chart("\ue6c4"),

    /** pie_chart_outlined. */
    pie_chart_outlined("\ue6c5"),

    /** publish. */
    publish("\ue255"),

    /** short_text. */
    short_text("\ue261"),

    /** show_chart. */
    show_chart("\ue6e1"),

    /** space_bar. */
    space_bar("\ue256"),

    /** strikethrough_s. */
    strikethrough_s("\ue257"),

    /** text_fields. */
    text_fields("\ue262"),

    /** title. */
    title("\ue264"),

    /** vertical_align_bottom. */
    vertical_align_bottom("\ue258"),

    /** vertical_align_center. */
    vertical_align_center("\ue259"),

    /** vertical_align_top. */
    vertical_align_top("\ue25a"),

    /** wrap_text. */
    wrap_text("\ue25b");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsEditor(final String charCode) {
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
