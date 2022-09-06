package com.ou.foodie.server.impl;

import com.ou.foodie.mapper.UserAddressMapper;
import com.ou.foodie.pojo.UserAddress;
import com.ou.foodie.server.AddressService;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private UserAddressMapper userAddressMapper;
    @Override
    public JsonResult AddressList(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        List<UserAddress> select = userAddressMapper.select(userAddress);
        return JsonResult.isOk(select);
    }
}
