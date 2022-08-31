package com.ou.foodie.properties;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ClientProperties {
    private String clientId;
    private String clientSecret;
    private String autoApprove;
    private Integer refreshTokenValiditySeconds;
    private Integer accessTokenValiditySeconds;

    private String[] authorities={};
    private String[] resourceIds={};
    private String[] authorizedGrantTypes={};
    private String[] redirectUris={};
    private String[] scopes={};
    private Map<String,Object> additionalInformation=new HashMap<>();

}
