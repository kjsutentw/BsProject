package myspringboot.demo.bean.budget;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "budget_from")
public class BudgetFrom implements Serializable {

    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue
    private Integer id;//序号

    private String projectType;
    private String department;
    private String projectLeader;
    private String projectName;


    @Column(name="project_id",nullable = false)
    private String projectId;

    private Integer equipmentFee;//设备费
    private Integer materialCost;//材料费
    private Integer conferenceFee;//会议费
    private Integer TravelFee;//差旅费
    private Integer professionalClassFee;//专业课题建设费
    private Integer pageFee;
    private Integer labourServicesFee;//劳务费
    private Integer ExpertConsultationFee;//专家咨询费
    private Integer dataFee;
    private Integer resourceBaseFee;
    private Integer otherFee;
    private Integer sumFee;//经费合计
    private String createUser;//创建表单的人
    private Long createTime;
    @Column(name="status",nullable = false)
    private String status;//表单状态

}
