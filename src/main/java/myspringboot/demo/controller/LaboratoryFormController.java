package myspringboot.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import myspringboot.demo.bean.LaboratoryFeeForm;
import myspringboot.demo.bean.Result;
import myspringboot.demo.bean.UserAuthority;
import myspringboot.demo.service.BudgetLogService;
import myspringboot.demo.service.LaboratoryFormService;
import myspringboot.demo.util.Dateutil;
import myspringboot.demo.util.OtherUtil;
import myspringboot.demo.util.ReturnUtil;
import myspringboot.demo.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzx
 */
@RestController
@RequestMapping("/api/budget/lbBudget")
@CrossOrigin
@Api(value = "LaboratoryForm-api", tags = {"LaboratoryForm"})
public class LaboratoryFormController {


    @Autowired
    LaboratoryFormService laboratoryFormService;

    @Autowired
    BudgetLogService budgetLogService;


    @PreAuthorize("hasAuthority('budget')")
    @ApiOperation(value = "添加实验室费用表单", notes = "添加实验室费用表单")
    @PostMapping("/addfrom")
    public Object addBudgetFrom(@ApiParam(required = true, name = "实验室表单数据", value = "预算表单数据") @RequestBody JSONObject jsonpObject){

        Result result=new Result();

        String jsonString = JSON.toJSONString(jsonpObject);
        LaboratoryFeeForm budgetFrom=JSONObject.parseObject(jsonString,LaboratoryFeeForm.class);
        if(StringUtils.isEmpty(budgetFrom.getConBuild())){
            result.setMsg("续建不能为空");
            result.setCode(400);
            return result;
        }

        budgetFrom.setId(UuidUtil.getRandomUuid());
        budgetFrom.setCreateTime(Dateutil.getTime());
        //设置成未审批状态
        budgetFrom.setStatus(UserAuthority.Not_EXAMINE_APPROVE);
        laboratoryFormService.add(budgetFrom);

        budgetLogService.add(OtherUtil.setAddLog(budgetFrom.getId(),"实验室费用预算"));


        result.setCode(200);
        return result;

    }


    @PreAuthorize("hasAuthority('budget')")
    @ApiOperation(value = "修改实验室费用表单", notes = "修改实验室费用表单")
    @PostMapping("/updatefrom")
    public Object updateBudgetFrom(@ApiParam(required = true, name = "修改实验室费用表单", value = "修改实验室费用表单") @RequestBody JSONObject jsonpObject){

        Result result=new Result();

        String jsonString = JSON.toJSONString(jsonpObject);
        LaboratoryFeeForm budgetFrom=JSONObject.parseObject(jsonString,LaboratoryFeeForm.class);
        if(StringUtils.isEmpty(budgetFrom.getId())){
            return null;
        }

        laboratoryFormService.update(budgetFrom);

        result.setCode(200);
        return result;

    }


    /**
     * 分页查询实验室费用用表
     * @return
     */
    @PreAuthorize("hasAuthority('budget')")
    @ApiOperation(value = "分页查询实验室费用用表", notes = "查询表单")
    @GetMapping("/select")
    public Object selectBudgetFrom( @ApiParam(required = true, name = "分页数", value = "分页数") @RequestParam int currentPage,
                                    @ApiParam(required = true, name = "分页大小", value = "分页大小") @RequestParam int pagesize,
                                    @ApiParam(required = true, name = "用户名", value = "用户名") @RequestParam String username
    ){

        Result result=new Result();
        JSONObject rspjson=new JSONObject();

        Page<LaboratoryFeeForm> budgetFromPage=  laboratoryFormService.selectBudgetFrom(currentPage,pagesize,username);
        List<LaboratoryFeeForm> budgetFroms=budgetFromPage.getContent();

        rspjson.put("data",budgetFroms);
        result.setCode(200);
        result.setCount(1000);
        result.setData(ReturnUtil.returnMsg(rspjson));

        return result;
    }


    @PreAuthorize("hasAuthority('budget')")
    @ApiOperation(value = "批量删除实验室费用表单", notes = "删除实验室费用表单")
    @PostMapping("/delete/form")
    public Object deleleBudgetFrom(@ApiParam(required = true, name = "删除实验室费用表单", value = "删除实验室费用表单") @RequestBody JSONObject jsonObject){

        Result result=new Result();


        String jsonString = JSON.toJSONString(jsonObject.get("ids"));

        if(StringUtils.isEmpty(jsonString)){
            return Result.error("参数不能为空");
        }

        List<String> strings= JSONObject.parseArray(jsonString,String.class);


        if(strings.size()<=20){
            laboratoryFormService.deletes(strings);
        }

        return  Result.seccess();
    }


    @PreAuthorize("hasAuthority('budget')")
    @ApiOperation(value = "查询实验室费用表单", notes = "查询实验室费用表单")
    @GetMapping("/selectByid")
    public Object selectByid(@ApiParam(required = true, name = "查询实验室费用表单", value = "查询实验室费用表单") @RequestParam String id){


        if(StringUtils.isEmpty(id)){
            return Result.error("参数不能为空");
        }

        LaboratoryFeeForm laboratoryFeeForm=laboratoryFormService.findById(id);


        return  Result.seccess(ReturnUtil.returnMsg(laboratoryFeeForm));

    }
}
