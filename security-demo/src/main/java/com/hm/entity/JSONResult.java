package com.hm.entity;

import org.json.JSONObject;

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