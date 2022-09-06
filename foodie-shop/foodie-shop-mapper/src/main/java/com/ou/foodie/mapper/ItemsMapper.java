package com.ou.foodie.mapper;


import com.ou.foodie.pojo.Items;
import com.ou.foodie.pojo.bo.CatItemsBo;
import com.ou.foodie.pojo.bo.SearchItemsBo;
import com.ou.foodie.pojo.dto.ItemsInfo;
import com.ou.foodie.pojo.vo.SearchVo;
import com.ou.foodie.pojo.vo.ShopCartVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
public interface ItemsMapper extends tk.mybatis.mapper.common.Mapper<Items> {
      List<Items> getLikeKeywords(String keywords);

      List<SearchVo> getSearchItems(SearchItemsBo bo);

      List<SearchVo> getCatItems(CatItemsBo catItemsBo);

      List<ShopCartVo> queryShopCast(List shopCastId);

      int decreaseItemSpectStock(@Param("id") String id,@Param("buyCounts") int buyCounts);
}




