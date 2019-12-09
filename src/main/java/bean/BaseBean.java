package bean;

import view.centercontent.GenerateSql;

import java.util.List;

/**
 * Created by lyd on 2017-07-06.
 */
public abstract class BaseBean  {
    private  static String sqlDelete="DELETE FROM %1$s where C_FUNCTIONNO = '%2$s';";
    static {
        if(SystemData.isIsLowerCaseSql()){
            sqlDelete="delete from %1$s where c_functionno = '%2$s';";
        }
    }
    public String generateDelSql(){
        return String.format(sqlDelete, getTableName(),getC_functionno());
    }
    public static String generateDelSql(String tableName,String uc){
        if(SystemData.isIsLowerCaseSql()){
            tableName=tableName.toLowerCase();
        }
        return String.format(sqlDelete, tableName,uc);
    }
    public abstract String generateInsertSql();
    /**
     * 主键
     * @return
     */
    public abstract List<String> getKeyStr();
    /**
     * 主键
     * @return
     */
    public abstract String getKeyValue();
    /**
     *
     * @return
     */
    public abstract  List<String> getKeyValueStr();

    /**
     *UC功能号
     */
    protected String c_functionno="";

    public String getC_functionno() {
        return c_functionno;
    }

    public void setC_functionno(String c_functionno) {
        this.c_functionno = c_functionno;
    }
    public abstract  String getTableName();
}
