package bean.gencode.adapter;

import bean.gencode.ClassInfo;
import bean.gencode.IClassToFile;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class DaoClassAdapter  extends ClassInfoAdapter implements IClassToFile {
    public DaoClassAdapter(ClassInfo classInfo) {
        super(classInfo);
    }

    @Override
    protected void initExtra() {
        ClassInfo dto =new ClassInfo();
        dto.setPackageStr(classInfo.getPackageStr()+".dto");
        dto.setClassName(classInfo.getClassName());
        referenceClsInfo.add(dto);
        classInfo.setGeneric(dto);

        ClassInfo ServiceBaseDao =new ClassInfo();
        ServiceBaseDao.setPackageStr("com.fingard.ats.core.db.common.dao");
        ServiceBaseDao.setClassName("ServiceBaseDao");
        referenceClsInfo.add(ServiceBaseDao);

        classInfo.setType(ClassInfo.TYPE_INTERFACE);
        classInfo.setField(null);
        classInfo.setParentClass("ServiceBaseDao");
        classInfo.setPackageStr(classInfo.getPackageStr()+".dao");
        classInfo.setClassName(classInfo.getClassName()+"Dao");
    }
}
