package myspringboot.demo.util;

import com.google.gson.Gson;

public class GsonUtil {
    private static final Gson gson = new Gson();

    /**
     * 将任意实体类转换成json字符串
     * @param obj 实体类
     * @return json字符串
     */
    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }
}