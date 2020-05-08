package myspringboot.demo.service;

import myspringboot.demo.bean.User;
import myspringboot.demo.dao.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "userRepository")
    UserRepository userRepository;

    @Override
    public User getByName(String username,String type) {
        return userRepository.findByUsernameAndUserAuthority(username,type);
    }

    @Override
    public User get(String username, String password) {
        return userRepository.getByUsernameAndPassword(username,password);
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isExist(String username) {
        User user=userRepository.findByUsername(username);
        if(user==null){
            return false;
        }

        return true;
    }

    @Override
    public Page<User> selectBudgetFrom(int currentPage, int pagesize) {
        PageRequest pageable = PageRequest.of(currentPage-1, pagesize, Sort.Direction.ASC, "id");
        return userRepository.findAll(pageable);

    }

    @Override
    public int getCount() {
        int count=new Long(userRepository.count()).intValue();
        return count;
    }


}
