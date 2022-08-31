package com.ou.foodie.mapper;


import com.ou.foodie.pojo.Category;
import com.ou.foodie.pojo.vo.SubCategorysVo;

import java.util.List;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
public interface CategoryMapper extends tk.mybatis.mapper.common.Mapper<Category> {
    List<SubCategorysVo> getSubCats(Integer RootGetId);

}




