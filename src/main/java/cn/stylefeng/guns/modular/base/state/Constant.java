package cn.stylefeng.guns.modular.base.state;

public class Constant {

	//redis保存用户token有效时间
	public static final long SAVEUSERTIME = 24*60*60;



	public static final String SMS="SMS";

	public static final String TOKEN="TOKEN";

	//商户角色id
	public static final String MERCHANT_ROLE_ID="10";


	/**
	 * ============================== 排序参数 ==========================================
	 *
	 */
	//创建时间
	public static final String CREATE_TIME="CREATE_TIME";
	public static final String SORT="sort";


	/**
	 * ============================ 系统参数 ============================================
	 */

	//红包金额
	public static final String PACKET_MONEY="PACKET_MONEY";

	//最少提现
	public static final String MIN_WITHDRAW="MIN_WITHDRAW";

	//token 失效时间 s
	public static final String TOKEN_EXPIRE = "TOKEN_EXPIRE";


}
