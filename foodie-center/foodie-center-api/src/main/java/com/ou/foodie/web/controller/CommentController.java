package com.ou.foodie.web.controller;

import com.ou.foodie.pojo.ItemsComments;
import com.ou.foodie.pojo.bo.OrderItemBo;
import com.ou.foodie.server.AddressService;
import com.ou.foodie.server.CommentService;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/mycomments")
@RestController
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping("/pending")
    public JsonResult pendingComment(String userId,String orderId){
        return commentService.pendingComment(userId,orderId);
    }

    @PostMapping("/saveList")
    public JsonResult saveList(String userId, String orderId, @RequestBody List<OrderItemBo> orderItemList){
        return commentService.saveList(userId,orderId,orderItemList);
    }

    @PostMapping("/query")
    public JsonResult query(String userId, @RequestParam(required = false,defaultValue = "1") Integer page,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        return commentService.query(userId,page,pageSize);
    }
}
