package cn.stylefeng.guns.modular.bulletin.service;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.modular.base.state.Constant;
import cn.stylefeng.guns.modular.base.state.PromotionFactory;
import cn.stylefeng.guns.modular.base.util.HttpUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Service
public class SendSMSExtService{

	// 华信短信:  smsAccount smsPassword
	private static final String smsAccount = PromotionFactory.me().getSysConfigValueByCode(Constant.SMS_ACCOUNT);
	private static final String smsPassword = PromotionFactory.me().getSysConfigValueByCode(Constant.SMS_PASSWORD);

	
	private static final String smsAccountI18 = "";
	private static final String smsPasswordI18 = "";
    @Resource
    private GunsProperties gunsProperties;
    
    public boolean sendSms(String content, String phone,Long typeId) {
    	boolean i=false;
    	String r=organizationData(phone, content,typeId);
    	String smsUrl = null;
    	if(typeId ==1) {
    		smsUrl = "https://dx.ipyy.net/sms.aspx?action=send&userid=&"+r;
    	}else {
    		smsUrl = "https://dx.ipyy.net/I18NSms.aspx?action=send&userid=&"+r;
    	}
        try {
            URL url = new URL(smsUrl);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            String result=HttpUtil.sendPost(smsUrl,null);
            Document doc =null;
			try {
				doc = DocumentHelper.parseText(result);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			// 获取根节点
			Element rootElt = doc.getRootElement();
			// 获取根节点下的子节点的值
			String returnstatus = rootElt.elementText("returnstatus").trim();
            if(returnstatus.equals("Success")){
            	i=true;
            }else{
            	i=false;
            }
       
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }


    public static String organizationData(String phone, String content,Long typeId) {
        StringBuilder sendBuilder = new StringBuilder();
        if(typeId ==1) {
        	 sendBuilder.append("account="+smsAccount);//机构ID:用户登录名MYC001
             sendBuilder.append("&password="+smsPassword);//密码
        }else {
        	 sendBuilder.append("account="+smsAccountI18);//机构ID:用户登录名MYC001
             sendBuilder.append("&password="+smsPasswordI18);//密码
        }
        sendBuilder.append("&mobile=").append(phone);//接收手机号，多个号码间以逗号分隔且最大不超过100个号码
        try {
        	if(typeId ==1) {
        		sendBuilder.append("&content=").append(URLEncoder.encode(content, "UTF-8"));
        	}else {
        		sendBuilder.append("&content=").append(encodeHexStr(8, content));
        	}
            //发送内容,标准内容不能超过70个汉字
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendBuilder.append("&sendTime=");//发送时间
        sendBuilder.append("&extno=");
        if(typeId ==1) {
        	 sendBuilder.append("&code=8");
        }else {
        	 sendBuilder.append("&code=8");
        }
       
        return sendBuilder.toString();
    }
    
 // 字符编码成HEX
 		public static String encodeHexStr(int dataCoding, String realStr) {
 			String strhex = "";
 			try {
 				byte[] bytSource = null;
 				if (dataCoding == 15) {
 					bytSource = realStr.getBytes("GBK");
 				} else if (dataCoding == 3) {
 					bytSource = realStr.getBytes("ISO-8859-1");
 				} else if (dataCoding == 8) {
 					bytSource = realStr.getBytes("UTF-16BE");
 				} else {
 					bytSource = realStr.getBytes("ASCII");
 				}
 				strhex = bytesToHexString(bytSource);

 			} catch (Exception e) {
 				e.printStackTrace();
 			}
 			return strhex;
 		}
    
 		/** */
		/**
		 * 把字节数组转换成16进制字符串
		 * 
		 * @param bArray
		 * @return
		 */
		public static final String bytesToHexString(byte[] bArray) {
			StringBuffer sb = new StringBuffer(bArray.length);
			String sTemp;
			for (int i = 0; i < bArray.length; i++) {
				sTemp = Integer.toHexString(0xFF & bArray[i]);
				if (sTemp.length() < 2)
					sb.append("0");
				sb.append(sTemp.toUpperCase());
			}
			return sb.toString();
		}
}
