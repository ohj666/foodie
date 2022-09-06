package com.ou.foodie.web.controller;

import com.ou.foodie.server.AddressService;
import com.ou.foodie.server.UserService;
import com.ou.foodie.util.JsonResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/address")
@RestController
@AllArgsConstructor
public class AddressController {
    private AddressService addressService;

    @PostMapping("/list")
    public JsonResult AddressList(String userId){
        return addressService.AddressList(userId);
    }
}
