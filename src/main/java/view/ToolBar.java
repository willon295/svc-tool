package view;

import constant.EnActionEvent;
import control.MyActionListener;
import util.StringUtils;
import view.component.SButton;
import view.factory.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by lyd on 2017/1/4.
 */
public class ToolBar extends JToolBar implements ItemListener {
    private SButton newtab;
    private SButton closealltab;
    private SButton refresh;
    private SButton genDbCode;
    private MyActionListener myActionListener;
    private JComboBox systemType;

    public ToolBar(MyActionListener myActionListener) {
        this.myActionListener = myActionListener;
        initToolBar();
    }
    private void initToolBar(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        newtab=new SButton("新窗口");
        newtab.setActionCommand(EnActionEvent.NEWTABCLICK.getCmd());
        newtab.addActionListener(myActionListener);
        closealltab=new SButton("关闭所有");
        closealltab.setActionCommand(EnActionEvent.CLOSEALLTABCLICK.getCmd());
        closealltab.addActionListener(myActionListener);

        refresh=new SButton("刷新缓存");
        refresh.setActionCommand(EnActionEvent.REFRESHCACHE.getCmd());
        refresh.addActionListener(myActionListener);

        systemType=new JComboBox(new String[]{"ATS","SAAS"});
        systemType.setFont(FontFactory.getDefaultFont());
        systemType.addItemListener(this);

        genDbCode=new SButton("生成db代码");
        genDbCode.setActionCommand(EnActionEvent.OPENGENDBCODE.getCmd());
        genDbCode.addActionListener(myActionListener);

        this.add(newtab);
        this.addSeparator();
        this.add(closealltab);
        this.addSeparator();
        this.add(refresh);
        this.addSeparator();
        this.add(systemType);
        this.addSeparator();
        this.add(genDbCode);
        this.addSeparator();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            //将系统信息放入msg
            myActionListener.actionPerformed( MyActionListener.getActionEvent(EnActionEvent.SWITCHSYSTEM, StringUtils.valueOf(e.getItem())));
        }
    }
}
