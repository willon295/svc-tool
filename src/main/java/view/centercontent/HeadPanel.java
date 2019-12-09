package view.centercontent;

import view.factory.ColorFactory;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Created by lyd on 2017/5/11.
 */
public class HeadPanel extends BaseJPanel {
    //uc 功能编号，
    private JLabel ucCodeLabel;
    private JTextField ucCodeField;

    //uc 功能号
    private JLabel ucNoLabel;
    private JTextField ucNoField;
    //uc 功能名称
    private JLabel ucNameLabel;
    private JTextField ucNameField;

    @Override
    public void close() {

    }

    public HeadPanel() {
        init();
    }

    /**
     *
     * @param ucCode uc的code ak001
     * @param ucNo   uc的no UC_TEST
     * @param ucName uc的name
     */
    public void setHeadValue(String ucCode,String ucNo,String ucName){
        ucCodeField.setText(ucCode);
        ucNoField.setText(ucNo);
        ucNameField.setText(ucName);
    }
    public String getUcNo(){
        return ucNoField.getText();
    }
    public String getUcCodeField(){
        return ucCodeField.getText();
    }
    private void init(){
        ucCodeLabel=new JLabel("UC功能编号：");
        ucNoLabel=new JLabel("UC功能号：");
        ucNameLabel=new JLabel("名称：");

        ucCodeLabel.setFont(FontFactory.getDefaultFont());
        ucNoLabel.setFont(FontFactory.getDefaultFont());
        ucNameLabel.setFont(FontFactory.getDefaultFont());


        ucCodeField=new JTextField("");
        ucNoField=new JTextField("");
        ucNameField=new JTextField("");

        ucCodeField.setFont(FontFactory.getDefaultFont());
        ucNoField.setFont(FontFactory.getDefaultFont());
        ucNameField.setFont(FontFactory.getDefaultFont());

        //region 设置不可编辑
        ucCodeField.setEditable(false);
        ucNoField.setEditable(false);
        ucNameField.setEditable(false);
        //endregion

        //region 布局管理
        GridBagLayout layout=new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill=GridBagConstraints.BOTH;
        constraints.weightx=0.0;
        layout.setConstraints(ucCodeLabel,constraints);
        constraints.weightx=1.0;
        layout.setConstraints(ucCodeField,constraints);
        constraints.weightx=0.0;
        layout.setConstraints(ucNoLabel,constraints);
        constraints.weightx=1.0;
        layout.setConstraints(ucNoField,constraints);
        constraints.weightx=0.0;
        layout.setConstraints(ucNameLabel,constraints);
        constraints.weightx=1.0;
        layout.setConstraints(ucNameField,constraints);
        //endregion


        //region 添加标签
        this.add(ucCodeLabel);
        this.add(ucCodeField);
        this.add(ucNoLabel);
        this.add(ucNoField);
        this.add(ucNameLabel);
        this.add(ucNameField);
        //endregion

        //添加border
        this.setBorder(new MatteBorder(10,5,10,5, ColorFactory.getNormalColor()));

    }
}
