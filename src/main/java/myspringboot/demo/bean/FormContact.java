package myspringboot.demo.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 预算表设置
 */
@Table(name = "t_budget_contact")
public class FormContact {

    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue
    private Integer id;//序号

    private String office;

    private String professional;

    private String other;

    private String baseProject;

    //是否开启专业建设预算的扩展字段
    private String isProfessionalEx;

    //最后一次修改时间
    private Long time;



}
