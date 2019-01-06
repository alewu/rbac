package com.ale.common.response;

import com.alibaba.fastjson.JSONObject;

/**
 * @author alewu
 * @date 2018/6/4 20:59
 */
public class JSONResult {
    public static String fillResultString(Integer status, String message, Object result) {
        JSONObject jsonObject = new JSONObject() {{
            put("status", status);
            put("message", message);
            put("result", result);
        }};

        return jsonObject.toString();
    }
}
