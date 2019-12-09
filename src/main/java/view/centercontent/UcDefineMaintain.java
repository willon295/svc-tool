package view.centercontent;

import bean.HsiRight;
import bean.SystemData;
import constant.ENSystem;
import constant.EnActionEvent;
import service.ServiceFactory;
import service.UcDefineService;
import service.impl.UcDefineServiceImpl;
import util.CommonUtil;
import util.StringUtils;
import control.MyActionListener;
import view.centercontent.fcdefine.CenterTable;
import view.centercontent.fcdefine.FootPanel;
import view.component.SButton;
import view.factory.ColorFactory;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyd on 2017/5/11.
 * 功能表定义维护panel
 * 使用BorderLayout布局，上中下三部分
 */
public class UcDefineMaintain extends BaseJPanel implements KeyListener {
    private CenterContentPanel centerContentPanel;
    UcDefineService ucDefineService = ServiceFactory.getUcDefineService();
    private JButton insertBtn;    //插入按钮
    private JButton tailInsertBtn;//尾加按钮
    private JButton delBtn;       //删除按钮
    private JButton saveBtn;      //存盘按钮
    private JButton exportExcelBtn;//导出excel按钮

    private JTextField queryInput;//输入查询框
    private JButton queryBtn; //查询按钮

    private BorderLayout layout;

    private UcDefineControl control;
    private CenterTable centerTable;//中间table展示
    private FootPanel footPanel;//中间table展示

    private Map<String, HsiRight> addUcMap = new HashMap<String, HsiRight>();
    private Map<String, HsiRight> editUcMap = new HashMap<String, HsiRight>();
    private List<String> deleUcList = new ArrayList<String>();


    @Override
    public void close() {
        centerTable.close();
        footPanel.close();
        ucDefineService = null;
        addUcMap = null;
        editUcMap = null;
        deleUcList = null;

    }

    /**
     * 获得输入框数据
     *
     * @return
     */
    public String getInputTxt() {
        return queryInput.getText();
    }

    /**
     * 向上传播动作,传播到myActionListener
     *
     * @param e
     */
    public void spreadAction(ActionEvent e) {
        myActionListener.actionPerformed(e);
    }

    public UcDefineMaintain(MyActionListener myActionListener, CenterContentPanel centerContentPanel) {
        this.centerContentPanel = centerContentPanel;
        this.myActionListener = myActionListener;
        control = new UcDefineControl(this);
        centerTable = new CenterTable(myActionListener, this);
        footPanel = new FootPanel(myActionListener, this);
        init();
    }

    private void init() {
        layout = new BorderLayout();
        this.setLayout(layout);
        initNorth();
        this.add(centerTable, BorderLayout.CENTER);
        if (SystemData.getSystem() == ENSystem.ATS) {
            this.add(footPanel, BorderLayout.SOUTH);
        }
    }

    /**
     * BorderLayout的North
     */
    private void initNorth() {
        insertBtn = new SButton("插入");
        tailInsertBtn = new SButton("尾加");
        delBtn = new SButton("删除");
        saveBtn = new SButton("存盘");
        queryInput = new JTextField(20);
        queryInput.setFont(FontFactory.getTxtInputFootFont());
        queryInput.addKeyListener(this);
        queryBtn = new SButton("查询");



        //region 监听命令添加
        if (SystemData.getSystem() == ENSystem.ATS) {
            insertBtn.setActionCommand(EnActionEvent.UCDEFINE_INSERTCLICK.getCmd());
            tailInsertBtn.setActionCommand(EnActionEvent.UCDEFINE_TAILINSERTCLICK.getCmd());
            delBtn.setActionCommand(EnActionEvent.UCDEFINE_DELCLICK.getCmd());
            saveBtn.setActionCommand(EnActionEvent.UCDEFINE_SAVECLICK.getCmd());

            insertBtn.addActionListener(control);
            tailInsertBtn.addActionListener(control);
            delBtn.addActionListener(control);
            saveBtn.addActionListener(control);
        }
        queryBtn.setActionCommand(EnActionEvent.UCDEFINE_QUERYCLICK.getCmd());
        queryBtn.addActionListener(control);

        //endregion


        JPanel northPanel = new JPanel();
        //设置边框
        northPanel.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(), 1));

        //region 布局管理
        GridLayout layout = new GridLayout(1, 2);
        northPanel.setLayout(layout);

        JPanel btnJpanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        btnJpanel.setLayout(flowLayout);
        // btnJpanel.add(insertBtn);
        if (SystemData.getSystem() == ENSystem.ATS) {
            btnJpanel.add(tailInsertBtn);
            btnJpanel.add(delBtn);
            btnJpanel.add(saveBtn);
        }

        JPanel queryJpanel = new JPanel();
        queryJpanel.setLayout(flowLayout);
        //queryInput.setSize(50,10);
        //queryInput.setBounds(10,10,50,170);
        JLabel jLabel=new JLabel("权限/UC");
        jLabel.setFont(FontFactory.getDefaultFont());
        queryJpanel.add(jLabel);
        queryJpanel.add(queryInput);
        queryJpanel.add(queryBtn);
       /* GridBagLayout queryPanellayout=new GridBagLayout();
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill=GridBagConstraints.BOTH;
        constraints.weightx=0.0;
        queryJpanel.setLayout(queryPanellayout);
        queryInput.setSize(50,10);
        queryJpanel.add(new JLabel("权限/UC"));
        constraints.weightx=1.0;
        queryJpanel.add(queryInput);
        constraints.weightx=0.0;
        queryJpanel.add(queryBtn);*/
        //endregion

        //添加元素
        northPanel.add(btnJpanel);
        northPanel.add(queryJpanel);


        this.add(northPanel, BorderLayout.NORTH);
    }

    public CenterTable getCenterTable() {
        return centerTable;
    }

    public void tableSelect(int index, String functionno) {
        HsiRight hsiRight = null;
        String opttype = "";
        if(SystemData.getSystem()== ENSystem.ATS) {
            if (addUcMap.containsKey(index + "")) {
                opttype = FootPanel.OPTTYPE_ADD;
                hsiRight = addUcMap.get(index + "");
            } else if (editUcMap.containsKey(index + "")) {
                opttype = FootPanel.OPTTYPE_MOD;
                hsiRight = editUcMap.get(index + "");
            } else {
                opttype = FootPanel.OPTTYPE_MOD;

                if (StringUtils.isNotNullAndNotEmpty(functionno)) {
                    //不为空才去查
                    hsiRight = ucDefineService.getUc(functionno);
                }

            }
            if (hsiRight == null) {
                //更改title
                centerContentPanel.setTitle("home");
                //头部更改
                centerContentPanel.setHeadValue("", "", "");
                //底部修改
                footPanel.setFootPanelData(null, index, "");
            } else {
                //更改title
                centerContentPanel.setTitle(hsiRight.getC_rightname());
                //头部更改
                centerContentPanel.setHeadValue(hsiRight.getC_rightcode(), hsiRight.getC_functionno(), hsiRight.getC_rightname());
                //底部修改
                footPanel.setFootPanelData(hsiRight, index, opttype);
            }
        }else{
            //更改title
            centerContentPanel.setTitle(functionno);
            //头部更改
            centerContentPanel.setHeadValue("", functionno, "");
        }


    }

    public void addAddUcMap(String index, HsiRight hsiRight) {

        addUcMap.put(index, hsiRight);
        //联动更改列表数据
        centerTable.setColumnData(Integer.parseInt(index), hsiRight);
        dataChahge();
    }

    public void removeAddUcMap(String index, HsiRight hsiRight) {
        if (addUcMap.containsKey(index)) {
            addUcMap.remove(index);
            //联动更改列表数据
            centerTable.setColumnData(Integer.parseInt(index), hsiRight);
        }
        dataChahge();
    }

    public void addEditUcMap(String index, HsiRight hsiRight) {
        editUcMap.put(index, hsiRight);
        //联动更改列表数据
        centerTable.setColumnData(Integer.parseInt(index), hsiRight);
        dataChahge();
    }

    public void removeEditUcMap(String index, HsiRight hsiRight) {
        if (editUcMap.containsKey(index)) {
            editUcMap.remove(index);
            //联动更改列表数据
            centerTable.setColumnData(Integer.parseInt(index), hsiRight);
        }
        dataChahge();
    }

    //内部监听类
    class UcDefineControl implements ActionListener {
        UcDefineMaintain ucDefineMaintain;

        public UcDefineControl(UcDefineMaintain ucDefineMaintain) {
            this.ucDefineMaintain = ucDefineMaintain;
        }

        public void actionPerformed(ActionEvent e) {
            System.err.println(e.getActionCommand());
            if (e.getActionCommand().equals(EnActionEvent.UCDEFINE_QUERYCLICK.getCmd())) {
                query();
            } else if (e.getActionCommand().equals(EnActionEvent.UCDEFINE_INSERTCLICK.getCmd())) {
                //插入

            } else if (e.getActionCommand().equals(EnActionEvent.UCDEFINE_TAILINSERTCLICK.getCmd())) {
                //尾插入
                //region 尾插
                HsiRight hsiRight = centerTable.tailInsert();
                int index = (centerTable.getRowCount() - 1);
                addUcMap.put(index + "", hsiRight);
                centerTable.cloumSelect(index);
                System.out.println("尾加：" + index);
                //endregion
            } else if (e.getActionCommand().equals(EnActionEvent.UCDEFINE_DELCLICK.getCmd())) {
                //region 删除
                //删除,先保存删除的uc
                Map<String, List<String>> delMap = ucDefineMaintain.getCenterTable().removeSelect();
                List<String> ucList = delMap.get("uc");
                List<String> indexList = delMap.get("index");
                deleUcList.addAll(ucList);
                //如果在编辑map中，则清除
                for (String index : indexList) {
                    if (addUcMap.containsKey(index)) {
                        addUcMap.remove(index);
                    }
                    if (editUcMap.containsKey(index)) {
                        editUcMap.remove(index);
                    }
                }
                //endregion
            } else if (e.getActionCommand().equals(EnActionEvent.UCDEFINE_SAVECLICK.getCmd())) {
                //region 存盘
                List addUcList = CommonUtil.mapvalueToList(addUcMap);
                List editUcList = CommonUtil.mapvalueToList(editUcMap);
                boolean suc = true;
                ucDefineService.save(deleUcList, addUcList, editUcList);
                if (suc) {
                    //清空所有缓存数据
                    addUcMap.clear();
                    ;
                    editUcMap.clear();
                    deleUcList.clear();
                    //刷新数据
                    //重新加载table
                    String searchTxt = ucDefineMaintain.getInputTxt();
                    centerTable.reloadUc(searchTxt);
                    //底部选择第一行
                    centerTable.cloumSelect(0);
                }
                //endregion
            }
            dataChahge();
            //向上传播
            ucDefineMaintain.spreadAction(e);
        }

    }

    private void query() {
        //清空所有缓存数据
        addUcMap.clear();
        editUcMap.clear();
        deleUcList.clear();
        //重新加载table
        String searchTxt = this.getInputTxt();
        centerTable.reloadUc(searchTxt);
        //底部选择第一行
        centerTable.cloumSelect(0);

    }

    @Override
    public boolean canLoseFcous() {
        if (addUcMap.size() > 0 || editUcMap.size() > 0 || deleUcList.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public void onFocus(boolean refresh) {
        if (refresh) {
            query();
        }
    }

    private void dataChahge() {
        if (addUcMap.size() > 0 || editUcMap.size() > 0 || deleUcList.size() > 0) {
            centerContentPanel.dataChange(true);
        } else {
            centerContentPanel.dataChange(false);
        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()==KeyEvent.VK_ENTER){
            query();
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar()!=KeyEvent.VK_ENTER){
            String searchTxt = this.getInputTxt();
            if(StringUtils.isNotNullAndNotEmpty(searchTxt)){
                query();
            }
        }
    }
}
