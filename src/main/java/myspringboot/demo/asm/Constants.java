package myspringboot.demo.asm;

/** 系统常量定义
 * @author  by wzx
 * date by
 */
public interface Constants {

    /**
     *  请求成功
     */
    int OK = 200;
    //请求参数有误
    int BAD_REQUEST = 400;
    //没有认证信息
    int UNAUTHORIZED = 401;
    //请求资源不允许访问
    int FORBIDDEN = 403;
    //请求资源不存在
    int NOT_FOUND = 404;
    //系统内部错误
    int INTERNAL_SERVER_ERROR = 500;

    /**
     * 预算类型 专业预算
     */
     String FORM_PRO="专业建设预算";

    /**
     * 预算类型 办公预算
     */
    String FORM_OFFICE="办公费用";

    /**
     * 预算类型 其他预算
     */

    /**
     * 预算类型 实验室预算
     */
    String FORM_LABORATORY="实验室预算";




}
