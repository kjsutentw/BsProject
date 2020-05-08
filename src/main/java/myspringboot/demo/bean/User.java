package myspringboot.demo.bean;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

//get,set,toString,全参数，无参的构造方法

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
//因为是做前后端分离，而前后端数据交互用的是 json 格式。 那么 User 对象就
// 会被转换为 json 数据。 而本项目使用 jpa 来做实体类的持久化，jpa 默认会使用 hibernate,
// 在 jpa 工作过程中，就会创造代理类来继承 User ，并添加 handler 和 hibernateLazyInitializer
// 这两个无须 json 化的属性，所以这里需要用 JsonIgnoreProperties 把这两个属性忽略掉。
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {

    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String createTime;
    private String userAuthority;
    private String memo;


    private String roles;

    public List<String> getroles(){
        return null;
    }



}
