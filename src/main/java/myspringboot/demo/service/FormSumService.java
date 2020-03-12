package myspringboot.demo.service;

import myspringboot.demo.bean.BudgetFormSum;

public interface FormSumService extends FormService{

    void insert(BudgetFormSum budgetFormSum);
}
