package myspringboot.demo.dao.repository;

import myspringboot.demo.bean.BudgetFromExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("BudgetFromExtendRepository")
public interface BudgetFromExtendRepository extends JpaRepository<BudgetFromExtend,Integer> {
}
