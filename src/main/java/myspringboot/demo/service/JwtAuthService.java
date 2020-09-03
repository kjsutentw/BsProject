package myspringboot.demo.service;

import myspringboot.demo.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JwtAuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Resource
    UserDetailsServiceImpl userDetailsService;

    @Resource
    JwtTokenUtil jwtTokenUtil;


    /**
     * 登录认证换取jwt令牌
     * @return
     */
    public String login(String username,String password){
        try{
            UsernamePasswordAuthenticationToken upToken=
                    new UsernamePasswordAuthenticationToken(username,password);
            Authentication authentication= authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            return null;
        }

        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);


    }


}
