package dao;

import bean.BaseBean;
import bean.SqlFieldType;

import java.util.List;
import java.util.Map;

/**
 * 功能说明: 通用DAO操作<br>
 * 系统版本: 0.1.1 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-09<br>
 * <br>
 */
public interface SvcDao extends  IBaseDao<BaseBean> {
    public List<SqlFieldType> findField(String sql) throws Exception;
    public Map<String,String> findFieldComments(List<String> tableName) throws Exception;
}
