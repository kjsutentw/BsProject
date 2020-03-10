package myspringboot.demo.service;

import myspringboot.demo.bean.BudgetFrom;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BudgetFromService {
    BudgetFrom getBudgetFromByPid(String pid);

    boolean addBudgetFrom(BudgetFrom budgetFrom);

    boolean updataBudgetFrom(BudgetFrom budgetFrom);

    //根据页码查询所有的
    Page<BudgetFrom> selectBudgetFrom(int currentPage, int pagesize);

    int getOtherFromBudgetFrom();

    //根据页码查询该用户自己的
    Page<BudgetFrom> selectBudgetFromOr(int currentPage, int pagesize,String username);

    //改变表单状态
    boolean updataStatus(String punid,String status);

    boolean deleteForm(String punid);


    List<BudgetFrom> uploadFile(MultipartFile file);

    boolean addAllForm(List<BudgetFrom> list);

}
