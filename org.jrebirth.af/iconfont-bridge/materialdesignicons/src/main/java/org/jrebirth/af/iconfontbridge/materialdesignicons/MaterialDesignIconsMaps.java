package org.jrebirth.af.iconfontbridge.materialdesignicons;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.provided.IconFont;

/**
 * Material Design Icons category: maps.
 */
public enum MaterialDesignIconsMaps implements IconFont {

    /** add_location. */
    add_location("\ue567"),

    /** beenhere. */
    beenhere("\ue52d"),

    /** directions. */
    directions("\ue52e"),

    /** directions_bike. */
    directions_bike("\ue52f"),

    /** directions_boat. */
    directions_boat("\ue532"),

    /** directions_bus. */
    directions_bus("\ue530"),

    /** directions_car. */
    directions_car("\ue531"),

    /** directions_railway. */
    directions_railway("\ue534"),

    /** directions_run. */
    directions_run("\ue566"),

    /** directions_subway. */
    directions_subway("\ue533"),

    /** directions_transit. */
    directions_transit("\ue535"),

    /** directions_walk. */
    directions_walk("\ue536"),

    /** edit_location. */
    edit_location("\ue568"),

    /** ev_station. */
    ev_station("\ue56d"),

    /** flight. */
    flight("\ue539"),

    /** hotel. */
    hotel("\ue53a"),

    /** layers. */
    layers("\ue53b"),

    /** layers_clear. */
    layers_clear("\ue53c"),

    /** local_activity. */
    local_activity("\ue53f"),

    /** local_airport. */
    local_airport("\ue53d"),

    /** local_atm. */
    local_atm("\ue53e"),

    /** local_bar. */
    local_bar("\ue540"),

    /** local_cafe. */
    local_cafe("\ue541"),

    /** local_car_wash. */
    local_car_wash("\ue542"),

    /** local_convenience_store. */
    local_convenience_store("\ue543"),

    /** local_dining. */
    local_dining("\ue556"),

    /** local_drink. */
    local_drink("\ue544"),

    /** local_florist. */
    local_florist("\ue545"),

    /** local_gas_station. */
    local_gas_station("\ue546"),

    /** local_grocery_store. */
    local_grocery_store("\ue547"),

    /** local_hospital. */
    local_hospital("\ue548"),

    /** local_hotel. */
    local_hotel("\ue549"),

    /** local_laundry_service. */
    local_laundry_service("\ue54a"),

    /** local_library. */
    local_library("\ue54b"),

    /** local_mall. */
    local_mall("\ue54c"),

    /** local_movies. */
    local_movies("\ue54d"),

    /** local_offer. */
    local_offer("\ue54e"),

    /** local_parking. */
    local_parking("\ue54f"),

    /** local_pharmacy. */
    local_pharmacy("\ue550"),

    /** local_phone. */
    local_phone("\ue551"),

    /** local_pizza. */
    local_pizza("\ue552"),

    /** local_play. */
    local_play("\ue553"),

    /** local_post_office. */
    local_post_office("\ue554"),

    /** local_printshop. */
    local_printshop("\ue555"),

    /** local_see. */
    local_see("\ue557"),

    /** local_shipping. */
    local_shipping("\ue558"),

    /** local_taxi. */
    local_taxi("\ue559"),

    /** map. */
    map("\ue55b"),

    /** my_location. */
    my_location("\ue55c"),

    /** navigation. */
    navigation("\ue55d"),

    /** near_me. */
    near_me("\ue569"),

    /** person_pin. */
    person_pin("\ue55a"),

    /** person_pin_circle. */
    person_pin_circle("\ue56a"),

    /** pin_drop. */
    pin_drop("\ue55e"),

    /** place. */
    place("\ue55f"),

    /** rate_review. */
    rate_review("\ue560"),

    /** restaurant. */
    restaurant("\ue56c"),

    /** restaurant_menu. */
    restaurant_menu("\ue561"),

    /** satellite. */
    satellite("\ue562"),

    /** store_mall_directory. */
    store_mall_directory("\ue563"),

    /** streetview. */
    streetview("\ue56e"),

    /** subway. */
    subway("\ue56f"),

    /** terrain. */
    terrain("\ue564"),

    /** traffic. */
    traffic("\ue565"),

    /** train. */
    train("\ue570"),

    /** tram. */
    tram("\ue571"),

    /** transfer_within_a_station. */
    transfer_within_a_station("\ue572"),

    /** zoom_out_map. */
    zoom_out_map("\ue56b");

    /** The char code designating the icon. */
    private final String charCode;

    MaterialDesignIconsMaps(final String charCode) {
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
