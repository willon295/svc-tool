package service;

import bean.gencode.ClassInfo;
import bean.gencode.GenCodeViewConfig;
import org.apache.log4j.Logger;
import service.impl.SvcServiceImpl;
import util.LogUtil;

import java.util.List;
import java.util.Map;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public interface GenerateCodeService {
    /**
     * 获取表的 dto、dao、daoimpl
     * @return
     */
    public ClassInfo getTableCodeClass(GenCodeViewConfig codeViewConfig) throws Exception;

    /**
     *生成bean xml配置
     * @param beanid
     * @param beanFullName
     * @return
     */
    public String getBeanXml(String beanid,String beanFullName,String describe);

    /**
     * key为，表名+注释
     * value：表名
     * 用于界面获取所有表
     * @return
     */
    public Map<String, String> getTables() throws Exception;

    /**
     * key为，表名+注释
     * value：表名
     * 用于界面获取所有表
     * @return
     */
    public Map<String, String> getTableCode(GenCodeViewConfig codeViewConfig) throws Exception;
}
