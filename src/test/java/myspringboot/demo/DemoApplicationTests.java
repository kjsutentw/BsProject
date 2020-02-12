package myspringboot.demo;

import myspringboot.demo.bean.BudgetFromExtend;
import myspringboot.demo.bean.BudgetFromExtendResult;
import myspringboot.demo.service.BudgetFromExtendService;
import myspringboot.demo.service.BudgetFromService;
import myspringboot.demo.service.UserService;
import myspringboot.demo.util.Dateutil;
import myspringboot.demo.util.Nsqlutil;
import myspringboot.demo.util.RRException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    BudgetFromExtendService budgetFromExtendService;

    @Autowired
    UserService userService;

    @Test
    public void runtest2(){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("001",007);
        int dai=(int)hashMap.get("001");
        System.out.println(dai);
    }

    @Test
    public void runtest3(){
        BudgetFromExtend budgetFromExtend=new BudgetFromExtend();
        budgetFromExtend.setCreateTime(Dateutil.getnowtime());
        budgetFromExtend.setFieldLength(16);
        budgetFromExtend.setFieldName("other_free01");
        budgetFromExtend.setFieldType("varchar");
        budgetFromExtendService.inserFiled(budgetFromExtend);

    }


    @Test
    public void runtest4()  {




       //' or 1=1#
        String str="'  1=1#";
        try {
            str= Nsqlutil.sqlInject(str);
        } catch (RRException e) {
            e.printStackTrace();
        }
        System.out.println(str);
//       BudgetFromExtendResult budgetFromExtendResult= budgetFromExtendService.selectByPid(str);
//        System.out.println(budgetFromExtendResult);


    }





}
