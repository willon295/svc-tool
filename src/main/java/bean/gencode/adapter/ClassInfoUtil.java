package bean.gencode.adapter;

import bean.gencode.ClassInfo;


import java.util.*;

/**
 * 功能说明:
 * @author: lyd
 * 开发时间: 2018-10-15
 */
public class ClassInfoUtil {
    private static Map<String,ClassInfo> classInfoMap;
    static {
        classInfoMap =new HashMap<>(getClassInfoMap());
    }
    /**
     * 获取内置的类信息
     * @return
     */
    public static  Map<String,ClassInfo> getClassInfoMap(){
        Map<String,ClassInfo> map=new LinkedHashMap<>();

        ClassInfo bigDecimal=new ClassInfo();
        bigDecimal.setPackageStr("java.math");
        bigDecimal.setClassName("BigDecimal");
        map.put("BigDecimal",bigDecimal);

        ClassInfo date=new ClassInfo();
        date.setPackageStr("java.util");
        date.setClassName("Date");
        map.put("Date",date);

        ClassInfo BaseTenantBizDto=new ClassInfo();
        BaseTenantBizDto.setPackageStr("com.fingard.ats.core.db.common.dto");
        BaseTenantBizDto.setClassName("BaseTenantBizDto");
        map.put("BaseTenantBizDto",BaseTenantBizDto);

        ClassInfo BaseTenantDataBizDto=new ClassInfo();
        BaseTenantDataBizDto.setPackageStr("com.fingard.ats.core.db.common.dto");
        BaseTenantDataBizDto.setClassName("BaseTenantDataBizDto");
        map.put("BaseTenantDataBizDto",BaseTenantDataBizDto);

        ClassInfo AtsCommonObject=new ClassInfo();
        AtsCommonObject.setPackageStr("com.fingard.ats.core.common.beans");
        AtsCommonObject.setClassName("AtsCommonObject");
        map.put("AtsCommonObject",AtsCommonObject);

        return map;
    }
    /**
     * 获取内置的类信息
     * @return
     */
    public static  Map<String,ClassInfo> getDtoParentClassInfoMap(){
        Map<String,ClassInfo> map=new LinkedHashMap<>();

        ClassInfo BaseTenantBizDto=new ClassInfo();
        BaseTenantBizDto.setPackageStr("com.fingard.ats.core.db.common.dto");
        BaseTenantBizDto.setClassName("BaseTenantBizDto");
        map.put("BaseTenantBizDto",BaseTenantBizDto);

        ClassInfo BaseTenantDataBizDto=new ClassInfo();
        BaseTenantDataBizDto.setPackageStr("com.fingard.ats.core.db.common.dto");
        BaseTenantDataBizDto.setClassName("BaseTenantDataBizDto");
        map.put("BaseTenantDataBizDto",BaseTenantDataBizDto);

        ClassInfo AtsCommonObject=new ClassInfo();
        AtsCommonObject.setPackageStr("com.fingard.ats.core.common.beans");
        AtsCommonObject.setClassName("AtsCommonObject");
        map.put("AtsCommonObject",AtsCommonObject);

        return map;
    }
    public Map<String,ClassInfo> getAllClassInfoMap(){
        return classInfoMap;
    }

    public static List<String> getDtoNotGenField(){
        List<String> retList=new ArrayList<>();
        retList.add("urid");
        retList.add("tenantid");
        retList.add("createdby");
        retList.add("createdon");
        retList.add("lastmodifiedby");
        retList.add("lastmodifiedon");
        retList.add("rowversion");
        return  retList;
    }
}
