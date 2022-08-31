package com.ou.foodie.social.qq;

import com.ou.foodie.properties.ProjectConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
@Slf4j
public class QQApi extends AbstractOAuth2ApiBinding implements QQ {
    private String appid;
    private String openid;

    public QQApi(String accessToken, String appid) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appid = appid;
        String url = String.format(ProjectConstant.GET_QQ_OPENID_URL, accessToken);
        QQOpenidResult result = getRestTemplate().getForObject(url, QQOpenidResult.class);
        log.info("get qq openid"+result.getOpenid());
        this.openid=result.getOpenid();
    }

    @Override
    public QQInfo getQQUserInfo() {
        String url = String.format(ProjectConstant.GET_QQ_USER_INFO_URL, appid, openid);
        QQInfo qqUserInfo = getRestTemplate().getForObject(url, QQInfo.class);
        return qqUserInfo;
    }
}
