package myspringboot.demo.service;




import myspringboot.demo.bean.LoginUser;
import myspringboot.demo.bean.User;
import myspringboot.demo.bean.UserAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserService userService;

    @Autowired
    SysPermissionService permissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userService.getByName(username, UserAuthority.ORDINARY);
        if(user==null){
            user = userService.getByName(username, UserAuthority.APPROVER);
        }
        if (StringUtils.isEmpty(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }

        return createLoginUser(user);
    }



    public UserDetails createLoginUser(User user)
    {
        List<String> list=new ArrayList<>();
        list.add("budget");
        return new LoginUser(user.getUsername(),user.getPassword(),list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
