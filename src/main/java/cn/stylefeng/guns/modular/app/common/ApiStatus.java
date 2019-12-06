package cn.stylefeng.guns.modular.app.common;

/**
 * API返回状态码
 */
public enum ApiStatus {

    ERROR(100, "错误")
    ,OK(200,"成功")
    ,BAD_REQUEST(400, "错误请求")
    ,NOT_FOUND(404,"未找到")
    ,MSG_ERROR(2001,"验证码有误")
    ,ERROR_PAY_PWD(2002,"交易密码错误")
    ,PAY_PWD_EMPTY(2003,"交易密码为空")
    ,NOT_REAL(2004,"未实名认证")
    ,WALLET_LESS(2005,"钱包余额不足")
    ,OLD_ERROR(2006,"旧密码有误")
    ,NOT_DIRECTOR(2007,"未找到推荐人")
    ;

    private int code;

    private String msg;

    ApiStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Return the integer value of this status code.
     */
    public int code() {
        return this.code;
    }

    /**
     * Return the integer value of this status code.
     */
    public String msg() {
        return this.msg;
    }

}
