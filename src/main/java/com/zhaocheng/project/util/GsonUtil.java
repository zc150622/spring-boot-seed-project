package com.zhaocheng.project.util;



import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author ZC
 */
public class GsonUtil {
    /**
     * 不用创建对象,直接使用Gson.就可以调用方法
     */
    private static Gson gson = null;

    //判断gson对象是否存在了,不存在则创建对象
    static {
        //当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,显示形式是"key":null，而直接new出来的就没有"key":null的
        gson = new GsonBuilder()
                            .setPrettyPrinting()
                            .serializeNulls()
                             // 支持Map的key为复杂对象的形式
                             .enableComplexMapKeySerialization()
                             // 不导出实体类中没有用@Expose注解的属性
                             .excludeFieldsWithoutExposeAnnotation()
                             .setDateFormat("yyyy-MM-dd HH:mm:ss")
                             //.setVersion()
                             //排除了具有private、final或static修饰符的字段
                             //.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.STATIC, Modifier.PRIVATE)
                             .create();

    }

    /**
     * 无参的私有构造方法
     */
    private GsonUtil() { }

    /**
     * 将对象转成json格式
     *
     * @param object /
     * @return String  /
     */
    public static String gsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 将json转成特定的cls的对象
     *
     * @param gsonString /
     * @param cls /
     * @return /
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            //传入json对象和对象类型,将json转成对象
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * json字符串转成list
     *
     * @param gsonString /
     * @param cls /
     * @return /
     */
    public static <T> List<T> gsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            //根据泛型返回解析指定的类型,TypeToken<List<T>>{}.getType()获取返回类型
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * json字符串转成list
     *
     * @param json /
     * @param cls /
     * @return /
     */

    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = JsonParser.parseString(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }

    /**
     * json字符串转成list中有map的
     *
     * @param gsonString /
     * @return /
     */
    public static <T> List<Map<String, T>> gsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * json字符串转成map的
     *
     * @param gsonString /
     * @return /
     */
    public static <T> Map<String, T> gsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}
