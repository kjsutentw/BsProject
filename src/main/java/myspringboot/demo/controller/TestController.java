package myspringboot.demo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import myspringboot.demo.util.Nsqlutil;
import myspringboot.demo.util.RRException;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business-app")
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
}
