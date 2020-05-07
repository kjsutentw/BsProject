package myspringboot.demo.bean;


import lombok.Data;
import myspringboot.demo.asm.Constants;

import java.util.List;

@Data
public class Result {

        //响应码
        private int code;

        private Object data;

        private String msg;

        private int count;

        public  Result(String msg){
                this.msg=msg;
                this.code= Constants.BAD_REQUEST;
        }

        public Result(){

        }

        public static Result seccess(){
                Result result=new Result();
                result.setCode(200);
                return result;
        }

        public static Result seccess(Object data){
                Result result=new Result();
                result.setCode(200);
                result.setData(data);
                return result;
        }

        public static Result error(String msg){
                return new Result(msg);
        }


}
