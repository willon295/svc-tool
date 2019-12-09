package view.centercontent;

import bean.TsvcViewconfig;
import constant.EnActionEvent;
import control.MyActionListener;
import service.ServiceFactory;
import service.SvcService;
import service.impl.SvcServiceImpl;
import view.centercontent.ucout.UcOutCenterTable;
import view.component.SButton;
import view.factory.ColorFactory;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by lyd on 2017/5/11.
 */
public class UcOutMaintain extends BaseJPanel   implements ActionListener {
    SvcService svcService= ServiceFactory.getSvcService();
    private   CenterContentPanel centerContentPanel;
    //初始化输入输出
    private JButton initBtn=new SButton("初始化输出");
    //拷贝自
    private JButton copyBtn=new SButton("拷贝自");
    //插入
    private JButton insertBtn=new SButton("插入");
    //尾加
    private JButton tailInsertBtn=new SButton("尾加");
    //删除
    private JButton delBtn=new SButton("删除");
    //存盘
    private JButton saveBtn=new SButton("存盘");
    //刷新
    private JButton refreshBtn=new SButton("刷新");
    //上移
    private JButton upBtn=new SButton("上移");
    //下移
    private JButton downBtn=new SButton("下移");

    //中间table展示
    private UcOutCenterTable centerTable;
    //用于判断是否刷新
    String currentUc="";
    List<TsvcViewconfig> ucOutDatas;
    private boolean isDataChanged=false;
    @Override
    public void close() {
        centerTable=null;
    }


    public UcOutMaintain(MyActionListener myActionListener,CenterContentPanel centerContentPanel) {
        this.centerContentPanel=centerContentPanel;
        this.myActionListener= myActionListener;
        centerTable=new UcOutCenterTable(myActionListener,this,centerContentPanel);
        init();
    }

    private void init(){
        //<editor-fold desc="设置命令">
        initBtn.setActionCommand(EnActionEvent.UCOUT_INIT.getCmd());
        copyBtn.setActionCommand(EnActionEvent.UCOUT_COPY.getCmd());
        insertBtn.setActionCommand(EnActionEvent.UCOUT_INSERT.getCmd());
        tailInsertBtn.setActionCommand(EnActionEvent.UCOUT_TAILINSERT.getCmd());
        delBtn.setActionCommand(EnActionEvent.UCOUT_DEL.getCmd());
        saveBtn.setActionCommand(EnActionEvent.UCOUT_SAVE.getCmd());
        refreshBtn.setActionCommand(EnActionEvent.UCOUT_REFRESH.getCmd());
        upBtn.setActionCommand(EnActionEvent.UCOUT_UP.getCmd());
        downBtn.setActionCommand(EnActionEvent.UCOUT_DOWN.getCmd());
        //</editor-fold>

        //<editor-fold desc="设置监听">
        initBtn.addActionListener(this);
        copyBtn.addActionListener(this);
        insertBtn.addActionListener(this);
        tailInsertBtn.addActionListener(this);
        delBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        refreshBtn.addActionListener(this);
        upBtn.addActionListener(this);
        downBtn.addActionListener(this);
        //</editor-fold>

        //<editor-fold desc="设置字体">
      /*  initBtn.setFont(FontFactory.getBtnFont());
        copyBtn.setFont(FontFactory.getBtnFont());
        insertBtn.setFont(FontFactory.getBtnFont());
        tailInsertBtn.setFont(FontFactory.getBtnFont());
        delBtn.setFont(FontFactory.getBtnFont());
        saveBtn.setFont(FontFactory.getBtnFont());
        refreshBtn.setFont(FontFactory.getBtnFont());
        upBtn.setFont(FontFactory.getBtnFont());
        downBtn.setFont(FontFactory.getBtnFont());*/
        //</editor-fold>

        //<editor-fold desc="北部panel">
        JPanel northJPanel=new JPanel();
        //设置边框
        northJPanel.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));
        northJPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        northJPanel.add(initBtn);
        //northJPanel.add(copyBtn);
        northJPanel.add(insertBtn);
        northJPanel.add(tailInsertBtn);
        northJPanel.add(delBtn);
        northJPanel.add(saveBtn);
        northJPanel.add(refreshBtn);
        northJPanel.add(upBtn);
        northJPanel.add(downBtn);
        //</editor-fold>

        //添加组件到窗体
        this.setLayout(new BorderLayout());
        this.add(northJPanel,BorderLayout.NORTH);
        this.add(centerTable,BorderLayout.CENTER);

    }
    @Override
    public void onFocus(boolean refresh) {
        if(!currentUc.equals(centerContentPanel.getUcNo())){
            centerTable.reloadUc(centerContentPanel.getUcNo());
        }else{
            if(refresh){
                centerTable.reloadUc(centerContentPanel.getUcNo());
            }
        }
        currentUc=centerContentPanel.getUcNo();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println(e.getActionCommand());
        if(e.getActionCommand().equals(EnActionEvent.UCOUT_SAVE.getCmd())){
            java.util.List<TsvcViewconfig> tsvcInterfaceList=centerTable.getAllColumnDatas();
            //存盘
            svcService.saveTsvcViewconfig(tsvcInterfaceList,centerContentPanel.getUcNo());
            //刷新
            centerTable.reloadUc(centerContentPanel.getUcNo());
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_INIT.getCmd())){
            centerTable.initData();
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_COPY.getCmd())){

        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_INSERT.getCmd())){
            //插入
            centerTable.insert();
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_TAILINSERT.getCmd())){
            //尾加
            centerTable.tailInsert();
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_DEL.getCmd())){
            //删除
            centerTable.removeSelect();
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_REFRESH.getCmd())){
            centerTable.reloadUc(centerContentPanel.getUcNo());
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_UP.getCmd())){
            int index=centerTable.getCurrentSelIndex();
            if(index>0){
                if(svcService.exchange(centerTable.getCurrentCloum(),centerTable.getCloum(index-1))){
                    centerTable.reloadUc(centerContentPanel.getUcNo());
                    centerTable.cloumSelect(index-1);
                }
            }
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_DOWN.getCmd())){
            int index=centerTable.getCurrentSelIndex();
            if(index<centerTable.getTotalCount()-1){
                if(svcService.exchange(centerTable.getCurrentCloum(),centerTable.getCloum(index+1))){
                    centerTable.reloadUc(centerContentPanel.getUcNo());
                    centerTable.cloumSelect(index+1);
                }
            }
        }
    }
    private void dataChahge(){
        if(hasChange()){
            centerContentPanel.dataChange(true);
            isDataChanged=true;
        }else{
            centerContentPanel.dataChange(false);
            isDataChanged=false;
        }

    }

    /**
     * 判断是否数据改变
     * @return
     */
    private boolean hasChange(){
        List<TsvcViewconfig> ucInDatasView=centerTable.getAllColumnDatas();
        if(ucInDatasView==null || ucInDatasView.size()==0){
            if(ucOutDatas!=null && ucOutDatas.size()>0){
                return true;
            }
        }else{
            if(ucOutDatas==null || ucOutDatas.size()==0){
                return true;
            }
            /**
             * 判断是否有变更
             *1、长度不相同
             * 2、逐条判断hash
             */
            if(ucInDatasView.size()!=ucOutDatas.size()){
                return true;
            }
            for (int i=0;i<ucOutDatas.size();i++){
                if(ucOutDatas.get(i).hashCode()!=ucInDatasView.get(i).hashCode()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canLoseFcous() {
        dataChahge();
        return super.canLoseFcous();
    }

    public void setUcOutDatas(List<TsvcViewconfig> ucOutDatas) {
        this.ucOutDatas = ucOutDatas;
    }

    public boolean isDataChanged() {
        return isDataChanged;
    }

    public void setDataChanged(boolean dataChanged) {
        isDataChanged = dataChanged;
    }
}
