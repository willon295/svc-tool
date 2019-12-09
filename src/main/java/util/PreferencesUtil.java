package util;

import bean.SystemData;
import constant.ENSystem;

import java.util.prefs.Preferences;

/**
 * 功能说明:
 * 系统版本: 2.5.8.0
 *
 * @author: eric
 * 开发时间: 2019-05-27
 */
public class PreferencesUtil {
    private final static PreferencesUtil  util=new PreferencesUtil();
    //偏好
    Preferences preferences;

    private  ENSystem system=ENSystem.ATS;
    private  String defaultdir="";
    private   boolean isusecode=false;
    private   String createuser="admin";
    private   String default_font_size="16";
    private   String default_font_name="宋体";
    private   Integer winWidth=1200;
    private   Integer winHeight=800;
    private   boolean isLowerCaseSql=false;

    private PreferencesUtil() {
    }
    public static PreferencesUtil getInstance(){
        return util;
    }

    public static void main(String[] args) {
        PreferencesUtil preferencesUtil= PreferencesUtil.getInstance();
        preferencesUtil.setDefault_font_name("华文仿宋");
        preferencesUtil.setDefault_font_size("30");

        preferencesUtil.refreshPreferences();

        preferencesUtil.setDefault_font_name("");
        preferencesUtil.setDefault_font_size("");
        System.out.println("清除成功："+preferencesUtil.getDefault_font_name()+","+preferencesUtil.getDefault_font_size());

        preferencesUtil.readPreferences();
        System.out.println(preferencesUtil.getDefault_font_name()+","+preferencesUtil.getDefault_font_size());



    }

    public void initPreferences(){
        preferences = Preferences.systemNodeForPackage(this.getClass());
        system=SystemData.getSystem();
        defaultdir=SystemData.getDefaultdir();
        isusecode=SystemData.isIsusecode();
        createuser=SystemData.createuser;
        default_font_size=SystemData.default_font_size.toString();
        default_font_name=SystemData.default_font_name;
        isLowerCaseSql=SystemData.isIsLowerCaseSql();
        winWidth=SystemData.winWidth;
        winHeight=SystemData.winHeight;
        readPreferences();
        refreshSysData();
    }
    public void refresh(){
        refreshPreferences();
        refreshSysData();
    }
    private void readPreferences(){
        default_font_size=preferences.get("default_font_size",default_font_size);
        default_font_name=preferences.get("default_font_name",default_font_name);
        winWidth=new Integer(preferences.get("winWidth",winWidth+""));
        winHeight=new Integer(preferences.get("winHeight",winHeight+""));
    }
    private void refreshPreferences(){
        preferences.put("system",ENSystem.ATS.getSystemName());
        preferences.put("defaultdir","");
        preferences.put("isusecode","");
        preferences.put("createuser",createuser);
        preferences.put("default_font_size",default_font_size);
        preferences.put("default_font_name",default_font_name);
        preferences.put("isLowerCaseSql","");
        preferences.put("winWidth",winWidth+"");
        preferences.put("winHeight",winHeight+"");

    }

    private void refreshSysData(){
        SystemData.default_font_size=new Integer(default_font_size);
        SystemData.default_font_name=default_font_name;
        SystemData.winWidth=winWidth;
        SystemData.winHeight=winHeight;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public ENSystem getSystem() {
        return system;
    }

    public void setSystem(ENSystem system) {
        this.system = system;
    }

    public String getDefaultdir() {
        return defaultdir;
    }

    public void setDefaultdir(String defaultdir) {
        this.defaultdir = defaultdir;
    }

    public boolean isIsusecode() {
        return isusecode;
    }

    public void setIsusecode(boolean isusecode) {
        this.isusecode = isusecode;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getDefault_font_size() {
        return default_font_size;
    }

    public void setDefault_font_size(String default_font_size) {
        this.default_font_size = default_font_size;
    }

    public String getDefault_font_name() {
        return default_font_name;
    }

    public void setDefault_font_name(String default_font_name) {
        this.default_font_name = default_font_name;
    }

    public boolean isLowerCaseSql() {
        return isLowerCaseSql;
    }

    public void setLowerCaseSql(boolean lowerCaseSql) {
        isLowerCaseSql = lowerCaseSql;
    }

    public Integer getWinWidth() {
        return winWidth;
    }

    public void setWinWidth(Integer winWidth) {
        this.winWidth = winWidth;
    }

    public Integer getWinHeight() {
        return winHeight;
    }

    public void setWinHeight(Integer winHeight) {
        this.winHeight = winHeight;
    }
}
