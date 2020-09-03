package myspringboot.demo.service;

import myspringboot.demo.bean.budget.BudgetLog;
import myspringboot.demo.dao.repository.BudgetLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<BudgetLog> queryList(int currentPage, int pageSize) {
        PageRequest pageable = PageRequest.of(currentPage-1, pageSize);
        return  budgetLogRepository.selectAllBystatus(pageable);
    }

    @Override
    public List<BudgetLog> queryListByunid(String unid) {
        return null;
    }
}
