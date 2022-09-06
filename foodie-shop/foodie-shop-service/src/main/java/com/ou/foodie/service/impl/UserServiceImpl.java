package com.ou.foodie.service.impl;

import com.ou.foodie.mapper.UsersMapper;
import com.ou.foodie.pojo.LoginUser;
import com.ou.foodie.pojo.Users;
import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;

import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import javax.resource.spi.RetryableUnavailableException;
import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService,SocialUserDetailsService {

    private PasswordEncoder passwordEncoder;
    private HttpServletRequest request;
    private ProjectProperties properties;
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users = new Users();
        users.setUsername(s);
        Users result = usersMapper.selectOne(users);
        if(result==null){
            throw new RuntimeException("用户名不存在");
        }

        return new LoginUser(result);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        return null;
    }

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user=null;
//        String action = request.getRequestURI();
//        if(StringUtils.equals(ProjectConstant.MOBILE_AUTHENTICATION_PROCESSING_URL,action)){
//            User user1 = new User();
//            user1.setMobile(s);
//            user=userMapper.selectOne(user1);
//            System.out.println(user);
//        }
//        else{
//            user=userMapper.selectByPrimaryKey(s);
//        }
//        if(user==null){
//            throw new UsernameNotFoundException("用户名没有找到");
//        }
//
//
//        return new org.springframework.security.core.userdetails.User(user.getId().toString(),user.getPassword(),true,true,true,true,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
//    }
//
//    @Override
//    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
//        return null;
//    }
}
