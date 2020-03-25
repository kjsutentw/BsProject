package myspringboot.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import myspringboot.demo.asm.Constants;
import myspringboot.demo.bean.BudgetFormSum;
import myspringboot.demo.bean.UserAuthority;

/**
 * 公共的方法调用类
 */
public class OtherUtil {

    /**
     *
     * @param tableName  表名
     * @param extendDataJson 传入的json
     * @param punid 是否带有punid
     * @return
     */
    public static String buildSql(String tableName, JSONArray extendDataJson,String punid){


        String sql="INSERT INTO "+tableName+" (punid, ";
        String sqlvalue="   VALUES " + " ('"+punid+"', ";

        for(int i=0;i<extendDataJson.size();i++){
            JSONObject jsonObject=extendDataJson.getJSONObject(i);
            String field=jsonObject.getString("fieldName");
            String type=jsonObject.getString("fieldType");
            String  value= jsonObject.getString("value");
            Object o=null;
            boolean bo=false;
            if(type.equals("int")){
                o=Integer.parseInt(value);
                bo=true;
            }else {
                o=value;
            }

            if(i!=extendDataJson.size()-1){
                sql+=""+field+",";
                sqlvalue+=(!bo)?" '"+o+"', ":" "+o+" ";
            }else {
                sql+=""+field+") ";
                sqlvalue+=(!bo)?" '"+o+"' );":" "+o+" ); ";
            }

        }

        sql+=sqlvalue;
        System.out.println(sql);

        return sql;
    }


    /**
     * 注入到预算总表
     */
    public static BudgetFormSum setFormSum(String unid,String punid,Long creatTime,String createUser,String type,String status,int sumFee){
        BudgetFormSum budgetFormSum=new BudgetFormSum();
        budgetFormSum.setUnid(unid);
        budgetFormSum.setPunid(punid);
        budgetFormSum.setCreateTime(creatTime);
        budgetFormSum.setUserCreate(createUser);
        budgetFormSum.setSumFee(sumFee);
        budgetFormSum.setProjectType(type);
        budgetFormSum.setStatus(status);

        return budgetFormSum;

    }
}
