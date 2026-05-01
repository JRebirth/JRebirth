package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: communication.
 */
public enum MaterialDesignIconsCommunication implements IconFont {

    /** business. */
    business("\ue0af"),

    /** call. */
    call("\ue0b0"),

    /** call_end. */
    call_end("\ue0b1"),

    /** call_made. */
    call_made("\ue0b2"),

    /** call_merge. */
    call_merge("\ue0b3"),

    /** call_missed. */
    call_missed("\ue0b4"),

    /** call_missed_outgoing. */
    call_missed_outgoing("\ue0e4"),

    /** call_received. */
    call_received("\ue0b5"),

    /** call_split. */
    call_split("\ue0b6"),

    /** chat. */
    chat("\ue0b7"),

    /** chat_bubble. */
    chat_bubble("\ue0ca"),

    /** chat_bubble_outline. */
    chat_bubble_outline("\ue0cb"),

    /** clear_all. */
    clear_all("\ue0b8"),

    /** comment. */
    comment("\ue0b9"),

    /** contact_mail. */
    contact_mail("\ue0d0"),

    /** contact_phone. */
    contact_phone("\ue0cf"),

    /** contacts. */
    contacts("\ue0ba"),

    /** dialer_sip. */
    dialer_sip("\ue0bb"),

    /** dialpad. */
    dialpad("\ue0bc"),

    /** email. */
    email("\ue0be"),

    /** forum. */
    forum("\ue0bf"),

    /** import_contacts. */
    import_contacts("\ue0e0"),

    /** import_export. */
    import_export("\ue0c3"),

    /** invert_colors_off. */
    invert_colors_off("\ue0c4"),

    /** live_help. */
    live_help("\ue0c6"),

    /** location_off. */
    location_off("\ue0c7"),

    /** location_on. */
    location_on("\ue0c8"),

    /** mail_outline. */
    mail_outline("\ue0e1"),

    /** message. */
    message("\ue0c9"),

    /** no_sim. */
    no_sim("\ue0cc"),

    /** phone. */
    phone("\ue0cd"),

    /** phonelink_erase. */
    phonelink_erase("\ue0db"),

    /** phonelink_lock. */
    phonelink_lock("\ue0dc"),

    /** phonelink_ring. */
    phonelink_ring("\ue0dd"),

    /** phonelink_setup. */
    phonelink_setup("\ue0de"),

    /** portable_wifi_off. */
    portable_wifi_off("\ue0ce"),

    /** present_to_all. */
    present_to_all("\ue0df"),

    /** ring_volume. */
    ring_volume("\ue0d1"),

    /** rss_feed. */
    rss_feed("\ue0e5"),

    /** screen_share. */
    screen_share("\ue0e2"),

    /** speaker_phone. */
    speaker_phone("\ue0d2"),

    /** stay_current_landscape. */
    stay_current_landscape("\ue0d3"),

    /** stay_current_portrait. */
    stay_current_portrait("\ue0d4"),

    /** stay_primary_landscape. */
    stay_primary_landscape("\ue0d5"),

    /** stay_primary_portrait. */
    stay_primary_portrait("\ue0d6"),

    /** stop_screen_share. */
    stop_screen_share("\ue0e3"),

    /** swap_calls. */
    swap_calls("\ue0d7"),

    /** textsms. */
    textsms("\ue0d8"),

    /** voicemail. */
    voicemail("\ue0d9"),

    /** vpn_key. */
    vpn_key("\ue0da");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsCommunication(final String charCode) {
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
