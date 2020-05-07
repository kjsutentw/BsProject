package myspringboot.demo.controller;


import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import myspringboot.demo.bean.*;
import myspringboot.demo.service.BudgetFromExtendService;
import myspringboot.demo.service.BudgetFromService;
import myspringboot.demo.util.Dateutil;
import myspringboot.demo.util.ExcelFileUtil;
import myspringboot.demo.util.OtherUtil;
import myspringboot.demo.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        //这里还要验证是否很多文字，是否是数字
        if(pid.equals("")){
            result.setCode(400);
            return result;
        }

        result.setCode(200);
        result.setData("正常返回");
        return result;
    }



    @PostMapping("/selectExtend/bypid")
    public Object getBudgetFromExtendByProjectID( @RequestBody JSONObject jsonpObject){

        Result result=new Result();
        JSONObject rspjson=new JSONObject();

        String pid= jsonpObject.getString("pid");

        //这里还要验证是否很多文字，是否是数字
        if(pid.equals("")){
            result.setCode(200);
            return result;
        }
        BudgetFromExtendResult budgetFromExtendResult= budgetFromExtendService.selectByPid(pid);

        rspjson.put("data",budgetFromExtendResult.getList());

        result.setData(ReturnUtil.returnMsg(rspjson));
        result.setCode(200);

        return result;
    }





    @ApiOperation(value = "添加预算表单", notes = "添加预算表单")
    @PostMapping("/addfrom")
    public Object addBudgetFrom(@ApiParam(required = true, name = "预算表单数据", value = "预算表单数据") @RequestBody JSONObject jsonpObject){
        Result result=new Result();

        JSONObject datajson= jsonpObject.getJSONObject("budgetfromData");
        String jsonString = JSON.toJSONString(datajson);
        BudgetFrom budgetFrom=JSONObject.parseObject(jsonString,BudgetFrom.class);
        budgetFrom.setCreateTime(Dateutil.getTime());
        budgetFrom.setStatus(UserAuthority.Not_EXAMINE_APPROVE);//设置成未审批状态
        boolean isok=budgetFromService.addBudgetFrom(budgetFrom);
        JSONArray extendDataJson= jsonpObject.getJSONArray("extenData");
        String punid=budgetFrom.getProjectId();
        String sql= OtherUtil.buildSql("budgetfrom_extend_other",extendDataJson,punid);

        boolean bo= budgetFromExtendService.addExden(sql);
        if(bo&&isok){
            result.setCode(200);
            return result;
        }


        result.setCode(400);
        result.setMsg("添加失败");

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
        result.setCount(1000);
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


    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile uploadFile,@RequestParam("username") String username, HttpServletRequest req){

        Result result=new Result();

        String realPath = "D:\\self\\myspringboot\\src\\main\\resources\\static\\upload";
        File file = new File(realPath);
        if (!file.isDirectory()){
            file.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = username+"_"+Dateutil.getnowtime()+"_"+UUID.randomUUID().toString() + oldName.substring(oldName.indexOf("."), oldName.length());
        try {
            uploadFile.transferTo(new File(file,newName));
            String path = realPath+"\\"+ newName;

            List<BudgetFrom> budgetFromList= new  ExcelFileUtil().ReadFile(path);
            budgetFromService.addAllForm(budgetFromList);
            result.setCode(200);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        result.setCode(400);
        result.setMsg("上传文件失败");

        return result;
    }


    @PostMapping("/update/status")
    public Object updateStatus(@RequestBody JSONObject jsonpObject){

        String  punid= jsonpObject.getString("punid");
        String  status= jsonpObject.getString("status");

        System.out.println(punid+status);
        Result result=new Result();

       boolean bo= budgetFromService.updataStatus(punid,status);
       if(bo){
           result.setCode(200);
           return result;
       }

        result.setCode(400);
        return result;

    }


    @PostMapping("/delete/form")
    public Object deleteForm(@RequestBody JSONObject jsonpObject){

        String  punid= jsonpObject.getString("punid");

        Result result=new Result();

        boolean bo= budgetFromService.deleteForm(punid);
        if(bo){
            result.setCode(200);
            return result;
        }

        result.setCode(400);
        return result;

    }



}
