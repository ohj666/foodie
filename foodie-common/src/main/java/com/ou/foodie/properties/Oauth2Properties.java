package com.ou.foodie.properties;

import lombok.Data;

@Data
public class Oauth2Properties {
    private ClientProperties[] client={};
    private String storeType="redis";
    private String jwtSignKey="sajdkasjdklsa";
}
