package com.ou.foodie.properties;

import java.io.File;
import java.util.Locale;

public class ProjectConstant {
    public final static String REQUEST_AUTHENTICATION_URL="/authentication/request";
    public final static String REQUEST_VALIDATE_CODE="/validate";
    public final static String VALIDATE_CODE_IN_SESSION="validate_code_in_session";

    public final static String MOBILE_AUTHENTICATION_PROCESSING_URL="/validate/mobile";

    public final static String REQUIRE_URL_REGISTER="/authentication/register";

    public final static String MOBILE_AUTHENTICATION_FORM_USERNAME="mobile";

    public final static String MOBILE_AUTHENTICATION_FORM_PASSWORD="validateCode";


    public static final String GET_QQ_OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%S";
    public static final String GET_CUSTOM_OPENID_URL = "http://localhost:8080/oauth/?access_token=%S";
    public static final String GET_QQ_ACCESSTOKEN_CODE_URL = "https://graph.qq.com/oauth2.0/token";
    public static final String GET_CUSTOM_ACCESSTOKEN_CODE_URL = "http://localhost:8080/oauth/token";
    public static final String GET_QQ_AUTHORIZE_CODE_URL = "https://graph.qq.com/oauth2.0/authorize";
    public static final String GET_CUSTOM_AUTHORIZE_CODE_URL = "http://localhost:8080/oauth/authorize";
    public static final String GET_QQ_USER_INFO_URL = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%S&openid=%S";
    public static final String GET_CUSTOM_USER_INFO_URL = "http://localhost:8080/user/me";

    public static final String RETURN_URL="http://localhost:8080/orders/notifyMerchantOrderPaid";
    public static final String PAYMENTSERVERURL="http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    public static final String IMAGE_URL="D:" +
            File.separator+"imageTest" +
            File.separator+"foodieImage";
}
