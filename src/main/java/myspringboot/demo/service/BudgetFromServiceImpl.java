package myspringboot.demo.service;

import myspringboot.demo.bean.BudgetFrom;
import myspringboot.demo.dao.repository.BudgetFromRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BudgetFromServiceImpl implements BudgetFromService{

    @Resource(name = "BudgetFromRepository")
    BudgetFromRepository budgetFromRepository;

    @Override
    public BudgetFrom getBudgetFromByPid(String pid) {

        return budgetFromRepository.getBudgetFromByProjectId(pid);
    }

    @Override
    public boolean addBudgetFrom(BudgetFrom budgetFrom) {

        if( this.getBudgetFromByPid(budgetFrom.getProjectId())==null){
            budgetFromRepository.save(budgetFrom);
            return true;
        }

        return false;
    }

    @Override
    public boolean updataBudgetFrom(BudgetFrom budgetFrom) {

        if(this.getBudgetFromByPid(budgetFrom.getProjectId())!=null){
            budgetFromRepository.save(budgetFrom);
            return true;
        }
        return false;
    }

    @Override
    public  Page<BudgetFrom> selectBudgetFrom(int currentPage, int pagesize) {
        PageRequest pageable = PageRequest.of(currentPage-1, pagesize, Sort.Direction.ASC, "id");
        return budgetFromRepository.findAll(pageable);
    }

    @Override
    public int getOtherFromBudgetFrom() {
        int count=new Long(budgetFromRepository.count()).intValue();
        return count;
    }


}
