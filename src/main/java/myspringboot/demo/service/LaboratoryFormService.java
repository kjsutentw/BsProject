package myspringboot.demo.service;

import myspringboot.demo.bean.LaboratoryFeeForm;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LaboratoryFormService extends FormService {

    void add(LaboratoryFeeForm laboratoryFeeForm);

    void update(LaboratoryFeeForm laboratoryFeeForm);

    Page<LaboratoryFeeForm> selectBudgetFrom(int currentPage, int pagesize, String username);

    LaboratoryFeeForm findById(String id);

    void deletes(List<String> strings);
}
