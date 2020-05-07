package myspringboot.demo.jwt;

import myspringboot.demo.bean.User;
import myspringboot.demo.dao.repository.UserRepository;
import myspringboot.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service()
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<String> list=new ArrayList<>();
            list.add("budget");
            return new JwtUser(user.getUsername(), user.getPassword(), list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
    }

}