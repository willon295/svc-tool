package view.component; /**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-01<br>
 * <br>
 */
import util.SvcUtil;

import javax.swing.*;
import javax.swing.text.*;

public class S03FixedAutoSelection extends PlainDocument {
    ComboBoxModel model;
    // flag to indicate if setSelectedItem has been called
    // subsequent calls to remove/insertString should be ignored
    boolean selecting=false;

    public S03FixedAutoSelection(ComboBoxModel model) {
        this.model = model;
    }

    public void remove(int offs, int len) throws BadLocationException {
        // return immediately when selecting an item
        if (selecting) return;
        super.remove(offs, len);
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        // return immediately when selecting an item
        if (selecting) return;
        System.out.println("insert " + str + " at " + offs);
        // insert the string into the document
        super.insertString(offs, str, a);
        // get the resulting string
        String content = getText(0, getLength());
        // lookup a matching item
        Object item = lookupItem(content);
        // select the item (or deselect if null)
        if(item!=model.getSelectedItem()) System.out.println("Selecting '" + item + "'");
        selecting = true;
        model.setSelectedItem(item);
        selecting = false;
    }

    private Object lookupItem(String pattern) {
        // iterate over all items
        for (int i=0, n=model.getSize(); i < n; i++) {
            Object currentItem = model.getElementAt(i);
            // current item starts with the pattern?
            if (currentItem.toString().toUpperCase().startsWith(pattern.toUpperCase())) {
                return currentItem;
            }
        }
        // no item starts with the pattern => return null
        return null;
    }

    private static void createAndShowGUI() {
        // the combo box (add/modify items if you like to)
        JComboBox comboBox = new JComboBox(new Object[] { "组织代码", "哈哈",
                "Jorge", "Sergi","Sergi","Sergi","Sergi","Sergi","Sergi","你好"});
        // has to be editable
        comboBox.setEditable(true);
        // get the combo boxes editor component
        JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
         //change the editor's document
        editor.setDocument(new S03FixedAutoSelection(comboBox.getModel()));

        // create and show a window containing the combo box

        ComboBoxMapModel mapModel=new ComboBoxMapModel(SvcUtil.getUcInConditionType());
        EditComBox comboBox2=new EditComBox(mapModel);
        //EditComBox editComBox=new EditComBox(new String[]{"组织代码", "哈哈",
                //"Jorge", "Sergi","Sergi","Sergi","Sergi","Sergi","Sergi","你好"});

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().add(comboBox2);
        frame.pack(); frame.setVisible(true);
    }


    public static void main(String[] args) {
        //createAndShowGUI();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}