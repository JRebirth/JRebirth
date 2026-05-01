package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: notification.
 */
public enum MaterialDesignIconsNotification implements IconFont {

    /** adb. */
    adb("\ue60e"),

    /** airline_seat_flat. */
    airline_seat_flat("\ue630"),

    /** airline_seat_flat_angled. */
    airline_seat_flat_angled("\ue631"),

    /** airline_seat_individual_suite. */
    airline_seat_individual_suite("\ue632"),

    /** airline_seat_legroom_extra. */
    airline_seat_legroom_extra("\ue633"),

    /** airline_seat_legroom_normal. */
    airline_seat_legroom_normal("\ue634"),

    /** airline_seat_legroom_reduced. */
    airline_seat_legroom_reduced("\ue635"),

    /** airline_seat_recline_extra. */
    airline_seat_recline_extra("\ue636"),

    /** airline_seat_recline_normal. */
    airline_seat_recline_normal("\ue637"),

    /** bluetooth_audio. */
    bluetooth_audio("\ue60f"),

    /** confirmation_number. */
    confirmation_number("\ue638"),

    /** disc_full. */
    disc_full("\ue610"),

    /** do_not_disturb. */
    do_not_disturb("\ue612"),

    /** do_not_disturb_alt. */
    do_not_disturb_alt("\ue611"),

    /** do_not_disturb_off. */
    do_not_disturb_off("\ue643"),

    /** do_not_disturb_on. */
    do_not_disturb_on("\ue644"),

    /** drive_eta. */
    drive_eta("\ue613"),

    /** enhanced_encryption. */
    enhanced_encryption("\ue63f"),

    /** event_available. */
    event_available("\ue614"),

    /** event_busy. */
    event_busy("\ue615"),

    /** event_note. */
    event_note("\ue616"),

    /** folder_special. */
    folder_special("\ue617"),

    /** live_tv. */
    live_tv("\ue639"),

    /** mms. */
    mms("\ue618"),

    /** more. */
    more("\ue619"),

    /** network_check. */
    network_check("\ue640"),

    /** network_locked. */
    network_locked("\ue61a"),

    /** no_encryption. */
    no_encryption("\ue641"),

    /** ondemand_video. */
    ondemand_video("\ue63a"),

    /** personal_video. */
    personal_video("\ue63b"),

    /** phone_bluetooth_speaker. */
    phone_bluetooth_speaker("\ue61b"),

    /** phone_forwarded. */
    phone_forwarded("\ue61c"),

    /** phone_in_talk. */
    phone_in_talk("\ue61d"),

    /** phone_locked. */
    phone_locked("\ue61e"),

    /** phone_missed. */
    phone_missed("\ue61f"),

    /** phone_paused. */
    phone_paused("\ue620"),

    /** power. */
    power("\ue63c"),

    /** priority_high. */
    priority_high("\ue645"),

    /** rv_hookup. */
    rv_hookup("\ue642"),

    /** sd_card. */
    sd_card("\ue623"),

    /** sim_card_alert. */
    sim_card_alert("\ue624"),

    /** sms. */
    sms("\ue625"),

    /** sms_failed. */
    sms_failed("\ue626"),

    /** sync. */
    sync("\ue627"),

    /** sync_disabled. */
    sync_disabled("\ue628"),

    /** sync_problem. */
    sync_problem("\ue629"),

    /** system_update. */
    system_update("\ue62a"),

    /** tap_and_play. */
    tap_and_play("\ue62b"),

    /** time_to_leave. */
    time_to_leave("\ue62c"),

    /** vibration. */
    vibration("\ue62d"),

    /** voice_chat. */
    voice_chat("\ue62e"),

    /** vpn_lock. */
    vpn_lock("\ue62f"),

    /** wc. */
    wc("\ue63d"),

    /** wifi. */
    wifi("\ue63e");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsNotification(final String charCode) {
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
