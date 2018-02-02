package com.wangjinzhao.springbootdemo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapLikeType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JacksonUtil {
    private static final ObjectMapper objectMapper;

    static {

        objectMapper = new ObjectMapper();

        //序列化时候统一日期格式

        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        //设置null时候不序列化(只针对对象属性)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //反序列化时，属性不存在的兼容处理

        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        //单引号处理

        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

    }


    //将jsonStr转成相应的Map
    public static Map<String, Object> convertJson2Map(String jsonStr) {
        MapLikeType mapLikeType = JacksonUtil.objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, Object.class);
        try {
            return objectMapper.readValue(jsonStr, mapLikeType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    //将json转成相应的List
    public static List convertJson2List(String jsonStr, Class clazz) {
        JavaType javaType = JacksonUtil.objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {

        }

        return null;

    }


    //反序列化json

    public static <T> T json2Object(String json, Class<T> clazz) {

        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;

    }


    //序列化java对象

    public static <T> String object2Json(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
