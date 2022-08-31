package com.ou.foodie.vaildate.sender;

import com.ou.foodie.vaildate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface SmsCodeSender {
    void send(ServletWebRequest request, ValidateCode code);
}
