package myspringboot.demo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import myspringboot.demo.bean.Result;
import myspringboot.demo.util.Nsqlutil;
import myspringboot.demo.util.RRException;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pr")
public class TestController {

    @ApiOperation(value = "测试防sql注入", notes = "测试")
    @PostMapping("/test/nsql")
    public Object test1(@ApiParam(required = false, name = "测试参数", value = "name")String name){

        boolean bo=true;
        System.out.println(name);
        try {
            String name2=Nsqlutil.sqlInject(name);
        } catch (RRException e) {
            bo=false;
        }

        return bo;

    }

    @ApiOperation(value = "nginx转发验证码错误重现", notes = "测试")
    @PostMapping("/test/login")
    public Object test2(){

        Result result=new Result();

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        System.out.println( request.getSession().getId());
        System.out.println("post");


        return result;

    }

    @GetMapping("/test/code")
    public Object test3(){

        Result result=new Result();

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        request.getSession().setAttribute("id","123456" );
        System.out.println( request.getSession().getId());


        result.setData(3663);
        return result;

    }




}
