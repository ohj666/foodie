package com.ou.foodie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.ou.**.mapper")
@EnableCaching
public class FoodieShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodieShopApplication.class,args);
    }
}
