package com.ou.foodie.service.impl;

import com.alibaba.druid.filter.AutoLoad;
import com.ou.foodie.mapper.UsersMapper;
import com.ou.foodie.pojo.Users;
import com.ou.foodie.pojo.bo.UserBo;
import com.ou.foodie.pojo.vo.UserVo;
import com.ou.foodie.service.AddressService;
import com.ou.foodie.service.RegisterService;
import com.ou.foodie.util.*;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import tk.mybatis.mapper.entity.Example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private UsersMapper usersMapper;

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
                .password(MD5Utils.getMD5Str(userBo.getPassword()))
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
    public UserVo LoginUser(UserBo userBo) {
        Example example = new Example(Users.class);
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        password=MD5Utils.getMD5Str(password);
        example.createCriteria().andEqualTo("username",username).andEqualTo("password",password);

        Users result = usersMapper.selectOneByExample(example);
        if(result==null){
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(result,userVo);
        return userVo;
    }




}
