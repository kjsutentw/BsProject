package myspringboot.demo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import myspringboot.demo.bean.Result;
import myspringboot.demo.util.ReturnUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/budget")
@RestController
@CrossOrigin
public class BudgetController {

    @ApiOperation(value = "预算月图表数据", notes = "支出")
    @GetMapping("/select/")
    public Object getBudgetFromByProjectID(@ApiParam(required = true, name = "Json格式带有pid", value = "项目编号pid") String punid){

        Result result=new Result();


        return result;
    }

}
