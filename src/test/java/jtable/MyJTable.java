package jtable;

import view.component.ComboBox;
import view.component.EditComBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-01<br>
 * <br>
 */
public class MyJTable extends JTable  implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        TableModel tableModel=this.getModel();
        System.out.println(tableModel.getValueAt(0,0));
        System.out.println(tableModel.getValueAt(0,1));
        System.out.println(tableModel.getValueAt(0,2));

    }
    public static void main(String[] args) {
        MyJTable table=new MyJTable();
        table.setRowHeight(20);
        String[] values = new String[] { "1", "2", "3" };
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn("A", new Object[] { "1","2" });
        model.addColumn("B", new Object[] { "3","4" });
        model.addColumn("C", new Object[] { "5","6" });
        ComboBox comboBox;
        Map<String, String> item=new LinkedHashMap<>();
        item.put("abc","1231");
        item.put("abc2","1232");
        item.put("3","1233");
        item.put("4","1234");
        item.put("5","1235");
        comboBox=new ComboBox(item);
        comboBox.setEditable(true);
        comboBox.addActionListener(table);
        EditComBox editComBox=new EditComBox(new String[]{"组织代码", "哈哈",
                "Jorge", "Sergi","Sergi","Sergi","Sergi","Sergi","Sergi","你好"});
        editComBox.addDataChangeActionListener(table);
        DefaultCellEditor cellEditor=new DefaultCellEditor(editComBox);
        TableColumnModel columnModel=table.getColumnModel();
        columnModel.getColumn(1).setCellEditor( cellEditor );
        columnModel.getColumn(2).setCellEditor( cellEditor );
       // table.setRowHeight(50);


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
