/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-31<br>
 * <br>
 */

import view.component.ComboBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

//测试窗体
public class Test extends JFrame {


    private JTable table;
    private MyTableModel model;
    public Test(){
        init();
    }
    //初始化界面
    private void init() {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("测试自定义JTable");
        //采用自定义数据模型
        model=new MyTableModel();
        table=new JTable(model);
        //插入单元格元素，采用自定义元素
       // ProgressBarColumn progressBarColumn = new ProgressBarColumn(table, 3);
        //ButtonColumn buttonColumn = new ButtonColumn(table, 4);
        //ComboBoxColumn comboBoxColumn = new ComboBoxColumn(table, 1);
        TableColumnModel columnModel=table.getColumnModel();


        Map<String, String> item=new LinkedHashMap<>();
        item.put("1","1231");
        item.put("2","1232");
        item.put("3","1233");
        item.put("4","1234");
        item.put("5","1235");
        ComboBox comboBox=new ComboBox(item);
        DefaultCellEditor cellEditor=new DefaultCellEditor(comboBox);
        ComboBoxColumn comboBoxColumn=new ComboBoxColumn(comboBox);
        //columnModel.getColumn(1).setCellRenderer( comboBoxColumn );
        columnModel.getColumn(1).setCellEditor( cellEditor );
       // table.setDefaultEditor(ComboBox.class, new DefaultCellEditor(comboBox));
        table.setRowHeight(30);

        JScrollPane jsp =new JScrollPane(table);
        this.getContentPane().add(jsp,BorderLayout.CENTER);
        this.setVisible(true);
    }
    public static void main(String[] args) {

        //new SeparatorDemo1();
    }
}
//自定义表格模型
class MyTableModel extends AbstractTableModel{
    static Map<String, String> item=new HashMap<String, String>();
    //单元格元素类型
    private Class []cellType={String.class,ComboBox.class,Boolean.class,JProgressBar.class,JButton.class};
    //表头
    private String title[]={"编号","图标","是否选中","进度","操作"};
    //模拟数据
    private Object data[][]={{"1","",new Boolean(true),0,new JButton("start1")},
            {"2","",new Boolean(false),60,new JButton("start2")},
            {"3","",new Boolean(false),25,new JButton("start3")}};
    public MyTableModel(){

    }
    @Override
    public Class<?> getColumnClass(int arg0) {
        // TODO Auto-generated method stub
        return cellType[arg0];
    }
    @Override
    public String getColumnName(int arg0) {
        // TODO Auto-generated method stub
        return title[arg0];
    }
    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return title.length;
    }
    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return data.length;
    }
    @Override
    public Object getValueAt(int r, int c) {
        // TODO Auto-generated method stub
        return data[r][c];
    }
    //重写isCellEditable方法
    public boolean isCellEditable(int r,int c)
    {
        return true;
    }
    //重写setValueAt方法
    public void setValueAt(Object value,int r,int c)
    {
        data[r][c]=value;
        this.fireTableCellUpdated(r,c);
    }
}
class ComboBoxColumn extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
    ComboBox comboBox;

    public ComboBoxColumn(  ComboBox comboBox) {
        super();

         this.comboBox=comboBox;

    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        return comboBox;
    }

    @Override
    public Object getCellEditorValue() {
        return new Button("hhh");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return comboBox;
    }
}
//自定义JButton列，按钮每点击一次，进度条将+5
class ButtonColumn extends AbstractCellEditor implements TableCellEditor, TableCellRenderer ,ActionListener{
    //按钮的两种状态
    private JButton rb,eb;
    private int row;
    private JTable table;
    private String text="开始";
    public ButtonColumn(){}
    public ButtonColumn(JTable table, int column)
    {
        super();
        this.table = table;
        rb = new JButton("开始");
        eb = new JButton("开始");
        eb.setFocusPainted( false );
        eb.addActionListener( this );
        //设置该单元格渲染和编辑样式
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
    }
    @Override
    public Object getCellEditorValue() {
        // TODO Auto-generated method stub
        return null;
    }
    //监听器方法
    @Override
    public void actionPerformed(ActionEvent arg0) {
        int v=Integer.parseInt(table.getValueAt(row, 3).toString());
        System.out.println("row :"+row+" bar value :"+table.getValueAt(row, 3));
        //更新进度条 列的值
        table.setValueAt(v+5, row, 3);
    }
    @Override
    public Component getTableCellRendererComponent(JTable arg0, Object value,
                                                   boolean arg2, boolean arg3, int arg4, int arg5) {
        rb.setText(text);
        return rb;
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        eb.setText( text );
        //编辑行，行号
        this.row=row;
        return eb;
    }
}
//自定义ProgressBar 列，实现方式与按钮类似
class ProgressBarColumn extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
    JProgressBar rjsb,ejsb;;
    JTable table;
    private int row;
    public  ProgressBarColumn(JTable table,int column){
        super();
        this.table = table;
        rjsb=new JProgressBar();
        rjsb.setMaximum(100);
        rjsb.setBackground(Color.BLUE);
        rjsb.setForeground(Color.LIGHT_GRAY);
        rjsb.setStringPainted(true);
        rjsb.setBorderPainted(false);
        ejsb=new JProgressBar();
        ejsb.setMaximum(100);
        ejsb.setBackground(Color.DARK_GRAY);
        ejsb.setForeground(Color.LIGHT_GRAY);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
    }
    @Override
    public Component getTableCellRendererComponent(JTable arg0, Object value,
                                                   boolean arg2, boolean arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub
        rjsb.setValue(Integer.parseInt(value.toString()));
        return rjsb;
    }
    @Override
    public Object getCellEditorValue() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        // TODO Auto-generated method stub
        this.row = row;
        ejsb.setValue(ejsb.getValue()+5);
        return ejsb;
    }
}
