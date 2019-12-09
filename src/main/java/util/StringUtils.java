package util;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类
 */
public class StringUtils {
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * 检测字符串是否为非空
     *
     * @param str 待检测的字符串
     * @return
     */
    public static boolean isNotNullAndNotEmpty(String str) {

        return !StringUtils.isNullOrEmpty(str);
    }


    /**
     * 去除空格后检测字符串是否为空
     *
     * @param str 待检测的字符串
     * @return
     */
    public static boolean isNullOrEmptyIgnoreWhitespace(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 检测字符串是否为空
     *
     * @param str 待检测的字符串
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 去除字符串头部的指定字符串
     *
     * @param str     待去除头部的字符串
     * @param trimStr 指定去除的字符串
     * @return 去除头部指定字符串后的字符串
     */
    public static String trimStart(String str, String trimStr) {
        String result = str;
        while (result.startsWith(trimStr)) {
            result = result.substring(trimStr.length(), result.length());
        }
        return result;
    }

    /**
     * 去除字符串尾部的指定字符串
     *
     * @param str     待去除尾部的字符串
     * @param trimStr 指定去除的字符串
     * @return 去除尾部指定字符串后的字符串
     */
    public static String trimEnd(String str, String trimStr) {
        String result = str;
        while (result.endsWith(trimStr)) {
            result = result.substring(0, result.length() - trimStr.length());
        }
        return result;
    }

    /**
     * 去除字符串头尾部的指定字符串
     *
     * @param str     待去除头尾部的字符串
     * @param trimStr 指定去除的字符串
     * @return 去除头尾部指定字符串后的字符串
     */
    public static String trim(String str, String trimStr) {
        return trimStart(trimEnd(str, trimStr), trimStr);
    }


    /**
     * 获取日期时间字符串
     *
     * @param date 日期
     * @return 返回格式为：yyyy-MM-dd hh:mm:ss 的字符串
     */
    public static String convertToDateTimeString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取日期字符串
     *
     * @param date 日期
     * @return 返回格式为：yyyy-MM-dd 的字符串
     */
    public static String convertToDateString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 根据格式化字符串将日期转换为对应格式的字符串
     *
     * @param date   日期
     * @param format 格式化字符串
     * @return
     */
    public static String convertToString(Date date, String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 转换成用逗号隔开的string
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T extends Collection<?>> String convertToString(T collection, boolean addSingleQuotes) {
        if (collection == null || collection.size() == 0) {

            return null;
        }
        StringBuilder sql = new StringBuilder();
        for (Object id : collection) {
            if (id == null) {
                continue;
            }
            if (addSingleQuotes) {
                sql.append("'").append(id.toString()).append("',");
            } else {
                sql.append(id.toString()).append(",");
            }
        }
        String result = sql.toString();
        result = result.substring(0, result.length() - 1);
        return result;
    }

    /**
     * @param @param  String []
     * @param @return
     * @return String 返回类型
     * @throws
     * @Title: genAbstract
     * @Description:把数组转换为一个用逗号分开的字符串 ，以便于用in+String 查询
     * @version 创建时间：2010-11-17 下午17:27:04
     */
    public static String converToString(Object[] array) {
        return converToString(array, ',');
    }

    /**
     * 将数组中各项值按指定连接符连接成新字符串
     *
     * @param array
     * @param connector
     * @return
     */
    public static String converToString(Object[] array, char connector) {
        StringBuilder str = new StringBuilder();
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {

                str.append(array[i]).append(connector);
            }
        }
        return str.substring(0, str.length() - 1);
    }

    /**
     * 将数组中各项值按指定连接符连接成新字符串
     * NOTE:此处加上的String参数没有意义，只是为了区分,用于无间隔符连接
     *
     * @param array
     * @param noUse
     * @return
     */
    public static String converToString(Object[] array, String noUse) {
        StringBuilder str = new StringBuilder();
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                str.append(array[i]);
            }
        }
        return str.substring(0, str.length());
    }

    /**
     * 获取某个字符串所在的位置
     *
     * @param str
     * @param searchStr
     * @return
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        return str.indexOf(searchStr);
    }

    /**
     * 替换字符串
     *
     * @param text
     * @param searchString
     * @param replacement
     * @return
     */
    public static String replace(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    /**
     * 替换字符串
     *
     * @param text
     * @param searchString
     * @param replacement
     * @param max
     * @return
     */
    public static String replace(String text, String searchString, String replacement, int max) {
        if (isNullOrEmpty(text) || isNullOrEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == INDEX_NOT_FOUND) {
            return text;
        }
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        StringBuilder buf = new StringBuilder(text.length() + increase);
        while (end != INDEX_NOT_FOUND) {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * 字符串最后一次存在的位置
     *
     * @param str
     * @param searchStr
     * @return
     */
    public static int lastIndexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        return str.lastIndexOf(searchStr);
    }
    
    /**
     * 给指定数字前面添加指定的符号知道一定数量
     * 
     * @param num	
     * @param toLen		填充后的总长度
     * @param inFill	使用的指定填充符
     * @return
     */
    public static String fill(int num, int toLen, String inFill) {
    	StringBuilder strValue = new StringBuilder(String.valueOf(num));
    	int strLen = String.valueOf(num).length();
    	if (strLen < toLen) {
    		while(toLen-- != strLen) {
    			strValue.insert(0, inFill);
    		}
    	}
    	return strValue.toString();
    }

    /**
     * 获取字符串字节长度
     * @param str
     * @return 返回-1时获取失败
     */
   public static int byteLength(String str)
   {
       if(StringUtils.isNullOrEmpty(str)) return 0;
       try
       {
            return str.getBytes("UTF-8").length;
       }
       catch (UnsupportedEncodingException ex)
       {
           return -1;
       }
   }

    /**
     * 判断字符串是否为整数字符串
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        if(isNullOrEmpty(str)){
            return false;
        }
        Pattern pattern = Pattern.compile("^[-+]?[0-9]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否为数字字符串
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if(isNullOrEmpty(str)){
            return false;
        }
        Pattern pattern = Pattern.compile("^[-+]?[0-9]+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if(!isNum.matches()){
            return false;
        }
        return true;
    }

    /**
     * 创建字符全部为 ch 长度为 length 的字符串
     * @param ch
     * @param length
     * @return
     */
    public static String createString(char ch,Integer length){
        if(ch=='\0'|| length == null|| length.equals(0)){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(length);
        for (Integer i = 0;i<length;i++){
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    /**
     * 时间格式字符串转换成时间
     * @param dateStr 输入时间格式字符串不正确时输出null
     * @return
     */
    public static Date dateStringConvertToDate(String dateStr){
        if(isNullOrEmptyIgnoreWhitespace(dateStr)){
            return null;
        }
        String dealStr=dateStr.trim();
        String dateFormat ="";
        String timeFormat ="";
        if(dealStr.contains("-")){
            dateFormat="yyyy-MM-dd";
        }else if(dealStr.contains("/")){
            dateFormat="yyyy/MM/dd";
        }else if(dealStr.contains(".")){
            dateFormat="yyyy.MM.dd";
        }else {
            dateFormat="yyyyMMdd";
        }
        if(dealStr.length()>10){
            timeFormat=" HH:mm:ss";
        }
        try {
            // 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
            SimpleDateFormat format = new SimpleDateFormat(dateFormat+timeFormat);
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            return format.parse(dealStr);
        }catch (ParseException e){
            return null;
        }
    }

    /**
     * 比较两个字符串是否相等。
     * 假定两个传入的字符串不会同时为null。
     * （两个null不能保证两个字符串在逻辑上也相等。）
     * @param str1 字符1
     * @param str2 字符2
     * @return str1是否与str2完全等同
     */
    public static boolean equals(String str1,String str2){
        return str1 == null ? str2.equals(str1) : str1.equals(str2);
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String firstUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    public static boolean isNull(String s) {
        return StringUtils.isBlank(s);
    }
    public static String UUID() {
        String uuid = UUID.randomUUID().toString();
        return StringUtils.replaceStringCaseInsensitive(uuid, "-", "");
    }
    public static String replaceStringCaseInsensitive(String source, String regex, String replacement) {
        StringBuffer result = new StringBuffer();
        Pattern p = Pattern.compile(regex, 2);
        Matcher m = p.matcher(source);

        while(m.find()) {
            m.appendReplacement(result, replacement);
        }

        m.appendTail(result);
        return result.toString();
    }
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
    /**
     * 获取对象的字符串值，若对象为空则返回“”
     *
     * @param obj
     * @return
     */
    public static String valueOf(Object obj) {
        if (obj == null) return "";
        return obj.toString();
    }
    /**
     * 获取对象的字符串值，若对象为空则返回“”
     *
     * @param obj
     * @return
     */
    public static String valueWithNull(Object obj) {
        if (obj == null)  return null;
        if(isNullOrEmpty(valueOf(obj))){
            return null;
        }
        return obj.toString();
    }
    public static String valueOfWithNull(Object obj) {
        if (obj == null) return "NULL";
        String str=valueOf(obj);
        if (isNullOrEmpty(str)) return "NULL";
        StringBuilder ret=new StringBuilder();
        ret.append("'").append(obj.toString()).append("'");
        return ret.toString();
    }
    /**
     *计算出现次数
     * @param str
     * @param s
     */
    public static int countString(String str,String s) {
        int count = 0;
        for(int i= 0; ; ){
            if(i>str.length()){
                break;
            }
            if(str.indexOf(s) == i){
                str = str.substring(i+1,str.length());
                count++;
                i=0;
            }else{
                i++;
            }
        }
        return count;
    }
    public static String lowerFirstChar(String str){
        if(isNotNullAndNotEmpty(str)){
            str=str.substring(0,1).toLowerCase()+str.substring(1,str.length());
        }
        return str;
    }
    public static String upperFirstChar(String str){
        if(isNotNullAndNotEmpty(str)){
            str=str.substring(0,1).toUpperCase()+str.substring(1,str.length());
        }
        return str;
    }
}
