package view.component;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-05<br>
 * <br>
 */
public class SvcTableCellEditor extends DefaultCellEditor
        implements javax.swing.table.TableCellEditor, TreeCellEditor {

    public SvcTableCellEditor(JComboBox comboBox) {
        super(comboBox);
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 int row, int column){
        ((JComboBox)editorComponent).setSelectedItem(value);
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}
