package com.ou.foodie.service;

import com.ou.foodie.pojo.bo.CommentsBo;
import com.ou.foodie.pojo.vo.CountsVo;
import com.ou.foodie.pojo.vo.ItemInfoVo;
import com.ou.foodie.pojo.vo.ShopCartVo;
import com.ou.foodie.util.PagedGridResult;

import java.util.List;

public interface ItemsService {
    ItemInfoVo getItemsInfo(String id);


    PagedGridResult renderingComment(String itemId,Integer level,Integer PageNum,Integer PageSize);

    CountsVo renderCommentLevel(String itemId);

    PagedGridResult renderSearch(String keywords,String sort, Integer page, Integer pageSize);

    PagedGridResult renderItemSearch(String catId, String sort, Integer page, Integer pageSize);

    List<ShopCartVo> RefreshShopCast(String itemSpecIds);
}
