package service.impl;

import bean.HsiRight;
import constant.EnActionEvent;
import dao.DaoFactory;
import dao.HsiRightDao;
import dao.impl.HsiRightDaoImpl;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.SvcService;
import service.UcDefineService;
import util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyd on 2017-06-30.
 */
public class UcDefineServiceImpl implements UcDefineService {
    private static Logger logger= LogUtil.getLogger(UcDefineServiceImpl.class);
    HsiRightDao hsiRightDao= DaoFactory.getHsiRightDao();
    private SvcService svcService = ServiceFactory.getSvcService();
    @Override
    public List<HsiRight> getAllUc()  {
        try {
            return hsiRightDao.getHsiRighFuzzy("");
        } catch (Exception e) {
            logger.error("查询异常", e);
            svcService.throwErrorMsg(EnActionEvent.COMMOM_ERROR, e.getMessage());
        }
        return new ArrayList<HsiRight>();
    }

    @Override
    public HsiRight getUc(String functionno) {
        try {
            return hsiRightDao.getHsiRigh(functionno);
        } catch (Exception e) {
            logger.error("查询异常", e);
            svcService.throwErrorMsg(EnActionEvent.COMMOM_ERROR, e.getMessage());
        }
        return new HsiRight();
    }

    @Override
    public List<HsiRight> getAllUc(String condition)  {
        try {
            return hsiRightDao.getHsiRighFuzzy(condition);
        } catch (Exception e) {
            logger.error("查询异常", e);
            svcService.throwErrorMsg(EnActionEvent.COMMOM_ERROR, e.getMessage());
        }
        return new ArrayList<HsiRight>();
    }


    @Override
    public void save(List<String> delList, List<HsiRight> addUcList, List<HsiRight> editUcList)  {
        try {
            //开启事务
            hsiRightDao.openTransaction();
            hsiRightDao.delete(delList);
            hsiRightDao.edit(editUcList);
            hsiRightDao.add(addUcList);
            //提交事务
            hsiRightDao.commitTransaction();
        } catch (Exception e) {
            //回滚事务
            hsiRightDao.rollbackTransaction();
            logger.error("保存uc定义异常", e);
            svcService.throwErrorMsg(EnActionEvent.COMMOM_ERROR, e.getMessage());
        }


    }
}
