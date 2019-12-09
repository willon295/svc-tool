package bean.gencode;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class ClassField  implements Cloneable {
    /**
     * 字段名称
     */
    private String name;
    /**
     *字段类型
     */
    private String type;
    /**
     *默认值
     */
    private String defaultValue;
    /**
     *字段描述
     */
    private String describe;
    /**
     *是否生成get
     */
    private boolean isgenerateget;
    /**
     *是否生成set
     */
    private boolean isgenerateset;
    private boolean isfinal;
    private boolean isstatic;
    /**
     * 是否可用于dto更新
     */
    private boolean iscanupdate;
    /**
     * public\protected \ private
     */
    private String permission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isIsgenerateget() {
        return isgenerateget;
    }

    public void setIsgenerateget(boolean isgenerateget) {
        this.isgenerateget = isgenerateget;
    }

    public boolean isIsgenerateset() {
        return isgenerateset;
    }

    public void setIsgenerateset(boolean isgenerateset) {
        this.isgenerateset = isgenerateset;
    }

    public boolean isIsfinal() {
        return isfinal;
    }

    public void setIsfinal(boolean isfinal) {
        this.isfinal = isfinal;
    }

    public boolean isIsstatic() {
        return isstatic;
    }

    public void setIsstatic(boolean isstatic) {
        this.isstatic = isstatic;
    }

    public boolean isIscanupdate() {
        return iscanupdate;
    }

    public void setIscanupdate(boolean iscanupdate) {
        this.iscanupdate = iscanupdate;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "ClassField{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", describe='" + describe + '\'' +
                ", isgenerateget=" + isgenerateget +
                ", isgenerateset=" + isgenerateset +
                ", isfinal=" + isfinal +
                ", isstatic=" + isstatic +
                ", iscanupdate=" + iscanupdate +
                ", permission='" + permission + '\'' +
                '}';
    }
}
