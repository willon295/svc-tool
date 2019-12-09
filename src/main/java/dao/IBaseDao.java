package dao;

import bean.BaseBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lyd on 2017-07-06.
 */
public interface IBaseDao<TDtoModel extends BaseBean>  {

    /**
     * 开启事务
     */
    public void openTransaction() throws Exception ;
    /**
     * 回滚事务
     */
    public void rollbackTransaction() ;
    /**
     * 提交事务
     */
    public void commitTransaction() throws Exception ;

    public int del(TDtoModel bean)throws Exception;
    public void update(TDtoModel bean)throws Exception;
    public void update(TDtoModel bean,String[] fieldNames)throws Exception;
    public boolean insert(TDtoModel bean)throws Exception;
    public int executeSql(String sql) throws Exception;
    public List<Map<String,Object>> queryForList(String sql)throws Exception;
}
