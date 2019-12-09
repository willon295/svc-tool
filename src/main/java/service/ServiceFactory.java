package service;

import service.impl.GenerateCodeServiceImpl;
import service.impl.SvcServiceImpl;
import service.impl.TsvcSqlServiceImpl;
import service.impl.UcDefineServiceImpl;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-24<br>
 * <br>
 */
public class ServiceFactory {
    private static SvcService svcService;
    private static TsvcSqlService tsvcSqlService;
    private static UcDefineService ucDefineService;
    private static GenerateCodeService generateCodeService;

    public static SvcService getSvcService() {
        if(svcService==null){
            synchronized (ServiceFactory.class) {
                if(svcService==null){
                    svcService=new SvcServiceImpl();
                }
            }
        }
        return svcService;
    }
    public static TsvcSqlService getTsvcSqlService() {
        if(tsvcSqlService==null){
            synchronized (ServiceFactory.class) {
                if(tsvcSqlService==null){
                    tsvcSqlService=new TsvcSqlServiceImpl();
                }
            }
        }
        return tsvcSqlService;
    }
    public static UcDefineService getUcDefineService() {
        if(ucDefineService==null){
            synchronized (ServiceFactory.class) {
                if(ucDefineService==null){
                    ucDefineService=new UcDefineServiceImpl();
                }
            }
        }
        return ucDefineService;
    }
 public static GenerateCodeService getGenerateCodeService() {
        if(generateCodeService==null){
            synchronized (ServiceFactory.class) {
                if(generateCodeService==null){
                    generateCodeService=new GenerateCodeServiceImpl();
                }
            }
        }
        return generateCodeService;
    }


}
