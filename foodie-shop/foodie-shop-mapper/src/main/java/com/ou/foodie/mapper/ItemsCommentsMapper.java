package com.ou.foodie.mapper;


import com.ou.foodie.pojo.ItemsComments;
import com.ou.foodie.pojo.dto.CommentAndLevel;
import com.ou.foodie.pojo.vo.CountsVo;

import java.util.List;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
public interface ItemsCommentsMapper extends tk.mybatis.mapper.common.Mapper<ItemsComments> {
    List<CommentAndLevel> getItemsCommentsAndLevel(String ItemId);

}




