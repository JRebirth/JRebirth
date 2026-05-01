package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: social.
 */
public enum MaterialDesignIconsSocial implements IconFont {

    /** cake. */
    cake("\ue7e9"),

    /** domain. */
    domain("\ue7ee"),

    /** group. */
    group("\ue7ef"),

    /** group_add. */
    group_add("\ue7f0"),

    /** location_city. */
    location_city("\ue7f1"),

    /** mood. */
    mood("\ue7f2"),

    /** mood_bad. */
    mood_bad("\ue7f3"),

    /** notifications. */
    notifications("\ue7f4"),

    /** notifications_active. */
    notifications_active("\ue7f7"),

    /** notifications_none. */
    notifications_none("\ue7f5"),

    /** notifications_off. */
    notifications_off("\ue7f6"),

    /** notifications_paused. */
    notifications_paused("\ue7f8"),

    /** pages. */
    pages("\ue7f9"),

    /** party_mode. */
    party_mode("\ue7fa"),

    /** people. */
    people("\ue7fb"),

    /** people_outline. */
    people_outline("\ue7fc"),

    /** person. */
    person("\ue7fd"),

    /** person_add. */
    person_add("\ue7fe"),

    /** person_outline. */
    person_outline("\ue7ff"),

    /** plus_one. */
    plus_one("\ue800"),

    /** poll. */
    poll("\ue801"),

    /** public_. */
    public_("\ue80b"),

    /** school. */
    school("\ue80c"),

    /** sentiment_dissatisfied. */
    sentiment_dissatisfied("\ue811"),

    /** sentiment_neutral. */
    sentiment_neutral("\ue812"),

    /** sentiment_satisfied. */
    sentiment_satisfied("\ue813"),

    /** sentiment_very_dissatisfied. */
    sentiment_very_dissatisfied("\ue814"),

    /** sentiment_very_satisfied. */
    sentiment_very_satisfied("\ue815"),

    /** share. */
    share("\ue80d"),

    /** whatshot. */
    whatshot("\ue80e");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsSocial(final String charCode) {
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
