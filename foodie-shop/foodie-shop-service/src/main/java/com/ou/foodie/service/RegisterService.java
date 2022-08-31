package com.ou.foodie.service;

import com.ou.foodie.pojo.Users;
import com.ou.foodie.pojo.bo.UserBo;
import com.ou.foodie.pojo.vo.UserVo;
import org.springframework.security.core.userdetails.User;

public interface RegisterService {
    boolean getUsername(String username);

    UserVo createUser(UserBo userBo);

    UserVo LoginUser(UserBo userBo);
}
