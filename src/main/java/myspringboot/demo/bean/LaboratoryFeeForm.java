package myspringboot.demo.bean;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "t_laboratory_form")
public class LaboratoryFeeForm {

    @Id
    @Column(name="id",nullable = false)
    private Integer id;

    //续建
    private String conBuild;

    //年份
    private String formYear;

    //设备金额
    private String equipmentFee;


    //装修金额
    private String renovation;

    //设计费
    private String designFee;

    private String otherFee;
    //经费合计
    private Integer sumFee;

    //备注
    private String memo;
    //扩展字段(json字符串形式)
    private String ext;
    //创建表单的人
    private String createUser;

    private Long createTime;
    //表单状态
    @Column(name="status")
    private String status;




}
