package com.ou.foodie.vaildate.code;

import com.alibaba.druid.filter.AutoLoad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCode implements Serializable {
    private String code;
    private LocalDateTime expireTime;

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

    public ValidateCode(String code,int expireIn){
        this.code=code;
        this.expireTime=LocalDateTime.now().plusSeconds(expireIn);
    }
}
