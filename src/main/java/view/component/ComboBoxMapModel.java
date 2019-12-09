package view.component;

import util.StringUtils;

import javax.swing.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-05<br>
 * <br>
 */
public class ComboBoxMapModel  extends AbstractListModel<String> implements ComboBoxModel<String>, Serializable {
    Vector<Object> key;
    Map<Object,String> objects;
    Object selectedObject;

    public ComboBoxMapModel(Map<Object, String> map,String nouser) {
        objects = new LinkedHashMap<Object,String>();
        key=new Vector<Object>();
        int i,c;
        for ( Map.Entry<Object,String> entry : map.entrySet()){
            key.add(entry.getKey());
            objects.put(entry.getKey(),entry.getValue());
        }
        if ( getSize() > 0 ) {
            selectedObject = objects.get(getElementAt( 0 ));
        }
    }
    public ComboBoxMapModel(Map<String, String> map) {
        objects = new LinkedHashMap<Object,String>();
        key=new Vector<Object>();
        int i,c;
        for ( Map.Entry<String,String> entry : map.entrySet()){
            key.add(entry.getKey());
            objects.put(entry.getKey(),entry.getValue());
        }
        if ( getSize() > 0 ) {
            selectedObject = objects.get(getElementAt( 0 ));
        }
    }

    public ComboBoxMapModel(List list) {
        objects = new LinkedHashMap<Object,String>();
        key=new Vector<Object>();
        for(Object str :list){
            String strrr=StringUtils.valueOf(str);
            objects.put(strrr,strrr);
            key.add(strrr);
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if(anItem==null || StringUtils.isNullOrEmpty(anItem.toString())){
            selectedObject="";
        }else{
            if(objects.containsKey(anItem.toString())){
                selectedObject=objects.get(anItem.toString());
            }else if(objects.containsKey(anItem)){
                selectedObject=objects.get(anItem);
            }else{
                boolean hasItem=false;
                for(Map.Entry<Object,String> entry : objects.entrySet()){
                    if(anItem.toString().equals(entry.getValue())){
                        selectedObject=entry.getValue();
                        hasItem=true;
                        break;
                    }else if(anItem.toString().equals(entry.getKey().toString())){
                        selectedObject=entry.getValue();
                        hasItem=true;
                        break;
                    }
                }
                if(!hasItem){
                    selectedObject=anItem.toString();
                }
            }
        }
        fireContentsChanged(this, -1, -1);
    }

    @Override
    public Object getSelectedItem() {
        return selectedObject;
    }

    @Override
    public int getSize() {
        return objects.size();
    }

    @Override
    public String getElementAt(int index) {
        return key.get(index).toString();
    }
    public String getElementValueAt(int index) {
        return objects.get(key.get(index)).toString();
    }
    /**
     * 通过value 获取对应的key
     * @param value
     * @return
     */
    public Object getKey(String value){
        Object obj=null;
        for(Map.Entry<Object,String> entry : objects.entrySet()){
            if(value.equals(entry.getValue())){
                obj=entry.getKey();
                break;
            }
        }
        return obj;
    }
}
