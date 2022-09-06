package com.ou.foodie.service;

import com.ou.foodie.pojo.UserAddress;
import com.ou.foodie.pojo.bo.AddressBO;
import com.ou.foodie.util.JsonResult;

import java.util.List;

public interface AddressService {
    List<UserAddress> queryAll(String userId);

    void Add(AddressBO addressBO);

    void setDefault(String userId,String addressId);

    void DelAddress(String userId, String addressId);

    void Update(AddressBO addressBO);

    UserAddress selectByIdAndUseId(String Id,String UserId);
}
