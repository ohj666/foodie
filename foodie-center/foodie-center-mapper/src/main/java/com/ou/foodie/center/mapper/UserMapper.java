package com.ou.foodie.center.mapper;


import com.ou.foodie.pojo.User;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/

public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {
    int insertSelectKey(User user);
}




