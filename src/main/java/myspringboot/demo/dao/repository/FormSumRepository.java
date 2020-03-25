package myspringboot.demo.dao.repository;
import myspringboot.demo.bean.BudgetFormSum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("formSumRepository")
public interface FormSumRepository extends JpaRepository<BudgetFormSum,Integer> {

    @Query(nativeQuery =true,value ="SELECT * FROM t_budgetform_sum " +
            " WHERE status=?1 ")
    Page<BudgetFormSum> selectAllBystatus(String status, PageRequest pageable);


    @Modifying
    @Query(nativeQuery =true,value =" update t_budgetform_sum set status=?2 where punid=?1")
    void updateByid(String punid, String status);

    BudgetFormSum findByPunid(String punid);

    List<BudgetFormSum> findAllBystatus(String status);


}
