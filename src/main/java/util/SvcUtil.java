package util;

import service.ServiceFactory;
import service.SvcService;
import service.impl.SvcServiceImpl;

import java.util.*;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.1.5 <br>
 * 开发人员: lyd
 * 开发时间: 2017-09-20<br>
 * <br>
 */
public class SvcUtil {
    private static SvcService svcService= ServiceFactory.getSvcService();
    private static List<String> DICTIONARIEANDCACHES =new ArrayList<>();
    /**
     * 字典，不包含字典缓存
     */
    private static List<String> DICTIONARIES = new ArrayList<>();
    private static Map<String,String> MIDSEARCH =new HashMap<>();


    public static void init() throws Exception {
        refreshCache();
    }
    public  static void refreshCache() throws Exception {
        //初始化字字典
        DICTIONARIES=svcService.getDictionies();
        DICTIONARIEANDCACHES=svcService.getDictionieAndCache();
        //辅助查询
        MIDSEARCH=svcService.getMidsearch();
    }

    public static String getSysName(){
        return "FINGARDATS";
    }
    public static Map<String,String> getUcOutViewLevel(){
        Map<String,String> map=new  LinkedHashMap<String,String>() ;
        map.put("1:列表显示","1");
        map.put("2:详细显示","2");
        map.put("0:不显示","0");
        return map;
    }
    public static Map<String,String> getUcOutViewType(){
        Map<String,String> map=new  LinkedHashMap<String,String>() ;
        map.put("S:字符串","S");
        map.put("HC:隐藏电话","HC");
        map.put("HM:隐藏邮箱","HM");
        map.put("N:字典显示","N");
        map.put("SN:选择字典显示","SN");
        map.put("D:日期","D");
        map.put("T:时间","T");
        map.put("DT:日期时间","DT");
        map.put("C:字符","C");
        map.put("I:整型","I");
        map.put("F:浮点数(F4表示4位小数)","F");
        map.put("F4:浮点数(4位小数)","F4");
        map.put("H:HTML","H");
        map.put("W:文件大小(Byte,K,M)","W");
        map.put("P:百分比显示","P");
        map.put("PK:千","PK");
        map.put("FW:万","FW");
        map.put("FM:百万","FM");
        map.put("FY:亿","FY");
        return map;
    }
    public static Map<String,String> getUcInFieldInOrOut(){
        Map<String,String> map=new  LinkedHashMap<String,String>() ;
        map.put("0:输入","0");
        map.put("1:输出","1");
        return map;
    }
    public static Map<String,String> getUcInDatabaseType(){
        Map<String,String> map=new  LinkedHashMap<String,String>() ;
        map.put("S:字符串","S");
        map.put("D:日期","D");
        map.put("DT:日期时间","DT");
        map.put("C:字符","C");
        map.put("I:整型","I");
        map.put("F:浮点数","F");
        /**
         * F4用于查询条件
         */
        map.put("F4:浮点数","F4");
        return map;
    }
    public static Map<String,String> getUcInNotNull(){
        Map<String,String> map=new  LinkedHashMap<String,String>() ;
        map.put("1:非空","1");
        map.put("D:可空","0");
        return map;
    }
    public static Map<String,String> getUcInFieldType(){
        Map<String,String> map=new  LinkedHashMap<String,String>() ;
        map.put("0:普通","0");
        map.put("1:主键","1");
        map.put("2:查询条件(另条件：>,<,=,like,exist,incsub)","2");
        map.put("3:传变量(property=var,则sql中应配成:var)","3");
        return map;
    }
    public static Map<String,String> getUcInConditionType(){
        Map<String,String> map=new  LinkedHashMap<String,String>() ;
        map.put("=","=");
        map.put(">=",">=");
        map.put("<=","<=");
        map.put("like","like");
        map.put(">",">");
        map.put("<","<");
        map.put("exists","exists");
        map.put("not exists","not exists");
        map.put("incsub","incsub");
        map.put("in","in");
        map.put("not in","not in");
        map.put("<>","<>");
        map.put("rlike","rlike");
        map.put("lrlike","lrlike");
        map.put("lrlikeor","lrlikeor");
        return map;
    }
    public static Map<String,String> getUcInViewLevel(){
        Map<String,String> map=new  LinkedHashMap<String,String>() ;
        map.put("0:高级查询","0");
        map.put("1:显示","1");
        map.put("2:不显示","2");
        return map;
    }
    public static Map<String,String> getUcInViewType(){
        Map<String,String> map=new  LinkedHashMap<String,String>() ;
        map.put("S:字符串","S");
        map.put("N:字典显示","N");
        map.put("D:日期","D");
        map.put("T:时间","T");
        map.put("DT:日期时间","DT");
        map.put("C:字符","C");
        map.put("I:整型","I");
        map.put("F:浮点数(F4表示4位小数)","F");
        map.put("F4:浮点数(4位小数)","F4");
        map.put("M:辅助查询","M");
        return map;
    }

    public static Map<String,String> getMidsearch(){
        return MIDSEARCH;
    }
    public static List<String> getDiction(){
        return DICTIONARIES;
    }
    public static List<String> getDictionAndCache(){
        return DICTIONARIEANDCACHES;
    }

}
