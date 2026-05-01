package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: device.
 */
public enum MaterialDesignIconsDevice implements IconFont {

    /** access_alarm. */
    access_alarm("\ue190"),

    /** access_alarms. */
    access_alarms("\ue191"),

    /** access_time. */
    access_time("\ue192"),

    /** add_alarm. */
    add_alarm("\ue193"),

    /** airplanemode_active. */
    airplanemode_active("\ue195"),

    /** airplanemode_inactive. */
    airplanemode_inactive("\ue194"),

    /** battery_alert. */
    battery_alert("\ue19c"),

    /** battery_charging_full. */
    battery_charging_full("\ue1a3"),

    /** battery_full. */
    battery_full("\ue1a4"),

    /** battery_std. */
    battery_std("\ue1a5"),

    /** battery_unknown. */
    battery_unknown("\ue1a6"),

    /** bluetooth. */
    bluetooth("\ue1a7"),

    /** bluetooth_connected. */
    bluetooth_connected("\ue1a8"),

    /** bluetooth_disabled. */
    bluetooth_disabled("\ue1a9"),

    /** bluetooth_searching. */
    bluetooth_searching("\ue1aa"),

    /** brightness_auto. */
    brightness_auto("\ue1ab"),

    /** brightness_high. */
    brightness_high("\ue1ac"),

    /** brightness_low. */
    brightness_low("\ue1ad"),

    /** brightness_medium. */
    brightness_medium("\ue1ae"),

    /** data_usage. */
    data_usage("\ue1af"),

    /** developer_mode. */
    developer_mode("\ue1b0"),

    /** devices. */
    devices("\ue1b1"),

    /** dvr. */
    dvr("\ue1b2"),

    /** gps_fixed. */
    gps_fixed("\ue1b3"),

    /** gps_not_fixed. */
    gps_not_fixed("\ue1b4"),

    /** gps_off. */
    gps_off("\ue1b5"),

    /** graphic_eq. */
    graphic_eq("\ue1b8"),

    /** location_disabled. */
    location_disabled("\ue1b6"),

    /** location_searching. */
    location_searching("\ue1b7"),

    /** network_cell. */
    network_cell("\ue1b9"),

    /** network_wifi. */
    network_wifi("\ue1ba"),

    /** nfc. */
    nfc("\ue1bb"),

    /** screen_lock_landscape. */
    screen_lock_landscape("\ue1be"),

    /** screen_lock_portrait. */
    screen_lock_portrait("\ue1bf"),

    /** screen_lock_rotation. */
    screen_lock_rotation("\ue1c0"),

    /** screen_rotation. */
    screen_rotation("\ue1c1"),

    /** sd_storage. */
    sd_storage("\ue1c2"),

    /** settings_system_daydream. */
    settings_system_daydream("\ue1c3"),

    /** signal_cellular_4_bar. */
    signal_cellular_4_bar("\ue1c8"),

    /** signal_cellular_connected_no_internet_4_bar. */
    signal_cellular_connected_no_internet_4_bar("\ue1cd"),

    /** signal_cellular_no_sim. */
    signal_cellular_no_sim("\ue1ce"),

    /** signal_cellular_null. */
    signal_cellular_null("\ue1cf"),

    /** signal_cellular_off. */
    signal_cellular_off("\ue1d0"),

    /** signal_wifi_4_bar. */
    signal_wifi_4_bar("\ue1d8"),

    /** signal_wifi_4_bar_lock. */
    signal_wifi_4_bar_lock("\ue1d9"),

    /** signal_wifi_off. */
    signal_wifi_off("\ue1da"),

    /** storage. */
    storage("\ue1db"),

    /** usb. */
    usb("\ue1e0"),

    /** wallpaper. */
    wallpaper("\ue1bc"),

    /** widgets. */
    widgets("\ue1bd"),

    /** wifi_lock. */
    wifi_lock("\ue1e1"),

    /** wifi_tethering. */
    wifi_tethering("\ue1e2");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsDevice(final String charCode) {
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
