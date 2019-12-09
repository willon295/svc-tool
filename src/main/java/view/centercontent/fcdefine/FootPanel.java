package view.centercontent.fcdefine;

import bean.HsiRight;
import constant.EnActionEvent;
import control.MyActionListener;
import util.StringUtils;
import view.centercontent.BaseJPanel;
import view.centercontent.UcDefineMaintain;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lyd on 2017/5/16.
 *  * 功能表定义维护-单个uc定义  hsi_right
 */
public class FootPanel  extends BaseJPanel{
    public static  String OPTTYPE_MOD="mod";
    public static  String OPTTYPE_ADD="add";
    private UcDefineMaintain ucDefineMaintain;
    //暂存当前。如果有更改依据currentUc去更改数据
    private String currentUc="";
    private HsiRight newUcData;
    private HsiRight oldUcData;
    private String opttype=OPTTYPE_ADD;//mod、add
    private String index="0";

    //监听文本框变动
    private FootPanelDocumentListener textFiledListener;
    //监听普通事件
    private FootPanelControl footPanelControl;

    private ActionListener actionListener;
    /**
     * 功能编号
     */
    private JLabel rightcodelabel=new JLabel("功能编号：");
    private JTextField rightcodetextfield=new JTextField();
    /**
     * UC功能号
     */
    private JLabel functionnolabel=new JLabel("UC功能号：");
    private JTextField functionnotextfield=new JTextField();

    /**
     *权限名称
     */
    private JLabel rightnamelabel=new JLabel("权限名称：");
    private JTextField rightnametextfield=new JTextField();
    /**
     * 权限类别
     */
    private JLabel classlabel=new JLabel("权限类别：");
    private JComboBox classcombobox=new JComboBox(new String[]{"0:操作权限","2:公共权限","1:其他权限"});
    /**
     *BO类名
     */
    private JLabel javaclasslabel=new JLabel("BO类名：");
    private JTextField javaclasstextfield=new JTextField("com.fingard.rdc.devtools.app.pub.bo.CommonQueryBO");
    /**
     *BO方法
     */
    private JLabel javamethodlabel=new JLabel("BO方法：");
    private JTextField javamethodtextfield=new JTextField("execute");
    /**
     *功能类型
     */
    private JLabel uctypelabel=new JLabel("功能类型：");
    private JComboBox uctypecombobox=new JComboBox(
            new String[]{"1:查询","2:修改","3:删除","4:增加","5:相关主题","6:其他"});
    /**
     *主表名称
     */
    private JLabel tablenamelabel=new JLabel("主表名称：");
    private JTextField tablenametextfield=new JTextField();
    /**
     *日终处理时停用
     */
    private JLabel islimitlabel=new JLabel("日终处理时停用：");
    private JComboBox islimitcombobox=new JComboBox(new String[]{"0:否","1:是"});


    @Override
    public void close() {
        footPanelControl=null;
    }

    public FootPanel(ActionListener actionListener, UcDefineMaintain ucDefineMaintain) {
        this.actionListener=actionListener;
        this.ucDefineMaintain=ucDefineMaintain;
        textFiledListener=new FootPanelDocumentListener();
        footPanelControl=new FootPanelControl();
        classcombobox.setActionCommand(EnActionEvent.UCDEFINE_CLASSSELECT.getCmd());
        uctypecombobox.setActionCommand(EnActionEvent.UCDEFINE_UCTYPESELECT.getCmd());
        islimitcombobox.setActionCommand(EnActionEvent.UCDEFINE_ISLIMITSELECT.getCmd());
        init();
    }

    private void init(){
        rightcodetextfield.setFont(FontFactory.getUcDefineFootFont());
        rightnametextfield.setFont(FontFactory.getUcDefineFootFont());
        functionnotextfield.setFont(FontFactory.getUcDefineFootFont());
        javaclasstextfield.setFont(FontFactory.getUcDefineFootFont());
        javamethodtextfield.setFont(FontFactory.getUcDefineFootFont());
        tablenametextfield.setFont(FontFactory.getUcDefineFootFont());
        classcombobox.setFont(FontFactory.getUcDefineFootFont());
        uctypecombobox.setFont(FontFactory.getUcDefineFootFont());
        islimitcombobox.setFont(FontFactory.getUcDefineFootFont());

        rightcodelabel.setFont(FontFactory.getUcDefineFootFont());
        functionnolabel.setFont(FontFactory.getUcDefineFootFont());
        rightnamelabel.setFont(FontFactory.getUcDefineFootFont());
        classlabel.setFont(FontFactory.getUcDefineFootFont());
        javaclasslabel.setFont(FontFactory.getUcDefineFootFont());
        javamethodlabel.setFont(FontFactory.getUcDefineFootFont());
        uctypelabel.setFont(FontFactory.getUcDefineFootFont());
        tablenamelabel.setFont(FontFactory.getUcDefineFootFont());
        islimitlabel.setFont(FontFactory.getUcDefineFootFont());

        //region 布局管理
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        //创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(rightcodelabel)
                .addComponent(classlabel)
                .addComponent(uctypelabel));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(rightcodetextfield)
                .addComponent(classcombobox)
                .addComponent(uctypecombobox));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(functionnolabel)
                .addComponent(javaclasslabel)
                .addComponent(tablenamelabel));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(functionnotextfield)
                .addComponent(javaclasstextfield)
                .addComponent(tablenametextfield));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(rightnamelabel)
                .addComponent(javamethodlabel)
                .addComponent(islimitlabel));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(rightnametextfield)
                .addComponent(javamethodtextfield)
                .addComponent(islimitcombobox));
        hGroup.addGap(5);
        layout.setHorizontalGroup(hGroup);
        //创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(rightcodelabel).addComponent(rightcodetextfield).addComponent(functionnolabel)
                .addComponent(functionnotextfield).addComponent(rightnamelabel).addComponent(rightnametextfield));
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(classlabel).addComponent(classcombobox).addComponent(javaclasslabel)
                .addComponent(javaclasstextfield).addComponent(javamethodlabel).addComponent(javamethodtextfield));
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(uctypelabel).addComponent(uctypecombobox).addComponent(tablenamelabel)
                .addComponent(tablenametextfield).addComponent(islimitlabel).addComponent(islimitcombobox));
        vGroup.addGap(10);
        //设置垂直组
        layout.setVerticalGroup(vGroup);
        //endregion
    }
    public HsiRight getFootPanelData(){
        HsiRight hsiRight= (HsiRight) oldUcData.clone();
        hsiRight.setC_rightcode(rightcodetextfield.getText());
        hsiRight.setC_rightname(rightnametextfield.getText());
        String classcomboboxstr=(String)classcombobox.getSelectedItem();
        hsiRight.setC_class(classcomboboxstr.split(":")[0]);
        hsiRight.setC_functionno(functionnotextfield.getText());
        hsiRight.setC_javaclass(javaclasstextfield.getText());
        hsiRight.setC_javamethod(javamethodtextfield.getText());
        hsiRight.setC_tablename(tablenametextfield.getText());
        String uctypecomboboxstr=(String)uctypecombobox.getSelectedItem();
        hsiRight.setC_uctype(uctypecomboboxstr.split(":")[0]);
        String islimitcomboboxstr=(String)islimitcombobox.getSelectedItem();
        hsiRight.setC_islimit(islimitcomboboxstr.split(":")[0]);
        return hsiRight;
    }
    public void setFootPanelData(HsiRight hsiRight,int index,String opttype){
        //System.out.println("setFootPanelData"+index+"||"+opttype+"||"+hsiRight);
        this.opttype=opttype;
        this.index=index+"";
        //取消监听
        dataChangeListener(false);
        if(hsiRight==null){
            //清除暂存
            oldUcData=null;
            currentUc="";
            newUcData=null;
            //清除文本框
            rightcodetextfield.setText("");
            rightnametextfield.setText("");
            functionnotextfield.setText("");
            javaclasstextfield.setText("");
            javamethodtextfield.setText("");
            tablenametextfield.setText("");
            classcombobox.setSelectedIndex(0);
            uctypecombobox.setSelectedIndex(0);
            islimitcombobox.setSelectedIndex(0);
        }else{
            //保存暂存
            oldUcData=(HsiRight)hsiRight.clone();
            currentUc=hsiRight.getC_functionno_hid();
            newUcData=(HsiRight)hsiRight.clone();
            rightcodetextfield.setText(hsiRight.getC_rightcode());
            rightnametextfield.setText(hsiRight.getC_rightname());
            Integer classindex= 0;
            if (StringUtils.isNotNullAndNotEmpty(hsiRight.getC_class())) {
                classindex = Integer.parseInt(hsiRight.getC_class());
            }
            if(classindex==2){
                classindex=1;
            }else if(classindex==1){
                classindex=2;
            }
            classcombobox.setSelectedIndex(classindex);
            functionnotextfield.setText(hsiRight.getC_functionno());
            javaclasstextfield.setText(hsiRight.getC_javaclass());
            javamethodtextfield.setText(hsiRight.getC_javamethod());
            tablenametextfield.setText(hsiRight.getC_tablename());
            Integer uctype= 0;
            if (StringUtils.isNotNullAndNotEmpty(hsiRight.getC_uctype())) {
                uctype = Integer.parseInt(hsiRight.getC_uctype())-1;
            }
            uctypecombobox.setSelectedIndex(uctype);

            Integer islimit= 0;
            if (StringUtils.isNotNullAndNotEmpty(hsiRight.getC_islimit())) {
                islimit = Integer.parseInt(hsiRight.getC_islimit());
            }
            islimitcombobox.setSelectedIndex(islimit);
        }
        //加上监听
        dataChangeListener(true);
    }


    private void ucDataChange(){
        //先判是否真的修改
        newUcData=this.getFootPanelData();
        if(!newUcData.equals(oldUcData)){
           //有过修改，则触发
            if(opttype.equals(OPTTYPE_ADD)){
                ucDefineMaintain.addAddUcMap(index,newUcData);
            }else if(opttype.equals(OPTTYPE_MOD)){
                ucDefineMaintain.addEditUcMap(index,newUcData);
            }
            System.out.println("ucDataChange:"+"有修改过："+oldUcData);
        }else{
            //没有有过修改，则触发删除
            if(opttype.equals(OPTTYPE_ADD)){
                ucDefineMaintain.removeAddUcMap(index,newUcData);
            }else if(opttype.equals(OPTTYPE_MOD)){
                ucDefineMaintain.removeEditUcMap(index,newUcData);
            }
        }
    }

    /**
     *
     * @param add
     */
    private void dataChangeListener(boolean add){
        if(add){
            rightcodetextfield.getDocument().addDocumentListener(textFiledListener);
            functionnotextfield.getDocument().addDocumentListener(textFiledListener);
            rightnametextfield.getDocument().addDocumentListener(textFiledListener);
            javaclasstextfield.getDocument().addDocumentListener(textFiledListener);
            javamethodtextfield.getDocument().addDocumentListener(textFiledListener);
            tablenametextfield.getDocument().addDocumentListener(textFiledListener);
            classcombobox.addActionListener(footPanelControl);
            uctypecombobox.addActionListener(footPanelControl);
            islimitcombobox.addActionListener(footPanelControl);
        }else{
            rightcodetextfield.getDocument().removeDocumentListener(textFiledListener);
            functionnotextfield.getDocument().removeDocumentListener(textFiledListener);
            rightnametextfield.getDocument().removeDocumentListener(textFiledListener);
            javaclasstextfield.getDocument().removeDocumentListener(textFiledListener);
            javamethodtextfield.getDocument().removeDocumentListener(textFiledListener);
            tablenametextfield.getDocument().removeDocumentListener(textFiledListener);

            classcombobox.removeActionListener(footPanelControl);
            uctypecombobox.removeActionListener(footPanelControl);
            islimitcombobox.removeActionListener(footPanelControl);
        }
    }
    private void validInput(){
        HsiRight hsiRight=getFootPanelData();

        validText(hsiRight.getC_functionno(),"UC功能号");
        validText(hsiRight.getC_rightname(),"权限名称");
        validText(hsiRight.getC_rightcode(),"功能编号");
        validText(hsiRight.getC_javaclass(),"BO类名");
        validText(hsiRight.getC_javamethod(),"BO方法");

    }
    private void validText(String text,String name){
        if(StringUtils.isNotNullAndNotEmpty(text)){
            if(text.contains(" ") || text.contains("　")){
                EnActionEvent actionEvent=  EnActionEvent.WARNING;
                actionEvent.setMsg(name+":有空格");
                ActionEvent action= MyActionListener.getActionEvent(actionEvent);
                actionListener.actionPerformed(action);
                actionEvent.setMsg("");
            }
        }
    }

    class FootPanelControl implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //下拉框事件
            if(e.getActionCommand().equals(EnActionEvent.UCDEFINE_CLASSSELECT.getCmd())||
                    e.getActionCommand().equals(EnActionEvent.UCDEFINE_UCTYPESELECT.getCmd())||
                    e.getActionCommand().equals(EnActionEvent.UCDEFINE_ISLIMITSELECT.getCmd()) ){
                ucDataChange();
            }

        }
    }

    class FootPanelDocumentListener implements DocumentListener{
        @Override
        public void insertUpdate(DocumentEvent e) {
            System.out.println("insertUpdate");
            validInput();
            ucDataChange();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            System.out.println("removeUpdate");
            validInput();
            ucDataChange();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            System.out.println("changedUpdate");
            validInput();
            ucDataChange();
        }
    }

}
