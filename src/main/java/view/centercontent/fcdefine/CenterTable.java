package view.centercontent.fcdefine;

import bean.HsiRight;
import bean.SystemData;
import constant.ENSystem;
import control.MyActionListener;
import service.ServiceFactory;
import service.UcDefineService;
import service.impl.UcDefineServiceImpl;
import util.StringUtils;
import view.centercontent.BaseJPanel;
import view.centercontent.UcDefineMaintain;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by lyd on 2017/5/18.
 * 功能表定义维护-列表显示界面
 */
public class CenterTable  extends BaseJPanel {
    UcDefineService ucDefineService= ServiceFactory.getUcDefineService();

    private UcDefineMaintain ucDefineMaintain;
    private JTable table;
    //private String[] title=new String[]{"权限代码","UC功能号","权限名称","分类","BO类名","BO方法"};
    DefaultTableModel tableModel =null;
    DefaultListSelectionModel model ;

    @Override
    public void close() {
        tableModel=null;
        model=null;
        table=null;
    }

    public CenterTable(MyActionListener myActionListener, UcDefineMaintain ucDefineMaintain) {
        this.myActionListener=myActionListener;
        this.ucDefineMaintain=ucDefineMaintain;
        init();
    }

    private void init(){
        tableModel=new DefaultTableModel(null,getTitle()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        table=new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(FontFactory.getJTableFont());

        JTableHeader head = table.getTableHeader();
        head.setPreferredSize(new Dimension(head.getWidth(), SystemData.default_font_size+2));// 设置表头大小
        head.setFont(FontFactory.getDataTabtitleName());

        table.setRowHeight(SystemData.default_font_size+6);



        model=(DefaultListSelectionModel)table.getSelectionModel();
        this.setLayout(new GridLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int sr;
                if ((sr = table.getSelectedRow()) == -1) {
                    return;
                }
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
                    int index=getClickedRow(e.getY());
                    if(index>=0){
                        cloumSelect(index);
                    }
                }
            }
        });
        //异步加载数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                reloadUc("");
                //选择第一行
                cloumSelect(0);
            }
        }).start();
    }

    public void removeAll(){
        for (int i = tableModel.getDataVector().size()-1; i >=0; i--) {
            tableModel.removeRow(i);// rowIndex是要删除的行序号
        }

    }

    /**
     *
     * @return 返回的map <li>key:uc  value:删除的uc</li>
     *            <li> key:index  value:删除的下表</li>
     */
    public Map<String,List<String>> removeSelect(){
        Map<String,List<String>> retMap=new HashMap<String,List<String>>();
        List<String> ucList=new ArrayList<>();
        List<String> index=new ArrayList<>();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        int[] rows=table.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            String function = (String)tableModel.getValueAt(rows[i]-i,6);
            if(StringUtils.isNotNullAndNotEmpty(function)){
                ucList.add(function);
            }

            index.add(rows[i]+"");
            tableModel.removeRow(rows[i]-i);
        }
        retMap.put("uc",ucList);
        retMap.put("index",index);
        return retMap;

    }

    /**
     * 尾加
     */
    public HsiRight tailInsert(){
        HsiRight hsiRight=HsiRight.generateDefault();
        tableModel.addRow(hsiRight.toVector());
        return hsiRight;
    }
    public int getRowCount(){
        return tableModel.getRowCount();
    }
    private Vector<String> getTitle(){
        Vector<String> title=new Vector<String>();
        title.add("权限代码");
        title.add("UC功能号");
        title.add("权限名称");
        title.add("分类");
        title.add("BO类名");
        title.add("BO方法");
        title.add("UC功能号");
        title.add("权限代码");
        return  title;
    }

    private Vector<Vector<String>> getData(String condition){
        List<HsiRight> hsiRightList=new ArrayList<HsiRight>();
        hsiRightList=ucDefineService.getAllUc(condition);
        Vector<Vector<String>> dataVector=new Vector<Vector<String>>();
        for(HsiRight hsiRight : hsiRightList){
            dataVector.add(hsiRight.toVector());
        }

        return dataVector;

    }
    /**
     * 获取鼠标点击的行号
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
     * @param index
     */
    public void cloumSelect(int index){
        //选中这一行
        model.setSelectionInterval(index, index);
        //滚动到这一行
        Rectangle rect = table.getCellRect(index, 0, true);
        table.scrollRectToVisible(rect);
        String functionno= "";
        if (index<tableModel.getRowCount()) {
            if(SystemData.getSystem()== ENSystem.ATS){
                functionno = (String)tableModel.getValueAt(index,6);
            }else{
                functionno = (String)tableModel.getValueAt(index,1);
            }

        }
        ucDefineMaintain.tableSelect(index,functionno);
    }
    public void reloadUc(String condition){
        tableModel.setDataVector(getData(condition),getTitle());
        //region 隐藏最后一列
        TableColumnModel tcm = table.getColumnModel();
        //其实没有移除，仅仅隐藏显示而已
        TableColumn tc = tcm.getColumn(7);
        tcm.removeColumn(tc);
        tc = tcm.getColumn(6);
        tcm.removeColumn(tc);
        if(SystemData.getSystem()== ENSystem.SAAS){
            tc = tcm.getColumn(5);
            tcm.removeColumn(tc);
            tc = tcm.getColumn(4);
            tcm.removeColumn(tc);
            tc = tcm.getColumn(3);
            tcm.removeColumn(tc);
            tc = tcm.getColumn(2);
            tcm.removeColumn(tc);
            tc = tcm.getColumn(0);
            tcm.removeColumn(tc);
        }
        //endregion
    }

    /**
     * 设置列表每一行的值.最后隐藏列用于判断是否修改过
     * @param index
     * @param hsiRight
     */
    public void setColumnData(int index ,HsiRight hsiRight){
        System.out.println("setColumnData:"+index+"||"+hsiRight);
        tableModel.setValueAt(hsiRight.getC_rightcode(),index,0);
        tableModel.setValueAt(hsiRight.getC_functionno(),index,1);
        tableModel.setValueAt(hsiRight.getC_rightname(),index,2);
        tableModel.setValueAt(hsiRight.getC_className(),index,3);
        tableModel.setValueAt(hsiRight.getC_javaclass(),index,4);
        tableModel.setValueAt(hsiRight.getC_javamethod(),index,5);
        tableModel.setValueAt(hsiRight.getC_functionno_hid(),index,6);
        tableModel.setValueAt(hsiRight.getC_rightcode_hid(),index,7);
    }

}
