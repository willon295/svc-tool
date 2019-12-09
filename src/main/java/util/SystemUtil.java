package util;

import bean.SystemData;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-31<br>
 * <br>
 */
public class SystemUtil {
   static Map<String,String> fileMap=new HashMap<String,String>();
    static {
        fileMap.put("01","01.系统设置(basicdata)");
        fileMap.put("02","02.系统工具");
        fileMap.put("03","03.银行账户(bankaccount)");
        fileMap.put("04","04.现金流管理");
        fileMap.put("05","05.资金计划");
        fileMap.put("06","06.资金结算(settlement)");
        fileMap.put("07","07.支票管理");
        fileMap.put("08","08.多银行资金池");
        fileMap.put("09","09.融资管理");
        fileMap.put("10","10.投资管理");
        fileMap.put("11","11.或有负债管理");
        fileMap.put("12","12.报表");
        fileMap.put("13","13.财务系统对接");
        fileMap.put("14","14.今日首页");
        fileMap.put("15","15.我的工作台");
        fileMap.put("16","16.外部系统对接");
        fileMap.put("18","18.webservice接口配置");
        fileMap.put("19","19.企业系统对接");
        fileMap.put("20","20.第三方结算");
        fileMap.put("99","99.公共");
    }

    public static String getSvcDirectory(){
        String fileLogPath=SystemUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if(fileLogPath.indexOf("/")==0){
            fileLogPath=fileLogPath.substring(1,fileLogPath.length());
        }
        fileLogPath=fileLogPath.substring(0,fileLogPath.length()-1);
        fileLogPath=fileLogPath.substring(0,fileLogPath.lastIndexOf("/")+1);
        fileLogPath=fileLogPath+ "uc"+"/";
        return fileLogPath;
    }
    public static String getDirectory(){
        String fileLogPath=SystemUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if(fileLogPath.indexOf("/")==0){
            fileLogPath=fileLogPath.substring(1,fileLogPath.length());
        }
        fileLogPath=fileLogPath.substring(0,fileLogPath.length()-1);
        fileLogPath=fileLogPath.substring(0,fileLogPath.lastIndexOf("/")+1);
        return fileLogPath;
    }
    public static String getDbDirectory(){
        return getDirectory()+"db"+"/";
    }
    public static String getSvcDirectory(String fileName){
        String fileLogPath= SystemData.getDefaultdir();
        if(StringUtils.isNotNullAndNotEmpty(fileLogPath)){
            if(SystemData.isIsusecode()){
                String code=fileName;
                if(fileName.length()>3){
                     code=fileName.substring(1,3);
                }
                String codedir=fileMap.get(code);
                if(StringUtils.isNotNullAndNotEmpty(codedir)){
                    fileLogPath+=codedir+"/";
                }

            }
        }else{
            fileLogPath=getSvcDirectory();
        }
        return fileLogPath;
    }
}
