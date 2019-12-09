package view.centercontent;

import bean.SystemData;
import constant.EnActionEvent;
import control.MyActionListener;
import util.PreferencesUtil;
import util.StringUtils;
import view.component.SButton;
import view.factory.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 功能说明:
 * 系统版本: 2.5.8.0
 *
 * @author: eric
 * 开发时间: 2019-05-27
 */
public class PreferencesWin  extends BaseJPanel   implements ActionListener {

    private SButton saveBtn=new SButton("保存");
    PreferencesUtil preferencesUtil=PreferencesUtil.getInstance();

    JTextField fontName =new JTextField("",15);
    JTextField fontSize =new JTextField("",15);
    JTextField winWidth =new JTextField("",15);
    JTextField winHeight =new JTextField("",15);
    JTextField autoGenSqlTpye =new JTextField("",15);


    static  PreferencesWin win = new PreferencesWin();
    private PreferencesWin() {
        init();
    }
    public static PreferencesWin getInstance(MyActionListener myActionListener){
        win.setMyActionListener(myActionListener);
        return win;
    }
    private void init(){
      /*  GridLayout layout=new GridLayout(5,2);
        this.setLayout(layout);*/

        JPanel frontNamePanel =new JPanel();
        JLabel jLabel=new JLabel("字体：");
        jLabel.setFont(FontFactory.getDefaultFont());
        fontName.setFont(FontFactory.getDefaultFont());
        frontNamePanel.add(jLabel);
        frontNamePanel.add(fontName);


        JPanel frontSizePanel =new JPanel();
        jLabel=new JLabel("字体大小：");
        jLabel.setFont(FontFactory.getDefaultFont());
        fontSize.setFont(FontFactory.getDefaultFont());
        frontSizePanel.add(jLabel);
        frontSizePanel.add(fontSize);

        jLabel=new JLabel("自动生成sql类型：");
        jLabel.setFont(FontFactory.getDefaultFont());
        autoGenSqlTpye.setFont(FontFactory.getDefaultFont());
        frontSizePanel.add(jLabel);
        frontSizePanel.add(autoGenSqlTpye);


        JPanel winSizePanel =new JPanel();
        jLabel=new JLabel("起始窗口宽度：");
        jLabel.setFont(FontFactory.getDefaultFont());
        winWidth.setFont(FontFactory.getDefaultFont());
        winSizePanel.add(jLabel);
        winSizePanel.add(winWidth);

        jLabel=new JLabel("起始窗口高度：");
        jLabel.setFont(FontFactory.getDefaultFont());
        winHeight.setFont(FontFactory.getDefaultFont());
        winSizePanel.add(jLabel);
        winSizePanel.add(winHeight);



        JPanel btnPanel =new JPanel();
        saveBtn.setActionCommand(EnActionEvent.PREFERENCE_SAVE.getCmd());
        saveBtn.addActionListener(this);
        btnPanel.add(saveBtn);

        this.add(frontNamePanel);
        this.add(frontSizePanel);
        this.add(winSizePanel);
        this.add(btnPanel);

        fontName.setText(preferencesUtil.getDefault_font_name());
        fontSize.setText(preferencesUtil.getDefault_font_size());
        winWidth.setText(preferencesUtil.getWinWidth()+"");
        winHeight.setText(preferencesUtil.getWinHeight()+"");
        autoGenSqlTpye.setText(SystemData.AutoGenSqlTpye);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(EnActionEvent.PREFERENCE_SAVE.getCmd())){
            String fontNameStr=fontName.getText();
            String fontSizeStr=fontSize.getText();
            String widthStr=winWidth.getText();
            String heightStr=winHeight.getText();
            String autoGenSqlTpyeStr=autoGenSqlTpye.getText();

            if(StringUtils.isNullOrEmpty(fontNameStr)){
                fontNameStr=preferencesUtil.getDefault_font_name();
            }
            if(StringUtils.isNotNullAndNotEmpty(autoGenSqlTpyeStr)){
                SystemData.AutoGenSqlTpye=autoGenSqlTpyeStr;
            }
            int  width;
            int   height;
            try {
                int  fontsize=new Integer(fontSizeStr);
            } catch (NumberFormatException e1) {
                showWarningMsg("字体大小需要为整型数字");
                return;
            }
            try {
                width=new Integer(widthStr);
            } catch (NumberFormatException e1) {
                showWarningMsg("宽度需要为整型数字！");
                return;
            }
            try {
                height=new Integer(heightStr);
            } catch (NumberFormatException e1) {
                showWarningMsg("高度需要为整型数字！");
                return;
            }

            preferencesUtil.setDefault_font_name(fontNameStr);
            preferencesUtil.setDefault_font_size(fontSizeStr);
            preferencesUtil.setWinHeight(height);
            preferencesUtil.setWinWidth(width);



            preferencesUtil.refresh();

            showWarningMsg("设置成功，页面重绘时会生效，部分需要重启后生效！");

        }
    }

    @Override
    public void close() {

    }
    private void showWarningMsg(String msg){
        JOptionPane.showMessageDialog(this,msg,"提示",JOptionPane.WARNING_MESSAGE);

    }
}
