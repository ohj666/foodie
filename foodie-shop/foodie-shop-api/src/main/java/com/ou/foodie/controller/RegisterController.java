package com.ou.foodie.controller;

import com.ou.foodie.pojo.bo.UserBo;
import com.ou.foodie.pojo.vo.UserVo;
import com.ou.foodie.service.ItemsService;
import com.ou.foodie.service.RegisterService;
import com.ou.foodie.util.CookieUtils;
import com.ou.foodie.util.JsonResult;
import com.ou.foodie.util.JsonUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.jni.Time;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/passport")
public class RegisterController {
    private RegisterService registerService;

    @GetMapping("/usernameIsExist")
    public JsonResult getUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return JsonResult.error(500, "用户名不能为空");
        }
        if (registerService.getUsername(username)) {
            return JsonResult.error(500, "用户名已经存在了");
        }
        return JsonResult.isOk();
    }

    @PostMapping("/regist")
    public JsonResult creteUser(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response) {

        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();
        String username = userBo.getUsername();
        if (registerService.getUsername(username)) {
            return JsonResult.error(500, "用户名已经存在了");
        }
        if (StringUtils.isBlank(password) || StringUtils.isBlank(username) || StringUtils.isBlank(confirmPassword)) {
            return JsonResult.error(500, "用户名或密码不能为空");
        }
        if (password.length() < 6) {
            return JsonResult.error(500, "密码长度不能小于6");
        }
        if (!password.equals(confirmPassword)) {
            return JsonResult.error(500, "两次密码不一致");
        }

        UserVo user = registerService.createUser(userBo);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);


        return JsonResult.isOk(user);
    }
    @PostMapping("/login")
    public JsonResult LoginUser(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response) {
        String password = userBo.getPassword();
        String username = userBo.getUsername();
        if (StringUtils.isBlank(password) || StringUtils.isBlank(username)) {
            return JsonResult.error(500, "用户名或密码不能为空");
        }
        if (password.length() < 6) {
            return JsonResult.error(500, "密码长度不能小于6");
        }
        return registerService.LoginUser(userBo,request,response);
    }

    @PostMapping("/logout")
    public JsonResult Logout(String userId,HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request,response,"user");
        return JsonResult.isOk();
    }




}