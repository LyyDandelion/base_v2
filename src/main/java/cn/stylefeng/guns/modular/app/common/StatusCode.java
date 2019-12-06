package cn.stylefeng.guns.modular.app.common;

/**
 * 状态码
 */
public interface StatusCode {

    /**
     * 成功
     */
    int SUCCESS=200;


    /**
     * 常规失败
     */
    int FAIL=100;

    /**
     * 审核中
     */
    int CHECK=300;

    /**
     * 需要上传收款码
     */
    int UPLOAD=3366;

    /**
     * 未找到
     */
    int NOT_FOUND=4004;

    /**
     * 登录失效
     */
    int LOGIN_FAIL=403;

    /**
     * 用户被禁用
     */
    int USER_FALSE=5003;

    /**
     * 接口异常
     */
    int SERVER_EXCEPTION=500;


    /**
     * 请求参数为空
     */
    int PARAM_NULL=4065;

    /**
     * 越权操作
     */
    int OVERPOWER=5005;

    /**
     * 业务操作频繁
     */
    int OVEROP=4076;
    /**
     * 参数预警
     */
    int VALUE_WARN=4087;
    /**
     * 业务超时
     */
    int TIMEOUT=5000;

    /**
     * 验签失败
     */
    int VERIFY=57832;

}
