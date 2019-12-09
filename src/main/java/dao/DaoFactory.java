package dao;

import bean.TsvcInterface;
import dao.impl.*;
import service.SvcService;
import service.TsvcSqlService;
import service.UcDefineService;
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
public class DaoFactory {
    private static HsiRightDao hsiRightDao;
    private static SvcDao svcDao;
    private static TsvcInterfaceDao tsvcInterfaceDao;
    private static TsvcSqlDao tsvcSqlDao;
    private static TsvcViewconfigDao tsvcViewconfigDao;
    private static GenCodeDao genCodeDao;


    public static HsiRightDao getHsiRightDao() {
        if(hsiRightDao==null){
            synchronized (DaoFactory.class) {
                if(hsiRightDao==null){
                    hsiRightDao=new HsiRightDaoImpl();
                }
            }
        }
        return hsiRightDao;
    }
    public static SvcDao getSvcDao() {
        if(svcDao==null){
            synchronized (DaoFactory.class) {
                if(svcDao==null){
                    svcDao=new SvcDaoImpl();
                }
            }
        }
        return svcDao;
    }
    public static TsvcInterfaceDao getTsvcInterfaceDao() {
        if(tsvcInterfaceDao==null){
            synchronized (DaoFactory.class) {
                if(tsvcInterfaceDao==null){
                    tsvcInterfaceDao=new TsvcInterfaceDaoImpl();
                }
            }
        }
        return tsvcInterfaceDao;
    }
    public static TsvcSqlDao getTsvcSqlDao() {
        if(tsvcSqlDao==null){
            synchronized (DaoFactory.class) {
                if(tsvcSqlDao==null){
                    tsvcSqlDao=new TsvcSqlDaoImpl();
                }
            }
        }
        return tsvcSqlDao;
    }
    public static TsvcViewconfigDao getTsvcViewconfigDao() {
        if(tsvcViewconfigDao==null){
            synchronized (DaoFactory.class) {
                if(tsvcViewconfigDao==null){
                    tsvcViewconfigDao=new TsvcViewconfigDaoImpl();
                }
            }
        }
        return tsvcViewconfigDao;
    }
    public static GenCodeDao getGenCodeDao() {
        if(genCodeDao==null){
            synchronized (DaoFactory.class) {
                if(genCodeDao==null){
                    genCodeDao=new GenCodeDaoImpl();
                }
            }
        }
        return genCodeDao;
    }


}
