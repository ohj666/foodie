package com.ou.foodie.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Monty
 * @date 2022/07/21 14:15
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    /**
     * 状态码
     */
    private int status;

    private Object data;

    private String msg;


    public static JsonResult isOk(Object data) {

        return new JsonResult(200, data, "success");
    }

    public static JsonResult isOk() {
        return new JsonResult(200, null, "success");
    }

    public static JsonResult error(int status, String msg) {
        return new JsonResult(status, null, msg);
    }

    public static JsonResult Parmerror(String msg,Object o) {
        return new JsonResult(400, o, msg);
    }

    public static JsonResult errorAuthorize(String msg,Object o) {
        return new JsonResult(401, o, msg);
    }
}
