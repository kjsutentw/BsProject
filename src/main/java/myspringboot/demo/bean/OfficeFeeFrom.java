package myspringboot.demo.bean;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_office")
public class OfficeFeeFrom implements Serializable {

    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer count;

    private Integer unitPrice;//单价

    private String price;

    private String department;

    private String feeExplain;//说明

    private String memo;

    private String ext;//扩展字段(json字符串形式)

    private String createUser;//创建表单的人

    private Long createTime;

    @Column(name="status")
    private String status;//表单状态
}
