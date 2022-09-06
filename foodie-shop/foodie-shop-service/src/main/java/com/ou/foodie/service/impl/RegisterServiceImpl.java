package com.ou.foodie.service.impl;

import com.alibaba.druid.filter.AutoLoad;
import com.ou.foodie.mapper.UsersMapper;
import com.ou.foodie.pojo.LoginUser;
import com.ou.foodie.pojo.Users;
import com.ou.foodie.pojo.bo.UserBo;
import com.ou.foodie.pojo.vo.UserVo;
import com.ou.foodie.service.AddressService;
import com.ou.foodie.service.RegisterService;
import com.ou.foodie.util.*;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private UsersMapper usersMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    private static  final String faceUrl="http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";
    @Override
    public boolean getUsername(String username) {
        Example example = new Example(Users.class);
        example.createCriteria().andEqualTo("username", username);
        Users users = usersMapper.selectOneByExample(example);

        return users==null? false :true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserVo createUser(UserBo userBo) {

        Users users = Users.builder().birthday(DateUtils.parseDate("1970-01-01"))
                .id(SidUtils.get()).username(userBo.getUsername())
                .face(faceUrl).nickname(userBo.getUsername())
                .password(passwordEncoder.encode(userBo.getPassword()))
                .sex(Sex.secret.type)
                .createdTime(new Date())
                .updatedTime(new Date())
                .build();

        usersMapper.insert(users);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(users,userVo);


        return userVo;
    }

    @Override
    public JsonResult LoginUser(UserBo userBo, HttpServletRequest request, HttpServletResponse response) {
//        Example example = new Example(Users.class);
//        String username = userBo.getUsername();
//        String password = userBo.getPassword();
//        password=MD5Utils.getMD5Str(password);
//        example.createCriteria().andEqualTo("username",username).andEqualTo("password",password);
//
//        Users result = usersMapper.selectOneByExample(example);
//        if(result==null){
//            return null;
//        }
//        UserVo userVo = new UserVo();
//        BeanUtils.copyProperties(result,userVo);
//        return userVo;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userBo.getUsername(), userBo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        System.out.println(authenticate);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Users users = loginUser.getUsers();
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(users,userVo);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(users), true);
        return JsonResult.isOk();
    }




}
