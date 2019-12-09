package dao;

import bean.HsiRight;

import java.util.List;

/**
 * Created by lyd on 2017-06-28.
 * 表：HSI_RIGHT。uc定义
 */
public interface HsiRightDao extends IBaseDao<HsiRight> {
    public List<HsiRight> getAllData() throws Exception;
    public HsiRight getHsiRigh(String functionno) throws Exception;

    /**
     *
     * @param condition  模糊查询条件
     * @return
     * @throws Exception
     */
    public List<HsiRight> getHsiRighFuzzy(String condition) throws Exception;

    public void delete(List<String> ucList) throws Exception;
    public void add(List<HsiRight> ucList) throws Exception;
    public void edit(List<HsiRight> ucList) throws Exception;
}
