package dao;

import bean.HsiRight;
import bean.gencode.TableInfo;

import java.util.List;

/**
 * Created by lyd on 2018-09-27.
 */
public interface GenCodeDao extends IBaseDao<TableInfo> {
    /**
     * 获取表信息
     * @param tableName
     * @return
     */
    public TableInfo getTableInfo(String tableName) throws Exception;

    public List<TableInfo> getTables() throws Exception;

}
