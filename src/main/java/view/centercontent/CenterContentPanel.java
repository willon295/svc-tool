package view.centercontent;

import control.MyActionListener;
import view.CenterpanelTab;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyd on 2017/5/11.
 * 内层panel,包含多个tab页
 */
public class CenterContentPanel extends BaseJPanel implements ChangeListener {
    /**
     * 记录选项卡当前选择下表，切换前用于判断是否可以切换
     */
    private int currentSelectIndex=-1;
    private CenterpanelTab centerpanelTab;
    private HeadPanel headPanel;
    private JTabbedPane contentTab;
    private BaseJPanel ucDefineMaintain;
    private BaseJPanel sqlMaintain;
    private BaseJPanel ucInMaintain;
    private BaseJPanel ucOutMaintain;
    private BaseJPanel generateSql;
    private BaseJPanel genCode;
    private Map<Integer,String> title=new HashMap<>();

    public CenterContentPanel(MyActionListener myActionListener, CenterpanelTab centerpanelTab) {
        title.put(0,"功能表定义维护");
        title.put(1,"数据源SQL维护");
        title.put(2,"功能输入输出定义");
        title.put(3,"数据源→查询显示配置");
        this.centerpanelTab = centerpanelTab;
        this.myActionListener = myActionListener;
        ucDefineMaintain = new UcDefineMaintain(myActionListener, this);
        sqlMaintain = new SqlMaintain(myActionListener, this);
        ucInMaintain = new UcInMaintain(myActionListener, this);
        ucOutMaintain = new UcOutMaintain(myActionListener, this);
        generateSql = new GenerateSql(myActionListener, this);
        //genCode = new GenCode(myActionListener);
        init();
        currentSelectIndex=contentTab.getSelectedIndex();
    }

    private void init() {
        setLayout(new BorderLayout());
        headPanel = new HeadPanel();
        contentTab = new JTabbedPane();
        //todo title 样式
        //contentTab.setFont(FontFactory.getContentTabTitle());

        contentTab.addTab("功能表定义维护", ucDefineMaintain);
        contentTab.addTab("数据源SQL维护", sqlMaintain);
        contentTab.addTab("功能输入输出定义", ucInMaintain);
        contentTab.addTab("数据源→查询显示配置", ucOutMaintain);
        contentTab.addTab("生成SQL语言", generateSql);

        contentTab.setFont(FontFactory.getDefaultFont());

        //contentTab.addTab("DB代码生成", genCode);
        contentTab.addChangeListener(this);
        this.add(headPanel, BorderLayout.NORTH);
        this.add(contentTab, BorderLayout.CENTER);
    }

    public void setHeadValue(String ucCode, String ucNo, String ucName) {
        headPanel.setHeadValue(ucCode, ucNo, ucName);
    }

    public String getUcNo() {
        return headPanel.getUcNo();
    }

    public String getUcCodeField() {
        return headPanel.getUcCodeField();
    }

    /**
     * 更改外层title
     * @param title
     */
    public void setTitle(String title) {
        centerpanelTab.setTitle(title);
    }

    /**
     * 更改内层title。用于记录是否有数据变动
     */
    public void dataChange(boolean hasChange){
        String titleStr=title.get(contentTab.getSelectedIndex());
        JLabel titleLabel=new JLabel(titleStr);
        titleLabel.setFont(FontFactory.getContentTabTitle());
        if(hasChange){
            titleLabel.setForeground(Color.red);

        }else{
            titleLabel.setForeground(Color.BLACK);
        }
        contentTab.setTabComponentAt(contentTab.getSelectedIndex(),titleLabel);
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        ((BaseJPanel) contentTab.getSelectedComponent()).onFocus(false);
        contentTab.removeChangeListener(this);
        int nextIndex=contentTab.getSelectedIndex();
        contentTab.setSelectedIndex(currentSelectIndex);
        if(((BaseJPanel) contentTab.getSelectedComponent()).canLoseFcous()){
            //有为改变数据
        }
        contentTab.setSelectedIndex(nextIndex);
       /* if(((BaseJPanel) contentTab.getSelectedComponent()).canLoseFcous()){
            contentTab.setSelectedIndex(nextIndex);
            ((BaseJPanel) contentTab.getSelectedComponent()).onFocus(true);
        }else{
            int i=JOptionPane.showConfirmDialog(this,"修改没有保存。确定不保存当前修改吗？","提示修改",JOptionPane.WARNING_MESSAGE);
            if(i==2){
                //取消
                ((BaseJPanel) contentTab.getSelectedComponent()).onFocus(false);
            }else{
                //确认切换
                contentTab.setSelectedIndex(nextIndex);
                ((BaseJPanel) contentTab.getSelectedComponent()).onFocus(true);

            }
        }*/
        contentTab.addChangeListener(this);
        currentSelectIndex=contentTab.getSelectedIndex();
    }

    public void close() {
        headPanel = null;
        contentTab = null;

        ucDefineMaintain.close();
        sqlMaintain.close();
        ucInMaintain.close();
        ucOutMaintain.close();
        generateSql.close();
        //genCode.close();

        ucDefineMaintain = null;
        sqlMaintain = null;
        ucInMaintain = null;
        ucOutMaintain = null;
        generateSql = null;
        //genCode = null;
    }
}
