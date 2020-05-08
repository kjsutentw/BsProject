package myspringboot.demo.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import myspringboot.demo.asm.Constants;
import myspringboot.demo.bean.BudgetFormSum;
import myspringboot.demo.bean.Result;
import myspringboot.demo.bean.UserAuthority;
import myspringboot.demo.service.BudgetFromService;
import myspringboot.demo.service.BudgetLogService;
import myspringboot.demo.service.FormSumService;
import myspringboot.demo.service.OfficeFeeService;
import myspringboot.demo.util.Dateutil;
import myspringboot.demo.util.OtherUtil;
import myspringboot.demo.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/budget")
@RestController
@CrossOrigin
public class BudgetController {


    @Autowired
    FormSumService formSumService;

    @Autowired
    BudgetFromService budgetFromService;

    @Autowired
    OfficeFeeService officeFeeService;

    @Autowired
    BudgetLogService budgetLogService;

    @ApiOperation(value = "预算月图表数据", notes = "支出")
    @GetMapping("/select/moneyMoth")
    public Object getBudgetFromByProjectID(@RequestParam String status){

        Result result=new Result();
        List<JSONObject> jsonObjectList=formSumService.getMothdata(status);

        result.setData(jsonObjectList);
        result.setCode(200);

        return result;
    }

    @ApiOperation(value = "查询某状态的总预算表单", notes = "预算")
    @GetMapping("/selectAll/byStatus")
    public Object getBudgetFromByStatus(@ApiParam(required = true, name = "currentPage", value = "第几页") int currentPage,
                                        @ApiParam(required = true, name = "pageSize", value = "分页大小") int pageSize,
            @ApiParam(required = true, name = "status", value = "表单状态状态") String status){

        Result result=new Result();
        JSONObject rspJson=new JSONObject();

        Page<BudgetFormSum> budgetFromPage=  formSumService.selectAllBystatus(currentPage,pageSize,status);
        List<BudgetFormSum> budgetFroms=budgetFromPage.getContent();
        List<JSONObject> jsonObjectList=new ArrayList<>();
        for(BudgetFormSum budgetFormSum:budgetFroms){
            String str=JSONObject.toJSONString(budgetFormSum);
            JSONObject jsonObject=JSONObject.parseObject(str);

            Long time=budgetFormSum.getCreateTime();
            String times=Dateutil.timestampToString(time.intValue());
            jsonObject.put("createTime",times);
            jsonObjectList.add(jsonObject);
        }



        rspJson.put("data",jsonObjectList);
        result.setCode(200);
        result.setCount(1000);
        result.setData(ReturnUtil.returnMsg(rspJson));

        return result;
    }


    @ApiOperation(value = "根据id和类型查找单挑数据", notes = "预算")
    @GetMapping("/selectData")
    public Object getBudgetFromByStatus(@RequestParam("id") String id,
                                     @RequestParam("type") String projectType){

        Result result=new Result();
        Object obj=null;
        switch (projectType){
            case Constants.FORM_PRO:
                //关联的是专业预算表单的项目编号
                obj= budgetFromService.getBudgetFromByPid(id);
                break;
            case Constants.FORM_OFFICE:
                obj= officeFeeService.findById(id);
                break;
            case Constants.FORM_LABORATORY:
                break;
        }

        result.setCode(200);
        result.setCount(1000);
        result.setData(ReturnUtil.returnMsg(obj));

        return result;
    }


    @ApiOperation(value = "提交审批预算", notes = "预算")
    @PostMapping("/sumbitForm")
    public Object sumbitForm(@ApiParam(required = true, name = "提交审批预算", value = "提交审批预算") @RequestBody JSONObject jsonObject ){

        Result result=new Result();
        Object obj=null;

        if(jsonObject.get("id")==null||jsonObject.get("projectType")==null||jsonObject.get("option")==null||jsonObject.get("consenter")==null){
            result.setMsg("参数为空");
            result.setCode(400);
            return result;
        }

        String id=jsonObject.getString("id");
        String projectType=jsonObject.getString("projectType");
        String option=jsonObject.getString("option");
        String consenter=jsonObject.getString("consenter");
        boolean bo= formSumService.updataStatus(id, UserAuthority.GO,projectType,option,consenter);

        if(!bo){
            budgetLogService.add(OtherUtil.setApproveOK(id));
            result.setMsg("提交失败");
            result.setCode(400);
            return result;
        }

        result.setCode(200);
        return result;
    }

    @ApiOperation(value = "退回预算", notes = "预算")
    @PostMapping("/returnForm")
    public Object returnForm(@ApiParam(required = true, name = "退回预算", value = "退回预算") @RequestBody JSONObject jsonObject ){

        Result result=new Result();
        Object obj=null;

        if(jsonObject.get("id")==null||jsonObject.get("projectType")==null){
            result.setMsg("参数为空");
            result.setCode(400);
            return result;
        }

        String id=jsonObject.getString("id");
        String projectType=jsonObject.getString("projectType");
        boolean bo= formSumService.updataStatus(id, UserAuthority.RETURN,projectType,"","");

        if(!bo){
            budgetLogService.add(OtherUtil.setApproveNO(id));
            result.setMsg("退回失败");
            result.setCode(400);
            return result;
        }

        result.setCode(200);

        return result;
    }






}
