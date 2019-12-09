package dao;

import bean.TsvcInterface;

import java.util.List;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-23<br>
 * <br>
 */
public interface TsvcInterfaceDao extends IBaseDao<TsvcInterface> {
    public List<TsvcInterface> getOutTsvcInterface(String uc) throws Exception;
    public List<TsvcInterface> getTsvcInterfaceList(String uc) throws Exception;
    public List<TsvcInterface> getTsvcInterfaceListHasOrder(String uc) throws Exception;
    public List<TsvcInterface> getConditionTsvcInterfaceList(String uc) throws Exception;

}
