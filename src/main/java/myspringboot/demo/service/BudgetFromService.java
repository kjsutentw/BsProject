package myspringboot.demo.service;

import myspringboot.demo.bean.BudgetFrom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BudgetFromService {
    BudgetFrom getBudgetFromByPid(String pid);

    boolean addBudgetFrom(BudgetFrom budgetFrom);

    boolean updataBudgetFrom(BudgetFrom budgetFrom);

    Page<BudgetFrom> selectBudgetFrom(int currentPage, int pagesize);

    int getOtherFromBudgetFrom();
    

}
