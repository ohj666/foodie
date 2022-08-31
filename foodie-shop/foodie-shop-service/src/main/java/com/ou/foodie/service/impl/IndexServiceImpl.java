package com.ou.foodie.service.impl;

import com.github.pagehelper.PageHelper;
import com.ou.foodie.mapper.CarouselMapper;
import com.ou.foodie.mapper.CategoryMapper;
import com.ou.foodie.mapper.ItemsImgMapper;
import com.ou.foodie.mapper.ItemsMapper;
import com.ou.foodie.pojo.Carousel;
import com.ou.foodie.pojo.Category;
import com.ou.foodie.pojo.Items;
import com.ou.foodie.pojo.ItemsImg;
import com.ou.foodie.pojo.vo.*;
import com.ou.foodie.service.IndexService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class IndexServiceImpl implements IndexService {
    private CarouselMapper carouselMapper;
    private CategoryMapper categoryMapper;
    private ItemsImgMapper itemsImgMapper;
    private ItemsMapper itemsMapper;
    @Override
    public List<CarouselVo> getRenderCarouse() {

        Carousel carousel=new Carousel();
        carousel.setIsShow(1);
        List<Carousel> select = carouselMapper.select(carousel);
        List<CarouselVo> result=new ArrayList<>();
        for (Carousel carousel1 : select) {
            CarouselVo carouselVo = new CarouselVo();
            BeanUtils.copyProperties(carousel1,carouselVo);
            result.add(carouselVo);
        }


        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategorysVo> getCats() {
        Category category1 = new Category();
        category1.setType(1);
        List<Category> categories = categoryMapper.select(category1);
        List<CategorysVo> categorysVos=new ArrayList<>();
        for (Category category : categories) {
            CategorysVo categorysVo = new CategorysVo();
            BeanUtils.copyProperties(category,categorysVo);
            categorysVos.add(categorysVo);
        }
        return categorysVos;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<SubCategorysVo> getRootCat(Integer RootCatId) {
        List<SubCategorysVo> result=new ArrayList<>();
        Category category = new Category();
        category.setFatherId(RootCatId);
        List<Category> SubCat = categoryMapper.select(category);
        for (Category category1 : SubCat) {

            SubCategorysVo subCategorysVo = new SubCategorysVo();
            subCategorysVo.setName(category1.getName());
            Category thirdCats=new Category();
            thirdCats.setFatherId(category1.getId());
            List<ThridSubCategorysVo> thridSubCategorysVoList=new ArrayList<>();
            for (Category category2 : categoryMapper.select(thirdCats)) {
                ThridSubCategorysVo thridSubCategorysVo = new ThridSubCategorysVo();
                thridSubCategorysVo.setId(category2.getId());
                thridSubCategorysVo.setName(category2.getName());
                thridSubCategorysVoList.add(thridSubCategorysVo);
            }
            subCategorysVo.setThridSubCategorysVoList(thridSubCategorysVoList);
            result.add(subCategorysVo);
        }

        return result;
//        return categoryMapper.getSubCats(RootCatId);
    }

    @Override
    public SixNewItemsVo renderSixNewItems(Integer rootCatId) {
        SixNewItemsVo result=new SixNewItemsVo();
        Category category = new Category();
        category.setId(rootCatId);
        Category current = categoryMapper.selectOne(category);

        result.setRootCatName(current.getName());


        BeanUtils.copyProperties(current,result);

        Example example = new Example(Items.class);
        example.createCriteria().andEqualTo("rootCatId",rootCatId);
        example.orderBy("sellCounts").desc();
        PageHelper.startPage(1,6);
        List<Items> items = itemsMapper.selectByExample(example);
        List<SimpleItemVo> simpleItemList=new ArrayList<>();
        for (Items item : items) {
            SimpleItemVo simpleItemVo = new SimpleItemVo();
            BeanUtils.copyProperties(item,simpleItemVo);
            simpleItemVo.setItemId(item.getId());
            ItemsImg itemsImg1 = new ItemsImg();
            itemsImg1.setItemId(item.getId());
            itemsImg1.setIsMain(1);
            ItemsImg itemsImg = itemsImgMapper.selectOne(itemsImg1);

            simpleItemVo.setItemUrl(itemsImg.getUrl());
            simpleItemList.add(simpleItemVo);
        }
        result.setSimpleItemList(simpleItemList);
        return result;
    }
}
