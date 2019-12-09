package view.centercontent;

import bean.SqlFieldType;
import bean.TsvcInterface;
import constant.EnActionEvent;
import control.MyActionListener;
import service.ServiceFactory;
import service.SvcService;
import service.impl.SvcServiceImpl;
import util.BeanUtils;
import util.ConfigManager;
import view.centercontent.ucin.UcInCenterTable;
import view.component.SButton;
import view.factory.ColorFactory;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/**
 * Created by lyd on 2017/5/11.
 */
public class UcInMaintain extends BaseJPanel  implements ActionListener,DocumentListener {
    SvcService svcService= ServiceFactory.getSvcService();
    private   CenterContentPanel centerContentPanel;
    //初始化输入输出
    private JButton initInBtn=new SButton("初始化输入");
    private JButton initOutBtn=new SButton("初始化输出");
    //拷贝自
    private JButton copyBtn=new SButton("拷贝自");
    //插入
    private JButton insertBtn=new SButton("插入");
    //尾加
    private JButton tailInsertBtn=new SButton("尾加");
    //删除
    private JButton delBtn=new SButton("删除");
    //存盘
    private JButton saveBtn=new SButton("存盘");
    //刷新
    private JButton refreshBtn=new SButton("刷新");
    //上移
    private JButton upBtn=new SButton("上移");
    //下移
    private JButton downBtn=new SButton("下移");
    //检查条件
    private JButton testCondtionBtn=new SButton("检查条件");
    //输入条件sql
    private JTextArea existsArea=new JTextArea();
    private JTextArea existsOtherArea=new JTextArea();
    JScrollPane sqlexistsAreaScrollPane = new JScrollPane(existsArea);
    JScrollPane sqlexistsAreaOtherScrollPane = new JScrollPane(existsOtherArea);
    //分隔existsArea
    JSplitPane jSplitPane=new JSplitPane();

    //中间table展示
    private UcInCenterTable centerTable;
    //用于判断是否刷新
    String currentUc="";
    List<TsvcInterface> ucInDatas;
    private boolean isDataChanged=false;

    @Override
    public void close() {
        centerTable=null;
    }

    public UcInMaintain(MyActionListener myActionListener, CenterContentPanel centerContentPanel) {
        this.centerContentPanel=centerContentPanel;
        this.myActionListener= myActionListener;
        centerTable=new UcInCenterTable(myActionListener,this,centerContentPanel);
        init();
    }

    private void init(){
        //<editor-fold desc="设置命令">
        initInBtn.setActionCommand(EnActionEvent.UCIN_INITIN.getCmd());
        initOutBtn.setActionCommand(EnActionEvent.UCIN_INITOUT.getCmd());
        copyBtn.setActionCommand(EnActionEvent.UCIN_COPY.getCmd());
        insertBtn.setActionCommand(EnActionEvent.UCIN_INSERT.getCmd());
        tailInsertBtn.setActionCommand(EnActionEvent.UCIN_TAILINSERT.getCmd());
        delBtn.setActionCommand(EnActionEvent.UCIN_DEL.getCmd());
        saveBtn.setActionCommand(EnActionEvent.UCIN_SAVE.getCmd());
        refreshBtn.setActionCommand(EnActionEvent.UCIN_REFRESH.getCmd());
        upBtn.setActionCommand(EnActionEvent.UCIN_UP.getCmd());
        downBtn.setActionCommand(EnActionEvent.UCIN_DOWN.getCmd());
        testCondtionBtn.setActionCommand(EnActionEvent.UCIN_TESTCONDTION.getCmd());
        //</editor-fold>

        //<editor-fold desc="设置监听">
        initInBtn.addActionListener(this);
        initOutBtn.addActionListener(this);
        copyBtn.addActionListener(this);
        insertBtn.addActionListener(this);
        tailInsertBtn.addActionListener(this);
        delBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        refreshBtn.addActionListener(this);
        upBtn.addActionListener(this);
        downBtn.addActionListener(this);
        testCondtionBtn.addActionListener(this);
        //</editor-fold>

        //<editor-fold desc="设置字体">
       /* initInBtn.setFont(FontFactory.getBtnFont());
        initOutBtn.setFont(FontFactory.getBtnFont());
        copyBtn.setFont(FontFactory.getBtnFont());
        insertBtn.setFont(FontFactory.getBtnFont());
        tailInsertBtn.setFont(FontFactory.getBtnFont());
        delBtn.setFont(FontFactory.getBtnFont());
        saveBtn.setFont(FontFactory.getBtnFont());
        refreshBtn.setFont(FontFactory.getBtnFont());
        upBtn.setFont(FontFactory.getBtnFont());
        downBtn.setFont(FontFactory.getBtnFont());
        testCondtionBtn.setFont(FontFactory.getBtnFont());*/
        //</editor-fold>

        //<editor-fold desc="北部panel">
        JPanel northJPanel=new JPanel();
        //设置边框
        northJPanel.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));
        northJPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        northJPanel.add(initInBtn);
        northJPanel.add(initOutBtn);
       // northJPanel.add(copyBtn);
        northJPanel.add(insertBtn);
        northJPanel.add(tailInsertBtn);
        northJPanel.add(delBtn);
        northJPanel.add(saveBtn);
        northJPanel.add(refreshBtn);
        northJPanel.add(upBtn);
        northJPanel.add(downBtn);
        northJPanel.add(testCondtionBtn);
        //</editor-fold>



        //添加组件到窗体
        this.setLayout(new BorderLayout());
        this.add(northJPanel,BorderLayout.NORTH);
        this.add(centerTable,BorderLayout.CENTER);
        existsArea.setFont(FontFactory.getSqlInputFootFont());
        existsOtherArea.setFont(FontFactory.getSqlInputFootFont());
        sqlexistsAreaScrollPane.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));
        sqlexistsAreaOtherScrollPane.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));

        //设置分割线距离左边的位置。2.4貌似是正好中间的位置
        jSplitPane.setDividerLocation((int)(Integer.valueOf(ConfigManager.getWinWidth())/2.0f));
        //将两个sql面板设置在左右两边
        jSplitPane.setLeftComponent(sqlexistsAreaScrollPane);
        jSplitPane.setRightComponent(sqlexistsAreaOtherScrollPane);
        this.add(jSplitPane,BorderLayout.SOUTH);
        existsArea.getDocument().addDocumentListener(this);
        existsOtherArea.getDocument().addDocumentListener(this);
        hideExistsArea();

    }
    @Override
    public void onFocus(boolean refresh) {
        if(!currentUc.equals(centerContentPanel.getUcNo())){
            centerTable.asynReloadUc(centerContentPanel.getUcNo());
        }else{
            if(refresh){
                centerTable.asynReloadUc(centerContentPanel.getUcNo());
            }
        }
        currentUc=centerContentPanel.getUcNo();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if(centerTable.isRefresh()){
            System.out.println("正在刷新");
            return;
        }
        if(e.getActionCommand().equals(EnActionEvent.UCIN_SAVE.getCmd())){
            java.util.List<TsvcInterface> tsvcInterfaceList=centerTable.getAllColumnDatas();
            //存盘
            svcService.saveTsvcInterface(tsvcInterfaceList,centerContentPanel.getUcNo());
            //刷新
            centerTable.asynReloadUc(centerContentPanel.getUcNo());
        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_INITIN.getCmd())){
            //初始化
            List<SqlFieldType> fieldList= svcService.findSqlField(centerContentPanel.getUcNo());
            centerTable.initIn(fieldList);

        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_INITOUT.getCmd())){
            //初始化
            List<SqlFieldType> fieldList= svcService.findSqlField(centerContentPanel.getUcNo());
            centerTable.initOut(fieldList);

        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_COPY.getCmd())){

        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_INSERT.getCmd())){
            //插入
            centerTable.insert();
        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_TAILINSERT.getCmd())){
            //尾加
            centerTable.tailInsert();
        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_DEL.getCmd())){
            //删除
            centerTable.removeSelect();
        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_REFRESH.getCmd())){
            centerTable.asynReloadUc(centerContentPanel.getUcNo());
        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_UP.getCmd())){
            int index=centerTable.getCurrentSelIndex();
            if(index>0){
                if(svcService.exchange(centerTable.getCurrentRow(),centerTable.getRow(index-1))){
                    centerTable.asynReloadUc(centerContentPanel.getUcNo());
                    centerTable.cloumSelect(index-1);
                }
            }
        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_DOWN.getCmd())){
            int index=centerTable.getCurrentSelIndex();
            if(index<centerTable.getTotalCount()-1){
                if(svcService.exchange(centerTable.getCurrentRow(),centerTable.getRow(index+1))){
                    centerTable.asynReloadUc(centerContentPanel.getUcNo());
                    centerTable.cloumSelect(index+1);
                }
            }
        } if(e.getActionCommand().equals(EnActionEvent.UCIN_TESTCONDTION.getCmd())){
            svcService.testingCondition(centerContentPanel.getUcNo());
        }

    }
    public void tableSelect(int index,TsvcInterface tsvcInterface){
        if(tsvcInterface.getC_flag().equals("0")){
            if(tsvcInterface.getC_condition().equals("exists") || tsvcInterface.getC_condition().equals("not exists")){
                existsAreaSetText("");
                existsOtherAreaSetText("");
                showExistsArea();
                existsAreaSetText(tsvcInterface.getC_existvalue());
                //这里写死成mysql
                existsOtherAreaSetText(tsvcInterface.getC_existvalue_mysql());
            }else{
                hideExistsArea();
            }
        }else{
            hideExistsArea();
        }

    }
    public void showExistsArea(){
        existsArea.setColumns(5);
        existsArea.setRows(5);
        existsArea.setVisible(true);
        sqlexistsAreaScrollPane.setVisible(true);

        existsOtherArea.setColumns(5);
        existsOtherArea.setRows(5);
        existsOtherArea.setVisible(true);
        sqlexistsAreaOtherScrollPane.setVisible(true);
        jSplitPane.setVisible(true);
        jSplitPane.setDividerLocation((int)(Integer.valueOf(ConfigManager.getWinWidth())/2.4f));
        //<editor-fold desc="触发界面重新绘制">
        this.setVisible(false);
        this.setVisible(true);
        //</editor-fold>
    }
    public void hideExistsArea(){
        existsArea.setColumns(0);
        existsArea.setRows(0);
        existsArea.setVisible(false);
        sqlexistsAreaScrollPane.setVisible(false);

        existsOtherArea.setColumns(0);
        existsOtherArea.setRows(0);
        existsOtherArea.setVisible(false);
        sqlexistsAreaOtherScrollPane.setVisible(false);

        jSplitPane.setVisible(false);
        this.setVisible(false);
        this.setVisible(true);

    }

    public void existsAreaSetText(String txt){
        existsArea.setText(txt);
    }

    public void existsOtherAreaSetText(String txt){
        existsOtherArea.setText(txt);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changeExistsSql(existsArea.getText());
        changeExistsOtherSql(existsOtherArea.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changeExistsSql(existsArea.getText());
        changeExistsOtherSql(existsOtherArea.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        changeExistsSql(existsArea.getText());
        changeExistsOtherSql(existsOtherArea.getText());
    }
    private void changeExistsSql(String sql){
        TsvcInterface tsvcInterface=centerTable.getCurrentRow();
        tsvcInterface.setC_existvalue(sql);
        centerTable.updateRow(centerTable.getCurrentSelIndex(),tsvcInterface);
    }
    private void changeExistsOtherSql(String sql){
        TsvcInterface tsvcInterface=centerTable.getCurrentRow();
        //这里写死成mysql
        tsvcInterface.setC_existvalue_mysql(sql);
        centerTable.updateOtherRow(centerTable.getCurrentSelIndex(),tsvcInterface);
    }

    public void setUcInDatas(List<TsvcInterface> ucInDatas) {
        this.ucInDatas = ucInDatas;
    }

    private void dataChahge(){
        if(hasChange()){
            centerContentPanel.dataChange(true);
            isDataChanged=true;
        }else{
            centerContentPanel.dataChange(false);
            isDataChanged=false;
        }

    }

    /**
     * 判断是否数据改变
     * @return
     */
    private boolean hasChange(){
        List<TsvcInterface> ucInDatasView=centerTable.getAllColumnDatas();
        if(ucInDatasView==null || ucInDatasView.size()==0){
            if(ucInDatas!=null && ucInDatas.size()>0){
                return true;
            }
        }else{
            if(ucInDatas==null || ucInDatas.size()==0){
                return true;
            }
            /**
             * 判断是否有变更
             *1、长度不相同
             * 2、逐条判断hash
             */
            if(ucInDatasView.size()!=ucInDatas.size()){
                return true;
            }
            for (int i=0;i<ucInDatas.size();i++){
                if(BeanUtils.checkNull(ucInDatas.get(i)).hashCode()!=BeanUtils.checkNull(ucInDatasView.get(i)).hashCode()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canLoseFcous() {
        dataChahge();
        return super.canLoseFcous();
    }

    public boolean isDataChanged() {
        return isDataChanged;
    }

    public void setDataChanged(boolean dataChanged) {
        isDataChanged = dataChanged;
    }
}
