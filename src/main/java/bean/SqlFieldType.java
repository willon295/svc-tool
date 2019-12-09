package bean;

import util.StringUtils;

import java.io.Serializable;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-09<br>
 * <br>
 */
public class SqlFieldType  implements Serializable {
    private String field;
    private Integer fieldType;
    private Integer fieldLength;
    private Map<String,String> fieldNameMap;
    private List<String> fieldNameList;

    /**
     * 输入还是输出
     * @param way 0-输入，1-输出
     * @return
     */
    public TsvcInterface toTsvcInterface(Integer way){
        TsvcInterface tsvcInterface= TsvcInterface.generateDefault();
        tsvcInterface.setC_fieldname(field);
        tsvcInterface.setC_explain(field);
        if(fieldNameList.size()>0){
            if(StringUtils.isNotNullAndNotEmpty(fieldNameList.get(0))){
                if(fieldNameList.get(0).length()>10){
                    tsvcInterface.setC_explain(fieldNameList.get(0).substring(0,9));
                }else{
                    tsvcInterface.setC_explain(fieldNameList.get(0));
                }

            }
        }
        tsvcInterface.setC_property(field.toLowerCase());
        tsvcInterface.setL_len(fieldLength+"");
        tsvcInterface.setC_fieldtype(getFieldTypeStr());
        if(way==0){
            tsvcInterface.setC_flag("0");
        }else{
            tsvcInterface.setC_flag("1");
        }
        return tsvcInterface;
    }
    public SqlFieldType() {
        fieldNameMap=new HashMap<String,String>();
        fieldNameList=new ArrayList<String>();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getFieldType() {
        return fieldType;
    }
    public String getFieldTypeStr() {
        String typeStr="S";
        switch (fieldType){
            case Types.FLOAT:
                typeStr="F";
                break;
            case Types.DATE:
                typeStr="D";
                break;
            case Types.TIMESTAMP:
                typeStr="D";
                break;
            case Types.NUMERIC:
                typeStr="F";
                break;
        }
        return typeStr;
    }
    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(Integer fieldLength) {
        this.fieldLength = fieldLength;
    }

    public Map<String, String> getFieldNameMap() {
        return fieldNameMap;
    }

    public void setFieldNameMap(String key, String value) {
        this.fieldNameMap .put(key,value);
        this.fieldNameList.add(value);
    }

    public List<String> getFieldNameList() {
        return fieldNameList;
    }

    public void setFieldNameList(List<String> fieldNameList) {
        this.fieldNameList = fieldNameList;
    }
    public String getFieldName() {
        if(fieldNameList.size()>0){
            return fieldNameList.get(0);
        }else{
           return field;
        }
    }

    @Override
    public String toString() {
        return field+"-"+getFieldName();
    }
}
