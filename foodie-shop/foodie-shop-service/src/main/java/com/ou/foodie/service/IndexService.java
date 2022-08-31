package com.ou.foodie.service;

import com.ou.foodie.pojo.vo.CarouselVo;
import com.ou.foodie.pojo.vo.CategorysVo;
import com.ou.foodie.pojo.vo.SixNewItemsVo;
import com.ou.foodie.pojo.vo.SubCategorysVo;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface IndexService {
    List<CarouselVo> getRenderCarouse();
    List<CategorysVo> getCats();

    List<SubCategorysVo> getRootCat(Integer RootCatId);

    SixNewItemsVo renderSixNewItems(Integer rootCatId);
}
