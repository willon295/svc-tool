package bean.gencode.adapter;

import bean.gencode.ClassField;
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
public class ClassMethodAdapter  implements IClassToFile {
    ClassMethod classMethod;

    public ClassMethodAdapter(ClassMethod classMethod) {
        this.classMethod = classMethod;
    }

    @Override
    public String toFileString() {
        StringBuilder methodStr = new StringBuilder();
        //<editor-fold desc="方法注释">
        methodStr.append("    /**\n");
        if(classMethod.getParams()!=null){
            for(ClassField fields: classMethod.getParams()){
                methodStr.append("     * @param ").append(StringUtils.lowerFirstChar(fields.getName()))
                        .append(" ").append(StringUtils.valueOf(fields.getDescribe())).append("\n");
            }
        }
        if(StringUtils.isNotNullAndNotEmpty(classMethod.getRetType())){
            methodStr.append("     * @return\n");
        }
        methodStr.append("     */\n");
        //</editor-fold>

        if(classMethod.isOverride()){
            methodStr.append("    @Override\n");
        }
        methodStr.append("    ").append(classMethod.getPermission()).append(" ");
        if(classMethod.isIsstatic()){
            methodStr.append(" static ");
        }
        if(StringUtils.isNotNullAndNotEmpty(classMethod.getRetType())){
            methodStr.append(" ").append(classMethod.getRetType());
            if(classMethod.getGeneric()!=null){
                methodStr.append("<").append(classMethod.getGeneric().getClassName()).append(">");
            }
            methodStr.append(" ");
        }else{
            methodStr.append(" void ");
        }
        //<editor-fold desc="方法参数">
        methodStr.append(classMethod.getName()).append("(");
        if(classMethod.getParams()!=null){
            StringBuilder params=new StringBuilder();
            for(ClassField fields: classMethod.getParams()){
                params.append(fields.getType()).append(" ").append(StringUtils.lowerFirstChar(fields.getName())).append(",");
            }
            if(params.length()>0){
                params.setLength(params.length()-1);
            }
            methodStr.append(params.toString());
        }
        methodStr.append("){\n");
        //</editor-fold>
        if(StringUtils.isNotNullAndNotEmpty(classMethod.getContent())){
            methodStr.append("     "+classMethod.getContent()).append(";\n");
        }
        if(StringUtils.isNotNullAndNotEmpty(classMethod.getRetType())){
            if(StringUtils.isNotNullAndNotEmpty(classMethod.getRetValue())){
                methodStr.append("        return ").append(classMethod.getRetValue()).append(";\n");
            }else{
                methodStr.append("        return ").append("null").append(";\n");
            }
        }
        methodStr.append("    }\n\n");
        return methodStr.toString();
    }
}
