package view;

import control.MyActionListener;
import view.centercontent.CenterContentPanel;
import view.factory.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by lyd on 2017/5/10.
 * 外层tab页
 */
public class CenterpanelTab implements MouseListener {
    private MyActionListener myActionListener;
    private JTabbedPane jTabbedPane;

    private CenterContentPanel content;
    private JPanel title;

    private JLabel titleName;
    private JLabel titleX;

    public CenterpanelTab(MyActionListener myActionListener,JTabbedPane jTabbedPane,String titlename) {
        this.myActionListener = myActionListener;
        this.jTabbedPane=jTabbedPane;
        init(titlename);
    }
    public void setTitle(String title){
        titleName.setText(title);
    }
    private void  init(String titlename){
        content=new CenterContentPanel(myActionListener,this);
        //标题
        title=new JPanel();
       /* GridLayout gl= new GridLayout(1,1,10,0);
        title.setLayout(gl);*/
        titleName=new JLabel(titlename);
        //设置字体
        titleName.setFont(FontFactory.getTabtitleName());
        titleName.setHorizontalAlignment(JLabel.LEFT);
        //关闭label的显示
        titleX=new JLabel("x");
        titleX.setFont(FontFactory.getTabtitleX());
        titleX.setHorizontalAlignment(JLabel.RIGHT);
        //titlePanel添加名字和x
        title.add(titleName);
        title.add(new JLabel("  "));
        title.add(titleX);
        titleX.addMouseListener(this);
        //
        jTabbedPane.addTab("",content);
        jTabbedPane.setTabComponentAt(jTabbedPane.indexOfComponent(content),title);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        content.close();
        jTabbedPane.remove(jTabbedPane.indexOfComponent(content));
        content=null;
        System.gc();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        titleX.setFont(FontFactory.getTabtitleXOn());
        titleX.setForeground(Color.red);
        //titleX.setText("x");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        titleX.setFont(FontFactory.getTabtitleXOut());
        titleX.setForeground(Color.BLACK);
        //titleX.setText("");
    }
}
