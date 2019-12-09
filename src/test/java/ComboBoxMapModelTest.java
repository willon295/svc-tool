import util.SvcUtil;
import view.component.ComboBox;
import view.component.ComboBoxMapModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-05<br>
 * <br>
 */
public class ComboBoxMapModelTest {
    public static void main(String[] args) {
        Map<String,String> items=new LinkedHashMap<>();
        items.put("t1","1");
        items.put("t2","2");
        items.put("t3","3");
        items.put("t4","4");
        items.put("t5","5");
        ComboBoxMapModel mapModel=new ComboBoxMapModel(SvcUtil.getUcOutViewType());
        JComboBox comboBox=new JComboBox(mapModel);

        JTable table=new JTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn("A", new Object[] { "","item4" });
        model.addColumn("B", new Object[] { "item2","item5" });
        model.addColumn("C", new Object[] { "item3","" });

        DefaultCellEditor cellEditor=new DefaultCellEditor(comboBox);
        TableColumnModel columnModel=table.getColumnModel();
        columnModel.getColumn(1).setCellEditor( cellEditor );

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
