package com.koston;

import java.util.HashMap;
import java.util.Map;

public class ApplicationUtil
{
    public static Map<String, Object> getResponseMap(int code, String message) throws Exception
    {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("message", message);

        return response;
    }
}
