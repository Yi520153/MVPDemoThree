package com.lcl.mvpdemothree.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Description : Json解析工具类
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public class JsonUtils {

    private static Gson  mGson=new Gson();

    /**
     * 将对象转化为json字符串
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String serialize(T object){
        return mGson.toJson(object);
    }

    /**
     * 将json字符串转化成为对象
     * @param json
     * @param clz
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T deserialize(String json,Class<T> clz) throws JsonSyntaxException{
        return mGson.fromJson(json,clz);
    }

    /**
     * 将json对象转化为实体对象
     * @param json
     * @param clz
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T deserialize(JsonObject json,Class<T> clz) throws JsonSyntaxException{
        return mGson.fromJson(json,clz);
    }

    /**
     * 将json字符串转化为对象
     * @param json
     * @param type
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T deserialize(String json, Type type) throws JsonSyntaxException{
        return mGson.fromJson(json,type);
    }
}
