package dao;

import bean.TsvcSql;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public interface TsvcSqlDao extends IBaseDao<TsvcSql> {
    /**
     * 获取ucSql
     * @param ucNo
     * @return
     */
    public TsvcSql getTsvcSql(String ucNo) throws Exception;



}
