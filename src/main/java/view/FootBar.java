package view;

import bean.SystemData;
import view.factory.FontFactory;

import javax.swing.*;

/**
 * Created by lyd on 2017/5/10.
 * 底部连接信息
 */
public class FootBar  extends JToolBar {
    private JLabel conninfo;
    private JLabel connstate;
    private JLabel errorinfo;
    private JLabel sysinfo;

    public FootBar() {
        init();
    }
    private void init(){
        conninfo=new JLabel("*");
        connstate=new JLabel("未连接数据库");
        errorinfo=new JLabel("*");
        sysinfo=new JLabel("||工具版本："+ SystemData.versionNumber);

        conninfo.setFont(FontFactory.getFootBar());
        connstate.setFont(FontFactory.getFootBar());
        errorinfo.setFont(FontFactory.getFootBar());
        sysinfo.setFont(FontFactory.getFootBar());

        this.add(conninfo);
        this.addSeparator();
        this.add(connstate);
        this.addSeparator();
        this.add(errorinfo);
        this.addSeparator();
        this.add(sysinfo);
    }
    public void changeMsg(String status,String msg){
        connstate.setText(status);
        errorinfo.setText(msg);
    }
    public void changeMsg(String conninfoStr,String status,String msg){
        conninfo.setText(conninfoStr);
        connstate.setText(status);
        errorinfo.setText(msg);
    }
}
