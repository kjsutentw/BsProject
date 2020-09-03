package myspringboot.demo.service;

import com.alibaba.fastjson.JSONObject;
import myspringboot.demo.asm.Constants;
import myspringboot.demo.bean.budget.BudgetFormSum;
import myspringboot.demo.dao.repository.BudgetFromRepository;
import myspringboot.demo.dao.repository.FormSumRepository;
import myspringboot.demo.dao.repository.OfficeFreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Transactional
@Service
public class FormSumServiceImpl implements FormSumService {

    @Resource(name = "formSumRepository")
    FormSumRepository formSumRepository;

    @Autowired
    BudgetFromRepository budgetFromRepository;

    @Autowired
    OfficeFreeRepository officeFreeRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

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
    public boolean updataStatus(String punid, String status,String type,String option,String consenter) {

        BudgetFormSum budgetFormSum= formSumRepository.findByPunid(punid);
        budgetFormSum.setFormOption(option);
        budgetFormSum.setStatus(status);
        budgetFormSum.setConsenter(consenter);
        formSumRepository.save(budgetFormSum);
        if(Constants.FORM_PRO.equals(type)){
                budgetFromRepository.updateStatus(punid,status);
        }else if(Constants.FORM_OFFICE.equals(type)){
                officeFreeRepository.updateStatus(punid,status);
        }else {
                return false;
        }

        return true;
    }

    @Override
    public List<BudgetFormSum> selectAllBystatus(String status) {

        return formSumRepository.findAllBystatus(status);
    }

    @Override
    public boolean deleteForm(String punid) {
        return false;
    }

    @Override
    public void insert(BudgetFormSum budgetFormSum) {
        formSumRepository.save(budgetFormSum);
    }

    @Override
    public Page<BudgetFormSum> selectAllBystatus(int currentPage, int pageSize, String status) {
        PageRequest pageable = PageRequest.of(currentPage-1, pageSize);
        return  formSumRepository.selectAllBystatus(status,pageable);
    }

    @Override
    public List<JSONObject> getMothdata(String status){
        String sql="  SELECT FROM_UNIXTIME(create_time,'%Y%m') methos,count(unid) COUNT, sum(sum_fee) sum_fee FROM t_budgetform_sum  where status='"+status+"' GROUP BY methos ";

        List<JSONObject> result = jdbcTemplate.query(sql, new RowMapper<JSONObject>() {

            @Override
            public JSONObject mapRow(ResultSet rs, int i) throws SQLException {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("sum_fee",rs.getInt("sum_fee"));
                jsonObject.put("count",rs.getInt("count"));
                jsonObject.put("methos",rs.getInt("methos"));
                return jsonObject;
            }
        });

        return result;
    }


}
