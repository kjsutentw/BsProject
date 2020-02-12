package myspringboot.demo.service;

import myspringboot.demo.bean.BudgetFromExtend;
import myspringboot.demo.bean.BudgetFromExtendResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BudgetFromExtendService {
    boolean inserFiled(BudgetFromExtend budgetFromExtend);

    void  add(BudgetFromExtend budgetFromExtend);

    BudgetFromExtendResult selectByPid(String punid);

    List<BudgetFromExtendResult> selectExtendResultAll();

    List<BudgetFromExtend> selectAll();


}
