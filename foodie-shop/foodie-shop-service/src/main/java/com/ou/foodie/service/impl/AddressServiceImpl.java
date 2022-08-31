package com.ou.foodie.service.impl;

import com.ou.foodie.enums.YesOrNo;
import com.ou.foodie.mapper.UserAddressMapper;
import com.ou.foodie.pojo.UserAddress;
import com.ou.foodie.pojo.bo.AddressBO;
import com.ou.foodie.service.AddressService;
import com.ou.foodie.util.JsonResult;
import com.ou.foodie.util.SidUtils;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private UserAddressMapper userAddressMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAll(String userId) {
        UserAddress userAddress=new UserAddress();
        userAddress.setUserId(userId);

        return   userAddressMapper.select(userAddress);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void Add(AddressBO addressBO) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO,userAddress);
        Integer isDefault=0;
        List<UserAddress> select = this.queryAll(userAddress.getUserId());
        if(select.isEmpty()||select==null||select.size()==0){
            isDefault=1;
        }
        userAddress.setId(SidUtils.get());
        userAddress.setCreatedTime(new Date());
        userAddress.setIsDefault(isDefault);
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(userAddress);



    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void setDefault(String userId,String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setIsDefault(YesOrNo.YES.type);
        userAddress.setUserId(userId);
        List<UserAddress> select = userAddressMapper.select(userAddress);
        for (UserAddress address : select) {
            address.setIsDefault(YesOrNo.NO.type);
            userAddressMapper.updateByPrimaryKeySelective(address);
        }

        userAddress.setId(addressId);
        userAddress.setIsDefault(YesOrNo.YES.type);
        userAddressMapper.updateByPrimaryKeySelective(userAddress);




    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void DelAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setId(addressId);
        userAddressMapper.deleteByPrimaryKey(userAddress);
        UserAddress userAddress1 = new UserAddress();
        userAddress1.setUserId(userId);
        List<UserAddress> select = userAddressMapper.select(userAddress1);
        if(select.size()==1){
            userAddress1.setIsDefault(YesOrNo.YES.type);
            Example example = new Example(UserAddress.class);
            example.createCriteria().andEqualTo("userId",userId);
            userAddressMapper.updateByExampleSelective(userAddress1,example);
        }


    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void Update(AddressBO addressBO) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO,userAddress);
        userAddress.setId(addressBO.getAddressId());
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }


}
