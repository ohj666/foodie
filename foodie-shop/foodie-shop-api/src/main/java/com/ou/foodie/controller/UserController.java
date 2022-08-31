package com.ou.foodie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/setSession")
public class UserController {
    @GetMapping()
    public String setSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("userInfo","new user");
        session.setMaxInactiveInterval(3600);

        return "ok";
    }
    @GetMapping("/get")
    public String getSession(HttpServletRequest request){
        HttpSession session = request.getSession();

        String userInfo = (String) session.getAttribute("userInfo");
        return userInfo;
    }

    @GetMapping("/del")
    public String delSession(HttpServletRequest request){
        HttpSession session = request.getSession();

        session.removeAttribute("userInfo");
        return "ok";
    }
}
//public class UserController {
//    @Autowired
//     private UserMapper userMapper;
//    @RequestMapping("/me")
//    public CustomUserInfo UserMe(Authentication authentication){
//        OAuth2Authentication oAuth2Authentication= (OAuth2Authentication) authentication;
//        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
//        User query=new User();
//        User ResultUser=null;
//
//        if(userAuthentication instanceof MobileAuthenticationToken){
//            query.setMobile((String) authentication.getPrincipal());
//             ResultUser = userMapper.selectOne(query);
//        }else if (userAuthentication instanceof UsernamePasswordAuthenticationToken){
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= (UsernamePasswordAuthenticationToken) userAuthentication;
//            org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) usernamePasswordAuthenticationToken.getPrincipal();
//            query.setId(Integer.valueOf(principal.getUsername()));
//            ResultUser=userMapper.selectOne(query);
//        }
//        CustomUserInfo customUserInfo=new CustomUserInfo();
//        customUserInfo.setFigureurl(ResultUser.getAddress());
//        customUserInfo.setNickName(ResultUser.getUsername());
//        customUserInfo.setOpenId(ResultUser.getId().toString());
//        return customUserInfo;
//    }
//}
