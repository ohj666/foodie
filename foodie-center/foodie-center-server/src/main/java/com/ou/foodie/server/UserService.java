package com.ou.foodie.server;


import com.ou.foodie.pojo.bo.UserInfoMoreBo;
import com.ou.foodie.util.JsonResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    JsonResult userInfo(String userId);

    JsonResult update(String userId, UserInfoMoreBo userInfoMoreBo, BindingResult result);

    JsonResult uploadFace(String userId, MultipartFile file, HttpServletRequest request, HttpServletResponse response);
}
