package bean.gencode;

/**
 * 功能说明:生成db代码显示配置
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class GenCodeViewConfig {

    /**
     * 表名称
     */
    private String tableName;
    /**
     * 类名称
     */
    private String className;
    /**
     * 包名
     */
    private String packageName;
    /**
     * dto继承类名称
     */
    private String dtoExtendClassName;
    /**
     * 操作用户
     */
    private String userName;
    /**
     * 生成目录
     */
    private String filePath;

    public GenCodeViewConfig() {
    }

    public GenCodeViewConfig(String tableName, String className, String packageName, String dtoExtendClassName, String userName, String filePath) {
        this.tableName = tableName;
        this.className = className;
        this.packageName = packageName;
        this.dtoExtendClassName = dtoExtendClassName;
        this.userName = userName;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDtoExtendClassName() {
        return dtoExtendClassName;
    }

    public void setDtoExtendClassName(String dtoExtendClassName) {
        this.dtoExtendClassName = dtoExtendClassName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
