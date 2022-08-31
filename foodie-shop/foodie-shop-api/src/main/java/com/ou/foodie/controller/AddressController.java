package com.ou.foodie.controller;


import com.ou.foodie.pojo.bo.AddressBO;
import com.ou.foodie.service.AddressService;
import com.ou.foodie.util.JsonResult;
import com.ou.foodie.util.MobileEmailUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {
    private AddressService addressService;

    @PostMapping("/add")
    public JsonResult Add(@RequestBody AddressBO addressBO) {
        if (checkAddress(addressBO).getStatus() == 500) {
            return checkAddress(addressBO);
        }
        addressService.Add(addressBO);
        return JsonResult.isOk();

    }

    @PostMapping("/list")
    public JsonResult AddressList(String userId) {
        if (StringUtils.isBlank(userId)) {
            return JsonResult.error(500, "用户名信息不能为空");
        }

        return JsonResult.isOk(addressService.queryAll(userId));
    }


    public JsonResult checkAddress(@RequestBody AddressBO addressBO) {
        String receiver = addressBO.getReceiver();
        String mobile = addressBO.getMobile();
        String detail = addressBO.getDetail();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();

        if (StringUtils.isBlank(receiver) || StringUtils.isBlank(detail) || StringUtils.isBlank(city) || StringUtils.isBlank(district)) {
            return JsonResult.error(500, "必填信息不能为空");
        }
        if (receiver.length() > 12) {

            return JsonResult.error(500, "用户名字太长了");
        }


        if (StringUtils.isBlank(mobile)) {

            return JsonResult.error(500, "手机号码不能为空");
        }
        if (mobile.length() != 11) {

            return JsonResult.error(500, "手机长度不为11");
        }

        if (!MobileEmailUtils.checkMobileIsOk(mobile)) {

            return JsonResult.error(500, "手机格式不正确");
        }
        return JsonResult.isOk();


    }

    @PostMapping("/setDefalut")
    public JsonResult SetDefault(String userId,String addressId){
        if(StringUtils.isBlank(userId)||StringUtils.isBlank(addressId)){
            return JsonResult.error(500,"用户未登录");
        }
        addressService.setDefault(userId,addressId);
        return JsonResult.isOk();

    }

    @PostMapping("/delete")
    public JsonResult DelAddress(String userId,String addressId){
        if(StringUtils.isBlank(userId)||StringUtils.isBlank(addressId)){
            return JsonResult.error(500,"用户未登录");
        }
        addressService.DelAddress(userId,addressId);
        return JsonResult.isOk();

    }

    @PostMapping("/update")
    public JsonResult Update(@RequestBody AddressBO addressBO){
        if(checkAddress(addressBO).getStatus()==500){
            return JsonResult.error(500,checkAddress(addressBO).getMsg());
        }
        addressService.Update(addressBO);
        return JsonResult.isOk();

    }



}
