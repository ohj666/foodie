package com.ou.foodie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ou.foodie.mapper.*;
import com.ou.foodie.pojo.*;
import com.ou.foodie.pojo.bo.CatItemsBo;
import com.ou.foodie.pojo.bo.CommentsBo;
import com.ou.foodie.pojo.bo.SearchItemsBo;
import com.ou.foodie.pojo.dto.CommentAndLevel;
import com.ou.foodie.pojo.dto.ItemsInfo;
import com.ou.foodie.pojo.vo.*;
import com.ou.foodie.service.ItemsService;
import com.ou.foodie.util.PagedGridResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemsService {
    private ItemsMapper itemsMapper;
    private ItemsImgMapper itemsImgMapper;
    private ItemsParamMapper itemsParamMapper;
    private ItemsSpecMapper itemsSpecMapper;
    private ItemsCommentsMapper itemsCommentsMapper;
    private UsersMapper usersMapper;

    @Override
    public ItemInfoVo getItemsInfo(String id) {
        ItemInfoVo result = new ItemInfoVo();

        ItemsParam param = new ItemsParam();
        param.setItemId(id);
        ItemsParam paramSelect = itemsParamMapper.selectOne(param);
        ItemParamVo itemParamVo = new ItemParamVo();
        BeanUtils.copyProperties(paramSelect,itemParamVo);
        result.setItemParams(itemParamVo);

        Items items = itemsMapper.selectByPrimaryKey(id);

        ItemVo itemVo = new ItemVo();
        itemVo.setItemName(items.getItemName());
        itemVo.setSellCounts(items.getSellCounts());
        result.setItem(itemVo);

        List<SelectedSkuVo> itemSpecList=new ArrayList<>();
        List<ItemImgVo> itemImgList=new ArrayList<>();

        ItemsImg itemsImg=new ItemsImg();
        itemsImg.setItemId(id);
        List<ItemsImg> ItemsImgs = itemsImgMapper.select(itemsImg);
        for (ItemsImg img : ItemsImgs) {
            ItemImgVo itemImgVo = new ItemImgVo();
            itemImgVo.setIsMain(img.getIsMain());
            itemImgVo.setUrl(img.getUrl());
            itemImgList.add(itemImgVo);
        }


        ItemsSpec itemsSpec = new ItemsSpec();
        itemsSpec.setItemId(id);
        List<ItemsSpec> select = itemsSpecMapper.select(itemsSpec);
        for (ItemsSpec spec : select) {
            SelectedSkuVo selectedSkuVo = new SelectedSkuVo();
            selectedSkuVo.setId(spec.getId());
            selectedSkuVo.setDiscounts(spec.getDiscounts().floatValue());
            selectedSkuVo.setName(spec.getName());
            selectedSkuVo.setPriceDiscount(spec.getPriceDiscount());
            selectedSkuVo.setStock(spec.getStock());
            selectedSkuVo.setPriceNormal(spec.getPriceNormal());
            itemSpecList.add(selectedSkuVo);
        }

        result.setItemSpecList(itemSpecList);
        result.setItemImgList(itemImgList);
        return result;
    }

    @Override
    public PagedGridResult renderingComment(String itemId, Integer level,Integer PageNum, Integer PageSize) {
        Page<Object> page = PageHelper.startPage(PageNum, PageSize, true, null, false);
        ItemsComments itemsComments = new ItemsComments();
        itemsComments.setItemId(itemId);
        if(level!=null){
            itemsComments.setCommentLevel(level);
        }
        List<ItemsComments> current = itemsCommentsMapper.select(itemsComments);
        List<CommentVo> commentVoList=new ArrayList<>();
        for (ItemsComments comments : current) {
            CommentVo commentVo = new CommentVo();
            BeanUtils.copyProperties(comments,commentVo);
            Users users = new Users();
            users.setId(comments.getUserId());
            Users user = usersMapper.selectOne(users);
            commentVo.setSpecName(comments.getSepcName());
            commentVo.setNickname(user.getNickname());
            commentVo.setUserFace(user.getFace());
            commentVoList.add(commentVo);
        }
        PagedGridResult gridResult=new PagedGridResult();
        gridResult.setPage(page.getPageNum());
        gridResult.setTotal(page.getPages());
        gridResult.setRecords(page.getTotal());
        gridResult.setRows(commentVoList);

        return gridResult;
    }


    @Override
    public CountsVo renderCommentLevel(String itemId) {
        CountsVo countsVo=new CountsVo();
        List<CommentAndLevel> itemsCommentsAndLevel = itemsCommentsMapper.getItemsCommentsAndLevel(itemId);
        int total=0;
        for (CommentAndLevel commentAndLevel : itemsCommentsAndLevel) {
            if(commentAndLevel.getLevel()==1){
                countsVo.setGoodCounts(commentAndLevel.getCommentCounts());
                total+=countsVo.getGoodCounts();
            }else if (commentAndLevel.getLevel()==2){
                countsVo.setNormalCounts(commentAndLevel.getCommentCounts());
                total+=countsVo.getNormalCounts();
            }else {
                countsVo.setBadCounts(commentAndLevel.getCommentCounts());
                total+=countsVo.getBadCounts();
            }
        }
        countsVo.setTotalCounts(total);
        return countsVo;
    }

//    @Override
//    public PagedGridResult renderSearch(String keywords, Integer page, Integer pageSize) {
//        Page<Object> page1 = PageHelper.startPage(page, pageSize, true, null, false);
//
//        List<Items> likeKeywords = itemsMapper.getLikeKeywords(keywords);
//        List<SearchVo> result=new ArrayList<>();
//        for (Items likeKeyword : likeKeywords) {
//            SearchVo searchVo = new SearchVo();
//            Example example = new Example(ItemsSpec.class);
//            example.createCriteria().andEqualTo("itemId",likeKeyword.getId());
//            example.orderBy("priceDiscount").asc();
//            List<ItemsSpec> itemsSpecs = itemsSpecMapper.selectByExample(example);
//            searchVo.setPrice(itemsSpecs.get(0).getPriceDiscount());
////            Items items = new Items();
////            items.setId(likeKeyword.getId());
////            Items items1 = itemsMapper.selectOne(items);
//
//            searchVo.setItemName(likeKeyword.getItemName());
//            searchVo.setSellCounts(likeKeyword.getSellCounts());
//            searchVo.setItemId(likeKeyword.getId());
//            Example example1 = new Example(ItemsImg.class);
//            example1.createCriteria().andEqualTo("itemId",likeKeyword.getId());
//            example1.createCriteria().andEqualTo("isMain",1);
//            List<ItemsImg> itemsImgs = itemsImgMapper.selectByExample(example1);
//            searchVo.setImgUrl(itemsImgs.get(0).getUrl());
//
//            result.add(searchVo);
//        }
//
//        PagedGridResult gridResult=new PagedGridResult();
//        gridResult.setRows(result);
//        gridResult.setPage(page1.getPageNum());
//        gridResult.setRecords(page1.getTotal());
//        gridResult.setTotal(page1.getPages());
//        return gridResult;
//    }


    @Override
    public PagedGridResult renderSearch(String keywords,String sort, Integer page, Integer pageSize) {
        Page<Object> pageResult = PageHelper.startPage(page, pageSize, true, null, false);
        SearchItemsBo searchItemsBo = new SearchItemsBo();
        searchItemsBo.setItemName(keywords);
        searchItemsBo.setSort(sort);
        List<SearchVo> searchItems = itemsMapper.getSearchItems(searchItemsBo);
        PagedGridResult gridResult=new PagedGridResult();
        gridResult.setPage(pageResult.getPageNum());
        gridResult.setTotal(pageResult.getPages());
        gridResult.setRecords(pageResult.getTotal());
        gridResult.setRows(searchItems);
        return gridResult;
    }

    @Override
    public PagedGridResult renderItemSearch(String catId, String sort, Integer page, Integer pageSize) {
        Page<Object> pageResult = PageHelper.startPage(page, pageSize, true, null, false);
        CatItemsBo catItemsBo = new CatItemsBo();
        catItemsBo.setCatId(catId);
        catItemsBo.setSort(sort);
        List<SearchVo> searchItems = itemsMapper.getCatItems(catItemsBo);
        PagedGridResult gridResult=new PagedGridResult();
        gridResult.setPage(pageResult.getPageNum());
        gridResult.setTotal(pageResult.getPages());
        gridResult.setRecords(pageResult.getTotal());
        gridResult.setRows(searchItems);
        return gridResult;
    }

    @Override
    public List<ShopCartVo> RefreshShopCast(String itemSpecIds) {
        String[] split = itemSpecIds.split(",");
        List list=new ArrayList();
        Collections.addAll(list,split);

        return itemsMapper.queryShopCast(list);
    }
}
