package view;

import control.MyActionListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lyd on 2017/5/10.
 * 中间最外层panel
 */
public class CenterPanel extends JPanel  {

    private MyActionListener myActionListener;
    private JTabbedPane jTabbedPane;
    public CenterPanel( MyActionListener myActionListener) {
        super();
        this.myActionListener = myActionListener;
        init();
    }

    public void addTab(String titleName){
        if(titleName==null || titleName.equals("")){
            titleName="Home";
        }
        new CenterpanelTab(myActionListener,jTabbedPane,titleName);
    }
    public void closeAllTab(){
        jTabbedPane.removeAll();
    }
    private void init(){
        jTabbedPane=new JTabbedPane();
        setLayout(new GridLayout(1, 1));
        this.add(jTabbedPane);
    }



}
