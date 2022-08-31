package com.ou.foodie.controller;

import com.ou.foodie.pojo.bo.CommentsBo;
import com.ou.foodie.service.ItemsService;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private ItemsService itemsService;
    @GetMapping("/info/{id}")
    public JsonResult getItemsInfo(@PathVariable String id){
        return JsonResult.isOk(itemsService.getItemsInfo(id));
    }


    @GetMapping("/comments")
    public JsonResult comments(String itemId,@RequestParam(required = false,defaultValue =  "") Integer level,@RequestParam(required = false, defaultValue = "1")Integer page,@RequestParam(required = false, defaultValue = "10")Integer pageSize){
        return JsonResult.isOk(itemsService.renderingComment(itemId,level,page,pageSize));
    }

    @GetMapping("/commentLevel")
    public JsonResult renderCommentLevel(String itemId){
        return JsonResult.isOk(itemsService.renderCommentLevel(itemId));
    }

    @GetMapping("/search")
    public JsonResult renderSearch(String keywords,String sort,@RequestParam(required = false,defaultValue = "1")Integer page,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        return JsonResult.isOk(itemsService.renderSearch(keywords,sort,page,pageSize));
    }

    @GetMapping("/catItems")
    public JsonResult renderItemSearch(String catId,String sort,@RequestParam(required = false,defaultValue = "1")Integer page,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        return JsonResult.isOk(itemsService.renderItemSearch(catId,sort,page,pageSize));
    }

    @GetMapping("/refresh")
    public JsonResult RefreshShopCast(String itemSpecIds){
        if(StringUtils.isBlank(itemSpecIds)){
            return JsonResult.isOk();
        }
        return  JsonResult.isOk(itemsService.RefreshShopCast(itemSpecIds));
    }


}
