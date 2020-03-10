package myspringboot.demo.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * 纵表返回形式
 */
@Data
public class FreeForm {

    private List<Result> list;

    @Getter
    @Setter
    public static class Result {
        private String fieldName;
        private String fieldValue;
        private String Name;
    }

}
