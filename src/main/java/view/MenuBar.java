package view;

import constant.EnActionEvent;
import control.MyActionListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lyd on 2017/1/4.
 */
public class MenuBar extends JMenuBar {
    private Font font=new Font("sanserif",Font.PLAIN,12);
    private MyActionListener myActionListener;
    private JMenu conversationMenu;     //会话菜单
    private JMenuItem loginItem;
    private JMenuItem logoutItem;
    private JMenuItem closeItem;
    private JMenu helpMenu;     //帮助菜单
    private JMenuItem aboutItem;
    //偏好
    private JMenuItem preferenceItem;


    private void initBar(){

        initconversationMenu();
        inithelpMenu();
        this.add(conversationMenu);
        this.add(helpMenu);

    }
    private void initconversationMenu(){
        conversationMenu=new JMenu("会话");
        loginItem = new JMenuItem("登录");
        loginItem.setActionCommand(EnActionEvent.LOGINCLICK.getCmd());
        logoutItem = new JMenuItem("断开");
        logoutItem.setActionCommand(EnActionEvent.LOGOUTCLICK.getCmd());
        closeItem =new JMenuItem("退出");
        closeItem.setActionCommand(EnActionEvent.CLOSECLICK.getCmd());
        //添加监听
        loginItem.addActionListener(myActionListener);
        logoutItem.addActionListener(myActionListener);
        closeItem.addActionListener(myActionListener);
        //添加到菜单
        conversationMenu.add(loginItem);
        conversationMenu.add(logoutItem);
        conversationMenu.addSeparator();
        conversationMenu.add(closeItem);
        //设置字体
        conversationMenu.setFont(font);


    }
    private void inithelpMenu(){
        helpMenu=new JMenu("其他");

        aboutItem=new JMenuItem("关于");
        aboutItem.setActionCommand(EnActionEvent.ABOUTCLICK.getCmd());
        //添加监听
        aboutItem.addActionListener(myActionListener) ;


        preferenceItem=new JMenuItem("偏好");
        preferenceItem.setActionCommand(EnActionEvent.PREFERENCE.getCmd());
        //添加监听
        preferenceItem.addActionListener(myActionListener);

        //添加到菜单
        helpMenu.add(preferenceItem);
        helpMenu.add(aboutItem);
        //设置字体
        helpMenu.setFont(font);

    }
    public MenuBar(MyActionListener myActionListener) {
        this.myActionListener = myActionListener;
        initBar();
    }
}
