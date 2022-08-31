package com.ou.foodie.controller;

import com.ou.foodie.pojo.bo.ShopCartBo;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@RestController
@RequestMapping("/shopcart")
public class ShopCartController {
    @PostMapping("/add")
    public JsonResult AddShopcart(String userId, @RequestBody ShopCartBo shopCartBo, HttpServletRequest request, HttpServletResponse response){
        if(StringUtils.isBlank(userId)){
            return JsonResult.error(500,"用户未登录");
        }
        System.out.println(shopCartBo);
        //TODO 前端用户在登录的情况下,添加商品到购物车,会同时在后端同步购物车到redis缓存
        return JsonResult.isOk();

    }

    @PostMapping("/del")
    public JsonResult delShopcart(String userId,String itemSpecId , HttpServletRequest request, HttpServletResponse response){
        if(StringUtils.isBlank(userId)||StringUtils.isBlank(itemSpecId)){
            return JsonResult.error(500,"信息不能为空");
        }



        //TODO 前端用户删除购物车中的商品数据，如果此时用户已经登录的话需要同步删除后端购物车中的数据
        return JsonResult.isOk();

    }


}
