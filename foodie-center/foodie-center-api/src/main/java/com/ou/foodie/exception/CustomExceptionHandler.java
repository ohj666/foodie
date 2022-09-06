package com.ou.foodie.exception;

import com.ou.foodie.util.JsonResult;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public JsonResult handlerMaxUploadFile(MaxUploadSizeExceededException exceededException){
        return JsonResult.error(500,"文件上传大小不能超过500k");

    }

//    @ExceptionHandler(MultipartException.class)
//    public JsonResult handlerMaxUploadFile(MultipartException exceededException){
//        return JsonResult.error(500,"文件上传大小不能超过500k");
//
//    }
}
