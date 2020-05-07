package myspringboot.demo.service;

import java.util.HashSet;
import java.util.Set;

import myspringboot.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 用户权限处理
 * 
 * @author ruoyi
 */
@Component
public class SysPermissionService
{


    /**
     * 获取角色数据权限
     * 
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(User user)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin(user))
        {
            roles.add("admin");
            roles.add("budget");
        }
        else
        {
            roles.add("budget");
        }
        return roles;
    }


}
