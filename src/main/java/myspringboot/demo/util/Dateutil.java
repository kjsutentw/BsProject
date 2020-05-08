package myspringboot.demo.util;

import java.util.Calendar;
import java.util.Date;

public class Dateutil {

    /**
     * 感觉这个可以再优化一点   可以根据传的参数来获取输出字符串数
     * @return
     */
    public static String getnowtime(){

        Calendar c1=Calendar.getInstance();
        int year=c1.get(Calendar.YEAR);
        int month=c1.get(Calendar.MONTH);//0代表1月，最大为11月
        int day1=c1.get(Calendar.DATE);
        int hour=c1.get(Calendar.HOUR);
        int min=c1.get(Calendar.MINUTE);
        int sec=c1.get(Calendar.SECOND);

        String str=year+"年"+month+"月"+day1+"日"+""+hour+":"+min+":"+sec;
        return  str;
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static  Long getTime(){
        Long time= System.currentTimeMillis()*10000;;

        return time;
    }


}
