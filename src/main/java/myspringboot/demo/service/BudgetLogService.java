package myspringboot.demo.service;

import myspringboot.demo.bean.budget.BudgetLog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BudgetLogService {
    void  add(BudgetLog budgetLog);

    void delete(String punid);

    void deletes(String punid);

    Page<BudgetLog> queryList(int currentPage, int pageSize);

    List<BudgetLog> queryListByunid(String unid);
}
