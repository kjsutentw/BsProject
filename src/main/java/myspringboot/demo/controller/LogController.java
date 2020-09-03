package myspringboot.demo.controller;

import com.alibaba.fastjson.JSONObject;
import myspringboot.demo.bean.Result;
import myspringboot.demo.bean.budget.BudgetLog;
import myspringboot.demo.service.BudgetLogService;
import myspringboot.demo.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/log")
@RestController
@CrossOrigin
public class LogController {


    @Autowired
    BudgetLogService budgetLogService;

    @GetMapping("/select")
    public Object getBudgetFromByProjectID(@RequestParam int currentPage, int pageSize){

        JSONObject rspjson=new JSONObject();
        Page<BudgetLog> budgetLogPage=budgetLogService.queryList(currentPage,pageSize);
        List<BudgetLog> budgetFroms=budgetLogPage.getContent();

        rspjson.put("data",budgetFroms);
        return Result.seccess(ReturnUtil.returnMsg(rspjson));
    }

}
