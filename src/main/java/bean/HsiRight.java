package bean;

import util.StringUtils;
import util.SvcUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by lyd on 2017-06-28.
 */
public class HsiRight extends BaseBean implements Serializable,Cloneable {
    private  static String hsiRightSql="INSERT INTO HSI_RIGHT" +
            "(C_RIGHTCODE,C_RIGHTNAME,C_CLASS,C_SYSNAME,C_FUNCTIONNO," +
            "C_JAVACLASS,C_JAVAMETHOD,C_CLIENTPROGS,C_TABLENAME,C_UCTYPE,C_ISLIMIT) VALUES" +
            " ('%s','%s','%s','FINGARDATS','%s','%s','%s',NULL,NULL,'%s','%s');";
    static {
        if(SystemData.isIsLowerCaseSql()){
            hsiRightSql="insert into hsi_right" +
                    "(c_rightcode,c_rightname,c_class,c_sysname,c_functionno," +
                    "c_javaclass,c_javamethod,c_clientprogs,c_tablename,c_uctype,c_islimit) values" +
                    " ('%s','%s','%s','FINGARDATS','%s','%s','%s',NULL,NULL,'%s','%s');";
        }
    }

    public static String generateHead() {
        return "-- ****************************************************\r\n" +
                "-- **  英文表名：HSI_RIGHT\r\n" +
                "-- ****************************************************\r\n";
    }

    @Override
    public String generateInsertSql() {
        return String.format(hsiRightSql, StringUtils.valueOf(getC_rightcode()),StringUtils.valueOf(getC_rightname()),
                StringUtils.valueOf(getC_class()),StringUtils.valueOf(getC_functionno()),
                StringUtils.valueOf(getC_javaclass()),StringUtils.valueOf(getC_javamethod()),
                StringUtils.valueOf(getC_uctype()),StringUtils.valueOf(getC_islimit()));
    }

    @Override
    public String getKeyValue() {
        return this.c_functionno;
    }


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

    public static String tableName="hsi_right";
    /**
     *功能编码
     */
    private String c_rightcode="";
    private String c_rightcode_hid="";
    /**
     *UC功能号
     */
    private String c_rightname="";
    /**
     *权限类别
     */
    private String c_class="";
    /**
     *系统名称
     */
    private String c_sysname= SvcUtil.getSysName();

    private String c_functionno_hid="";
    /**
     *BO类名
     */
    private String c_javaclass="";
    /**
     *BO方法
     */
    private String c_javamethod="";
    /**
     *客户过程
     */
    private String c_clientprogs="";
    /**
     *主表名称
     */
    private String c_tablename="";
    /**
     *功能类型
     */
    private String c_uctype="";
    /**
     *日终处理时停用
     */
    private String c_islimit="";

    public String getC_rightcode() {
        return c_rightcode;
    }

    public void setC_rightcode(String c_rightcode) {
        this.c_rightcode = c_rightcode;
    }

    public String getC_rightname() {
        return c_rightname;
    }

    public void setC_rightname(String c_rightname) {
        this.c_rightname = c_rightname;
    }

    public String getC_class() {
        return c_class;
    }
    public String getC_className() {
        //"0:操作权限","2:公共权限","1:其他权限"
        if(StringUtils.isNotNullAndNotEmpty(c_class)){
            if(c_class.equals("0")){
                return "操作权限";
            }else if(c_class.equals("2")){
                return "公共权限";
            }else if(c_class.equals("1")){
                return "其他权限";
            }
        }
        return c_class;
    }

    public void setC_class(String c_class) {
        this.c_class = c_class;
    }

    public String getC_sysname() {
        return c_sysname;
    }

    public void setC_sysname(String c_sysname) {
        this.c_sysname = c_sysname;
    }

    public String getC_functionno() {
        return c_functionno;
    }

    public void setC_functionno(String c_functionno) {
        this.c_functionno = c_functionno;
    }

    public String getC_javaclass() {
        return c_javaclass;
    }

    public void setC_javaclass(String c_javaclass) {
        this.c_javaclass = c_javaclass;
    }

    public String getC_javamethod() {
        return c_javamethod;
    }

    public void setC_javamethod(String c_javamethod) {
        this.c_javamethod = c_javamethod;
    }

    public String getC_clientprogs() {
        return c_clientprogs;
    }

    public void setC_clientprogs(String c_clientprogs) {
        this.c_clientprogs = c_clientprogs;
    }

    public String getC_tablename() {
        return c_tablename;
    }

    public void setC_tablename(String c_tablename) {
        this.c_tablename = c_tablename;
    }

    public String getC_uctype() {
        return c_uctype;
    }

    public void setC_uctype(String c_uctype) {
        this.c_uctype = c_uctype;
    }

    public String getC_islimit() {
        return c_islimit;
    }

    public void setC_islimit(String c_islimit) {
        this.c_islimit = c_islimit;
    }

    public String getC_rightcode_hid() {
        return c_rightcode_hid;
    }

    public void setC_rightcode_hid(String c_rightcode_hid) {
        this.c_rightcode_hid = c_rightcode_hid;
    }

    public  String getTableName(){
        return tableName;
    }

    public String getC_functionno_hid() {
        return c_functionno_hid;
    }

    public HsiRight() {
    }

    public void setC_functionno_hid(String c_functionno_hid) {
        this.c_functionno_hid = c_functionno_hid;
    }

    public HsiRight(String c_rightcode, String c_rightname, String c_class, String c_sysname, String c_functionno, String c_javaclass, String c_javamethod, String c_clientprogs, String c_uctype, String c_islimit) {
        this.c_rightcode = c_rightcode;
        this.c_rightname = c_rightname;
        this.c_class = c_class;
        this.c_sysname = c_sysname;
        this.c_functionno = c_functionno;
        this.c_javaclass = c_javaclass;
        this.c_javamethod = c_javamethod;
        this.c_clientprogs = c_clientprogs;
        this.c_uctype = c_uctype;
        this.c_islimit = c_islimit;
    }

    @Override
    public Object clone()  {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        HsiRight comper=(HsiRight)obj;
        if(comper.getC_class().equals(this.getC_class())
                && comper.getC_tablename().equals(this.getC_tablename())
                && comper.getC_clientprogs().equals(this.getC_clientprogs())
                && comper.getC_functionno().equals(this.getC_functionno())
                && comper.getC_islimit().equals(this.getC_islimit())
                && comper.getC_javaclass().equals(this.getC_javaclass())
                && comper.getC_javamethod().equals(this.getC_javamethod())
                && comper.getC_rightcode().equals(this.getC_rightcode())
                && comper.getC_rightname().equals(this.getC_rightname())
                && comper.getC_sysname().equals(this.getC_sysname())
                && comper.getC_uctype().equals(this.getC_uctype())
                && comper.getC_functionno_hid().equals(this.getC_functionno_hid())
                && comper.getC_rightcode_hid().equals(this.getC_rightcode_hid())
                ){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "HsiRight{" +
                "c_rightcode='" + c_rightcode + '\'' +
                ", c_rightname='" + c_rightname + '\'' +
                ", c_class='" + c_class + '\'' +
                ", c_sysname='" + c_sysname + '\'' +
                ", c_functionno='" + c_functionno + '\'' +
                ", c_functionno_hid='" + c_functionno_hid + '\'' +
                ", c_javaclass='" + c_javaclass + '\'' +
                ", c_javamethod='" + c_javamethod + '\'' +
                ", c_clientprogs='" + c_clientprogs + '\'' +
                ", c_tablename='" + c_tablename + '\'' +
                ", c_uctype='" + c_uctype + '\'' +
                ", c_islimit='" + c_islimit + '\'' +
                '}';
    }

    /**
     * 生成默认uc
     * @return
     */
    public static HsiRight generateDefault(){
        HsiRight hsiRight=new HsiRight();
        hsiRight.setC_javaclass("com.fingard.rdc.devtools.app.pub.bo.CommonQueryBO");
        hsiRight.setC_javamethod("execute");
        return hsiRight;
    }

    /**
     * 转换成Vector，
     * @return
     */
    public  Vector<String> toVector(){
        Vector<String> vector=new Vector<>();
        vector.add(this.getC_rightcode());
        vector.add(this.getC_functionno());
        vector.add(this.getC_rightname());
        vector.add(this.getC_className());
        vector.add(this.getC_javaclass());
        vector.add(this.getC_javamethod());
        vector.add(this.getC_functionno_hid());
        vector.add(this.getC_rightcode_hid());
        return vector;
    }
}
