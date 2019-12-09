package view.centercontent;

import bean.TsvcSql;
import constant.EnActionEvent;
import control.MyActionListener;
import service.ServiceFactory;
import service.TsvcSqlService;
import service.impl.TsvcSqlServiceImpl;
import util.ConfigManager;
import util.StringUtils;
import view.component.ComboBox;
import view.component.SButton;
import view.factory.ColorFactory;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lyd on 2017/5/11.
 */
public class SqlMaintain  extends BaseJPanel implements ActionListener,KeyListener {
    TsvcSqlService tsvcSqlService= ServiceFactory.getTsvcSqlService();
    private boolean enableTestSql=false;
    /**
     * 数据，原始
     */
    private TsvcSql tsvcSql_old;
    /**
     * 修改后
     */
    private TsvcSql tsvcSql_new;
    /**
     * 数据是否有过修改
     */
    private boolean isDataChange=false;
    /**
     *按钮事件控制
     */
    private SqlMaintainControl control;
    private   CenterContentPanel centerContentPanel;
    /**
     *删除按钮
     */
    private SButton delBtn=new SButton("删除");
    /**
     *刷新按钮
     */
    private SButton refreshBtn=new SButton("刷新");
    /**
     *存盘按钮
     */
    private SButton saveBtn=new SButton("存盘");
    /**
     *sql帮助按钮
     */
    private SButton helpBtn=new SButton("sql写法帮助");
    /**
     *sql测试
     */
    private SButton sqlTestBtn=new SButton("sql语句测试");
    /**
    * sql分页测试
    */
    private SButton sqlPagingBtn=new SButton("分页组合sql测试");
    /**
     * sql语句
     */
    private JTextArea sqlText=new JTextArea();

    private JTextArea sqlTextOther=new JTextArea();
    /**
     * 默认排序字段
     */
    private JTextField orderField=new JTextField(30);
    /**
     *sql类型
     */
    private ComboBox sqlType;
    /**
     * sql类型
     */
    private Map<String,String> sqlTypeMap;


    private JTable sqlTestResultTable=new JTable();//测试结果table

    //用于判断是否刷新
    String currentUc="";

    @Override
    public void close() {
        tsvcSqlService=null;
    }

    public SqlMaintain(MyActionListener myActionListener, CenterContentPanel centerContentPanel) {
        control=new SqlMaintainControl();
        sqlTypeMap=new LinkedHashMap<String,String>();
        sqlTypeMap.put("0:普通SQL语句","0");
        sqlTypeMap.put("1:存储过程","1");
        sqlType=new ComboBox(sqlTypeMap);

        sqlType.setFont(FontFactory.getDefaultFont());

        this.centerContentPanel=centerContentPanel;
        this.myActionListener= myActionListener;
        init();
    }
    private void init(){
       // JLabel jLabel=new JLabel("SqlMaintain");
        //this.add(jLabel);
        this.setLayout(new BorderLayout());
        //按钮区域
        JPanel northPanel=new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //设置边框
        northPanel.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));
        delBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_DEL.getCmd());
        saveBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_SAVE.getCmd());
        refreshBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_REFRESH.getCmd());
        helpBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_HELP.getCmd());
        delBtn.addActionListener(control);
        saveBtn.addActionListener(control);
        refreshBtn.addActionListener(control);
        helpBtn.addActionListener(control);
        northPanel.add(delBtn);
        northPanel.add(saveBtn);
        northPanel.add(refreshBtn);
       // northPanel.add(helpBtn);


        //中间区域
        JPanel centerPanel=new JPanel();
        //中间sql区域
        JPanel centerSqlHead=new JPanel();
        centerSqlHead.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel jLabel=new JLabel("SQL语句：");
        jLabel.setFont(FontFactory.getDefaultFont());
        centerSqlHead.add(jLabel);

        jLabel=new JLabel("默认排序字段：");
        jLabel.setFont(FontFactory.getDefaultFont());
        centerSqlHead.add(jLabel);

        orderField.setFont(FontFactory.getTxtInputFootFont());
        orderField.addKeyListener(this);
        centerSqlHead.add(orderField);
        jLabel=new JLabel("SQL类型：");
        jLabel.setFont(FontFactory.getDefaultFont());
        centerSqlHead.add(jLabel);
        sqlType.addActionListener(this);
        centerSqlHead.add(sqlType);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(centerSqlHead,BorderLayout.NORTH);
        //设置边框
        sqlText.setFont(FontFactory.getSqlInputFootFont());
        sqlText.addKeyListener(this);
        sqlTextOther.setFont(FontFactory.getSqlInputFootFont());
        sqlTextOther.addKeyListener(this);

        JScrollPane sqlTextScrollPane = new JScrollPane(sqlText);
        JScrollPane sqlScrollPaneOther = new JScrollPane(sqlTextOther);
        sqlTextScrollPane.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));
        sqlScrollPaneOther.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));
        JSplitPane jSplitPane=new JSplitPane();
        //设置分割线距离左边的位置
        jSplitPane.setDividerLocation((int)(Integer.valueOf(ConfigManager.getWinWidth())/2.0f));
        //将两个sql面板进行设置在左右两边
        jSplitPane.setLeftComponent(sqlTextScrollPane);
        jSplitPane.setRightComponent(sqlScrollPaneOther);
        centerPanel.add(jSplitPane,BorderLayout.CENTER);

        if(enableTestSql){
            //底部测区域
            JPanel testPanel=new JPanel();
            //底部测试区域按钮
            JPanel testPanelHead=new JPanel();
            testPanelHead.setLayout(new FlowLayout(FlowLayout.LEFT));
            sqlTestBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_SQLTEST.getCmd());
            sqlPagingBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_SQLPAGING.getCmd());
            sqlTestBtn.addActionListener(control);
            sqlPagingBtn.addActionListener(control);
            testPanelHead.add(sqlTestBtn);
            testPanelHead.add(sqlPagingBtn);
            sqlTestResultTable.setPreferredScrollableViewportSize(new Dimension(100,100));
            sqlTestResultTable.setFont(FontFactory.getJTableFont());
            JScrollPane scrollPane = new JScrollPane(sqlTestResultTable);
            testPanel.setLayout(new BorderLayout());
            testPanel.add(testPanelHead,BorderLayout.NORTH);
            testPanel.add(scrollPane,BorderLayout.CENTER);
            this.add(testPanel,BorderLayout.SOUTH);
        }
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
    }

    class SqlMaintainControl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("SQL维护："+e.getActionCommand());
            if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_DEL.getCmd())){
                clean();
            }else if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_SAVE.getCmd())){
                save();
            }else if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_REFRESH.getCmd())){
                reSetSqlMaintainData();
            }else if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_HELP.getCmd())){

            }else if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_SQLTEST.getCmd())){

            }else if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_SQLPAGING.getCmd())){

            }
            dataChahge();
        }
    }

    /**
     * 清空显示
     */
    private void clean(){
        tsvcSql_new=null;
        sqlText.setText("");
        orderField.setText("");
        sqlTextOther.setText("");
    }
    /**
     * 保存数据
     */
    private void save(){
        if (tsvcSql_old != null) {
            try {
                tsvcSql_new = (TsvcSql) tsvcSql_old.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        } else {
            tsvcSql_new = new TsvcSql();
            tsvcSql_new.setC_functionno(centerContentPanel.getUcNo());
        }
        tsvcSql_new.setC_orderby(orderField.getText());
        tsvcSql_new.setC_sqlstatement(sqlText.getText());
        //这里写死成mysql
        tsvcSql_new.setC_sqlstatement_mysql(sqlTextOther.getText());
        tsvcSql_new.setC_sqltype(sqlType.getSelectItemValue());
        if (!tsvcSql_new.equals(tsvcSql_old)) {
            tsvcSqlService.operate(tsvcSql_new);
        }
        //保存完后，刷新一遍
        reSetSqlMaintainData();
        tsvcSql_new=null;
    }
    /**
     * 重新设置界面显示
     */
    private void reSetSqlMaintainData(){
        clean();
        isDataChange=false;
        tsvcSql_old=null;
        String ucNo=centerContentPanel.getUcNo();
        if(StringUtils.isNotNullAndNotEmpty(ucNo)){
            TsvcSql tsvcSql=tsvcSqlService.getTsvcSql(ucNo);
            tsvcSql_old=tsvcSql;
            tsvcSql_new=tsvcSql;
            if(tsvcSql!=null){
                sqlText.setText(tsvcSql.getC_sqlstatement());
                //目前取值写死为mysql,后期可以改，或者设置一个下拉框选择sql
                sqlTextOther.setText(tsvcSql.getC_sqlstatement_mysql());
                orderField.setText(tsvcSql.getC_orderby());
                sqlType.setSelectedItem(tsvcSql.getC_sqltype());
            }
        }
        dataChahge();
    }

    /**
     * 检测是否变动
     * @return
     */
    public boolean isDataChange() {
        String sql=StringUtils.valueOf(sqlText.getText());
        String sqlOther=StringUtils.valueOf(sqlTextOther.getText());
        String order=StringUtils.valueOf(orderField.getText());
        String type=StringUtils.valueOf(sqlType.getSelectItemValue());
        if(tsvcSql_old==null){
            if(StringUtils.isNotNullAndNotEmpty(sql)){
                return true;
            }
        }else{
            if(!sql.equals(StringUtils.valueOf(tsvcSql_old.getC_sqlstatement())) || !order.equals(StringUtils.valueOf(tsvcSql_old.getC_orderby()))
                    || !type.equals(StringUtils.valueOf(tsvcSql_old.getC_sqltype())) || !sqlOther.equals(StringUtils.valueOf(tsvcSql_old.getC_sqlstatement_mysql()))){
                return true;
            }
        }
        return false;
    }
    private void dataChahge(){
        if(isDataChange()){
            centerContentPanel.dataChange(true);
            isDataChange=true;
        }else{
            centerContentPanel.dataChange(false);
            isDataChange=false;
        }

    }

    @Override
    public void onFocus(boolean refresh) {
       /* String ucNo=centerContentPanel.getUcNo();
        if(  tsvcSql_old!=null && tsvcSql_old.getC_functionno().equals(ucNo)){
            if(refresh){
                reSetSqlMaintainData();
            }
        }else{
            reSetSqlMaintainData();
        }
*/
        if(!currentUc.equals(centerContentPanel.getUcNo())){
            reSetSqlMaintainData();
        }else{
            if(refresh){
                reSetSqlMaintainData();
            }
        }
        currentUc=centerContentPanel.getUcNo();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dataChahge();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        dataChahge();
    }

    public void setDataChange(boolean dataChange) {
        isDataChange = dataChange;
    }
}
