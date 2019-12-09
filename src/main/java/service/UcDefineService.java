package service;

import bean.HsiRight;

import java.util.List;

/**
 * Created by lyd on 2017-06-30.
 * uc定义
 */
public interface UcDefineService {
    public List<HsiRight> getAllUc() ;
    public List<HsiRight> getAllUc( String condition) ;
    public HsiRight getUc(String functionno) ;
    /**
     *
     * @param delList
     * @param addUcList
     * @param editUcList
     */
    public void save(List<String> delList,List<HsiRight> addUcList,List<HsiRight> editUcList) ;

}
