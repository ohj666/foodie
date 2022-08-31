package com.ou.foodie.controller;


import com.ou.foodie.service.IndexService;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@AllArgsConstructor
@RequestMapping("/index")
public class IndexController {
    private IndexService indexService;

    @GetMapping("/carousel")
    public JsonResult renderCarousel(){
        return JsonResult.isOk(indexService.getRenderCarouse());
    }

    @GetMapping("/cats")
    public JsonResult renderCategorys(){
        return JsonResult.isOk(indexService.getCats());
    }

    @GetMapping("/subCat/{RootCatId}")
    public JsonResult getSubCats(@PathVariable Integer RootCatId){
        return JsonResult.isOk(indexService.getRootCat(RootCatId));
    }


    @GetMapping("/sixNewItems/{RootCatId}")
    public JsonResult renderSixNewItems(@PathVariable Integer RootCatId){
        return JsonResult.isOk(indexService.renderSixNewItems(RootCatId));
    }


}
