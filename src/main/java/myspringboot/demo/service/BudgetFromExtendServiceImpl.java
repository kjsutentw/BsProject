package myspringboot.demo.service;

import myspringboot.demo.bean.BudgetFrom;
import myspringboot.demo.bean.BudgetFromExtend;
import myspringboot.demo.bean.BudgetFromExtendResult;
import myspringboot.demo.dao.repository.BudgetFromExtendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetFromExtendServiceImpl implements BudgetFromExtendService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BudgetFromService budgetFromService;

    @Resource(name="BudgetFromExtendRepository")
    BudgetFromExtendRepository budgetFromExtendRepository;

    /**
     * 预算表添加字段
     * @return
     */
    @Override
    public boolean inserFiled(BudgetFromExtend budgetFromExtend) {

        boolean next=false;

        String fieldName=budgetFromExtend.getFieldName();
        Integer fieldNameLength=budgetFromExtend.getFieldLength();
        String fieldType=budgetFromExtend.getFieldType();
        String defaultStr=budgetFromExtend.getFieldDefault();
        String sql="alter table budgetfrom_extend_other add COLUMN  "+fieldName+" "+fieldType+"("+fieldNameLength+") DEFAULT '"+defaultStr+"'; ";
        System.out.println(sql);

        try{
            jdbcTemplate.execute(sql);
            this.add(budgetFromExtend);
            next =true;
        }catch (Exception e){
            next= false;
        }


        return next;
    }

    @Override
    public void add(BudgetFromExtend budgetFromExtend) {
        budgetFromExtendRepository.save(budgetFromExtend);
    }



    @Override
    public  BudgetFromExtendResult selectByPid(String punid) {

        List<BudgetFromExtend> budgetFromExtendList= this.selectAll();

        String[] strings=new String[budgetFromExtendList.size()];

        String sql="select";
        for(int i=0;i<budgetFromExtendList.size();i++){
            BudgetFromExtend budgetFromExtend=budgetFromExtendList.get(i);
            String str=budgetFromExtend.getFieldName();
            strings[i]=str;
            if(i==budgetFromExtendList.size()-1){
                sql+=" "+str+"";
            }else {
                sql+=" "+str+",";
            }

        }


        sql+=" from budgetfrom_extend_other where punid='"+punid+"' ";
        System.out.println(sql);
       BudgetFromExtendResult result = jdbcTemplate.queryForObject(sql, new RowMapper<BudgetFromExtendResult>() {
            @Override
            public BudgetFromExtendResult mapRow(ResultSet rs, int i) throws SQLException {
                BudgetFromExtendResult result=new BudgetFromExtendResult();
                List<BudgetFromExtendResult.Result> listResult=new ArrayList<>();
                for(int j=0;j<strings.length;j++){
                    BudgetFromExtendResult.Result result1=new  BudgetFromExtendResult.Result();
                    System.out.println(strings[j]);
                    result1.setFieldValue(rs.getString(strings[j]));
                    result1.setFieldName(strings[j]);
                    listResult.add(result1);
                }
                result.setList(listResult);
                return result;
            }
        });

        return result;
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<BudgetFromExtendResult> selectExtendResultAll(){

        String[] pidStrs=new String[10];

        List<BudgetFromExtendResult> results=new ArrayList<>();
        for(String str:pidStrs){
            results.add(this.selectByPid(str));
        }

        return results;

    }


    @Override
    public  List<BudgetFromExtend>  selectAll(){
        List<BudgetFromExtend> list= budgetFromExtendRepository.findAll();
        return  list;
    }



}
