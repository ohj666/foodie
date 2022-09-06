package com.ou.foodie.mapper;


import com.ou.foodie.pojo.ItemsComments;
import com.ou.foodie.pojo.dto.CommentAndLevel;
import org.bouncycastle.crypto.tls.MACAlgorithm;

import java.util.List;
import java.util.Map;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
public interface ItemsCommentsMapper extends tk.mybatis.mapper.common.Mapper<ItemsComments> {
    List<CommentAndLevel> getItemsCommentsAndLevel(String ItemId);
    void saveList(Map<String,Object> map);

}




