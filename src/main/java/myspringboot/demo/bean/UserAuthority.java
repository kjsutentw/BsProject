package myspringboot.demo.bean;

/**
 * 用户权限等级
 */
public class UserAuthority {
    public static String ORDINARY="ordinary";
    public static String LEADER="leader";
    public static String Root="root";
    public static String APPROVER="Approver";

    //表单状态

    //草稿状态
    public static String draft="00011";
    //未审批
    public static String Not_EXAMINE_APPROVE="00013";
    //已审批
    public static String EXAMINE_APPROVE="00019";
    //执行
    public static String GO="00255";
    //退回
    public static String RETURN="00963";
    //已完成
    public static String COMPLETED="00655";




}
