package myspringboot.demo.asm;

public interface BudgetConstants {
    /**
     * 预算日志提交
     */
    String COMMIT="提交预算";

    /**
     * 预算日志撤回
     */
    String WITHDRAW="撤回预算";

    /**
     * 预算日志撤回
     */
    String DELETE="删除预算";


    /**
     * 预算日志审批
     */
    String APPROVE_OK="审批预算:同意";


    String APPROVE_NO="审批预算:退回";

    /**
     * 预算日志执行排序 增加
     */
    int ADD_SORT=01;

    /**
     * 预算日志执行排序 审批
     */
    int APPROVE_SORT=02;

    /**
     * 预算日志执行排序 撤回
     */
    int WITHDRAW_SORT=03;

    /**
     * 预算日志执行排序 删除
     */
    int DELETE_SORT=04;
}
