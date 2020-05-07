package myspringboot.demo.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import myspringboot.demo.bean.BudgetFrom;
import myspringboot.demo.bean.Result;
import myspringboot.demo.service.BudgetFromExtendService;
import myspringboot.demo.service.BudgetFromService;
import myspringboot.demo.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
@CrossOrigin
public class OrdinaryFormController {

    @Autowired
    BudgetFromService budgetFromService;

    @Autowired
    BudgetFromExtendService budgetFromExtendService;


    @PreAuthorize("hasAuthority('budget')")
    @ApiOperation(value = "分页查询预算表单", notes = "查询预算表单")
    @PostMapping("/or/select")
    public Object selectBudgetFrom(@ApiParam(required = true, name = "分页查询预算表单", value = "分页查询预算表单") @RequestBody JSONObject jsonpObject){

        Result result=new Result();
        JSONObject datajson= jsonpObject.getJSONObject("senddata");
        JSONObject rspjson=new JSONObject();
        int currentPage= datajson.getIntValue("currentPage");
        int pagesize= datajson.getIntValue("pagesize");
        String username= datajson.getString("username");


        Page<BudgetFrom> budgetFromPage=budgetFromService.selectBudgetFromOr(currentPage,pagesize,username);
        List<BudgetFrom> budgetFroms=budgetFromPage.getContent();

        rspjson.put("data",budgetFroms);
        result.setCode(200);
        result.setCount(1000);
        result.setData(ReturnUtil.returnMsg(rspjson));

        return result;
    }

}
