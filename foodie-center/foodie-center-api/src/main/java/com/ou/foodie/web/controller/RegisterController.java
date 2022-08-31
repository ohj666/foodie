package com.ou.foodie.web.controller;

import com.ou.foodie.pojo.bo.UserRegisterBo;
import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.server.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegisterController {
    private UserService userService;
    @RequestMapping(ProjectConstant.REQUIRE_URL_REGISTER)
    @ResponseBody
    public String register(@Valid UserRegisterBo userRegisterBo){
        userService.userRegister(userRegisterBo);
        return "success";
    }

}
