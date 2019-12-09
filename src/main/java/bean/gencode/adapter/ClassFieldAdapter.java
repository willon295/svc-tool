package bean.gencode.adapter;

import bean.gencode.ClassField;
import bean.gencode.IClassToFile;
import util.StringUtils;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class ClassFieldAdapter implements IClassToFile {
    ClassField classField;

    public ClassFieldAdapter(ClassField classField) {
        this.classField = classField;
    }

    @Override
    public String toFileString() {
        StringBuilder fieldStr = new StringBuilder();
        if(StringUtils.isNotNullAndNotEmpty(classField.getDescribe())){
            fieldStr.append("    /**\n" +
                    "     *"+classField.getDescribe()+"\n" +
                    "     */\n");
        }
        fieldStr.append("    ").append(classField.getPermission()).append(" ");
        if(classField.isIsfinal()){
            fieldStr.append("final").append(" ");
        }
        if(classField.isIsstatic()){
            fieldStr.append("static").append(" ");
        }
        fieldStr.append(classField.getType()).append(" ")
                .append(classField.getName());
        if(StringUtils.isNotNullAndNotEmpty(classField.getDefaultValue())){
            fieldStr.append(" = ").append(classField.getDefaultValue());
        }
        fieldStr.append(";\n");
        return fieldStr.toString();
    }



    private void prepareToString(){

    }



}
