package myspringboot.demo.aspect;


import myspringboot.demo.util.GsonUtil;
import myspringboot.demo.util.Nsqlutil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** 统一日志处理
 * Created by zhangshilin on 2017/9/7.
 */
@Aspect
@Component
public class WebLogAspect {

    private Logger LOG = Logger.getLogger(this.getClass());

    @Pointcut("execution(* myspringboot.demo.controller..*.*(..))")
    public void webLog(){}


    @Before("webLog()")
    public void before(JoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        for (Object arg:args){
            if (arg == null || arg instanceof HttpServletRequest || arg.getClass() == HttpServletResponse.class){
                continue;
            }
            try{
                LOG.info("接口请求数据:"+ GsonUtil.toJson(arg));
                Nsqlutil.sqlInject((String)arg);
            }catch (Exception e){
                continue;
            }

        }
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void afterReturn(Object ret) throws Throwable{
        LOG.info("接口返回数据:"+GsonUtil.toJson(ret));
    }
}
