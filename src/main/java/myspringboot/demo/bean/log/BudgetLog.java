package myspringboot.demo.bean.log;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 预算操作日志
 */
@Data
@Entity
@Table(name = "t_budgetLog")
public class BudgetLog implements Serializable {

    @Id
    private String unid;

    private String punid;

    private Integer budgetSort;

    private String Log;


}
