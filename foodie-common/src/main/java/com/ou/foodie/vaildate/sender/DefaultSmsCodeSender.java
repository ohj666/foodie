package com.ou.foodie.vaildate.sender;

import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.vaildate.code.ValidateCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.ServletWebRequest;


@Data
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender{
    private ProjectProperties properties;

    @Override
    public void send(ServletWebRequest request, ValidateCode code) {
        String mobile = request.getParameter(ProjectConstant.MOBILE_AUTHENTICATION_FORM_USERNAME);
        if(StringUtils.isBlank(mobile)){
            throw new UsernameNotFoundException("请求中未找到手机号，请重新登录");
        }

        log.warn("默认的像发送流程");

        log.info("正在向手机号{}发送验证码{}",mobile,code.getCode());
    }
}
