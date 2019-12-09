package dao.impl;

import bean.BaseBean;
import bean.TsvcInterface;
import bean.TsvcViewconfig;
import dao.BaseDao;
import dao.TsvcViewconfigDao;
import util.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-29<br>
 * <br>
 */
public class TsvcViewconfigDaoImpl extends BaseDao<TsvcViewconfig> implements TsvcViewconfigDao {
    @Override
    public List<TsvcViewconfig> getTsvcViewconfig(String uc) throws Exception {
        String sqlWhere=" where c_businflag='DEFAULT' and C_FUNCTIONNO ='"+uc+"'";
        List<Map<String,Object>> maplist=getData(" t.* ",sqlWhere);
        List<TsvcViewconfig> list=new ArrayList<TsvcViewconfig>();
        for (Map<String,Object> map : maplist){
            TsvcViewconfig tsvcViewconfig=new TsvcViewconfig();
            BeanUtils.covertMapToBean(tsvcViewconfig,map);
            list.add(tsvcViewconfig);
        }
        return list;
    }
    @Override
    public List<TsvcViewconfig> getTsvcViewconfigHasOrder(String uc) throws Exception {
        String sqlWhere=" where c_businflag='DEFAULT' and C_FUNCTIONNO ='"+uc+"' order by t.l_no,t.c_property";
        List<Map<String,Object>> maplist=getData(" t.*,t.rowid ",sqlWhere);
        List<TsvcViewconfig> list=new ArrayList<TsvcViewconfig>();
        for (Map<String,Object> map : maplist){
            TsvcViewconfig tsvcViewconfig=new TsvcViewconfig();
            BeanUtils.covertMapToBean(tsvcViewconfig,map);
            list.add(tsvcViewconfig);
        }
        return list;
    }
    @Override
    protected String getTableName() {
        return TsvcViewconfig.tableName;
    }

    @Override
    protected String[] createUpdateFieldNames() {
        return new String[]{"c_functionno","c_businflag","c_property","c_viewlevel","c_viewtype",
                "c_dicname","c_viewname","c_reserve","l_no","c_hyperlink","c_frametype","c_canstat",
                "c_hiddenelem","c_edittype","c_inputtype","c_defaultvalue","c_limit",
                "c_midsearchname","c_event","c_searchlink","c_len","c_pattern"};
    }

    @Override
    protected String[] createInsertFieldNames() {
        return new String[]{"c_functionno","c_businflag","c_property","c_viewlevel","c_viewtype",
                "c_dicname","c_viewname","c_reserve","l_no","c_hyperlink","c_frametype","c_canstat",
                "c_hiddenelem","c_edittype","c_inputtype","c_defaultvalue","c_limit",
                "c_midsearchname","c_event","c_searchlink","c_len","c_pattern"};
    }
}
