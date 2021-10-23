package com.xyz.wizard.faraday.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 描述
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/30 15:20:55
 */
public class JsonUtils {

    private JsonUtils() {

    }

    private static final Gson GSON = new Gson();

    public static String toJsonString(Object object) {
        return GSON.toJson(object);
    }

    public static JsonObject toJsonObject(String json) {
        return GSON.fromJson(json, JsonObject.class);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        Type userListType = new TypeToken<ArrayList<T>>(){}.getType();
        return GSON.fromJson(json, userListType);
    }
}
