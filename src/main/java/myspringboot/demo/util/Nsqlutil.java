package myspringboot.demo.util;

import com.alibaba.druid.sql.parser.Lexer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class Nsqlutil {





    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str) throws RRException {


        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符

        if(str.contains("'")||str.contains("\"")||str.contains(";")||str.contains("\\")||str.contains("#")){

        }
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");
        str = StringUtils.replace(str, "#", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop","or"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new RRException("包含非法字符"+keyword);

            }
        }

        return str;
    }




}
