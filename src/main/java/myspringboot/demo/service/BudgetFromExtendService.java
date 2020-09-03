package myspringboot.demo.service;

import myspringboot.demo.bean.budget.BudgetFromExtend;
import myspringboot.demo.bean.budget.BudgetFromExtendResult;

import java.util.List;


public interface BudgetFromExtendService {
    boolean inserFiled(BudgetFromExtend budgetFromExtend);

    void  add(BudgetFromExtend budgetFromExtend);

    BudgetFromExtendResult selectByPid(String punid);

    List<BudgetFromExtendResult> selectExtendResultAll();

    List<BudgetFromExtend> selectAll();

    //增添扩展预算表单的字段
    boolean addExden(String sql);


}
