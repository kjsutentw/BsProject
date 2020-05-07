package myspringboot.demo.dao.repository;


import myspringboot.demo.bean.log.BudgetLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("BudgetLogRepository")
public interface BudgetLogRepository extends JpaRepository<BudgetLog,Integer> {

    void deleteBypunid(String punid);

    void deleteAllByPunid(String punid);
}
