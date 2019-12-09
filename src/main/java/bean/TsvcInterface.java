package bean;

import util.ConfigManager;
import util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-22<br>
 * <br>
 */
public class TsvcInterface extends BaseBean {
    private  static String insertSql="INSERT INTO TSVCINTERFACE(C_FUNCTIONNO,C_FLAG,C_PACKFLAG," +
            "C_FIELDNAME,C_EXPLAIN,C_PROPERTY,L_LEN,L_DECLEN,C_FIELDTYPE,C_FIELDFLAG,C_NOTNULL," +
            "C_CONDITION,C_EXISTVALUE,C_VIEWLEVEL,C_VIEWTYPE,C_DICNAME,C_ISDEFAULT,C_VALUE," +
            "C_MIDSEARCHNAME,L_NO,C_NOTSHOWALLITEM,C_EXISTVALUE_PGSQL,C_EXISTVALUE_MYSQL,C_EXISTVALUE_RESERVE) VALUES (%s,%s,%s,%s,%s,%s,%s," +
            "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);\r\n";
    private  static String insertoldSql="INSERT INTO TSVCINTERFACE(C_FUNCTIONNO,C_FLAG,C_PACKFLAG," +
            "C_FIELDNAME,C_EXPLAIN,C_PROPERTY,L_LEN,L_DECLEN,C_FIELDTYPE,C_FIELDFLAG,C_NOTNULL," +
            "C_CONDITION,C_EXISTVALUE,C_VIEWLEVEL,C_VIEWTYPE,C_DICNAME,C_ISDEFAULT,C_VALUE," +
            "C_MIDSEARCHNAME,L_NO,C_NOTSHOWALLITEM) VALUES (%s,%s,%s,%s,%s,%s,%s," +
            "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);\r\n";
    static {
        if(SystemData.isIsLowerCaseSql()){
            insertSql="insert into tsvcinterface(c_functionno,c_flag,c_packflag," +
                    "c_fieldname,c_explain,c_property,l_len,l_declen,c_fieldtype,c_fieldflag,c_notnull," +
                    "c_condition,c_existvalue,c_viewlevel,c_viewtype,c_dicname,c_isdefault,c_value," +
                    "c_midsearchname,l_no,c_notshowallitem,c_existvalue_pgsql,c_existvalue_mysql,c_existvalue_reserve) values (%s,%s,%s,%s,%s,%s,%s," +
                    "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);\r\n";
            insertoldSql="insert into tsvcinterface(c_functionno,c_flag,c_packflag," +
                    "c_fieldname,c_explain,c_property,l_len,l_declen,c_fieldtype,c_fieldflag,c_notnull," +
                    "c_condition,c_existvalue,c_viewlevel,c_viewtype,c_dicname,c_isdefault,c_value," +
                    "c_midsearchname,l_no,c_notshowallitem) values (%s,%s,%s,%s,%s,%s,%s," +
                    "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);\r\n";
        }
    }
    public static String tableName="tsvcinterface";
    private String c_functionno;
    private String c_flag;
    private String c_packflag;
    private String c_fieldname;
    private String c_explain;
    private String c_property;
    private String l_len;
    private String l_declen;
    private String c_fieldtype;
    private String c_fieldflag;
    private String c_notnull ;
    private String c_condition;
    private String c_existvalue;
    private String c_viewlevel;
    private String c_viewtype;
    private String c_dicname;
    private String c_isdefault;
    private String c_value;
    private String c_midsearchname;
    private String l_no;
    private String c_notshowallitem;
    private String rowid;
    private String c_existvalue_pgsql;
    private String c_existvalue_mysql;
    private String c_existvalue_reserve;

    public TsvcInterface() {
    }
    public static  TsvcInterface generateDefault(){
        TsvcInterface tsvcInterface=new TsvcInterface(
                "",
                "0", "0", "", "", "", "32", "0",
                "S", "0", "0", "", "", "", "",
                "", "", "", "", "","","","");
        return tsvcInterface;
    }
    public Vector<String> toVector(){
        Vector<String> vector = new Vector<String>();
        vector.add(StringUtils.valueOf(this.getC_flag()));
        vector.add(StringUtils.valueOf(this.getC_packflag()));
        vector.add(StringUtils.valueOf(this.getC_fieldname()));
        vector.add(StringUtils.valueOf(this.getC_explain()));
        vector.add(StringUtils.valueOf(this.getC_property()));
        vector.add(StringUtils.valueOf(this.getL_len()));
        vector.add(StringUtils.valueOf(this.getL_declen()));
        vector.add(StringUtils.valueOf(this.getC_fieldtype()));
        vector.add(StringUtils.valueOf(this.getC_notnull()));
        vector.add(StringUtils.valueOf(this.getC_fieldflag()));
        vector.add(StringUtils.valueOf(this.getC_condition()));
        vector.add(StringUtils.valueOf(this.getL_no()));
        vector.add(StringUtils.valueOf(this.getC_viewlevel()));
        vector.add(StringUtils.valueOf(this.getC_viewtype()));
        vector.add(StringUtils.valueOf(this.getC_dicname()));
        vector.add(StringUtils.valueOf(this.getC_midsearchname()));
        vector.add(StringUtils.valueOf(this.getC_isdefault()));
        vector.add(StringUtils.valueOf(this.getC_value()));

        vector.add(StringUtils.valueOf(this.getC_existvalue()));
        vector.add(StringUtils.valueOf(this.getRowid()));

        vector.add(StringUtils.valueOf(this.getC_existvalue_pgsql()));
        vector.add(StringUtils.valueOf(this.getC_existvalue_mysql()));
        vector.add(StringUtils.valueOf(this.getC_existvalue_reserve()));
        return vector;
    }
    public TsvcInterface(String c_functionno,
                         String c_flag,
                         String c_packflag,
                         String c_fieldname,
                         String c_explain,
                         String c_property,
                         String l_len,
                         String l_declen,
                         String c_fieldtype,
                         String c_notnull ,
                         String c_fieldflag,
                         String c_condition,
                         String l_no,
                         String c_viewlevel,
                         String c_viewtype,
                         String c_dicname,
                         String c_midsearchname,
                         String c_isdefault,
                         String c_value,
                         String c_existvalue,
                         String c_existvalue_pgsql,
                         String c_existvalue_mysql,
                         String c_existvalue_reserve) {
        this.c_functionno = c_functionno;
        this.c_flag = c_flag;
        this.c_packflag = c_packflag;
        this.c_fieldname = c_fieldname;
        this.c_explain = c_explain;
        this.c_property = c_property;
        this.l_len = l_len;
        this.l_declen = l_declen;
        this.c_fieldtype = c_fieldtype;
        this.c_fieldflag = c_fieldflag;
        this.c_notnull = c_notnull;
        this.c_condition = c_condition;
        this.c_existvalue = c_existvalue;
        this.c_viewlevel = c_viewlevel;
        this.c_viewtype = c_viewtype;
        this.c_dicname = c_dicname;
        this.c_isdefault = c_isdefault;
        this.c_value = c_value;
        this.c_midsearchname = c_midsearchname;
        this.l_no = l_no;
        this.c_existvalue_pgsql = c_existvalue_pgsql;
        this.c_existvalue_mysql = c_existvalue_mysql;
        this.c_existvalue_reserve = c_existvalue_reserve;
    }

    @Override
    public String generateInsertSql() {
        if(ConfigManager.getCreatMode().equalsIgnoreCase(SystemData.createDeaultMode)){
            return String.format(insertSql, StringUtils.valueOfWithNull(getC_functionno()),
                    StringUtils.valueOfWithNull(getC_flag()),StringUtils.valueOfWithNull(getC_packflag()),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_fieldname()).replaceAll("'","''")),
                    StringUtils.valueOfWithNull(getC_explain()),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_property()).replaceAll("'","''")),
                    StringUtils.valueOfWithNull(getL_len()),
                    StringUtils.valueOfWithNull(getL_declen()),StringUtils.valueOfWithNull(getC_fieldtype()),
                    StringUtils.valueOfWithNull(getC_fieldflag()),StringUtils.valueOfWithNull(getC_notnull()),
                    StringUtils.valueOfWithNull(getC_condition()),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_existvalue()).replaceAll("'","''")).
                            replaceAll("\n\n","\n").replaceAll("\r\n\r\n","\n").replaceAll("\r\n","\n").
                            replaceAll("\n","\r\n"),
                    StringUtils.valueOfWithNull(getC_viewlevel()),StringUtils.valueOfWithNull(getC_viewtype()),
                    StringUtils.valueOfWithNull(getC_dicname()),StringUtils.valueOfWithNull(getC_isdefault()),
                    StringUtils.valueOfWithNull(getC_value()),StringUtils.valueOfWithNull(getC_midsearchname()),
                    StringUtils.valueOfWithNull(getL_no()),StringUtils.valueOfWithNull(getC_notshowallitem()),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_existvalue_pgsql()).replaceAll("'","''")).
                            replaceAll("\n\n","\n").replaceAll("\r\n\r\n","\n").replaceAll("\r\n","\n").
                            replaceAll("\n","\r\n"),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_existvalue_mysql()).replaceAll("'","''")).
                            replaceAll("\n\n","\n").replaceAll("\r\n\r\n","\n").replaceAll("\r\n","\n").
                            replaceAll("\n","\r\n"),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_existvalue_reserve()).replaceAll("'","''")).
                            replaceAll("\n\n","\n").replaceAll("\r\n\r\n","\n").replaceAll("\r\n","\n").
                            replaceAll("\n","\r\n")
            );
        }else if(ConfigManager.getCreatMode().equalsIgnoreCase(SystemData.createOldMode)){
            return String.format(insertoldSql, StringUtils.valueOfWithNull(getC_functionno()),
                    StringUtils.valueOfWithNull(getC_flag()),StringUtils.valueOfWithNull(getC_packflag()),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_fieldname()).replaceAll("'","''")),
                    StringUtils.valueOfWithNull(getC_explain()),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_property()).replaceAll("'","''")),
                    StringUtils.valueOfWithNull(getL_len()),
                    StringUtils.valueOfWithNull(getL_declen()),StringUtils.valueOfWithNull(getC_fieldtype()),
                    StringUtils.valueOfWithNull(getC_fieldflag()),StringUtils.valueOfWithNull(getC_notnull()),
                    StringUtils.valueOfWithNull(getC_condition()),
                    StringUtils.valueOfWithNull(StringUtils.valueOf(getC_existvalue()).replaceAll("'","''")).
                            replaceAll("\n\n","\n").replaceAll("\r\n\r\n","\n").replaceAll("\r\n","\n").
                            replaceAll("\n","\r\n"),
                    StringUtils.valueOfWithNull(getC_viewlevel()),StringUtils.valueOfWithNull(getC_viewtype()),
                    StringUtils.valueOfWithNull(getC_dicname()),StringUtils.valueOfWithNull(getC_isdefault()),
                    StringUtils.valueOfWithNull(getC_value()),StringUtils.valueOfWithNull(getC_midsearchname()),
                    StringUtils.valueOfWithNull(getL_no()),StringUtils.valueOfWithNull(getC_notshowallitem())
            );
        }
       return "未指定生成模式！";
    }
    public static String generateHead() {
        return "-- ****************************************************\r\n" +
                "-- **  英文表名：TSVCINTERFACE\r\n" +
                "-- ****************************************************\r\n";
    }
    @Override
    public List<String> getKeyStr() {
        List<String> key=new ArrayList<>();
        key.add("c_functionno");
        key.add("c_flag");
        key.add("c_property");
        return key;
    }

    @Override
    public String getKeyValue() {
        return "C_FUNCTIONNO";
    }

    @Override
    public List<String> getKeyValueStr() {
        List<String> key=new ArrayList<>();
        key.add("c_functionno");
        key.add("c_flag");
        key.add("c_property");
        return key;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String getC_functionno() {
        return c_functionno;
    }

    @Override
    public void setC_functionno(String c_functionno) {
        this.c_functionno = c_functionno;
    }

    public String getC_flag() {
        return c_flag;
    }

    public void setC_flag(String c_flag) {
        this.c_flag = c_flag;
    }

    public String getC_packflag() {
        return c_packflag;
    }

    public void setC_packflag(String c_packflag) {
        this.c_packflag = c_packflag;
    }

    public String getC_fieldname() {
        return c_fieldname;
    }

    public void setC_fieldname(String c_fieldname) {
        this.c_fieldname = c_fieldname;
    }

    public String getC_explain() {
        return c_explain;
    }

    public void setC_explain(String c_explain) {
        this.c_explain = c_explain;
    }

    public String getC_property() {
        return c_property;
    }

    public void setC_property(String c_property) {
        this.c_property = c_property;
    }

    public String getL_len() {
        return l_len;
    }

    public void setL_len(String l_len) {
        this.l_len = l_len;
    }

    public String getL_declen() {
        return l_declen;
    }

    public void setL_declen(String l_declen) {
        this.l_declen = l_declen;
    }

    public String getC_fieldtype() {
        return c_fieldtype;
    }

    public void setC_fieldtype(String c_fieldtype) {
        this.c_fieldtype = c_fieldtype;
    }

    public String getC_fieldflag() {
        return c_fieldflag;
    }

    public void setC_fieldflag(String c_fieldflag) {
        this.c_fieldflag = c_fieldflag;
    }

    public String getC_notnull() {
        return c_notnull;
    }

    public void setC_notnull(String c_notnull) {
        this.c_notnull = c_notnull;
    }

    public String getC_condition() {
        return c_condition;
    }

    public void setC_condition(String c_condition) {
        this.c_condition = c_condition;
    }

    public String getC_existvalue() {
        return c_existvalue;
    }

    public void setC_existvalue(String c_existvalue) {
        this.c_existvalue = c_existvalue;
    }

    public String getC_viewlevel() {
        return c_viewlevel;
    }

    public void setC_viewlevel(String c_viewlevel) {
        this.c_viewlevel = c_viewlevel;
    }

    public String getC_viewtype() {
        return c_viewtype;
    }

    public void setC_viewtype(String c_viewtype) {
        this.c_viewtype = c_viewtype;
    }

    public String getC_dicname() {
        return c_dicname;
    }

    public void setC_dicname(String c_dicname) {
        this.c_dicname = c_dicname;
    }

    public String getC_isdefault() {
        return c_isdefault;
    }

    public void setC_isdefault(String c_isdefault) {
        this.c_isdefault = c_isdefault;
    }

    public String getC_value() {
        return c_value;
    }

    public void setC_value(String c_value) {
        this.c_value = c_value;
    }

    public String getC_midsearchname() {
        return c_midsearchname;
    }

    public void setC_midsearchname(String c_midsearchname) {
        this.c_midsearchname = c_midsearchname;
    }

    public String getL_no() {
        return l_no;
    }

    public void setL_no(String l_no) {
        this.l_no = l_no;
    }

    public String getC_notshowallitem() {
        return c_notshowallitem;
    }

    public void setC_notshowallitem(String c_notshowallitem) {
        this.c_notshowallitem = c_notshowallitem;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getC_existvalue_pgsql() {
        return c_existvalue_pgsql;
    }

    public void setC_existvalue_pgsql(String c_existvalue_pgsql) {
        this.c_existvalue_pgsql = c_existvalue_pgsql;
    }

    public String getC_existvalue_mysql() {
        return c_existvalue_mysql;
    }

    public void setC_existvalue_mysql(String c_existvalue_mysql) {
        this.c_existvalue_mysql = c_existvalue_mysql;
    }

    public String getC_existvalue_reserve() {
        return c_existvalue_reserve;
    }

    public void setC_existvalue_reserve(String c_existvalue_reserve) {
        this.c_existvalue_reserve = c_existvalue_reserve;
    }

    @Override
    public String toString() {
        return getC_property()+"-"+getC_explain();
    }

    @Override
    public int hashCode() {

        return Objects.hash(StringUtils.valueOf(c_functionno), StringUtils.valueOf(c_flag), StringUtils.valueOf(c_packflag),
                StringUtils.valueOf(c_fieldname),StringUtils.valueOf( c_explain), StringUtils.valueOf(c_property),
                        StringUtils.valueOf(l_len), StringUtils.valueOf(l_declen),
                        StringUtils.valueOf(c_fieldtype), StringUtils.valueOf( c_fieldflag),  StringUtils.valueOf(c_notnull),
                StringUtils.valueOf(c_condition),
                        StringUtils.valueOf(c_existvalue), StringUtils.valueOf(c_viewlevel), StringUtils.valueOf(c_viewtype),
                        StringUtils.valueOf(c_dicname),StringUtils.valueOf( c_isdefault), StringUtils.valueOf(c_value),
                        StringUtils.valueOf(c_midsearchname),  StringUtils.valueOf(l_no), StringUtils.valueOf(c_notshowallitem),
                StringUtils.valueOf(c_existvalue_pgsql),StringUtils.valueOf(c_existvalue_mysql),StringUtils.valueOf(c_existvalue_reserve));
    }
}
