package bean;

import util.ConfigManager;
import util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-10<br>
 * <br>
 */
public class TsvcSql extends  BaseBean implements Serializable,Cloneable {
    private  static String tsvcsql="INSERT INTO TSVCSQL(C_FUNCTIONNO,C_SQLSTATEMENT,C_ORDERBY,C_SQLTYPE,C_DATASOURCE,C_SQLSTATEMENT_PGSQL,C_SQLSTATEMENT_MYSQL,C_SQLSTATEMENT_RESERVE)" +
            " VALUES ('%s','%s',%s,'%s',NULL,%s,%s,%s);";
    private  static String oldtsvcsql="INSERT INTO TSVCSQL(C_FUNCTIONNO,C_SQLSTATEMENT,C_ORDERBY,C_SQLTYPE,C_DATASOURCE)" +
            " VALUES ('%s','%s',%s,'%s',NULL);";
    static {
        if(SystemData.isIsLowerCaseSql()){
            tsvcsql="insert into tsvcsql(c_functionno,c_sqlstatement,c_orderby,c_sqltype,c_datasource,c_sqlstatement_pgsql,c_sqlstatement_mysql,c_sqlstatement_reserve)" +
                    " values ('%s','%s',%s,'%s',NULL,%s,%s,%s);";
            oldtsvcsql="insert into tsvcsql(c_functionno,c_sqlstatement,c_orderby,c_sqltype,c_datasource)" +
                    " values ('%s','%s',%s,'%s',NULL);";
        }
    }
    @Override
    public String generateInsertSql() {
        if(ConfigManager.getCreatMode().equalsIgnoreCase(SystemData.createDeaultMode)){
            return String.format(tsvcsql,getC_functionno(),getC_sqlstatement().replaceAll("'","''").
                            replaceAll("\n\n","\n").replaceAll("\r\n\r\n","\n").replaceAll("\r\n","\n").
                            replaceAll("\n","\r\n"),
                    StringUtils.valueOfWithNull(getC_orderby()),getC_sqltype(),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_sqlstatement_pgsql()).replaceAll("'","''").
                            replaceAll("\n\n","\n").replaceAll("\r\n\r\n","\n").replaceAll("\r\n","\n").
                            replaceAll("\n","\r\n")),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_sqlstatement_mysql()).replaceAll("'","''").
                            replaceAll("\n\n","\n").replaceAll("\r\n\r\n","\n").replaceAll("\r\n","\n").
                            replaceAll("\n","\r\n")),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_sqlstatement_reserve()).replaceAll("'","''").
                            replaceAll("\n\n","\n").replaceAll("\r\n\r\n","\n").replaceAll("\r\n","\n").
                            replaceAll("\n","\r\n"))
            );
        }else if(ConfigManager.getCreatMode().equalsIgnoreCase(SystemData.createOldMode)){
            return String.format(oldtsvcsql,getC_functionno(),getC_sqlstatement().replaceAll("'","''").
                            replaceAll("\n\n","\n").replaceAll("\r\n\r\n","\n").replaceAll("\r\n","\n").
                            replaceAll("\n","\r\n"),
                    StringUtils.valueOfWithNull(getC_orderby()),getC_sqltype()
            );
        }
        return "未指定生成模式！";
    }
    public static String generateHead() {
        return "-- ****************************************************\r\n" +
                "-- **  英文表名：TSVCSQL\r\n" +
                "-- ****************************************************\r\n";
    }

    @Override
    public String getKeyValue() {
        return this.c_functionno;
    }

    public static String tableName="tsvcsql";
    /**
     * 函数编号
     */
    private String c_functionno;
    /**
     *SQL报表
     */
    private String c_sqlstatement;
    /**
     *排序
     */
    private String c_orderby;
    /**
     *SQL类型。0:普通SQL语句,1:存储过程
     */
    private String c_sqltype;
    /**
     *数据源
     */
    private String c_datasource;
    /**
     *SQL报表_PGSQL
     */
    private String c_sqlstatement_pgsql;
    /**
     *SQL报表_MYSQL
     */
    private String c_sqlstatement_mysql;
    /**
     *SQL报表_预留SQL
     */
    private String c_sqlstatement_reserve;

    @Override
    public List<String> getKeyStr() {
        List<String> key=new ArrayList<>();
        key.add("c_functionno");
        return key;
    }
    @Override
    public List<String> getKeyValueStr() {
        List<String> key=new ArrayList<>();
        key.add("c_functionno");
        return key;
    }

    public String getC_functionno() {
        return c_functionno;
    }

    public void setC_functionno(String c_functionno) {
        this.c_functionno = c_functionno;
    }

    public String getC_sqlstatement() {
        return c_sqlstatement;
    }

    public void setC_sqlstatement(String c_sqlstatement) {
        this.c_sqlstatement = c_sqlstatement;
    }

    public String getC_orderby() {
        return c_orderby;
    }

    public void setC_orderby(String c_orderby) {
        this.c_orderby = c_orderby;
    }

    public String getC_sqltype() {
        return c_sqltype;
    }

    public void setC_sqltype(String c_sqltype) {
        this.c_sqltype = c_sqltype;
    }

    public String getC_datasource() {
        return c_datasource;
    }

    public void setC_datasource(String c_datasource) {
        this.c_datasource = c_datasource;
    }

    public String getC_sqlstatement_pgsql() {
        return c_sqlstatement_pgsql;
    }

    public void setC_sqlstatement_pgsql(String c_sqlstatement_pgsql) {
        this.c_sqlstatement_pgsql = c_sqlstatement_pgsql;
    }

    public String getC_sqlstatement_mysql() {
        return c_sqlstatement_mysql;
    }

    public void setC_sqlstatement_mysql(String c_sqlstatement_mysql) {
        this.c_sqlstatement_mysql = c_sqlstatement_mysql;
    }

    public String getC_sqlstatement_reserve() {
        return c_sqlstatement_reserve;
    }

    public void setC_sqlstatement_reserve(String c_sqlstatement_reserve) {
        this.c_sqlstatement_reserve = c_sqlstatement_reserve;
    }

    public  String getTableName() {
        return tableName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TsvcSql tsvcSql = (TsvcSql) o;
        return Objects.equals(c_functionno, tsvcSql.c_functionno) &&
                Objects.equals(c_sqlstatement, tsvcSql.c_sqlstatement) &&
                Objects.equals(c_orderby, tsvcSql.c_orderby) &&
                Objects.equals(c_sqltype, tsvcSql.c_sqltype) &&
                Objects.equals(c_datasource, tsvcSql.c_datasource) &&
                Objects.equals(c_sqlstatement_pgsql, tsvcSql.c_sqlstatement_pgsql) &&
                Objects.equals(c_sqlstatement_mysql, tsvcSql.c_sqlstatement_mysql) &&
                Objects.equals(c_sqlstatement_reserve, tsvcSql.c_sqlstatement_reserve);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c_functionno, c_sqlstatement, c_orderby, c_sqltype, c_datasource, c_sqlstatement_pgsql, c_sqlstatement_mysql, c_sqlstatement_reserve);
    }
}
