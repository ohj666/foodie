package com.ou.foodie.social.custom;

import com.ou.foodie.properties.ProjectConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

@Slf4j
public class CustomImpl extends AbstractOAuth2ApiBinding implements Custom{



    public CustomImpl(String accessToken) {
        super(accessToken, TokenStrategy.AUTHORIZATION_HEADER);

    }

    @Override
    public CustomUserInfo getCustom() {
        CustomUserInfo customUserInfo = getRestTemplate().getForObject(ProjectConstant.GET_CUSTOM_USER_INFO_URL, CustomUserInfo.class);
        return customUserInfo;
    }
}
