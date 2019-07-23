package com.raymond.util;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 用于对字符串进行操作
 * 
 * @author Sun De Qiang
 * @version create time 2009-10-19
 */
public class StringUtil {
	
	private static final String REGEX_NUMBER_PATTERN = "(^|^-)\\d*(\\.\\d*)?$";

	/**
	 * 判断某一String对象是否为空，或为空丄1�7
	 */
	public static boolean isEmpty(Object str) {
		if (str == null || str.toString().trim().equals("")) {
			return true;
		}
		return false;
	}

	/***/
	public static String trim(String str) {
		if (str == null) {
			return "";
		} else {
			String rtn = str.replace(" ", "");
			return rtn;
		}
	}

	/**
	 * 获取子串，在页面显示时，过多内容只显示一部分，多余部分用"..."代替*
	 * 
	 * @param str
	 *            原字符串
	 * @param length
	 *            要获取子串的长度
	 * @return String 子串
	 */
	public static String getSubstring(String str, int length) {
		String rtn = "";
		if (str == null || str.toString().trim().equals("")) {
			return rtn;
		} else if (length > 0) {
			if (str.length() > length) {
				rtn = str.substring(0, length) + "*****";
			} else if (str.length() == length) {
				rtn = str.substring(0, length);
			} else {
				rtn = str.trim();
			}
			return rtn;
		}
		return rtn;

	}
	/**
	 * 验证手机格式
	 * */
	public static boolean checkMobile(String mobile){
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");  
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		//String operPass = readValue("emaysms.properties","OperPass");
		//testGetBalance();
	}
	/**
	 * 根据key 加载 properties
	 * */
	public static String getPpropertiesValueForKey(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("emaysms");
		String s = bundle.getString(key);
		return s;
	}
	/**                
	 * 提供给微信查询判断 fdsfdsfd
	 * 如果字符串第一位是“#” 结束是“#”
	 * 系统认为输入的是订单号
	 * @return boolean flag false:不是订单编号;true、是订单编号
	 * */
	public static boolean checkOrderNo(String str){
		boolean flag = false;
		if("#".equals(str.substring(0,1)) && "#".equals(str.substring(str.length()-1,str.length()))){
			flag = true;
		}
		System.out.println(flag);
		return flag ;
	}
	public static String subStringOrderNo(String str){
		String strs = str.substring(1,str.length()-1);
		return strs;
	}
	/**
	 * 判断输入字符串是否符合格弄1�7"A1B1C",忽略大小冄1�7
	 * */
	public static boolean matches(String str) {
		String patternString = "[a-z][0-9][a-z][0-9][a-z]";
		Pattern pattern = Pattern.compile(patternString,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * html内容简单替换
	 * */
	 public static String replace1(String content){
		 
		 if(!StringUtil.isEmpty(content)){
			 content = content.replaceAll("\\&", "&amp;");
			 content = content.replaceAll("\"", "&quot;");
			 content = content.replaceAll("\\<", "&lt;");
			 content = content.replaceAll("\\>", "&gt;");
			 content = content.replaceAll("\'", "&#146;");
//			 content = content.replaceAll(/\ /g,"&nbsp;");
//			 content = content.replaceAll(/\n/g,"<br>");
//			 content = content.replaceAll(/\t/g,"&nbsp;&nbsp;&nbsp;&nbsp;");			 
		 }

		 return content;
	 }
	 
	 /**
	  * 截取html指定长度的内容
	  * param：被截取的html内容
	  * length：指定长度
	  * end：多余部分替换形式 如：......
	  * */
	public static String subStringHTML(String param,int length,String end) {
		     StringBuffer result = new StringBuffer();
		     int n = 0;
		     char temp;
		     boolean isCode = false; //是不是HTML代码
		     boolean isHTML = false; //是不是HTML特殊字符,如&nbsp;
		     for (int i = 0; i < param.length(); i++) {
		       temp = param.charAt(i);
		       if (temp == '<') {
		         isCode = true;
		       }
		       else if (temp == '&') {
		         isHTML = true;
		       }
		       else if (temp == '>' && isCode) {
		         n = n - 1;
		         isCode = false;
		       }
		       else if (temp == ';' && isHTML) {
		         isHTML = false;
		       }

		       if (!isCode && !isHTML) {
		         n = n + 1;
		         //UNICODE码字符占两个字节
		         if ( (temp + "").getBytes().length > 1) {
		           n = n + 1;
		         }
		       }

		       result.append(temp);
		       if (n >= length) {
		         break;
		       }
		     }
		     if(param.length()>length){
		    	result.append(end);
		     }
		     
		     //取出截取字符串中的HTML标记
		     String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
		     //去掉不需要结素标记的HTML标记
		     temp_result = temp_result.replaceAll("</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
		                                          "");
		     //去掉成对的HTML标记
		     temp_result=temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>","$2");
		     //用正则表达式取出标记
		     Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		     Matcher m = p.matcher(temp_result);

		     List endHTML = new ArrayList();

		     while (m.find()) {
		       endHTML.add(m.group(1));
		     }
		     //补全不成对的HTML标记
		     for (int i = endHTML.size() - 1; i >= 0; i--) {
		       result.append("</");
		       result.append(endHTML.get(i));
		       result.append(">");
		     }

		     return result.toString();
		   }
	public static String resetPass(){
		int roandm = 0;
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");

		Random r = new Random();

		for (int count = 0; count < 6; count++) {
			// 生成10以内的随机整数
			roandm = Math.abs(r.nextInt(10));
			if (roandm >= 0 || roandm < str.length) {
				pwd.append("" + str[roandm]);
			}
		}
		return  pwd.toString();
	}
	/**
	 * 根据用户上传的文件判断是什么类型
	 * @param str 文件名
	 * @return 0:图片;1:视频;2:office文件
	 * */
	public static String getDocType(String str){
		String len = "";
		if(!StringUtil.isEmpty(str)){
			len = str.substring(str.lastIndexOf("."),str.length());
			if(".jpg".equals(len) || ".png".equals(len) || ".gif".equals(len) || ".jpeg".equals(len)){
				len = "0";
			}else if(".mp4".equals(len) || ".3gp".equals(len) || ".avi".equals(len) || ".flv".equals(len) || ".mpg".equals(len) || ".asx".equals(len) || ".asf".equals(len) || ".wmv".equals(len) || ".mov".equals(len)){
				len = "1";
			}
		}
		return len;
	}
	
	
	
	
	/**
	 * 利用正则表达式判断buf是否为数字，包括Integer,Float
	 * 
	 * @param buf
	 * @return
	 */
	public static boolean isNumber(final String buf) {	
		if (buf == null || buf.trim().equals("")) {
			return false;
		}
		
		return isRegexMatch(buf.trim(),REGEX_NUMBER_PATTERN);		
	} 
	
	//数组转字符串
	public static String ArrayToString(String [] arr){
		StringBuffer bf = new StringBuffer();
		for(int i = 0; i < arr.length; i++){
		 bf.append(arr[i]);
		}
		return bf.toString();
	}
	
	public static String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
		    	buf.append("0");
		   	}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}
	
	public static String byte2String(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
		    	buf.append("0");
		   	}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}
	
	private static boolean isRegexMatch(final String input, final String pattern) {
		if (input == null || pattern == null) {
			return false;
		}

		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(input);
		return m.matches();
	}
	public static boolean isMoblie(String mobile){
		Pattern p = Pattern.compile("^((1[354678][0-9])|(15[^4,\\D])|(18[0,3,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		boolean b = m.matches();
		return b;
	}
	
	public static boolean isEmail(String email) {  
	        boolean tag = true;  
	        final String pattern1 = "^([a-z0-9A-Z]+[-_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
	        final Pattern pattern = Pattern.compile(pattern1);  
	        final Matcher mat = pattern.matcher(email);  
	        if (!mat.find()) {  
	            tag = false;  
	        }  
	        return tag;  
	}  
	
}
