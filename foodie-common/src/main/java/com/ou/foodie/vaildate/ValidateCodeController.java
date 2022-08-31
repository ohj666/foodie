package com.ou.foodie.vaildate;

import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.vaildate.processor.ValidateCodeProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(ProjectConstant.REQUEST_VALIDATE_CODE)
@AllArgsConstructor
@Slf4j
public class ValidateCodeController {
    private List<ValidateCodeProcessor> validateCodeProcessors;



    private HttpServletRequest request;
    private HttpServletResponse response;

    private ProjectProperties properties;
    @GetMapping("/{type}")
    public void generatorValidateCode(@PathVariable String type) throws IOException {
        for (ValidateCodeProcessor validateCodeProcessor : validateCodeProcessors) {
            if (StringUtils.equals(validateCodeProcessor.getCodeType(), type)) {
                validateCodeProcessor.createValidateCode(new ServletWebRequest(request, response));
            }
        }
    }

}

