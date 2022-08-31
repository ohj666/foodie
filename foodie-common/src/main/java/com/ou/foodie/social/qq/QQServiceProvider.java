package com.ou.foodie.social.qq;


import com.ou.foodie.properties.ProjectConstant;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    public String appid;

    public QQServiceProvider(String appid, String appsecret) {
        super(new QQOauth2Template(appid,appsecret, ProjectConstant.GET_QQ_AUTHORIZE_CODE_URL,ProjectConstant.GET_QQ_ACCESSTOKEN_CODE_URL));
        this.appid=appid;

    }


    @Override
    public QQ getApi(String s) {
        return new QQApi(s,appid);
    }
}
