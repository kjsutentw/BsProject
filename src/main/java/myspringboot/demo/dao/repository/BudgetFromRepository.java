package myspringboot.demo.dao.repository;


import myspringboot.demo.bean.BudgetFrom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("BudgetFromRepository")
public interface BudgetFromRepository extends JpaRepository<BudgetFrom,Integer> {

    BudgetFrom getBudgetFromByProjectId(String pid);

    @Query(nativeQuery =true,value ="SELECT * FROM budget_from " +
            " WHERE status!=?1 ")
    Page<BudgetFrom> getBudgetFromByStatus(String status,Pageable pageable);

    @Query(nativeQuery =true,value ="SELECT * FROM budget_from " +
            " WHERE create_user=?1 ")
    Page<BudgetFrom> findAllByCreateUser(String lampName, Pageable pageable);


    @Modifying
    @Query(nativeQuery =true,value =" update budget_from set status=?2 where project_id=?1")
    Integer updateStatus(String punid, String status);
}
