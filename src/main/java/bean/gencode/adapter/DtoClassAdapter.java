package bean.gencode.adapter;

import bean.gencode.ClassField;
import bean.gencode.ClassInfo;
import bean.gencode.ClassMethod;
import bean.gencode.IClassToFile;
import util.StringUtils;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class DtoClassAdapter extends ClassInfoAdapter implements IClassToFile {
    public DtoClassAdapter(ClassInfo classInfo) {
        super(classInfo);
    }

    @Override
    protected void initExtra() {
        classInfo.setPackageStr(classInfo.getPackageStr()+".dto");
        if (StringUtils.isNotNullAndNotEmpty(classInfo.getParentClass())) {
            referenceCls.add(classInfo.getParentClass());
        }
        if (classInfo.getField() != null) {
            for (ClassField field : classInfo.getField()) {
                if (!referenceCls.contains(field.getType())) {
                    referenceCls.add(field.getType());
                }
            }
        }
        if (classInfo.getInterfaceList() != null) {
            for (String interfacestr : classInfo.getInterfaceList()) {
                if (!referenceCls.contains(interfacestr)) {
                    referenceCls.add(interfacestr);
                }
            }
        }
        classInfo.getInterfaceList().add("java.io.Serializable");
        classInfo.setType(ClassInfo.TYPE_CLASS);
        //生成set、get方法
        initMethod();

        ClassField TABLE_NAME=new ClassField();
        TABLE_NAME.setPermission("public");
        TABLE_NAME.setIsstatic(true);
        TABLE_NAME.setIsfinal(true);
        TABLE_NAME.setIscanupdate(false);
        TABLE_NAME.setIsgenerateget(false);
        TABLE_NAME.setIsgenerateset(false);
        TABLE_NAME.setType("String");
        TABLE_NAME.setName("TABLE_NAME");
        TABLE_NAME.setDefaultValue("\""+classInfo.getTableName()+"\"");
        classInfo.getField().add(0,TABLE_NAME);
    }

    /**
     * set\get方法
     */
    private void initMethod(){
       /* if(classInfo.getField()!=null){
            for(ClassField field : classInfo.getField()){
                if(field.isIsgenerateset()){
                    ClassMethod classMethod=genSetMethod(field);
                    classInfo.getMethods().add(classMethod);
                }
                if(field.isIsgenerateget()){
                    ClassMethod classMethod= genGetMethod(field);
                    classInfo.getMethods().add(classMethod);
                }
            }
        }*/

        ClassMethod getTableName=new ClassMethod();
        getTableName.setOverride(true);
        getTableName.setPermission("public");
        getTableName.setRetType("String");
        getTableName.setName("getTableName");
        getTableName.setRetValue(classInfo.getClassName()+".TABLE_NAME");
        classInfo.getMethods().add(getTableName);
    }
    private ClassMethod genSetMethod(ClassField field){
        ClassMethod method=new ClassMethod();
        method.setPermission("public");
        method.setName("set"+StringUtils.upperFirstChar(field.getName()));
        method.getParams().add(field);
        method.setContent("this."+field.getName()+"="+field.getName());
        //method.setDescribe(field.getDescribe());
        return method;
    }
    private ClassMethod genGetMethod(ClassField field){
        ClassMethod method=new ClassMethod();
        method.setPermission("public");
        method.setRetType(field.getType());
        method.setName("get"+StringUtils.upperFirstChar(field.getName()));
        method.setRetValue("this."+field.getName());
        //method.setDescribe(field.getDescribe());
        return method;
    }
}
