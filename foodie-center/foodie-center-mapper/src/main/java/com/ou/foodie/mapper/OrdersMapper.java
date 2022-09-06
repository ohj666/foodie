package com.ou.foodie.mapper;

import com.ou.foodie.pojo.OrderStatus;
import com.ou.foodie.pojo.Orders;
import com.ou.foodie.pojo.vo.MyOrdersVo;
import com.ou.foodie.util.JsonResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
public interface OrdersMapper extends tk.mybatis.mapper.common.Mapper<Orders> {
        List<MyOrdersVo>   selectMyOrder(@Param("paramsMap")Map<String,Object> map);
        Integer getMyOrderStatusCounts(Map<String,Object> map);

        List<OrderStatus> getTrend(String userId);


}




