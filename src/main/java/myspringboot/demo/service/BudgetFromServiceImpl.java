package myspringboot.demo.service;

import myspringboot.demo.asm.Constants;
import myspringboot.demo.bean.budget.BudgetFormSum;
import myspringboot.demo.bean.budget.BudgetFrom;
import myspringboot.demo.bean.UserAuthority;
import myspringboot.demo.dao.repository.BudgetFromRepository;
import myspringboot.demo.dao.repository.FormSumRepository;
import myspringboot.demo.util.OtherUtil;
import myspringboot.demo.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class BudgetFromServiceImpl implements BudgetFromService{

    @Resource(name = "BudgetFromRepository")
    BudgetFromRepository budgetFromRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Resource(name = "formSumRepository")
    FormSumRepository formSumRepository;


    @Override
    public BudgetFrom getBudgetFromByPid(String pid) {

        return budgetFromRepository.getBudgetFromByProjectId(pid);
    }




    @Override
    public boolean addBudgetFrom(BudgetFrom budgetFrom) {

        if( this.getBudgetFromByPid(budgetFrom.getProjectId())==null){
            budgetFromRepository.save(budgetFrom);
            //添加到总预算执行表中
            BudgetFormSum budgetFormSum= OtherUtil.setFormSum(UuidUtil.getRandomUuid(),budgetFrom.getProjectId(),budgetFrom.getCreateTime(),
                    budgetFrom.getCreateUser(),Constants.FORM_PRO,UserAuthority.Not_EXAMINE_APPROVE,budgetFrom.getSumFee());

            formSumRepository.save(budgetFormSum);

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
        PageRequest pageable = PageRequest.of(currentPage-1, pagesize);
        return budgetFromRepository.getBudgetFromByStatus("00011",pageable);
    }

    @Override
    public int getOtherFromBudgetFrom() {
        int count=new Long(budgetFromRepository.count()).intValue();
        return count;
    }

    @Override
    public Page<BudgetFrom> selectBudgetFromOr(int currentPage, int pagesize, String username) {
        PageRequest pageable = PageRequest.of(currentPage-1, pagesize);
        return  budgetFromRepository.findAllByCreateUser(username,pageable);
    }

    @Override
    public boolean updataStatus(String punid, String status) {

        boolean bo=false;
        try {
            budgetFromRepository.updateStatus(punid,status);
            bo=true;
        }catch (Exception e){
            System.out.println(e);
        }

        return bo;
    }

    @Override
    public boolean deleteForm(String punid) {

        boolean bo=false;

        BudgetFrom budgetFrom=getBudgetFromByPid(punid);
        if(budgetFrom!=null){
            budgetFromRepository.delete(budgetFrom);
            bo=true;
        }
        return bo;
    }

    @Override
    public List<BudgetFrom> uploadFile(MultipartFile file) {

        return null;
    }

    @Override
    public boolean addAllForm(List<BudgetFrom> list) {
        boolean bo=false;
        try{
            budgetFromRepository.saveAll(list);
            bo=true;
        }catch (Exception e){
          e.printStackTrace();
        }

        return bo;
    }


}
