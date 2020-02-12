package myspringboot.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import myspringboot.demo.bean.BudgetFrom;
import myspringboot.demo.bean.BudgetFromExtend;
import myspringboot.demo.bean.Result;
import myspringboot.demo.service.BudgetFromExtendService;
import myspringboot.demo.service.BudgetFromService;
import myspringboot.demo.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
@CrossOrigin
@Api(value = "BudgetFromService-api", description = "预算表单接口", tags = {"BudgetFromService"})
public class BudgetFromController {


    @Autowired
    BudgetFromService budgetFromService;

    @Autowired
    BudgetFromExtendService budgetFromExtendService;


    @ApiOperation(value = "通过项目编号查找预算表单", notes = "通过项目编号查找预算表单")
    @PostMapping("/select/byid")
    public Object getBudgetFromByProjectID(@ApiParam(required = true, name = "Json格式带有pid", value = "项目编号pid") @RequestBody JSONObject jsonpObject){

        Result result=new Result();

        String pid= jsonpObject.getString("pid");
        BudgetFrom budgetFrom= budgetFromService.getBudgetFromByPid(pid);
        result.setData(ReturnUtil.returnMsg(budgetFrom));
        result.setCode(200);

        return result;
    }

    @ApiOperation(value = "添加预算表单", notes = "添加预算表单")
    @PostMapping("/addfrom")
    public Object addBudgetFrom(@ApiParam(required = true, name = "预算表单数据", value = "预算表单数据") @RequestBody JSONObject jsonpObject){
        Result result=new Result();

        JSONObject datajson= jsonpObject.getJSONObject("data");
        String jsonString = JSON.toJSONString(datajson);
        BudgetFrom budgetFrom=JSONObject.parseObject(jsonString,BudgetFrom.class);
        boolean isok=budgetFromService.addBudgetFrom(budgetFrom);
        if(isok){
            result.setCode(200);
            return result;
        }

        result.setCode(400);

        return result;
    }



    @ApiOperation(value = "修改预算表单", notes = "修改预算表单")
    @PostMapping("/updatafrom")
    public Object updataBudgetFrom(@ApiParam(required = true, name = "修改表单数据", value = "修改表单数据") @RequestBody JSONObject jsonpObject){

        Result result=new Result();
        JSONObject datajson= jsonpObject.getJSONObject("data");
        String jsonString = JSON.toJSONString(datajson);
        BudgetFrom budgetFrom=JSONObject.parseObject(jsonString,BudgetFrom.class);
        boolean isok=budgetFromService.updataBudgetFrom(budgetFrom);
        if(isok){
            result.setCode(200);
            return result;
        }

        result.setCode(400);

        return result;
    }

    @ApiOperation(value = "分页查询预算表单", notes = "查询预算表单")
    @PostMapping("/select")
    public Object selectBudgetFrom(@ApiParam(required = true, name = "分页查询预算表单", value = "分页查询预算表单") @RequestBody JSONObject jsonpObject){

        Result result=new Result();
        JSONObject datajson= jsonpObject.getJSONObject("senddata");
        JSONObject rspjson=new JSONObject();
        int currentPage= datajson.getIntValue("currentPage");
        int pagesize= datajson.getIntValue("pagesize");

        Page<BudgetFrom> budgetFromPage=  budgetFromService.selectBudgetFrom(currentPage,pagesize);
        List<BudgetFrom> budgetFroms=budgetFromPage.getContent();

        rspjson.put("data",budgetFroms);
        result.setCode(200);
        int countint= budgetFromService.getOtherFromBudgetFrom();
        result.setCount(countint);
        result.setData(ReturnUtil.returnMsg(rspjson));

        return result;
    }



    @PostMapping("/add/fiedl")
    public Object addBudgetFromField(@RequestBody JSONObject jsonpObject){

        Result result=new Result();

        JSONObject datajson= jsonpObject.getJSONObject("senddata");

        String jsonString = JSON.toJSONString(datajson);
        BudgetFromExtend budgetFromExtend=JSONObject.parseObject(jsonString,BudgetFromExtend.class);

        if(budgetFromExtend.getFieldDefault().equals("")||budgetFromExtend.getFieldDefault()==null){
            budgetFromExtend.setFieldDefault("null");
        }
        boolean addSuccess= budgetFromExtendService.inserFiled(budgetFromExtend);
        if(addSuccess){
            result.setCode(200);
            return result;
        }

        result.setCode(400);
        result.setMsg("出现错误");

        return result;
    }

    @PostMapping("/queryFieldAll")
    public Object queryField(){

        Result result=new Result();
        List<BudgetFromExtend> list= budgetFromExtendService.selectAll();

        result.setCode(200);
        result.setData(list);

        return result;

    }


}
