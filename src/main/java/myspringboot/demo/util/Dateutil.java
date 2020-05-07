package myspringboot.demo.util;



import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        Long time= (Long) (System.currentTimeMillis() / 1000);

        return time;
    }

    /**
     * 将时间戳转换为时间格式
     * @return
     */
    public static String timestampToString(Integer time){
        //int转long时，先进行转型再进行计算，否则会是计算结束后在转型
        long temp = (long)time*1000;
        Timestamp ts = new Timestamp(temp);
        String tsStr = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //方法一
            tsStr = dateFormat.format(ts);
            System.out.println(tsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }



    public static void main(String[] args) {
        int lo=1585643913;
        System.out.println( Dateutil.timestampToString(lo));
        System.out.println( Dateutil.getTime());


    }


}
