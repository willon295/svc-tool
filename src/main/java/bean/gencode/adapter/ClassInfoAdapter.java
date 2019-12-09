package bean.gencode.adapter;

import bean.gencode.ClassField;
import bean.gencode.ClassInfo;
import bean.gencode.ClassMethod;
import bean.gencode.IClassToFile;
import util.DateUtil;
import util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public abstract class ClassInfoAdapter implements IClassToFile {
    protected ClassInfo classInfo;

    /**
     * 保存新创建类信息，用于关联
     */
    protected Map<String, ClassInfo> newClsMap = new HashMap<>();

    protected List<String> referenceCls = new ArrayList<>();
    //子类添加
    protected List<ClassInfo> referenceClsInfo = new ArrayList<>();

    public ClassInfoAdapter(ClassInfo classInfo) {
        this.classInfo = classInfo;
        this.prepareToString();
        this.init();
    }

    @Override
    public String toFileString() {
        StringBuilder fileStr = new StringBuilder();
        fileStr.append(generateHead());
        fileStr.append(generateClass());

        return fileStr.toString();
    }


    private  void prepareToString(){

    }

    private void init() {
        classInfo.setType(ClassInfo.TYPE_CLASS);
        initExtra();


    }

    protected String generateHead() {
        StringBuilder head = new StringBuilder();
        head.append("package ").append(classInfo.getPackageStr()).append(";\r\n").append("\r\n");

        if (referenceCls != null) {
            for (String cls : referenceCls) {
                if (ClassInfoUtil.getClassInfoMap().containsKey(cls)) {
                    ClassInfo classInfo = ClassInfoUtil.getClassInfoMap().get(cls);
                    head.append("import ").append(classInfo.getFullName()).append(";\r\n");
                }
            }
        }
        if(referenceClsInfo!=null){
            for (ClassInfo cls : referenceClsInfo) {
                head.append("import ").append(cls.getFullName()).append(";\r\n");
            }
        }

        head.append("\r\n/**\r\n" +
                "* " + classInfo.getDescribe() + "\r\n" +
                "* \r\n" +
                "* @author " + classInfo.getCreateUser() + "\r\n" +
                "* @date " + DateUtil.getCurrentDateString() + "\r\n" +
                "*/\r\n");

        return head.toString();
    }

    protected String generateClass() {
        StringBuilder cls = new StringBuilder();
        cls.append("public").append(" ");
        if(classInfo.getType().equalsIgnoreCase(ClassInfo.TYPE_INTERFACE)){
            cls.append("interface").append(" ");
        }else{
            cls.append("class").append(" ");
        }
        cls.append(classInfo.getClassName()).append(" ");
        if(StringUtils.isNotNullAndNotEmpty(classInfo.getParentClass())){
            cls.append("extends").append(" ").append(classInfo.getParentClass());
            if(classInfo.getGeneric()!=null){
                cls.append("<").append(classInfo.getGeneric().getClassName()).append(">").append(" ");
            }
            cls.append(" ");
        }
        if(classInfo.getInterfaceList()!=null && classInfo.getInterfaceList().size()>0){
            cls.append("implements").append(" ");
            for(String iter : classInfo.getInterfaceList()){
                cls.append(iter).append(",");
            }
            cls.setLength(cls.length()-1);
            cls.append(" ");
        }
        cls.append("{").append("\r\n");
        cls.append(generateField());
        cls.append(generateMethod());
        cls.append("}");
        return cls.toString();
    }

    protected String generateField() {
        List<ClassField> fieldList=classInfo.getField();
        StringBuilder fieldStr = new StringBuilder();
        if(fieldList!=null){
            for(ClassField classField : fieldList){
                if(ClassInfoUtil.getDtoNotGenField().contains(classField.getName())){
                    continue;
                }
                IClassToFile adapter=new ClassFieldAdapter(classField);
                fieldStr.append(adapter.toFileString());
            }
        }
        return fieldStr.toString();
    }

    protected String generateMethod() {
        StringBuilder methodStr = new StringBuilder();
        if(classInfo.getMethods()!=null){
            for(ClassMethod classMethod : classInfo.getMethods()){
                IClassToFile methodAdapter=new ClassMethodAdapter(classMethod);
                methodStr.append(methodAdapter.toFileString());
            }
        }
        return methodStr.toString();
    }



    protected  void initExtra() {

    }
}
