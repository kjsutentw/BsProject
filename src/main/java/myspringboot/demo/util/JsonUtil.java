package myspringboot.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class JsonUtil {

     public static  <T> T Tran(JSONObject jsonObject,String data,Class<T> classz){
         T res = null;
        JSONObject datajson= jsonObject.getJSONObject(data);
        String jsonString = JSON.toJSONString(datajson);
        res=JSONObject.parseObject(jsonString,classz);

        return res;
        }
}
