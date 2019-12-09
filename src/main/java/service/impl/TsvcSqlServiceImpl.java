package service.impl;

import bean.TsvcSql;
import constant.EnActionEvent;
import dao.DaoFactory;
import dao.TsvcSqlDao;
import dao.impl.TsvcSqlDaoImpl;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.SvcService;
import service.TsvcSqlService;
import util.LogUtil;
import util.StringUtils;
import validator.ValidTsvcSql;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public class TsvcSqlServiceImpl implements TsvcSqlService {
    private static Logger logger= LogUtil.getLogger(TsvcSqlServiceImpl.class);
    TsvcSqlDao tsvcSqlDao = DaoFactory.getTsvcSqlDao();
    private SvcService svcService = ServiceFactory.getSvcService();

    @Override
    public TsvcSql getTsvcSql(String ucNo) {
        try {
            return tsvcSqlDao.getTsvcSql(ucNo);
        } catch (Exception e) {
            logger.error("查询配置sql异常：",e);
            svcService.throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }
        return null;
    }

    @Override
    public void operate(TsvcSql tsvcSql) {
        if (StringUtils.isNotNullAndNotEmpty(tsvcSql.getC_functionno())) {
            try {
                ValidTsvcSql.valid(tsvcSql);
            } catch (Exception e) {
                svcService.throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
            }
            TsvcSql tsvcSqlDb = null;
            try {
                tsvcSqlDao.openTransaction();
                tsvcSqlDb = tsvcSqlDao.getTsvcSql(tsvcSql.getC_functionno());
                if (StringUtils.isNullOrEmpty(tsvcSql.getC_sqlstatement()) ) {
                    if(tsvcSqlDb != null){
                        //如果没有sql  删除
                        tsvcSqlDao.del(tsvcSql);
                    }else{
                        //不处理
                    }
                } else {
                    if (tsvcSqlDb != null) {
                        //更新
                        tsvcSqlDao.update(tsvcSql);
                    } else {
                        //插入
                        tsvcSqlDao.insert(tsvcSql);
                    }
                }
                tsvcSqlDao.commitTransaction();
            } catch (Exception e) {
                tsvcSqlDao.rollbackTransaction();
                logger.error("保存sql异常：",e);
                svcService.throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
            }
        }
    }
}
