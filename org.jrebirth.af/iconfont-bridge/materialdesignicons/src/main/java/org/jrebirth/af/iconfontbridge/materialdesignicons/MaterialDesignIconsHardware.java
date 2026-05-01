package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: hardware.
 */
public enum MaterialDesignIconsHardware implements IconFont {

    /** cast. */
    cast("\ue307"),

    /** cast_connected. */
    cast_connected("\ue308"),

    /** computer. */
    computer("\ue30a"),

    /** desktop_mac. */
    desktop_mac("\ue30b"),

    /** desktop_windows. */
    desktop_windows("\ue30c"),

    /** developer_board. */
    developer_board("\ue30d"),

    /** device_hub. */
    device_hub("\ue335"),

    /** devices_other. */
    devices_other("\ue337"),

    /** dock. */
    dock("\ue30e"),

    /** gamepad. */
    gamepad("\ue30f"),

    /** headset. */
    headset("\ue310"),

    /** headset_mic. */
    headset_mic("\ue311"),

    /** keyboard. */
    keyboard("\ue312"),

    /** keyboard_arrow_down. */
    keyboard_arrow_down("\ue313"),

    /** keyboard_arrow_left. */
    keyboard_arrow_left("\ue314"),

    /** keyboard_arrow_right. */
    keyboard_arrow_right("\ue315"),

    /** keyboard_arrow_up. */
    keyboard_arrow_up("\ue316"),

    /** keyboard_backspace. */
    keyboard_backspace("\ue317"),

    /** keyboard_capslock. */
    keyboard_capslock("\ue318"),

    /** keyboard_hide. */
    keyboard_hide("\ue31a"),

    /** keyboard_return. */
    keyboard_return("\ue31b"),

    /** keyboard_tab. */
    keyboard_tab("\ue31c"),

    /** keyboard_voice. */
    keyboard_voice("\ue31d"),

    /** laptop. */
    laptop("\ue31e"),

    /** laptop_chromebook. */
    laptop_chromebook("\ue31f"),

    /** laptop_mac. */
    laptop_mac("\ue320"),

    /** laptop_windows. */
    laptop_windows("\ue321"),

    /** memory. */
    memory("\ue322"),

    /** mouse. */
    mouse("\ue323"),

    /** phone_android. */
    phone_android("\ue324"),

    /** phone_iphone. */
    phone_iphone("\ue325"),

    /** phonelink. */
    phonelink("\ue326"),

    /** phonelink_off. */
    phonelink_off("\ue327"),

    /** power_input. */
    power_input("\ue336"),

    /** router. */
    router("\ue328"),

    /** scanner. */
    scanner("\ue329"),

    /** security. */
    security("\ue32a"),

    /** sim_card. */
    sim_card("\ue32b"),

    /** smartphone. */
    smartphone("\ue32c"),

    /** speaker. */
    speaker("\ue32d"),

    /** speaker_group. */
    speaker_group("\ue32e"),

    /** tablet. */
    tablet("\ue32f"),

    /** tablet_android. */
    tablet_android("\ue330"),

    /** tablet_mac. */
    tablet_mac("\ue331"),

    /** toys. */
    toys("\ue332"),

    /** tv. */
    tv("\ue333"),

    /** videogame_asset. */
    videogame_asset("\ue338"),

    /** watch. */
    watch("\ue334");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsHardware(final String charCode) {
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
