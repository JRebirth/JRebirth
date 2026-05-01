package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: av.
 */
public enum MaterialDesignIconsAv implements IconFont {

    /** add_to_queue. */
    add_to_queue("\ue05c"),

    /** airplay. */
    airplay("\ue055"),

    /** album. */
    album("\ue019"),

    /** art_track. */
    art_track("\ue060"),

    /** av_timer. */
    av_timer("\ue01b"),

    /** branding_watermark. */
    branding_watermark("\ue06b"),

    /** call_to_action. */
    call_to_action("\ue06c"),

    /** closed_caption. */
    closed_caption("\ue01c"),

    /** equalizer. */
    equalizer("\ue01d"),

    /** explicit. */
    explicit("\ue01e"),

    /** fast_forward. */
    fast_forward("\ue01f"),

    /** fast_rewind. */
    fast_rewind("\ue020"),

    /** featured_play_list. */
    featured_play_list("\ue06d"),

    /** featured_video. */
    featured_video("\ue06e"),

    /** fiber_dvr. */
    fiber_dvr("\ue05d"),

    /** fiber_manual_record. */
    fiber_manual_record("\ue061"),

    /** fiber_new. */
    fiber_new("\ue05e"),

    /** fiber_pin. */
    fiber_pin("\ue06a"),

    /** fiber_smart_record. */
    fiber_smart_record("\ue062"),

    /** forward_10. */
    forward_10("\ue056"),

    /** forward_30. */
    forward_30("\ue057"),

    /** forward_5. */
    forward_5("\ue058"),

    /** games. */
    games("\ue021"),

    /** hd. */
    hd("\ue052"),

    /** hearing. */
    hearing("\ue023"),

    /** high_quality. */
    high_quality("\ue024"),

    /** library_add. */
    library_add("\ue02e"),

    /** library_books. */
    library_books("\ue02f"),

    /** library_music. */
    library_music("\ue030"),

    /** loop. */
    loop("\ue028"),

    /** mic. */
    mic("\ue029"),

    /** mic_none. */
    mic_none("\ue02a"),

    /** mic_off. */
    mic_off("\ue02b"),

    /** movie. */
    movie("\ue02c"),

    /** music_video. */
    music_video("\ue063"),

    /** new_releases. */
    new_releases("\ue031"),

    /** not_interested. */
    not_interested("\ue033"),

    /** note. */
    note("\ue06f"),

    /** pause. */
    pause("\ue034"),

    /** pause_circle_filled. */
    pause_circle_filled("\ue035"),

    /** pause_circle_outline. */
    pause_circle_outline("\ue036"),

    /** play_arrow. */
    play_arrow("\ue037"),

    /** play_circle_filled. */
    play_circle_filled("\ue038"),

    /** play_circle_outline. */
    play_circle_outline("\ue039"),

    /** playlist_add. */
    playlist_add("\ue03b"),

    /** playlist_add_check. */
    playlist_add_check("\ue065"),

    /** playlist_play. */
    playlist_play("\ue05f"),

    /** queue. */
    queue("\ue03c"),

    /** queue_music. */
    queue_music("\ue03d"),

    /** queue_play_next. */
    queue_play_next("\ue066"),

    /** radio. */
    radio("\ue03e"),

    /** recent_actors. */
    recent_actors("\ue03f"),

    /** remove_from_queue. */
    remove_from_queue("\ue067"),

    /** repeat. */
    repeat("\ue040"),

    /** repeat_one. */
    repeat_one("\ue041"),

    /** replay. */
    replay("\ue042"),

    /** replay_10. */
    replay_10("\ue059"),

    /** replay_30. */
    replay_30("\ue05a"),

    /** replay_5. */
    replay_5("\ue05b"),

    /** shuffle. */
    shuffle("\ue043"),

    /** skip_next. */
    skip_next("\ue044"),

    /** skip_previous. */
    skip_previous("\ue045"),

    /** slow_motion_video. */
    slow_motion_video("\ue068"),

    /** snooze. */
    snooze("\ue046"),

    /** sort_by_alpha. */
    sort_by_alpha("\ue053"),

    /** stop. */
    stop("\ue047"),

    /** subscriptions. */
    subscriptions("\ue064"),

    /** subtitles. */
    subtitles("\ue048"),

    /** surround_sound. */
    surround_sound("\ue049"),

    /** video_call. */
    video_call("\ue070"),

    /** video_label. */
    video_label("\ue071"),

    /** video_library. */
    video_library("\ue04a"),

    /** videocam. */
    videocam("\ue04b"),

    /** videocam_off. */
    videocam_off("\ue04c"),

    /** volume_down. */
    volume_down("\ue04d"),

    /** volume_mute. */
    volume_mute("\ue04e"),

    /** volume_off. */
    volume_off("\ue04f"),

    /** volume_up. */
    volume_up("\ue050"),

    /** web. */
    web("\ue051"),

    /** web_asset. */
    web_asset("\ue069");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsAv(final String charCode) {
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
