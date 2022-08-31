package com.ou.foodie.server;

import com.ou.foodie.pojo.bo.UserRegisterBo;

public interface UserService {
    Integer userRegister(UserRegisterBo registerBo);
}
