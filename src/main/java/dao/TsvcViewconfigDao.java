package dao;

import bean.TsvcViewconfig;

import java.util.List;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-29<br>
 * <br>
 */
public interface TsvcViewconfigDao  extends IBaseDao<TsvcViewconfig> {
    public List<TsvcViewconfig> getTsvcViewconfig(String uc) throws Exception;
    public List<TsvcViewconfig> getTsvcViewconfigHasOrder(String uc) throws Exception;
}
