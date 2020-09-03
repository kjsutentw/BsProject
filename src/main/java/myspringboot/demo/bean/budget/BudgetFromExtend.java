package myspringboot.demo.bean.budget;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 记录扩展字段信息
 */
@Data
@Entity
@Table(name = "budget_from_extend")
public class BudgetFromExtend implements Serializable {

   @Id
   @Column(name="id",nullable = false)
   @GeneratedValue
   private  Integer id;

   private String fieldName;
   private String Name;
   private String Value;//临时记录表单的值
   private String fieldType;
   private Integer fieldLength;
   private String fieldDefault;
   private String createTime;


}
