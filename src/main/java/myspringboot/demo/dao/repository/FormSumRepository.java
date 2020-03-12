package myspringboot.demo.dao.repository;

import myspringboot.demo.bean.BudgetFormSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("formSumRepository")
public interface FormSumRepository extends JpaRepository<BudgetFormSum,Integer> {
}
