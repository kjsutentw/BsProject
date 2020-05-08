package myspringboot.demo.service;

import myspringboot.demo.bean.log.BudgetLog;

import java.util.List;

public interface BudgetLogService {
    void  add(BudgetLog budgetLog);

    void delete(String punid);

    void deletes(String punid);

    List<BudgetLog> queryList();

    List<BudgetLog> queryListByunid(String unid);
}
