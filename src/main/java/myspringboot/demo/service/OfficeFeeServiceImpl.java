package myspringboot.demo.service;


import myspringboot.demo.bean.OfficeFeeFrom;
import myspringboot.demo.dao.repository.OfficeFreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OfficeFeeServiceImpl implements OfficeFeeService {

    @Autowired
    OfficeFreeRepository officeFreeRepository;

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

    }
}
