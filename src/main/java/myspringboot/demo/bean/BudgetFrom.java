package myspringboot.demo.bean;

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

    private Integer equipmentFee;
    private Integer materialCost;//材料费
    private Integer conferenceFee;
    private Integer TravelFee;
    private Integer professionalClassFee;
    private Integer pageFee;
    private Integer labourServicesFee;
    private Integer ExpertConsultationFee;
    private Integer dataFee;
    private Integer resourceBaseFee;
    private Integer otherFee;
    private Integer sumFee;//经费合计

}
