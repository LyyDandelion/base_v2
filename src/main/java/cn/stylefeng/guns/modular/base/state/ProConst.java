package cn.stylefeng.guns.modular.base.state;

/***
 * 业务常量
 */
public class ProConst {

    /**
     * 提现类型
     */
    public enum WithdrawStatusEnum {
        CHECK("CHECK", "审核中"), PASS("PASS", "审核通过"), REJECT("REJECT", "拒绝");
        private String code;
        private String value;

        WithdrawStatusEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 用户类型
     */
    public enum MemberType {
        TEMP(0, "临时用户"), NORMAL(1, "会员"), MINE(2, "矿主"), BIG_MINE(3, "大矿主"), SUPER_MINE(4, "超级矿主");
        private int code;
        private String value;

        MemberType(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String value() {
            return value;
        }

        public String code() {
            return code + "";
        }
    }

    /**
     * 流水类型
     */

    public enum CashFlowTypeEnum {
        REG("REG", "注册奖励"), TASK("TASK", "任务");
        private String code;
        private String value;

        CashFlowTypeEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }


    /**
     * 资金流向
     */
    public enum CashFlowOpEnum {
        FLOW_IN(1L, "流入"),
        FLOW_OUT(0L, "流出");
        private Long code;
        private String value;

        CashFlowOpEnum(Long code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public Long getCode() {
            return code;
        }
    }

    /**
     * 支付
     */
    public enum PayEnum {
        PAID("PAID", "已支付"),
        WAIT("WAIT", "待支付"),
        UNPAID("UNPAID", "未支付"),
        NO_NEED("NO_NEED", "无需支付");
        private String code;
        private String value;

        PayEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }


    /**
     * 网关状态
     */
    public enum GatewayStatusEnum {
        OPEN("OPEN", "开放"),
        OPEN_LOG("OPEN_LOG", "开放且日志"),
        CLOSE("CLOSE", "关闭");
        private String code;
        private String value;

        GatewayStatusEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }


}
