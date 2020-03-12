package myspringboot.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import myspringboot.demo.bean.BudgetFormSum;
import myspringboot.demo.bean.OfficeFeeFrom;
import myspringboot.demo.bean.Result;
import myspringboot.demo.bean.UserAuthority;
import myspringboot.demo.service.FormSumService;
import myspringboot.demo.service.OfficeFeeService;
import myspringboot.demo.util.Dateutil;
import myspringboot.demo.util.ReturnUtil;
import myspringboot.demo.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/officeForm")
@CrossOrigin
public class OfficeFreeController {

    @Autowired
    OfficeFeeService officeFeeService;

    //总预算
    @Autowired
    FormSumService formSumService;

    /**
     * 分页查询我的办公费用表
     * @return
     */
    @ApiOperation(value = "分页查询办公费用预算表单", notes = "查询表单")
    @GetMapping("/select")
    public Object selectBudgetFrom( @ApiParam(required = true, name = "分页数", value = "分页数") @RequestParam int currentPage,
                                    @ApiParam(required = true, name = "分页大小", value = "分页大小") @RequestParam int pagesize,
                                    @ApiParam(required = true, name = "用户名", value = "用户名") @RequestParam String username
                                   ){

        Result result=new Result();
        JSONObject rspjson=new JSONObject();

        Page<OfficeFeeFrom> budgetFromPage=  officeFeeService.selectBudgetFrom(currentPage,pagesize,username);
        List<OfficeFeeFrom> budgetFroms=budgetFromPage.getContent();

        rspjson.put("data",budgetFroms);
        result.setCode(200);
        result.setCount(1000);
        result.setData(ReturnUtil.returnMsg(rspjson));

        return result;
    }



    @ApiOperation(value = "添加办公预算表单", notes = "添加办公预算表单")
    @PostMapping("/addfrom")
    public Object addBudgetFrom(@ApiParam(required = true, name = "预算表单数据", value = "预算表单数据") @RequestBody JSONObject jsonpObject){

        Result result=new Result();

        String jsonString = JSON.toJSONString(jsonpObject);
        OfficeFeeFrom budgetFrom=JSONObject.parseObject(jsonString,OfficeFeeFrom.class);
       if(StringUtils.isEmpty(budgetFrom.getName()))
           return null;
        budgetFrom.setCreateTime(Dateutil.getTime());
        budgetFrom.setStatus(UserAuthority.Not_EXAMINE_APPROVE);//设置成未审批状态
        officeFeeService.addBudgetFrom(budgetFrom);

        BudgetFormSum budgetFormSum=new BudgetFormSum(UuidUtil.getRandomUuid(),String.valueOf(budgetFrom.getId()),budgetFrom.getSumFee(),budgetFrom.getCreateTime(),""
                ,budgetFrom.getCreateUser(),"office",UserAuthority.Not_EXAMINE_APPROVE
                );
        formSumService.insert(budgetFormSum);
        result.setCode(200);
        return result;

    }


    @PostMapping("/queryFieldAll")
    public Object queryField(){

        Result result=new Result();

        result.setCode(200);


        return result;

    }

    @PostMapping("/update/status")
    public Object updateStatus(@RequestParam String punid,@RequestParam String status){

        Result result=new Result();

        boolean bo= officeFeeService.updataStatus(punid,status);
        if(bo){
            result.setCode(200);
            return result;
        }

        result.setCode(400);
        return result;

    }




}
