package com.ou.foodie.server.impl;

import com.ou.foodie.center.mapper.UserMapper;
import com.ou.foodie.center.mapper.UserconnectionMapper;
import com.ou.foodie.pojo.User;
import com.ou.foodie.pojo.Userconnection;
import com.ou.foodie.pojo.bo.UserRegisterBo;


import com.ou.foodie.server.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService{
    private UserMapper userMapper;

    private HttpServletRequest request;
    private PasswordEncoder passwordEncoder;
    private ProviderSignInUtils providerSignInUtils;
    private UserconnectionMapper userconnectionMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userMapper.selectByPrimaryKey(s);
//        return  new org.springframework.security.core.userdetails.User(user.getId()+"",user.getPassword(),true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
////        return null;
//
//    }
//
//    @Override
//    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
//        System.out.println("hello");
//        User user = userMapper.selectByPrimaryKey(s);
//
////        User user = new User();
////        user.setId(Integer.valueOf(s));
////        User user1 = userMapper.selectOne(user);
////        SocialUserDetails socialUserDetails=new SocialUser(user1.getUsername(),user1.getPassword(),AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
////        return socialUserDetails;
//
//        return  new SocialUser(user.getUsername(),user.getPassword(),true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
//    }
//
//    @Override
//    public Integer userRegister(UserRegisterBo registerBo) {
//        if(!registerBo.getPassword().equals(registerBo.getRePassword())){
//            throw new UsernameNotFoundException("两次密码输入不一致，请重新输入");
//        }
//        User user = new User();
//        user.setPassword(passwordEncoder.encode(registerBo.getPassword()));
//        user.setUsername(registerBo.getUsername());
//        userMapper.insertSelectKey(user);
//        providerSignInUtils.doPostSignUp(user.getId().toString(),new ServletWebRequest(request));
//        return user.getId();
//    }
}
