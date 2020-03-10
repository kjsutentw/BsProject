package myspringboot.demo.util;

import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.Excel07SaxReader;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import myspringboot.demo.bean.BudgetFrom;

import java.util.ArrayList;
import java.util.List;

public class ExcelFileUtil {

    private static List<List<Object>> lineList = new ArrayList<>();

    private RowHandler createRowHandler() {

        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, int rowIndex, List<Object> rowlist) {
//                Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowlist);打印显示
                lineList.add(rowlist);
            }
        };
    }

    public  List<BudgetFrom> ReadFile(String path){
        //读取Excel文件
        Excel07SaxReader Reader=ExcelUtil.read07BySax(path, 0, createRowHandler());

        List<BudgetFrom> budgetFromList=new ArrayList<>();
        //要转换的Bean的属性有
        String[] jsonStr=new String[]{"projectType", "department", "projectLeader","projectName","projectId","equipmentFee","materialCost",
                "conferenceFee","TravelFee","professionalClassFee","pageFee","labourServicesFee","ExpertConsultationFee",
                "dataFee","resourceBaseFee","otherFee","sumFee"};


        for(int i=0;i<lineList.size();i++){
            List<Object> list=lineList.get(i);
            JSONObject jsonObject=new JSONObject();


            //标题和第一个元素为空的不读取
            if(i<2||list.get(0)==null)
                continue;


            for(int j=0;j<jsonStr.length;j++){
                jsonObject.put(jsonStr[j],list.get(j));
            }

            jsonObject.put("createUser","wzx");
            jsonObject.put("createTime",Dateutil.getTime());
            jsonObject.put("status","00013");

            String jsonString = JSON.toJSONString(jsonObject);
            BudgetFrom budgetFrom=JSONObject.parseObject(jsonString,BudgetFrom.class);
            budgetFromList.add(budgetFrom);
        }


        lineList.clear();

        return budgetFromList;

    }

}
