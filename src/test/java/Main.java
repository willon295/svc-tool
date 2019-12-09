/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-01<br>
 * <br>
 */
import view.component.ComboBox;

import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] argv) throws Exception {
        MyTable2 table = new MyTable2();
        String[] values = new String[] { "1", "2", "3" };
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn("A", new Object[] { "","item4" });
        model.addColumn("B", new Object[] { "item2","item5" });
        model.addColumn("C", new Object[] { "item3","" });
        ComboBox comboBox;
        Map<String, String> item=new HashMap<String, String>();
        item.put("1","1231");
        item.put("2","1232");
        item.put("3","1233");
        item.put("4","1234");
        item.put("5","1235");
        comboBox=new ComboBox(item);
        ComboBox comboBoxw=new ComboBox(item);
       // table.setDefaultEditor(, new DefaultCellEditor(comboBox));
        table.setComboCell(1, 1, new DefaultCellEditor(comboBox));
        table.setComboCell(0, 1, new DefaultCellEditor(comboBoxw));
        //table.setComboCell(0, 0, new MyComboBoxEditor(values));

        //   TableColumn col = table.getColumnModel().getColumn(0);
        //   col.setCellEditor(new MyComboBoxEditor(values));
        //   col.setCellEditor(new MyComboBoxRenderer(values));
        //  table.setModel(model);
        //  table.updateUI();
        JScrollPane jp=new JScrollPane(table);
        jp.setViewportView(table);
        jp.setSize(400,300);
        JFrame jf=new JFrame();
        jf.getContentPane().add(jp);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,300);
        jf.setVisible(true);
    }
}
class MyTable2 extends JTable{
    int myRow=-1,myCol=-1;
    TableCellEditor myEditor;
    public void setComboCell(int r,int c,TableCellEditor ce){
        this.myRow=r;
        this.myCol=c;
        this.myEditor=ce;
    }
    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        System.out.println(row+","+column+";"+myRow+","+myCol+","+myEditor);
        if(row==myRow&&column==myCol&&myEditor!=null)
            return myEditor;
        return super.getCellEditor(row, column);
    }

}
class MyComboBoxEditor extends DefaultCellEditor {
    public MyComboBoxEditor(String[] items) {
        super(new JComboBox(items));
    }
}