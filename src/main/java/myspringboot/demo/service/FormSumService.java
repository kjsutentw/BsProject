package myspringboot.demo.service;

import com.alibaba.fastjson.JSONObject;
import myspringboot.demo.bean.budget.BudgetFormSum;
import org.springframework.data.domain.Page;

import java.util.List;


public interface FormSumService extends FormService{

    void insert(BudgetFormSum budgetFormSum);

    /**
     * 根据status状态去分页查询预算总表
     * @param currentPage
     * @param pageSize
     * @param status
     * @return
     */
    Page<BudgetFormSum> selectAllBystatus(int currentPage, int pageSize, String status);


    boolean updataStatus(String punid, String status,String type,String option,String consenter);

    /**
     * 根据status状态去查询预算总表
     */
    List<BudgetFormSum> selectAllBystatus( String status);


    /**
     * 查询当前每月的总预算支出金额和预算数量
     * @return
     */
    List<JSONObject> getMothdata(String status);

}
