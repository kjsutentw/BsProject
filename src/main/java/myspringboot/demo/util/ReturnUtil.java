package myspringboot.demo.util;


import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dell on 2017/9/28.
 */
public class ReturnUtil {
    final static String successCode = "0";
    final static String failureCode = "1";
    final static String successMsg = "成功";

    final static Map<String,String> resultMap = new HashMap<String,String>();

    public static JSONObject returnMsg(Object obj){
        JSONObject returnJson = new JSONObject();
        JSONObject   json =  JSONObject.fromObject(obj);
        String result = "";
        System.out.println("业务处理结果==="+json.toString());
        try {
            result=SecretKeyUtil.encrypt(json.toString(),"20200129!#xm@837");
        } catch (Exception e) {
            e.printStackTrace();
        }
      returnJson.put("data", result);

        return returnJson;
    }

    public static JSONObject returnMsg(String returnMsg){
        JSONObject returnJson = new JSONObject();
        JSONObject jsonObj = new JSONObject();

        try {
            returnJson.put("data",SecretKeyUtil.encrypt(jsonObj.toString(),"20200129!#xm@837"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnJson.put("result", failureCode);
        returnJson.put("msg", null == returnMsg? "请求失败" : returnMsg);
        System.out.println("请求结果==="+returnJson.toString());
        return returnJson;
    }



    public static JSONObject returnMsg(){
        JSONObject returnJson = new JSONObject();
        returnJson.put("result", successCode);
        returnJson.put("msg", successMsg);

        AESTool asetool = new AESTool();
        String resultJson = "";
        try {
            resultJson = SecretKeyUtil.encrypt("", "20200129!#xm@837");
        } catch (Exception e) {
            e.printStackTrace();
        }
        returnJson.put("data", resultJson);
        return returnJson;
    }
}
