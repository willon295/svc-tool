package view.component;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明:支持map的combo <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2017-10-12<br>
 * <br>
 */
public class ComboBox extends JComboBox<String> {
    private Map<String,String> item=new HashMap<String,String>();

    public ComboBox() {
        super();
    }

    public ComboBox(Map<String, String> item) {
        super(item.keySet().toArray(new String[]{}));
        this.item = item;
    }
    public String getSelectItemName(){
        String select=(String)getSelectedItem();
        return select;
    }
    public String getSelectItemValue(){
        return item.get(getSelectItemName());
    }

    public void setSelectedItem(String value) {
        Object selectObj=null;
        for(Map.Entry<String,String> entry : item.entrySet()){
            if(entry.getValue().equals(value)){
                selectObj=entry.getKey();
            }
        }
        super.setSelectedItem(selectObj);
    }
}
