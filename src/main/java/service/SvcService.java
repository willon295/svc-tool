package service;

import bean.SqlFieldType;
import bean.TsvcInterface;
import bean.TsvcViewconfig;
import constant.EnActionEvent;
import control.MyActionListener;

import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public interface SvcService {
    public void initSystem();
    /**
     * 生成sql语句
     * @param uc
     * @return
     */
    public String generateSql(String uc) ;

    public Vector<Vector<String>> ucTsvcInterfaceToVector(List<TsvcInterface> uc) ;
    public List<TsvcInterface> getUcTsvcInterface(String uc);
    public Vector<Vector<String>> ucTsvcViewconfigToVector(List<TsvcViewconfig> list) ;
    public  List<TsvcViewconfig> getUcTsvcViewconfig(String uc);
    /**
     * 获得字典
     * @return
     */
    public List<String> getDictionies() throws Exception;
    /**
     * 获得字典和字典缓存
     * @return
     */
    public List<String> getDictionieAndCache() throws Exception;
    /**
     * 获得辅助查询
     * @return
     */
    public Map<String,String> getMidsearch() throws Exception;

    /**
     * 先删除，后保存。
     * @param tsvcInterfaceList 同一uc的输出项
     */
    public void saveTsvcInterface(List<TsvcInterface> tsvcInterfaceList,String uc);

    /**
     * 先删除，后保存。
     * @param tsvcViewconfigsList 同一uc的输出项
     */
    public void saveTsvcViewconfig(List<TsvcViewconfig> tsvcViewconfigsList,String uc);

    /**
     * 获取输入输出定义的输出的字段
     * @param uc
     * @return
     */
    public List<TsvcInterface> getOutTsvcInterface(String uc);
    public List<SqlFieldType> findSqlField(String uc);
    public void setViewListener(MyActionListener viewListener);
    public  void throwErrorMsg(EnActionEvent enActionEvent, String msg);
    public boolean exchange(TsvcInterface current,TsvcInterface opp);
    public boolean exchange(TsvcViewconfig current,TsvcViewconfig opp);

    public void testingCondition(String uc);

}
