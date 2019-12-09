package util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2017-09-20<br>
 * <br>
 */
public class LogUtil {
    private static LogUtil logUtil;
    //private   Logger logger ;
    private static String fileLogPath;
    private LogUtil() {
        if(StringUtils.isNullOrEmpty(fileLogPath)){
            fileLogPath=LogUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            fileLogPath=fileLogPath.substring(0,fileLogPath.length()-1);
            fileLogPath=fileLogPath.substring(0,fileLogPath.lastIndexOf("/"));
            fileLogPath=fileLogPath+File.separator+"log"+File.separator;
        }else{
            fileLogPath+= File.separator+"log"+File.separator;
        }
        try {
            fileLogPath= URLDecoder.decode(fileLogPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.setProperty("log.base",fileLogPath);
        PropertyConfigurator.configure(LogUtil.class.getResourceAsStream("/log4j.properties") );

    }

    public static void main(String[] args) {
       /* String path=LogUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String path2 = LogUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        String path3 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(path);
        System.out.println("path 1 = " + path);
        System.out.println("path 2 = " + path2);
        System.out.println("path 3 = " + path3);*/
       /*for(int i=0;i<10;i++){
           new Thread(new Runnable() {
               public void run() {
                   LogUtil.error("hahah");
               }
           }).start();
       }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtil.error("hahah");
        LogUtil.info("hahah");*/


    }
    private static Logger getlog(Class clazz){
        Logger logger  =  Logger.getLogger(clazz );
        return logger;
    }

    public static Logger getLogger(Class clazz){
        if(logUtil==null){
            //System.out.println("进入null");
            synchronized(LogUtil.class){
                if (logUtil==null){
                    //System.out.println("new LogUtil");
                    logUtil=new LogUtil();
                }
            }
        }/*else{
            //System.out.println("未进入null");
        }*/
        return logUtil.getlog(clazz);
    }

    /**
     * 应对界面上线程阻塞
     * @param logger
     * @param msg
     */
    public static void info(final Logger logger, final String msg){
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info(msg);
            }
        }).start();

    }

    /**
     * 应对界面上线程阻塞
     * @param logger
     * @param msg
     */
    public static void error(final Logger logger, final String msg){
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.error(msg);
            }
        }).start();

    }

    /**
     * 应对界面上线程阻塞
     * @param logger
     * @param msg
     * @param t
     */
    public static void error(final Logger logger, final String msg, final Throwable t){
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.error(msg,t);
            }
        }).start();

    }

    public static void setFileLogPath(String fileLogPat) {
        fileLogPath = fileLogPat;
    }

}
