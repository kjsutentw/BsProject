package myspringboot.demo.dao.repository;

import myspringboot.demo.bean.budget.BudgetFromExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("BudgetFromExtendRepository")
public interface BudgetFromExtendRepository extends JpaRepository<BudgetFromExtend,Integer> {

    @Query(nativeQuery =true,value ="SELECT * FROM budgetfrom_extend_other " +
            " WHERE punid=?1 ")
    Object findByPunid(String punid);
}
