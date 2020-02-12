package myspringboot.demo.service;

import myspringboot.demo.bean.User;
import org.springframework.data.domain.Page;


public interface UserService {

    User getByName(String username);

    User get(String username, String password);

    void add(User user);

    //如果是空的话就返回fasle，查询到话就返回true
    boolean isExist(String username);

    Page<User> selectBudgetFrom(int currentPage, int pagesize);

    int getCount();





}
