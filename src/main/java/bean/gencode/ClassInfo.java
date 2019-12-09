package bean.gencode;


import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class ClassInfo implements Cloneable{
    public final static String TYPE_CLASS="class";
    public final static String TYPE_INTERFACE="interface";
    /**
     * 包名
     */
    private String packageStr;
    /**
     * 类名
     */
    private String className;
    /**
     * 类型、class、interface
     */
    private String type;
    /**
     * 继承泛型
     */
    private ClassInfo generic;
    /**
     * 继承接口名称
     */
    private List<String> interfaceList;
    /**
     * 父类
     */
    private String parentClass;
    /**
     * 成员
     */
    private List<ClassField> field;
    /**
     * 方法
     */
    private List<ClassMethod> methods;

    private String describe;

    private String tableName;

    private String createUser;

    public ClassInfo() {
        interfaceList=new ArrayList<>();
        field=new ArrayList<>();
        methods=new ArrayList<>();
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ClassInfo getGeneric() {
        return generic;
    }

    public void setGeneric(ClassInfo generic) {
        this.generic = generic;
    }

    public List<String> getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(List<String> interfaceList) {
        this.interfaceList = interfaceList;
    }

    public String getParentClass() {
        return parentClass;
    }

    public void setParentClass(String parentClass) {
        this.parentClass = parentClass;
    }

    public List<ClassField> getField() {
        return field;
    }

    public void setField(List<ClassField> field) {
        this.field = field;
    }

    public List<ClassMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<ClassMethod> methods) {
        this.methods = methods;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "packageStr='" + packageStr + '\'' +
                ", className='" + className + '\'' +
                ", type='" + type + '\'' +
                ", generic=" + generic +
                ", interfaceList=" + interfaceList +
                ", parentClass=" + parentClass +
                ", field=" + field +
                ", methods=" + methods +
                ", describe='" + describe + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ClassInfo obj=(ClassInfo)super.clone();
        List<String> interfaceList=new ArrayList<>(obj.getInterfaceList());
        obj.setInterfaceList(interfaceList);

        if (obj.getField()!=null) {
            List<ClassField> field=new ArrayList<>(obj.getField());
            obj.setField(field);
        }

        if (obj.getMethods()!=null) {
            List<ClassMethod> field=new ArrayList<>(obj.getMethods());
            obj.setMethods(field);
        }
        return obj;
    }
    public String getFullName(){
        return packageStr+"."+className;
    }
}
