package myspringboot.demo.bean;


import lombok.Data;

import java.util.List;

@Data
public class Result {

        //响应码
        private int code;

        private Object data;

        private String msg;

        private int count;


}
