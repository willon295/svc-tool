package view.component;

import view.component.ui.MyComboBoxUI;

import javax.swing.*;

/**
 * 功能说明: 联合ComboBoxMapModel使用<br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-05<br>
 * <br>
 */
public class MapComboBox extends JComboBox<String> {
    public MapComboBox() {
    }

    public MapComboBox(ComboBoxModel<String> aModel) {
        super(aModel);
        setUI(new MyComboBoxUI());
        ((MyComboBoxUI)getUI()).tips();
    }

    public void setSelectedItem(Object anObject) {
        dataModel.setSelectedItem(anObject);
        super.setSelectedItem(anObject);
    }

}
