package myspringboot.demo.service;





public interface FormService {

    //增添字段
    boolean addExden(String sql);

    //获取数量
    int getOtherFromBudgetFrom();


    //改变表单状态
    boolean updataStatus(String punid,String status);

    boolean deleteForm(String punid);




}
