package myspringboot.demo.service;

import myspringboot.demo.bean.BudgetFormSum;
import myspringboot.demo.dao.repository.FormSumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FormSumServiceImpl implements FormSumService {

    @Resource(name = "formSumRepository")
    FormSumRepository formSumRepository;

    @Override
    public boolean addExden(String sql) {
        return false;
    }

    @Override
    public int getOtherFromBudgetFrom() {
        return 0;
    }

    @Override
    public boolean updataStatus(String punid, String status) {
        return false;
    }

    @Override
    public boolean deleteForm(String punid) {
        return false;
    }

    @Override
    public void insert(BudgetFormSum budgetFormSum) {
        formSumRepository.save(budgetFormSum);
    }
}
