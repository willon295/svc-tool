package dao.impl;

import bean.HsiRight;
import bean.gencode.TableField;
import bean.gencode.TableInfo;
import dao.BaseDao;
import dao.GenCodeDao;
import util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class GenCodeDaoImpl  extends BaseDao<TableInfo> implements GenCodeDao {

    @Override
    public TableInfo getTableInfo(String tableName) throws Exception {
        TableInfo tableInfo=null;
        tableName=tableName.toUpperCase();
        Map<String,String> tableFieldComments=findFieldComments(tableName);
        String comments=getTableComments(tableName);
        List<Map<String,Object>> tableFieldList=getTableFields(tableName);


        if (tableFieldList!=null && tableFieldList.size()>0) {
            tableInfo=new TableInfo();
            tableInfo.setDescribe(comments);
            tableInfo.setName(tableName);
            for(Map<String,Object> field : tableFieldList){
                String name=StringUtils.valueOf(field.get("COLUMN_NAME"));
                String type=StringUtils.valueOf(field.get("DATA_TYPE"));
                String comment=StringUtils.valueOf(tableFieldComments.get(tableName+"#"+name.toUpperCase()+"#"));
                TableField tableField=new TableField(name.toLowerCase(),type,comment);
                tableInfo.getFields().add(tableField);
            }
        }

        return tableInfo;
    }

    public List<TableInfo> getTables() throws Exception {
        List<TableInfo> retList=new ArrayList<>();
        String sql="select * from user_tab_comments t" +
                " where t.TABLE_NAME not like '%_A' and  t.TABLE_NAME  like 'T\\_%' escape '\\'  and  t.table_type ='TABLE'  order by TABLE_NAME";
        List<Map<String,Object>> mapList=queryForList(sql);
        for(Map<String,Object> map : mapList){
            String tableName= StringUtils.valueOf(map.get("TABLE_NAME"));
            String comments=StringUtils.valueOf(map.get("COMMENTS"));
            TableInfo tableInfo=new TableInfo();
            tableInfo.setName(tableName);
            tableInfo.setDescribe(comments);
            retList.add(tableInfo);
        }
        return retList;
    }
    @Override
    @Deprecated
    protected String getTableName() {
        return null;
    }

    @Override
    @Deprecated
    protected String[] createUpdateFieldNames() {
        return new String[0];
    }

    @Override
    @Deprecated
    protected String[] createInsertFieldNames() {
        return new String[0];
    }
}
