package service;

import bean.TsvcSql;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public interface TsvcSqlService {
    /**
     * 获取ucSql
     * @param ucNo
     * @return
     */
    public TsvcSql getTsvcSql(String ucNo);

    /**
     *如果存在就更新， 不存在就添加，sql语句为空就删除
     * @param tsvcSql
     */
    public void operate(TsvcSql tsvcSql) ;
}
