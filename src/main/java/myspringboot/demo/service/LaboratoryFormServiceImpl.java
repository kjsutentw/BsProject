package myspringboot.demo.service;

import myspringboot.demo.bean.LaboratoryFeeForm;
import myspringboot.demo.dao.repository.LaboratoryFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class LaboratoryFormServiceImpl implements LaboratoryFormService {


    @Resource(name="LaboratoryFormRepository")
    LaboratoryFormRepository laboratoryFormRepository;


    @Override
    public boolean addExden(String sql) {
        return false;
    }

    @Override
    public int getOtherFromBudgetFrom() {
        return 0;
    }

    @Override
    public boolean updataStatus(String punid, String status) {
        return false;
    }

    @Override
    public boolean deleteForm(String punid) {
        LaboratoryFeeForm laboratoryFeeForm=findById(punid);
        laboratoryFormRepository.delete(laboratoryFeeForm);
        return true;
    }


    @Override
    public void add(LaboratoryFeeForm laboratoryFeeForm) {
        laboratoryFormRepository.save(laboratoryFeeForm);
    }

    @Override
    public void update(LaboratoryFeeForm laboratoryFeeForm) {
        laboratoryFormRepository.save(laboratoryFeeForm);
    }

    @Override
    public Page<LaboratoryFeeForm> selectBudgetFrom(int currentPage, int pagesize, String username) {
        PageRequest pageable = PageRequest.of(currentPage-1, pagesize);
        return laboratoryFormRepository.findAllByCreateUser(username,pageable);
    }

    @Override
    public LaboratoryFeeForm findById(String id) {

        return laboratoryFormRepository.findByid(id);
    }

    @Override
    public void deletes(List<String> strings){
        for (String string:strings) {
            System.out.println(string);
            deleteForm(string);
        }

    }

}
