package myspringboot.demo.service;


import myspringboot.demo.asm.Constants;
import myspringboot.demo.bean.budget.BudgetFormSum;
import myspringboot.demo.bean.OfficeFeeFrom;
import myspringboot.demo.bean.UserAuthority;
import myspringboot.demo.dao.repository.FormSumRepository;
import myspringboot.demo.dao.repository.OfficeFreeRepository;
import myspringboot.demo.util.OtherUtil;
import myspringboot.demo.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service
public class OfficeFeeServiceImpl implements OfficeFeeService {

    @Autowired
    OfficeFreeRepository officeFreeRepository;

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
        officeFreeRepository.updateStatus(punid,status);
        return true;
    }

    @Override
    public boolean deleteForm(String punid) {
        return false;
    }




    @Override
    public Page<OfficeFeeFrom> selectBudgetFrom(int currentPage, int pagesize,String usernmae) {
        PageRequest pageable = PageRequest.of(currentPage-1, pagesize);
        return officeFreeRepository.findAllByCreateUser(usernmae,pageable);
    }

    @Override
    public void addBudgetFrom(OfficeFeeFrom budgetFrom) {
        officeFreeRepository.save(budgetFrom);
        BudgetFormSum budgetFormSum= OtherUtil.setFormSum(UuidUtil.getRandomUuid(),budgetFrom.getId(),budgetFrom.getCreateTime(),
                budgetFrom.getCreateUser(),Constants.FORM_OFFICE,UserAuthority.Not_EXAMINE_APPROVE,budgetFrom.getPrice());
        formSumRepository.save(budgetFormSum);

    }

    @Override
    public OfficeFeeFrom findById(String id) {

        return officeFreeRepository.findById(id);
    }


}
