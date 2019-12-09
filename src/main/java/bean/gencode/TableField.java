package bean.gencode;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class TableField {
    private String name;
    private String fieldType;
    private String describe;

    public TableField() {
    }

    public TableField(String name, String fieldType, String describe) {
        this.name = name;
        this.fieldType = fieldType;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "TableField{" +
                "name='" + name + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
