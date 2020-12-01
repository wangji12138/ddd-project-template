package cn.changemax.ddd.infrastructure.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author WangJi
 * @Description gson
 * @Date 2020/12/1 15:10
 */
public final class GsonUtils {

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    private GsonUtils() {
        throw new RuntimeException("what are you 弄啥嘞？");
    }

    public static String toJsonString(Object object) {
        return object == null ? null : GSON.toJson(object);
    }

    /**
     * 转成json
     */
    public static String beanToString(Object object) {
        if (null == object) {
            return null;
        }
        return GSON.toJson(object);
    }

    /**
     * 转成bean
     */
    public static <T> T stringToBean(String gsonString, Class<T> cls) {
        if (StringUtils.isBlank(gsonString)) {
            return null;
        }
        return GSON.fromJson(gsonString, cls);
    }

    /**
     * 转成list
     */
    public static <T> List<T> stringToList(String gsonString, Class<T> cls) {
        if (StringUtils.isBlank(gsonString)) {
            return null;
        }
        List<T> list = new ArrayList<>();
        JsonArray array = new JsonParser().parse(gsonString).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(GSON.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 转成list, 有可能造成类型擦除
     */
    public static <T> ArrayList<T> stringToList(String gsonString) {
        if (StringUtils.isBlank(gsonString)) {
            return null;
        }
        return GSON.fromJson(gsonString, new TypeToken<ArrayList<T>>() {
        }.getType());
    }

    /**
     * 转成map的
     */
    public static <T> Map<String, T> stringToMaps(String gsonString) {
        if (StringUtils.isBlank(gsonString)) {
            return null;
        }
        return GSON.fromJson(gsonString, new TypeToken<Map<String, T>>() {
        }.getType());
    }

    /**
     * gson转bean
     *
     * @param gsonString
     * @param typeToken
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String gsonString, TypeToken<T> typeToken) {
        return GSON.fromJson(gsonString, typeToken.getType());
    }

    public static Gson getGson() {
        return GSON;
    }

}
