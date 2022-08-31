package com.ou.foodie.social.custom;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class CustomAdapter implements ApiAdapter<Custom> {
    @Override
    public boolean test(Custom custom) {
        return true;
    }

    @Override
    public void setConnectionValues(Custom api, ConnectionValues connectionValues) {
        CustomUserInfo custom = api.getCustom();
        connectionValues.setDisplayName(custom.getNickName());
        connectionValues.setImageUrl(custom.getFigureurl());
        connectionValues.setProviderUserId(custom.getOpenId());

    }

    @Override
    public UserProfile fetchUserProfile(Custom custom) {
        return null;
    }

    @Override
    public void updateStatus(Custom custom, String s) {

    }
}
