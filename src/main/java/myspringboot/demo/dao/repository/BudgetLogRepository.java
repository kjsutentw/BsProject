package myspringboot.demo.dao.repository;



import myspringboot.demo.bean.budget.BudgetLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("BudgetLogRepository")
public interface BudgetLogRepository extends JpaRepository<BudgetLog,Integer> {

    void deleteBypunid(String punid);

    void deleteAllByPunid(String punid);

    @Query(nativeQuery =true,value ="SELECT * FROM t_budget_log")
    Page<BudgetLog> selectAllBystatus(PageRequest pageable);
}
