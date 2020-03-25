package myspringboot.demo.dao.repository;

import myspringboot.demo.bean.OfficeFeeFrom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("OfficeFreeRepository")
public interface OfficeFreeRepository extends JpaRepository<OfficeFeeFrom,Integer> {

    @Query(nativeQuery =true,value ="SELECT * FROM t_office " +
            " WHERE create_user=?1 ")
    Page<OfficeFeeFrom> findAllByCreateUser(String lampName, Pageable pageable);

    @Modifying
    @Query(nativeQuery =true,value =" update t_office set status=?2 where id=?1")
    void updateStatus(String id, String status);

    OfficeFeeFrom findById(String id);
}
