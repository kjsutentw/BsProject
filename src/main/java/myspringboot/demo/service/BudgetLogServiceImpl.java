package myspringboot.demo.service;

import myspringboot.demo.bean.log.BudgetLog;
import myspringboot.demo.dao.repository.BudgetLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BudgetLogServiceImpl implements BudgetLogService {

    @Autowired
    BudgetLogRepository budgetLogRepository;

    @Override
    public void add(BudgetLog budgetLog) {
        budgetLogRepository.save(budgetLog);
    }

    @Override
    public void delete(String punid) {
        budgetLogRepository.deleteBypunid(punid);
    }

    @Override
    public void deletes(String punid) {
        budgetLogRepository.deleteAllByPunid(punid);
    }

    @Override
    public List<BudgetLog> queryList() {
        return null;
    }

    @Override
    public List<BudgetLog> queryListByunid(String unid) {
        return null;
    }
}
