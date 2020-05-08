package myspringboot.demo.dao.repository;

import myspringboot.demo.bean.LaboratoryFeeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("LaboratoryFormRepository")
public interface LaboratoryFormRepository extends JpaRepository<LaboratoryFeeForm,Integer> {

    @Query(nativeQuery =true,value ="SELECT * FROM t_laboratory_form " +
            " WHERE create_user=?1 ")
    Page<LaboratoryFeeForm> findAllByCreateUser(String lampName, Pageable pageable);

    LaboratoryFeeForm findByid(String id);


}
