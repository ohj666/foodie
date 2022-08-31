package com.ou.foodie.util;



import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Utils {
    public static String getMD5Str(String strValue){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        String base64String = Base64.getEncoder().encodeToString(md5.digest(strValue.getBytes()));
        return base64String;
    }



//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        System.out.println(getMD5Str("imooc"));
//    }
}
