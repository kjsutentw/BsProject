package myspringboot.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import myspringboot.demo.bean.Result;
import myspringboot.demo.bean.User;
import myspringboot.demo.jwt.JwtTokenUtil;
import myspringboot.demo.service.JwtAuthService;
import myspringboot.demo.service.UserService;
import myspringboot.demo.util.JsonUtil;
import myspringboot.demo.util.Nsqlutil;
import myspringboot.demo.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtAuthService jwtService;


    @ApiOperation(value = "用户登录APi", notes = "登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(@ApiParam(required = true, name = "登录用户名", value = "登录用户名")@RequestParam String username
    ,@ApiParam(required = true, name = "登录密码", value = "登录密码")@RequestParam String password,
                        @ApiParam(required = true, name = "权限", value = "用户权限类型")@RequestParam String authority
    ){

        Result result=new Result();

        if(userService.isExist(username)){
           User user1=userService.getByName(username,authority);
           if(user1==null){
               result.setCode(400);
               result.setMsg("不存在用户名");
               return result;
           }
           if(user1.getPassword().equals(password)){
               User userResult=user1;
               userResult.setId(0000);
               result.setData(userResult);
               result.setCode(200);
               result.setCount(1);
           }else {
               result.setMsg("密码效验错误");
           }


           String jwt=jwtService.login(username,password);
           if(jwt==null){
               result.setMsg("登录认证失败");
               result.setCode(400);
           }else {
               result.setCode(200);
               result.setData(jwt);
           }


           return result;

        }else {
            result.setMsg("无用户名");
        }

        result.setCode(400);

        return result;
    }




    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Object Regist(@RequestBody JSONObject jsonpObject){

        Result result=new Result();
        User user= JsonUtil.Tran(jsonpObject,"submitdata",User.class);

        if(userService.isExist(user.getUsername())){
            result.setCode(400);
            result.setMsg("已存在该用户名");
            return result;

        }
        userService.add(user);
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/selectpage",method = RequestMethod.POST)
    public Object selectPage( @RequestBody JSONObject jsonpObject){
        Result result=new Result();

        JSONObject datajson= jsonpObject.getJSONObject("senddata");
        JSONObject rspjson=new JSONObject();
        int currentPage= datajson.getIntValue("currentPage");
        int pagesize= datajson.getIntValue("pagesize");

        Page<User> userPage=  userService.selectBudgetFrom(currentPage,pagesize);
        List<User> users=userPage.getContent();

        rspjson.put("data",users);
        result.setCode(200);
        int countint=userService.getCount();
        result.setCount(countint);
        result.setData(ReturnUtil.returnMsg(rspjson));


        return result;
    }


}
