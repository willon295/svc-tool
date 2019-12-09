package dao.impl;

import bean.BaseBean;
import bean.SqlFieldType;
import bean.SystemData;
import dao.BaseDao;
import dao.SvcDao;
import util.CommonUtil;
import util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统版本: 0.1.1 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-09<br>
 * <br>
 */
public class SvcDaoImpl extends BaseDao<BaseBean> implements SvcDao {

    @Override
    public List<SqlFieldType> findField(String sql) throws Exception {
        List<SqlFieldType> fieldList = new ArrayList<SqlFieldType>();
        PreparedStatement pstmt = getPreStmt(sql);
        try {
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData(); //获得列集
            int col = rsm.getColumnCount();
            for (int i = 1; i <= col; i++) {
                SqlFieldType sqlFieldType=new SqlFieldType();
                sqlFieldType.setField(rsm.getColumnLabel(i)) ;
                sqlFieldType.setFieldType(rsm.getColumnType(i)); ;
                sqlFieldType.setFieldLength(rsm.getColumnDisplaySize(i));
                fieldList.add(sqlFieldType);
            }
        } finally {
            pstmt.close();
        }
        return fieldList;
    }
      public Map<String,String> findFieldComments(List<String> tableName) throws Exception {
          Map<String,String> commentsMap=new HashMap<String,String>();
          if(tableName==null || tableName.size()<1){
              return commentsMap;
          }
          String sql=null;
          String inString= CommonUtil.getInString(tableName,true,"table_name",999);
          if (SystemData.getDataConnInfo().getDrivertype().equalsIgnoreCase(SystemData.DataConnInfo.ORACLE)) {
              sql="select * from user_col_comments where "+inString;
          }else if (SystemData.getDataConnInfo().getDrivertype().equalsIgnoreCase(SystemData.DataConnInfo.MYSQL)){
              sql="select * from information_schema.columns where "+inString;
          }
          List<Map<String,Object>> mapList= queryForList(sql);
          if(mapList!=null && mapList.size()>0){
              for(Map<String,Object> map : mapList){
                  if (SystemData.getDataConnInfo().getDrivertype().equalsIgnoreCase(SystemData.DataConnInfo.ORACLE)) {
                      commentsMap.put(map.get("TABLE_NAME")+"#"+map.get("COLUMN_NAME")+"#", StringUtils.valueOf(map.get("COMMENTS")));
                  }else if (SystemData.getDataConnInfo().getDrivertype().equalsIgnoreCase(SystemData.DataConnInfo.MYSQL)){
                      commentsMap.put(map.get("TABLE_NAME")+"#"+map.get("COLUMN_NAME")+"#", StringUtils.valueOf(map.get("COLUMN_COMMENT")));
                  }
              }
          }
          return commentsMap;
      }
    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected String[] createUpdateFieldNames() {
        return new String[0];
    }

    @Override
    protected String[] createInsertFieldNames() {
        return new String[0];
    }
}
