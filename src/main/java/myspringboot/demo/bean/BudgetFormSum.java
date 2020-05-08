package myspringboot.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * @author wzx
 * date 2020.3.11
 */
@Data
@Entity
@Table(name = "t_budgetform_sum")
public class BudgetFormSum {


    /**
     * 预算表单unid
     */
    @Id
    @Column(name="unid",nullable = false)
    private String unid;

    private String punid;
    /**
     * 预算金额合计
     */
    private Integer sumFee;

    private Long createTime;
    /**
     * 同意者
     */
    private String consenter;
    /**
     * 创建预算者
     */
    private String userCreate;
    /**
     * 项目类型
     */
    private String projectType;

    /**
     * 表单状态
     */
    @Column(name="status",nullable = false)
    private String status;

    /**
     * 办理意见
     * json字符串格式
     * Approver:{
     *
     * },leader{
     *
     * }
     */
    private String formOption;

}
