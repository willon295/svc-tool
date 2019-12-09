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
public class ClassMethod {
    private String describe;
    /**
     * public\protected \ private
     */
    private String permission;
    /**
     * 返回类型，null为void
     */
    private String retType;
    /**
     * 泛型
     */
    private  ClassInfo generic;
    private String name;
    /**
     * 参数
     */
    private List<ClassField> params;
    /**
     * 方法体内容
     */
    private String content;
    /**
     * 返回值，void时不需用
     */
    private String retValue;

    /**
     * 是否是重写的方法
     */
    private boolean isOverride=false;
    /**
     * 是否是静态的
     */
    private boolean isstatic=false;

    public ClassMethod() {
        params=new ArrayList<>();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRetType() {
        return retType;
    }

    public void setRetType(String retType) {
        this.retType = retType;
    }

    public ClassInfo getGeneric() {
        return generic;
    }

    public void setGeneric(ClassInfo generic) {
        this.generic = generic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassField> getParams() {
        return params;
    }

    public void setParams(List<ClassField> params) {
        this.params = params;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRetValue() {
        return retValue;
    }

    public void setRetValue(String retValue) {
        this.retValue = retValue;
    }

    public boolean isOverride() {
        return isOverride;
    }

    public void setOverride(boolean override) {
        isOverride = override;
    }

    public boolean isIsstatic() {
        return isstatic;
    }

    public void setIsstatic(boolean isstatic) {
        this.isstatic = isstatic;
    }
}
