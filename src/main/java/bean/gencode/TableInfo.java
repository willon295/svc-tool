package bean.gencode;

import bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class TableInfo extends BaseBean {
    private String name;
    private String describe;
    private List<TableField> fields;

    public TableInfo() {
        fields=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        this.fields = fields;
    }

    @Override
    public String generateInsertSql() {
        return null;
    }

    @Override
    public List<String> getKeyStr() {
        return null;
    }

    @Override
    public String getKeyValue() {
        return null;
    }

    @Override
    public List<String> getKeyValueStr() {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", fields=" + fields +
                '}';
    }
}
