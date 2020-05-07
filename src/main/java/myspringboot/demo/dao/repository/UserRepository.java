package myspringboot.demo.dao.repository;

import myspringboot.demo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsernameAndUserAuthority(String username,String userType);

    User getByUsernameAndPassword(String username,String password);

    User findByUsername(String username);

}
