package constant;

/**
 * Created by lyd on 2017/1/4.
 */
public enum EnActionEvent {

    //region 公共
    COMMOM_WARNING("COMMOM_WARNING",ENWarningLevel.WARNING),
    COMMOM_ERROR("COMMOM_ERROR",ENWarningLevel.ERROR),
    COMMOM_INFO("COMMOM_INFO",ENWarningLevel.INFO),


    /**
     * 登录点击
     */
    LOGINCLICK("loginclick"),
    /**
     *断开点击
     */
    LOGOUTCLICK("logoutclick"),
    /**
     *退出点击
     */
    CLOSECLICK("closeclick"),
    /**
     *关于点击
     */
    ABOUTCLICK("aboutclick"),
    /**
     *偏好点击
     */
    PREFERENCE("preference"),
    /**
     *新窗口点击
     */
    NEWTABCLICK("newtabclick"),
    /**
     *刷新字典、辅助查询缓存
     */
    REFRESHCACHE("refreshcache"),
    /**
     *更改系统列下
     */
    SWITCHSYSTEM("switchsystem"),
    /**
     *关闭所有点击
     */
    CLOSEALLTABCLICK("closealltabclick"),

    /**
     *异常
     */
    EXCEPTION("EXCEPTION"),
    /**
     *提示
     */
    INFO("INFO"),
    /**
     *警告
     */
    WARNING("WARNING"),
    /**
     *打开生成db代码窗口
     */
    OPENGENDBCODE("OPENGENDBCODE"),

    //endregion

    //region  uc定义
    /**
     * uc定义中权限类别选中事件
     */
    /**
     *uc定义插入按钮点击
     */
    UCDEFINE_INSERTCLICK("insertclick"),
    /**
     *uc定义尾加按钮点击
     */
    UCDEFINE_TAILINSERTCLICK("tailinsertclick"),
    /**
     *uc定义删除按钮点击
     */
    UCDEFINE_DELCLICK("delclick"),
    /**
     *uc定义保存按钮点击
     */
    UCDEFINE_SAVECLICK("saveclick"),
    /**
     *uc定义查询按钮点击
     */
    UCDEFINE_QUERYCLICK("queryclick"),
    UCDEFINE_CLASSSELECT("UCDEFINECLASSSELECT"),
    /**
     *uc定义中功能类型选中事件
     */
    UCDEFINE_UCTYPESELECT("UCDEFINEUCTYPESELECT"),
    /**
     *uc定义中日终处理时停用选中事件
     */
    UCDEFINE_ISLIMITSELECT("UCDEFINEISLIMITSELECT"),
    //endregion

    //region  SQL维护
    /**
     * SQL维护删除
     */
    SQLMAINTAIN_DEL("SQLMAINTAIN_DEL"),
    /**
     *SQL维护存盘
     */
    SQLMAINTAIN_SAVE("SQLMAINTAIN_SAVE"),
    /**
     *SQL维护刷新
     */
    SQLMAINTAIN_REFRESH("SQLMAINTAIN_REFRESH"),
    /**
     *SQL维护sql写法帮助
     */
    SQLMAINTAIN_HELP("SQLMAINTAIN_HELP"),
    /**
     *SQL维护sql语句测试
     */
    SQLMAINTAIN_SQLTEST("SQLMAINTAIN_SQLTEST"),
    /**
     *SQL维护分页组合sql测试
     */
    SQLMAINTAIN_SQLPAGING("SQLMAINTAIN_SQLPAGING"),

    //endregion

    //region  生成sql,生成
    GENERATESQL_GEN("GENERATESQL_GEN"),
    GENERATESQL_GEN_NODATE("GENERATESQL_GEN_NODATE"),
    /**
     *生成sql,打开文件
     */
    GENERATESQL_OPENFILESEL("GENERATESQL_OPENFILESEL"),
    /**
     *生成sql,保存
     */
    GENERATESQL_SAVE("GENERATESQL_SAVE"),
    /**
     *打开生成文件目录
     */
    GENERATESQL_OPEN("GENERATESQL_OPEN"),
    /**
     *保存成功
     */
    GENERATESQL_SUCSAVE("GENERATESQL_SUCSAVE"),
    //endregion

    //region  UC输入定义-初始化输入输出
    UCIN_INITIN("UCIN_INITIN"),
    /**
     * UC输入定义-初始化输入输出
     */
    UCIN_INITOUT("UCIN_INIT"),
    /**
     * UC输入定义-拷贝自
     */
    UCIN_COPY("UCIN_COPY"),
    /**
     * UC输入定义-插入
     */
    UCIN_INSERT("UCIN_INSERT"),
    /**
     * UC输入定义-尾加
     */
    UCIN_TAILINSERT("UCIN_TAILINSERT"),
    /**
     *UC输入定义-删除
     */
    UCIN_DEL("UCIN_DEL"),
    /**
     * UC输入定义-存盘
     */
    UCIN_SAVE("UCIN_SAVE"),
    /**
     * UC输入定义-初刷新
     */
    UCIN_REFRESH("UCIN_REFRESH"),
    /**
     * UC输入定义-上移
     */
    UCIN_UP("UCIN_UP"),
    /**
     * UC输入定义-下移
     */
    UCIN_DOWN("UCIN_DOWN"),
    /**
     * 检查条件
     */
    UCIN_TESTCONDTION("UCIN_TESTCONDTION"),
    UCIN_CONDITIONSELECT("UCIN_CONDITIONSELECT"),
    //endregion
    //region  UC输出定义-初始化输出输出
    UCOUT_INIT("UCOUT_INIT"),
    /**
     * UC输出定义-拷贝自
     */
    UCOUT_COPY("UCOUT_COPY"),
    /**
     * UC输出定义-插入
     */
    UCOUT_INSERT("UCOUT_INSERT"),
    /**
     * UC输出定义-尾加
     */
    UCOUT_TAILINSERT("UCOUT_TAILINSERT"),
    /**
     *UC输出定义-删除
     */
    UCOUT_DEL("UCOUT_DEL"),
    /**
     * UC输出定义-存盘
     */
    UCOUT_SAVE("UCOUT_SAVE"),
    /**
     * UC输出定义-初刷新
     */
    UCOUT_REFRESH("UCOUT_REFRESH"),
    /**
     * UC输出定义-上移
     */
    UCOUT_UP("UCOUT_UP"),
    /**
     * UC输出定义-下移
     */
    UCOUT_DOWN("UCOUT_DOWN"),
//endregion
    //region 服务端事件
    /**
     * 后端初始化完成
     */
    SYSTEM_SUCSTART("SYSTEM_SUCSTART"),
    /**
     * 后端初始化失败
     */
    SYSTEM_FAILSTART("SYSTEM_FAILSTART"),
    //endregion


    //region 生成db代码
    GENCODE_SAVE("GENCODE_SAVE"),

    GENCODE_GEN("GENCODE_GEN"),

    GENCODE_OPEN("GENCODE_OPEN"),

    GENCODE_OPENFILESEL("GENCODE_OPENFILESEL"),

    GENCODE_TABLECHOOSED("GENCODE_TABLECHOOSED"),

    //endregion

    //偏好保存
    PREFERENCE_SAVE("preference_save"),




    ;
    private String cmd;
    private String msg;
    private Object obj;
    private ENWarningLevel warningLevel=ENWarningLevel.INFO;


    EnActionEvent(String cmd) {
        this.cmd = cmd;
    }

    EnActionEvent(String cmd, String msg) {
        this.cmd = cmd;
        this.msg = msg;
    }
    EnActionEvent(String cmd, ENWarningLevel warningLevel) {
        this.cmd = cmd;
        this.warningLevel = warningLevel;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCmd(){
        return this.cmd;
    }

    public ENWarningLevel getWarningLevel() {
        return warningLevel;
    }
    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
