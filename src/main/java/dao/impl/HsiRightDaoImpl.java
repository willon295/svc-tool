package dao.impl;

import bean.HsiRight;
import bean.SystemData;
import constant.ENSystem;
import dao.BaseDao;
import dao.HsiRightDao;
import util.BeanUtils;
import util.CommonUtil;
import util.StringUtils;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lyd on 2017-06-29.
 */
public class HsiRightDaoImpl extends BaseDao<HsiRight> implements HsiRightDao {
    @Override
    protected String[] createUpdateFieldNames() {
        return new String[]{"c_rightcode", "c_rightname", "c_class", "c_sysname",
                "c_functionno", "c_javaclass", "c_javamethod", "c_clientprogs", "c_tablename", "c_uctype", "c_islimit"};
    }

    @Override
    protected String[] createInsertFieldNames() {
        return new String[]{"c_rightcode", "c_rightname", "c_class",
                "c_sysname", "c_functionno", "c_javaclass", "c_javamethod", "c_clientprogs", "c_tablename", "c_uctype", "c_islimit"};
    }

    @Override
    @Deprecated
    public List<HsiRight> getAllData() throws Exception {
        List<Map<String, Object>> maplist = getAll();
        List<HsiRight> hsiRightList = new ArrayList<HsiRight>();
        for (Map<String, Object> map : maplist) {
            HsiRight hsiRight = new HsiRight();
            BeanUtils.covertMapToBean(hsiRight, map);
            hsiRightList.add(hsiRight);
        }
        return hsiRightList;
    }

    @Override
    public HsiRight getHsiRigh(String functionno) throws Exception {
        String sqlWhere = " where C_FUNCTIONNO ='" + functionno + "'";
        HsiRight hsiRight = new HsiRight();
        List<Map<String, Object>> maplist = getData(" t.*,t.C_FUNCTIONNO c_functionno_hid,C_RIGHTCODE c_rightcode_hid ", sqlWhere);
        if (maplist.size() > 0) {
            BeanUtils.covertMapToBeanWithoutNull(hsiRight, maplist.get(0));
        }
        return hsiRight;
    }

    @Override
    public List<HsiRight> getHsiRighFuzzy(String condition) throws Exception {
        List<HsiRight> hsiRightList = new ArrayList<HsiRight>();
        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
        if (SystemData.getSystem() == ENSystem.ATS) {
            String sqlWhere = " where 1=1 ";
            if (StringUtils.isNotNullAndNotEmpty(condition)) {
                condition = condition.toUpperCase().replaceAll("'","");
                if (SystemData.DataConnInfo.ORACLE.equals(SystemData.getDataConnInfo().getDrivertype())) {


                    sqlWhere += " and upper(C_FUNCTIONNO) like '%" + condition + "%' " +
                            " or upper(C_RIGHTCODE) like '%" + condition + "%'" +
                            " or upper(C_RIGHTNAME) like '%" + condition + "%' ";
                }else {
                    if (StringUtils.isNotNullAndNotEmpty(condition)) {
                        sqlWhere += " and C_FUNCTIONNO like '%" + condition + "%' " +
                                " or C_RIGHTCODE like '%" + condition + "%'" +
                                " or C_RIGHTNAME like '%" + condition + "%' ";
                    }
                }
            }

            sqlWhere += " order by C_RIGHTCODE ";
            maplist = getData(" t.*,t.C_FUNCTIONNO as c_functionno_hid,C_RIGHTCODE as c_rightcode_hid ", sqlWhere);

        } else if (SystemData.getSystem() == ENSystem.SAAS) {
            String sql = "select distinct c_functionno from tsvcinterface t where t.c_functionno like '%" + condition + "%' order by c_functionno";
            maplist = queryForList(sql);
        }
        for (Map<String, Object> map : maplist) {
            HsiRight hsiRight = new HsiRight();
            BeanUtils.covertMapToBean(hsiRight, map);
            hsiRightList.add(hsiRight);
        }
        return hsiRightList;
    }

    @Override
    public void delete(List<String> ucList) throws Exception {
        if (ucList == null || ucList.size() < 1) {
            return;
        }
        List<String> rightCodeList = getRightCode(ucList);
        String functionInstr = CommonUtil.getInString(ucList, true, "C_FUNCTIONNO", 999);
        String rightCodeInstr = CommonUtil.getInString(rightCodeList, true, "C_RIGHTCODE", 999);
        String hsi_rightsql = "DELETE FROM hsi_right where " + functionInstr;
        //String hsi_menurightsql="DELETE FROM hsi_menuright where "+rightCodeInstr;
        String tsvcsqlsql = "DELETE FROM tsvcsql where " + functionInstr;
        String tsvcinterfacesql = "DELETE FROM tsvcinterface where " + functionInstr;
        String tsvcviewconfigsql = "DELETE FROM tsvcviewconfig where " + functionInstr;
        int sucnum = executeSql(hsi_rightsql);
        //executeSql(hsi_menurightsql);
        executeSql(tsvcsqlsql);
        executeSql(tsvcinterfacesql);
        executeSql(tsvcviewconfigsql);
        if (ucList.size() != sucnum) {
            throw new Exception("需删除uc和成功删除笔数不相同");
        }
    }

    /**
     * 获得权限代码
     *
     * @param functionno
     * @return
     * @throws Exception
     */
    private List<String> getRightCode(List<String> functionno) throws Exception {
        List<String> rightCodeList = new ArrayList<String>();

        String sqlWhere = " where " + CommonUtil.getInString(functionno, true, "C_FUNCTIONNO", 999);

        List<Map<String, Object>> maplist = getData(" t.*,t.C_FUNCTIONNO c_functionno_hid ", sqlWhere);
        for (Map<String, Object> map : maplist) {
            rightCodeList.add((String) map.get("C_RIGHTCODE"));
        }
        return rightCodeList;
    }

    @Override
    public void add(List<HsiRight> ucList) throws Exception {
        for (HsiRight hsiRight : ucList) {
            if (!insert(hsiRight)) {
                throw new Exception("保存：" + hsiRight.getC_functionno() + "失败");
            }
        }

    }

    @Override
    public void edit(List<HsiRight> ucList) throws Exception {
        //先处理其他关联影响
        for (HsiRight hsiRight : ucList) {
            if (!hsiRight.getC_rightcode_hid().equals(hsiRight.getC_rightcode())) {
                //编码有修改
                String sql = "update HSI_MENURIGHT set C_RIGHTCODE='" + hsiRight.getC_rightcode() +
                        "'  where C_RIGHTCODE = '" + hsiRight.getC_rightcode_hid() + "'";
                executeSql(sql);
            }
            if (!hsiRight.getC_functionno().equals(hsiRight.getC_functionno_hid())) {
                //uc有修改
                String sql = "update  tsvcsql set C_FUNCTIONNO='" + hsiRight.getC_functionno() +
                        "' where C_FUNCTIONNO = '" + hsiRight.getC_functionno_hid() + "'";
                executeSql(sql);
                sql = "update  tsvcinterface set C_FUNCTIONNO='" + hsiRight.getC_functionno() +
                        "' where C_FUNCTIONNO = '" + hsiRight.getC_functionno_hid() + "'";
                executeSql(sql);
                sql = "update  tsvcviewconfig set C_FUNCTIONNO='" + hsiRight.getC_functionno() +
                        "' where C_FUNCTIONNO = '" + hsiRight.getC_functionno_hid() + "'";
                executeSql(sql);
            }
        }
        //更新本表
        for (HsiRight hsiRight : ucList) {
            update(hsiRight);
        }
    }

    @Override
    protected String getTableName() {
        return HsiRight.tableName;
    }

    @Override
    public void update(HsiRight hsiRight) throws Exception {
        String sql = "UPDATE hsi_right SET  c_rightcode=?,c_rightname=?,c_class=?,c_sysname=?,c_functionno=?," +
                "c_javaclass=?,c_javamethod=?,c_clientprogs=?,c_tablename=?,c_uctype=?,c_islimit=? WHERE " +
                " c_functionno=?  ";
        int tmpCount = executeSQL(sql, new Object[]{hsiRight.getC_rightcode(), hsiRight.getC_rightname(), hsiRight.getC_class(),
                hsiRight.getC_sysname(), hsiRight.getC_functionno(), hsiRight.getC_javaclass(), hsiRight.getC_javamethod(), hsiRight.getC_clientprogs(),
                hsiRight.getC_tablename(), hsiRight.getC_uctype(), hsiRight.getC_islimit(), hsiRight.getC_functionno_hid()});
        if (1 != tmpCount) {
            throw new Exception("更新行数为：" + tmpCount);
        }
    }
}
