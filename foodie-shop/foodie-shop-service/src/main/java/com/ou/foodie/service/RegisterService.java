package com.ou.foodie.service;

import com.ou.foodie.pojo.Users;
import com.ou.foodie.pojo.bo.UserBo;
import com.ou.foodie.pojo.vo.UserVo;
import com.ou.foodie.util.JsonResult;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RegisterService {
    boolean getUsername(String username);

    UserVo createUser(UserBo userBo);

    JsonResult LoginUser(UserBo userBo, HttpServletRequest request, HttpServletResponse response);
}
