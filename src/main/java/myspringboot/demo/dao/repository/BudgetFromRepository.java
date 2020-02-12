package myspringboot.demo.dao.repository;


import myspringboot.demo.bean.BudgetFrom;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("BudgetFromRepository")
public interface BudgetFromRepository extends JpaRepository<BudgetFrom,Integer> {

    BudgetFrom getBudgetFromByProjectId(String pid);

}
