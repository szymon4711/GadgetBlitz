package com.app.gadgetblitz.model.phone.components;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Network {
    Boolean has_nfc;
    Boolean has_mimo;
    Boolean has_wifi;
    Boolean has_volte;
    Boolean has_airplay;
    Boolean has_ibeacon;
    Boolean has_dual_sim;
    Boolean has_bluetooth;
    @Field("3g_standards")
    String _3g_standards;
    @Field("4g_standards")
    String _4g_standards;
    String type_sim_card;
    String wifi_standards;
    String sim_card_capacity;
    String generation_mobile_network;
    Integer bluetooth_version;
}
