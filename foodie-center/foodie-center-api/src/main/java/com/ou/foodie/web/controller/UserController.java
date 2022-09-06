package com.ou.foodie.web.controller;

import com.ou.foodie.pojo.bo.UserInfoMoreBo;
import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.server.UserService;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;

@RequestMapping("/center")
@RestController
@AllArgsConstructor
public class UserController {

    private  ProjectProperties properties;
    private UserService userService;

    @GetMapping("/userInfo")
    public JsonResult userInfo(String userId){
        return userService.userInfo(userId);
    }

    @PostMapping("/update")
    public JsonResult update(@RequestParam String userId,
                             @RequestBody @Valid UserInfoMoreBo userInfoMoreBo, BindingResult result){


        return userService.update(userId,userInfoMoreBo,result);
    }

    @PostMapping("/uploadFace")
    public JsonResult uploadFace(@RequestParam String userId,
                                  MultipartFile file,HttpServletRequest request,HttpServletResponse response){

        System.out.println("ok");
        return userService.uploadFace(userId,file,request,response);
    }

}
