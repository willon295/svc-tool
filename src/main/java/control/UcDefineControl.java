package control;

import constant.EnActionEvent;
import view.centercontent.UcDefineMaintain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lyd on 2017/5/16.
 */
public class UcDefineControl  implements ActionListener{
    UcDefineMaintain ucDefineMaintain;

    public UcDefineControl(UcDefineMaintain ucDefineMaintain) {
        this.ucDefineMaintain = ucDefineMaintain;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println(e.getActionCommand());
        if(e.getActionCommand().equals(EnActionEvent.UCDEFINE_QUERYCLICK.getCmd())){
            System.err.println(ucDefineMaintain.getInputTxt());
            //向上传播
            ucDefineMaintain.spreadAction(e);
        }else if(e.getActionCommand().equals(EnActionEvent.UCDEFINE_INSERTCLICK.getCmd())){
            //插入

        }else if(e.getActionCommand().equals(EnActionEvent.UCDEFINE_TAILINSERTCLICK.getCmd())){
            //尾插入

        }else if(e.getActionCommand().equals(EnActionEvent.UCDEFINE_DELCLICK.getCmd())){
            //删除 todo 数据中先删除
            ucDefineMaintain.getCenterTable().removeSelect();
        }else if(e.getActionCommand().equals(EnActionEvent.UCDEFINE_SAVECLICK.getCmd())){
            //存盘
        }

    }
}
