package com.ou.foodie.social.custom;


import com.ou.foodie.properties.ProjectConstant;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class CustomServiceProvider extends AbstractOAuth2ServiceProvider<Custom> {

    public String appid;

    public CustomServiceProvider(String appid, String appsecret) {
        super(new CustomOauth2Template(appid,appsecret, ProjectConstant.GET_CUSTOM_AUTHORIZE_CODE_URL, ProjectConstant.GET_CUSTOM_ACCESSTOKEN_CODE_URL));
        this.appid=appid;

    }


    @Override
    public Custom getApi(String s) {
        return new CustomImpl(s);
    }
}
