package view.centercontent.ucin;

import bean.SqlFieldType;
import bean.SystemData;
import bean.TsvcInterface;
import constant.EnActionEvent;
import control.MyActionListener;
import service.ServiceFactory;
import service.SvcService;
import service.impl.SvcServiceImpl;
import util.StringUtils;
import util.SvcUtil;
import view.centercontent.BaseJPanel;
import view.centercontent.CenterContentPanel;
import view.centercontent.UcInMaintain;
import view.component.ComboBoxMapModel;
import view.component.EditComBox;
import view.component.SvcTableCellEditor;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-02<br>
 * <br>
 */
public class UcInCenterTable extends BaseJPanel implements ActionListener, ItemListener {
    private SvcService svcService = ServiceFactory.getSvcService();
    private CenterContentPanel centerContentPanel;
    UcInMaintain ucInMaintain;
    private JTable table;
    DefaultTableModel tableModel = null;
    DefaultListSelectionModel model;
    private Boolean isRefresh = false;

    private int currentSelIndex = 0;

    public UcInCenterTable(MyActionListener myActionListener, UcInMaintain ucInMaintain, CenterContentPanel centerContentPanel) {
        this.myActionListener = myActionListener;
        this.ucInMaintain = ucInMaintain;
        this.centerContentPanel = centerContentPanel;
        init();
    }

    @Override
    public void close() {
        svcService = null;
        table = null;
    }

    private void init() {
        tableModel = new DefaultTableModel(null, getTitle());
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(FontFactory.getJTableFont());

        JTableHeader head = table.getTableHeader();
        head.setPreferredSize(new Dimension(head.getWidth(), SystemData.default_font_size+2));// 设置表头大小
        head.setFont(FontFactory.getDataTabtitleName());

        table.setRowHeight(SystemData.default_font_size+6);

        //表头不可拖动
        table.getTableHeader().setReorderingAllowed(false);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model = (DefaultListSelectionModel) table.getSelectionModel();
        this.setLayout(new GridLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int sr;
                if ((sr = table.getSelectedRow()) == -1) {
                    return;
                }
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
                    int index = getClickedRow(e.getY());
                    if (index >= 0) {
                        currentSelIndex = index;
                        cloumSelect(index);
                    }
                }
            }
        });
    }

    private Vector<String> getTitle() {
        Vector<String> title = new Vector<String>();
        title.add("输入/输出");
        title.add("公/私包");
        title.add("SQL字段");
        title.add("字段描述");
        title.add("程序属性");
        title.add("长度");
        title.add("小数位");
        title.add("数据库类型");
        title.add("非空");
        title.add("字段类别");
        title.add("条件");
        title.add("序号");
        title.add("输入显示级别");
        title.add("输入类型");
        title.add("字典名称");
        title.add("辅助查询");
        title.add("视图初始条件");
        title.add("视图字段初始条件值");
        title.add("exist语句");
        title.add("rowid");
        title.add("existpgsql语句");
        title.add("existmysql语句");
        title.add("existreserve语句");
        return title;
    }

    /**
     * 异步刷新
     *
     * @param uc
     */
    public void asynReloadUc(String uc) {
        reloadUc(centerContentPanel.getUcNo());
        //选择第一行
        cloumSelect(0);
        centerContentPanel.dataChange(false);
        ucInMaintain.setDataChanged(false);
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                if(!isRefresh()){
                    System.out.println("开始刷新："+isRefresh());
                    setIsRefresh(true);
                    reloadUc(centerContentPanel.getUcNo());
                    //选择第一行
                    cloumSelect(0);
                    *//*try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*//*
                    setIsRefresh(false);
                }else{
                    System.out.println("刷新中");
                }

            }
        }).start();*/
    }

    public void reloadUc(String uc) {
        removeAll();
        List<TsvcInterface> tsvcInterfaceList=svcService.getUcTsvcInterface(uc);
        Vector<Vector<String>> datas=svcService.ucTsvcInterfaceToVector(tsvcInterfaceList);
        ucInMaintain.setUcInDatas(tsvcInterfaceList);
        tableModel.setDataVector(datas, getTitle());
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i == 2 || i == 3 || i == 4) {
                table.getColumnModel().getColumn(i).setPreferredWidth(150);
            } else {
                //table.getColumnModel().getColumn(i).setPreferredWidth(75);
            }
        }
        TableColumnModel tcm = table.getColumnModel();
        //其实没有移除，仅仅隐藏显示而已,
        if (tcm.getColumnCount() >= 22) {
            tcm.removeColumn(tcm.getColumn(22));
            tcm.removeColumn(tcm.getColumn(21));
            tcm.removeColumn(tcm.getColumn(20));  
            tcm.removeColumn(tcm.getColumn(19));
        }
        if (tcm.getColumnCount() > 18) {
            tcm.removeColumn(tcm.getColumn(18));
        }
        tcm.removeColumn(tcm.getColumn(1));
        //初始化控件
        initCombo(tcm);
    }

    /**
     * 初始化下拉控件
     *
     * @param tcm
     */
    private void initCombo(TableColumnModel tcm) {
        //输入输出
        ComboBoxMapModel mapModel = new ComboBoxMapModel(SvcUtil.getUcInFieldInOrOut());
        JComboBox comboBox = new EditComBox(mapModel,false);
        SvcTableCellEditor cellEditor = new SvcTableCellEditor(comboBox);
        tcm.getColumn(0).setCellEditor(cellEditor);
        //数据库字段类型
        mapModel = new ComboBoxMapModel(SvcUtil.getUcInDatabaseType());
        comboBox = new EditComBox(mapModel,false);
        cellEditor = new SvcTableCellEditor(comboBox);
        tcm.getColumn(6).setCellEditor(cellEditor);

        //非空
        mapModel = new ComboBoxMapModel(SvcUtil.getUcInNotNull());
        comboBox = new EditComBox(mapModel,false);
        cellEditor = new SvcTableCellEditor(comboBox);
        tcm.getColumn(7).setCellEditor(cellEditor);
        //字段类别
        mapModel = new ComboBoxMapModel(SvcUtil.getUcInFieldType());
        comboBox = new EditComBox(mapModel,false);
        cellEditor = new SvcTableCellEditor(comboBox);
        tcm.getColumn(8).setCellEditor(cellEditor);
        //条件
        mapModel = new ComboBoxMapModel(SvcUtil.getUcInConditionType());
        comboBox = new EditComBox(mapModel,false);
        comboBox.setActionCommand(EnActionEvent.UCIN_CONDITIONSELECT.getCmd());
        comboBox.addActionListener(this);
        comboBox.addItemListener(this);
        cellEditor = new SvcTableCellEditor(comboBox);
        tcm.getColumn(9).setCellEditor(cellEditor);
        //输入显示级别
        mapModel = new ComboBoxMapModel(SvcUtil.getUcInViewLevel());
        comboBox = new EditComBox(mapModel,false);
        cellEditor = new SvcTableCellEditor(comboBox);
        tcm.getColumn(11).setCellEditor(cellEditor);
        //输入类型
        mapModel = new ComboBoxMapModel(SvcUtil.getUcInViewType());
        comboBox = new EditComBox(mapModel,false);
        cellEditor = new SvcTableCellEditor(comboBox);
        tcm.getColumn(12).setCellEditor(cellEditor);
        //字典名称
        mapModel = new ComboBoxMapModel(SvcUtil.getDiction());
        comboBox = new EditComBox(mapModel);
        cellEditor = new SvcTableCellEditor(comboBox);
        tcm.getColumn(13).setCellEditor(cellEditor);
        //辅助查询
        mapModel = new ComboBoxMapModel(SvcUtil.getMidsearch());
        comboBox = new EditComBox(mapModel);
        cellEditor = new SvcTableCellEditor(comboBox);
        tcm.getColumn(14).setCellEditor(cellEditor);

        //SQL字段
        mapModel = new ComboBoxMapModel(getOutField(), "");
        final EditComBox filedNameComboBox = new EditComBox(mapModel);
        ((EditComBox) filedNameComboBox).addDataChangeActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof SqlFieldType) {
                    backfill((SqlFieldType) e.getSource());
                }
            }
        });
        cellEditor = new SvcTableCellEditor(filedNameComboBox);
        tcm.getColumn(1).setCellEditor(cellEditor);

    }

    public void removeAll() {
        for (int i = tableModel.getDataVector().size() - 1; i >= 0; i--) {
            tableModel.removeRow(i);// rowIndex是要删除的行序号
        }
    }

    /**
     * 获取鼠标点击的行号
     *
     * @param y 点击位置的纵坐标值
     * @return 行号
     */
    private int getClickedRow(int y) {
        // JTable的行总数
        int rowCount = table.getRowCount();
        // JTable行的累计纵坐标
        int rowY = 0;

        if (y < 0) {
            return -1;
        }
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            rowY = rowY + (int) table.getCellRect(rowIndex, 0, true).getHeight();
            if (y < rowY) {
                return rowIndex;
            }
        }

        return -1;
    }

    /**
     * 选中某一行
     *
     * @param index
     */
    public void cloumSelect(int index) {
        this.currentSelIndex = index;
        //选中这一行
        model.setSelectionInterval(index, index);
        //滚动到这一行
        Rectangle rect = table.getCellRect(index, 0, true);
        table.scrollRectToVisible(rect);
        String functionno = "";
        if (index < tableModel.getRowCount()) {
            functionno = (String) tableModel.getValueAt(index, 6);
        }
        if(tableModel.getRowCount()>index){
            ucInMaintain.tableSelect(index, getCurrentRow());
        }
    }

    private void setIsRefresh(boolean refresh) {
        if (this.isRefresh != refresh) {
            synchronized (isRefresh) {
                if (this.isRefresh != refresh) {
                    this.isRefresh = refresh;
                }
            }
            ;
        }

    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public Vector<Vector<String>> getAllColumnDatasVector(){
        return  (Vector<Vector<String>>) tableModel.getDataVector();
    }
    public java.util.List<TsvcInterface> getAllColumnDatas() {
        java.util.List<TsvcInterface> retList = new java.util.ArrayList<TsvcInterface>();
        if (tableModel.getRowCount() > 0) {
            Vector<Vector<String>> datas = (Vector<Vector<String>>) tableModel.getDataVector();
            for (Vector<String> data : datas) {
                TsvcInterface tsvcInterface = new TsvcInterface(centerContentPanel.getUcNo(),
                        StringUtils.valueWithNull(data.get(0)),
                        StringUtils.valueWithNull(data.get(1)),
                        StringUtils.valueWithNull(data.get(2)),
                        StringUtils.valueWithNull(data.get(3)),
                        StringUtils.valueWithNull(data.get(4)),
                        StringUtils.valueWithNull(data.get(5)),
                        StringUtils.valueWithNull(data.get(6)),
                        StringUtils.valueWithNull(data.get(7)),
                        StringUtils.valueWithNull(data.get(8)),
                        StringUtils.valueWithNull(data.get(9)),
                        StringUtils.valueWithNull(data.get(10)),
                        StringUtils.valueWithNull(data.get(11)),
                        StringUtils.valueWithNull(data.get(12)),
                        StringUtils.valueWithNull(data.get(13)),
                        StringUtils.valueWithNull(data.get(14)),
                        StringUtils.valueWithNull(data.get(15)),
                        StringUtils.valueWithNull(data.get(16)),
                        StringUtils.valueWithNull(data.get(17)),
                        StringUtils.valueWithNull(data.get(18)),
                        StringUtils.valueWithNull(data.get(20)),//跳过rowid
                        StringUtils.valueWithNull(data.get(21)),
                        StringUtils.valueWithNull(data.get(22))
                );
                retList.add(tsvcInterface);
            }
        }
        return retList;
    }

    public void initIn(List<SqlFieldType> fieldList) {
        if (fieldList != null && fieldList.size() > 0) {
            for (SqlFieldType sqlFieldType : fieldList) {
                tailInsert(sqlFieldType.toTsvcInterface(0));
            }
        }
    }

    public void initOut(List<SqlFieldType> fieldList) {
        if (fieldList != null && fieldList.size() > 0) {
            for (SqlFieldType sqlFieldType : fieldList) {
                tailInsert(sqlFieldType.toTsvcInterface(1));
            }
        }
    }

    public TsvcInterface insert() {
        TsvcInterface tsvcInterface = TsvcInterface.generateDefault();
        insert(tsvcInterface);
        return tsvcInterface;
    }

    private TsvcInterface insert(TsvcInterface tsvcInterface) {
        tsvcInterface.setC_functionno(centerContentPanel.getUcNo());
        if (tableModel.getRowCount() > 0) {
            int endindex = (tableModel.getRowCount() - 1);
            if (currentSelIndex > endindex) {
                currentSelIndex = endindex;
            }
        } else {
            int endindex = tableModel.getRowCount();
            currentSelIndex = endindex;
            tsvcInterface.setL_no(10 + "");
        }
        tableModel.insertRow(currentSelIndex, tsvcInterface.toVector());
        this.cloumSelect(currentSelIndex);
        return tsvcInterface;
    }

    /**
     * 尾加
     */
    public TsvcInterface tailInsert() {
        TsvcInterface tsvcInterface = TsvcInterface.generateDefault();
        tailInsert(tsvcInterface);
        return tsvcInterface;
    }

    /**
     * 尾加
     */
    public TsvcInterface tailInsert(TsvcInterface tsvcInterface) {
        tsvcInterface.setC_functionno(centerContentPanel.getUcNo());
        if (tableModel.getRowCount() > 0) {
            int index = (tableModel.getRowCount() - 1);
            String l_noStr = StringUtils.valueOf(StringUtils.valueOf(tableModel.getValueAt(index, 11)));
            if (StringUtils.isNotNullAndNotEmpty(l_noStr)) {
                Integer l_no = Integer.parseInt(l_noStr);
                if (l_no != null) {
                    tsvcInterface.setL_no((l_no + 10) + "");
                }
            }
            tableModel.addRow(tsvcInterface.toVector());
            index = (tableModel.getRowCount() - 1);
            this.cloumSelect(index);
        } else {
            tsvcInterface.setL_no(10 + "");
            tableModel.addRow(tsvcInterface.toVector());
            int index = (tableModel.getRowCount() - 1);
            this.cloumSelect(index);
        }
        return tsvcInterface;
    }

    public void removeSelect() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        int[] rows = table.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            tableModel.removeRow(rows[i] - i);
        }
        if (tableModel.getRowCount() > 0) {
            int endindex = (tableModel.getRowCount() - 1);
            if (rows[0] > endindex) {
                this.cloumSelect(endindex);
            } else {
                this.cloumSelect(rows[0]);
            }
        } else {
            //全部删除完
            currentSelIndex = 0;
        }

    }

    private Map<Object, String> getOutField() {
        Map<Object, String> retMap = new LinkedHashMap<Object, String>();
        List<SqlFieldType> sqlFieldTypeList = svcService.findSqlField(centerContentPanel.getUcNo());
        for (SqlFieldType sqlFieldType : sqlFieldTypeList) {
            retMap.put(sqlFieldType, sqlFieldType.getField());
        }
        return retMap;
    }

    private void backfill(SqlFieldType sqlFieldType) {
        if (sqlFieldType == null) {
            return;
        }
        String viewName = StringUtils.valueOf(this.tableModel.getValueAt(currentSelIndex, 3));
        if (StringUtils.isNullOrEmpty(viewName)) {
            //可回填
            this.tableModel.setValueAt(sqlFieldType.getFieldName(), currentSelIndex, 3);
            this.tableModel.setValueAt(sqlFieldType.getField().toLowerCase(), currentSelIndex, 4);
            this.tableModel.setValueAt(sqlFieldType.getFieldLength(), currentSelIndex, 5);
            this.tableModel.setValueAt(sqlFieldType.getFieldTypeStr(), currentSelIndex, 7);
        }
    }

    public int getCurrentSelIndex() {
        return currentSelIndex;
    }

    public int getTotalCount() {
        return tableModel.getRowCount();
    }

    public TsvcInterface getRow(int row) {
        if (row > getTotalCount()) {
            return null;
        }
        Vector<Vector<String>> datas = (Vector<Vector<String>>) tableModel.getDataVector();
        Vector<String> data = datas.get(row);
        TsvcInterface tsvcInterface = new TsvcInterface(centerContentPanel.getUcNo(),
                StringUtils.valueOf(data.get(0)),
                StringUtils.valueOf(data.get(1)),
                StringUtils.valueOf(data.get(2)),
                StringUtils.valueOf(data.get(3)),
                StringUtils.valueOf(data.get(4)),
                StringUtils.valueOf(data.get(5)),
                StringUtils.valueOf(data.get(6)),
                StringUtils.valueOf(data.get(7)),
                StringUtils.valueOf(data.get(8)),
                StringUtils.valueOf(data.get(9)),
                StringUtils.valueOf(data.get(10)),
                StringUtils.valueOf(data.get(11)),
                StringUtils.valueOf(data.get(12)),
                StringUtils.valueOf(data.get(13)),
                StringUtils.valueOf(data.get(14)),
                StringUtils.valueOf(data.get(15)),
                StringUtils.valueOf(data.get(16)),
                StringUtils.valueOf(data.get(17)),
                StringUtils.valueOf(data.get(18)),
                StringUtils.valueOf(data.get(20)),
                StringUtils.valueOf(data.get(21)),
                StringUtils.valueOf(data.get(22)));
        return tsvcInterface;
    }

    public TsvcInterface getCurrentRow() {

        Vector<Vector<String>> datas = (Vector<Vector<String>>) tableModel.getDataVector();
        Vector<String> data = datas.get(getCurrentSelIndex());
        TsvcInterface tsvcInterface = new TsvcInterface(centerContentPanel.getUcNo(),
                StringUtils.valueOf(data.get(0)),
                StringUtils.valueOf(data.get(1)),
                StringUtils.valueOf(data.get(2)),
                StringUtils.valueOf(data.get(3)),
                StringUtils.valueOf(data.get(4)),
                StringUtils.valueOf(data.get(5)),
                StringUtils.valueOf(data.get(6)),
                StringUtils.valueOf(data.get(7)),
                StringUtils.valueOf(data.get(8)),
                StringUtils.valueOf(data.get(9)),
                StringUtils.valueOf(data.get(10)),
                StringUtils.valueOf(data.get(11)),
                StringUtils.valueOf(data.get(12)),
                StringUtils.valueOf(data.get(13)),
                StringUtils.valueOf(data.get(14)),
                StringUtils.valueOf(data.get(15)),
                StringUtils.valueOf(data.get(16)),
                StringUtils.valueOf(data.get(17)),
                StringUtils.valueOf(data.get(18)),
                StringUtils.valueOf(data.get(20)),  //跳过rowid
                StringUtils.valueOf(data.get(21)),
                StringUtils.valueOf(data.get(22)));
        return tsvcInterface;
    }

    public void updateRow(int row, TsvcInterface tsvcInterface) {
        tableModel.setValueAt(tsvcInterface.getC_existvalue(), row, 18);
    }
    public void updateOtherRow(int row, TsvcInterface tsvcInterface) {
        //这里写死成mysql
        tableModel.setValueAt(tsvcInterface.getC_existvalue_mysql(), row, 21);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       /* String selectValue=StringUtils.valueOf(((EditComBox)e.getSource()).getSelectedItemReminder());
        System.out.println(selectValue);
        if(e.getActionCommand().equals(EnActionEvent.UCIN_CONDITIONSELECT.getCmd())){
            tableModel.setValueAt(selectValue,getCurrentSelIndex(),10);
            TsvcInterface tsvcInterface=this.getCurrentRow();
            if(tsvcInterface.getC_flag().equals("0")) {
                ucInMaintain.tableSelect( getCurrentSelIndex(),tsvcInterface);
            }

        }*/
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        /*if (e.getStateChange() == ItemEvent.SELECTED) {
            String selectValue=StringUtils.valueOf(((EditComBox)e.getSource()).getSelectedItemReminder());
            tableModel.setValueAt(selectValue,getCurrentSelIndex(),10);
            TsvcInterface tsvcInterface = this.getCurrentRow();
            if (tsvcInterface.getC_flag().equals("0")) {
                ucInMaintain.tableSelect(getCurrentSelIndex(), tsvcInterface);
            }
        }*/
    }
}
