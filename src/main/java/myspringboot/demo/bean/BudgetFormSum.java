package myspringboot.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@AllArgsConstructor
@Entity
@Table(name = "t_budgetform_sum")
public class BudgetFormSum {

    @Id
    @Column(name="unid",nullable = false)
    @GeneratedValue
    private String unid;//序号

    private String punid;//外键id

    private Integer sumFee;//经费合计

    private Long createTime;

    private String consenter;//同意者

    private String userCreate;//创建者

    private String projectType;//项目类型

    @Column(name="status",nullable = false)
    private String status;//表单状态

}
