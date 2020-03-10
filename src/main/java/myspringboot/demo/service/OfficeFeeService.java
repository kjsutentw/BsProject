package myspringboot.demo.service;

import myspringboot.demo.bean.OfficeFeeFrom;
import org.springframework.data.domain.Page;

public interface OfficeFeeService extends FormService {
    Page<OfficeFeeFrom> selectBudgetFrom(int currentPage, int pagesize,String username);

    void  addBudgetFrom(OfficeFeeFrom budgetFrom);


}
