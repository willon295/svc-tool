package bean.gencode.adapter;

import bean.gencode.ClassField;
import bean.gencode.ClassInfo;
import bean.gencode.ClassMethod;
import bean.gencode.IClassToFile;
import util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class DaoImplClassAdapter  extends ClassInfoAdapter implements IClassToFile {
    ClassInfo dto ;
    public DaoImplClassAdapter(ClassInfo classInfo) {
        super(classInfo);
    }

    @Override
    protected void initExtra() {
        dto =new ClassInfo();

        dto.setPackageStr(classInfo.getPackageStr()+".dto");
        dto.setClassName(classInfo.getClassName());
        referenceClsInfo.add(dto);
        classInfo.setGeneric(dto);

        ClassInfo dao =new ClassInfo();
        dao.setPackageStr(classInfo.getPackageStr()+".dao");
        dao.setClassName(classInfo.getClassName()+"Dao");
        referenceClsInfo.add(dao);

        ClassInfo serviceBaseDaoImpl =new ClassInfo();
        serviceBaseDaoImpl.setPackageStr("com.fingard.ats.core.db.common.dao.Impl");
        serviceBaseDaoImpl.setClassName("ServiceBaseDaoImpl");
        referenceClsInfo.add(serviceBaseDaoImpl);

        classInfo.getInterfaceList().add(dao.getClassName());
        classInfo.setParentClass("ServiceBaseDaoImpl");
        classInfo.setPackageStr(classInfo.getPackageStr()+".dao.impl");
        classInfo.setClassName(classInfo.getClassName()+"DaoImpl");

        initField();
        initMethod();
    }

    public void initField(){
        List<ClassField> fieldList=new ArrayList<>();
        ClassField insert=new ClassField();
        insert.setPermission("private");
        insert.setIsstatic(true);
        insert.setIsfinal(true);
        insert.setType("String[]");
        insert.setName("INSERTFIELDNAMES");
        insert.setDescribe("插入列");

        ClassField update=new ClassField();
        update.setPermission("private");
        update.setIsstatic(true);
        update.setIsfinal(true);
        update.setType("String[]");
        update.setName("UPDATEFIELDNAMES");
        update.setDescribe("更新列");

        StringBuilder insertValue=new StringBuilder();
        StringBuilder updateValue=new StringBuilder();
        for(ClassField field : classInfo.getField()){
            insertValue.append("\"").append(field.getName()).append("\",");
            if(field.isIscanupdate()){
                updateValue.append("\"").append(field.getName()).append("\",");
            }
        }
        if(insertValue.length()>0){
            insertValue.setLength(insertValue.length()-1);
        }
        if(updateValue.length()>0){
            updateValue.setLength(updateValue.length()-1);
        }
        insert.setDefaultValue("new String[]{"+insertValue.toString()+"}");
        update.setDefaultValue("new String[]{"+updateValue.toString()+"}");
        fieldList.add(insert);
        fieldList.add(update);
        classInfo.setField(fieldList);
    }

    private void initMethod(){
        ClassMethod getModelClass=new ClassMethod();
        getModelClass.setOverride(true);
        getModelClass.setPermission("protected");
        getModelClass.setName("getModelClass");
        getModelClass.setRetType("Class");
        getModelClass.setGeneric(dto);
        getModelClass.setRetValue(dto.getClassName()+".class");
        classInfo.getMethods().add(getModelClass);

        ClassMethod getTableName=new ClassMethod();
        getTableName.setOverride(true);
        getTableName.setPermission("protected");
        getTableName.setRetType("String");
        getTableName.setName("getTableName");
        getTableName.setRetValue(dto.getClassName()+".TABLE_NAME");
        classInfo.getMethods().add(getTableName);

        ClassMethod createUpdateFieldNames=new ClassMethod();
        createUpdateFieldNames.setOverride(true);
        createUpdateFieldNames.setPermission("protected");
        createUpdateFieldNames.setRetType("String[]");
        createUpdateFieldNames.setName("createUpdateFieldNames");
        createUpdateFieldNames.setRetValue("UPDATEFIELDNAMES");
        classInfo.getMethods().add(createUpdateFieldNames);

        ClassMethod createInsertFieldNames=new ClassMethod();
        createInsertFieldNames.setOverride(true);
        createInsertFieldNames.setPermission("protected");
        createInsertFieldNames.setRetType("String[]");
        createInsertFieldNames.setName("createInsertFieldNames");
        createInsertFieldNames.setRetValue("INSERTFIELDNAMES");
        classInfo.getMethods().add(createInsertFieldNames);

    }
}
