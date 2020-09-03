package myspringboot.demo.bean.budget;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
public class BudgetFromExtendResult implements Serializable {
    private List<Result> list;
    private BudgetFrom budgetFrom;

    @Getter
    @Setter
  public static class Result {
         private String fieldName;
         private String fieldValue;
         private String Name;
    }
}
