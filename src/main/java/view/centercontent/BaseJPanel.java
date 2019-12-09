package view.centercontent;

import constant.EnActionEvent;
import control.MyActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by lyd on 2017/5/11.
 */
public abstract class BaseJPanel extends JPanel {

    protected MyActionListener myActionListener;


    protected void handleExceptionMsg(Exception e){
        ActionEvent action=MyActionListener.getActionEvent(EnActionEvent.EXCEPTION,e.getMessage());
        myActionListener.actionPerformed(action);
        e.printStackTrace();
    }
    /**
     * 控件被选中，tab页时重写即可
     */
    public boolean canLoseFcous(){
        return true;
    }
    /**
     * 控件被选中，tab页时重写即可
     */
    public void onFocus(boolean refresh){

    }
    public abstract void close();

    public MyActionListener getMyActionListener() {
        return myActionListener;
    }

    public void setMyActionListener(MyActionListener myActionListener) {
        this.myActionListener = myActionListener;
    }
}
