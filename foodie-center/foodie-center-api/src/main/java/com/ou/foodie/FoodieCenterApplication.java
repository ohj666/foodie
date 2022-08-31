package com.ou.foodie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ou"})
@MapperScan("com.ou.**.mapper")
@EnableScheduling
@EnableAsync
@Cacheable
@CacheEvict
public class FoodieCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodieCenterApplication.class,args);
    }
}
