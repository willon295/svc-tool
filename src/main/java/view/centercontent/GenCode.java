package view.centercontent;

import bean.FileInfo;
import bean.SystemData;
import bean.gencode.ClassInfo;
import bean.gencode.GenCodeViewConfig;
import bean.gencode.adapter.ClassInfoUtil;
import constant.EnActionEvent;
import control.MyActionListener;
import org.apache.log4j.Logger;
import service.GenerateCodeService;
import service.ServiceFactory;
import service.SvcService;
import util.FileUtil;
import util.LogUtil;
import util.StringUtils;
import util.SystemUtil;
import view.MainFrame;
import view.component.ComboBoxMapModel;
import view.component.EditComBox;
import view.component.SButton;
import view.factory.ColorFactory;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 功能说明:
 * @author: lyd
 * 开发时间: 2018-09-30
 */
public class GenCode extends BaseJPanel  implements ActionListener,ItemListener {
    private static Logger logger = LogUtil.getLogger(GenCode.class);
    SvcService svcService= ServiceFactory.getSvcService();
    GenerateCodeService generateCodeService= ServiceFactory.getGenerateCodeService();
    private JLabel tableNamelabel=new JLabel("表名称：");
    JComboBox tableName ;


    private JLabel dtoNamelabel=new JLabel("dto类名：");
    JTextField dtoName =new JTextField(30);

    private JLabel dtoParentlabel=new JLabel("dto父类名：");
    JComboBox dtoParent ;

    private JLabel packageStrlabel=new JLabel("包名：");
    JTextField packageStr =new JTextField("com.fingard.ats.core.db",30);

    private JLabel userNameStrlabel=new JLabel("用户：");
    JTextField userNameStr =new JTextField(SystemData.createuser,20);

    private SButton generateBtn=new SButton("生成");
    private JTextField filepath = new JTextField(60);

    private SButton filechooserbtn=new SButton("选择目录");
    private SButton saveBtn=new SButton("保存");
    private SButton openBtn=new SButton("打开目录");

    private JLabel dtoTextTitle=new JLabel("dto");
    private JLabel daoTextTitle=new JLabel("dao");
    private JLabel daoImplTextTitle=new JLabel("daoImpl");

    private JTextArea dtoText=new JTextArea();
    private JTextArea daoText=new JTextArea();
    private JTextArea daoImplText=new JTextArea();
    private JTextArea beanText=new JTextArea();


    private JTabbedPane jTabbedPane=new JTabbedPane();


    public GenCode(MyActionListener myActionListener) {
        this.myActionListener= myActionListener;
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();*/
        init();
    }
    private void init(){
        try {
            ComboBoxMapModel mapModel = new ComboBoxMapModel(generateCodeService.getTables());
            tableName = new EditComBox(mapModel,true);
        } catch (Exception e) {
            showWarningMsg(e.getMessage());
            return;
        }
        tableName.setActionCommand(EnActionEvent.GENCODE_TABLECHOOSED.getCmd());
        tableName.addItemListener(this);

        Map<String,ClassInfo> parentcls=ClassInfoUtil.getDtoParentClassInfoMap();

        ComboBoxMapModel mapModel = new ComboBoxMapModel( Arrays.asList(parentcls.keySet().toArray()));
        dtoParent=new EditComBox(mapModel);
        dtoParent.setSelectedIndex(0);


        tableNamelabel.setFont(FontFactory.getJTableFont());
        dtoNamelabel.setFont(FontFactory.getJTableFont());
        dtoParentlabel.setFont(FontFactory.getJTableFont());
        packageStrlabel.setFont(FontFactory.getJTableFont());

        dtoName.setFont(FontFactory.getTxtInputFootFont());
        packageStr.setFont(FontFactory.getTxtInputFootFont());
        filepath.setFont(FontFactory.getTxtInputFootFont());

        userNameStrlabel.setFont(FontFactory.getJTableFont());
        userNameStr.setFont(FontFactory.getTxtInputFootFont());

        generateBtn.setFont(FontFactory.getBtnFont());
        filechooserbtn.setFont(FontFactory.getBtnFont());
        saveBtn.setFont(FontFactory.getBtnFont());
        openBtn.setFont(FontFactory.getBtnFont());

        generateBtn.setActionCommand(EnActionEvent.GENCODE_GEN.getCmd());
        filechooserbtn.setActionCommand(EnActionEvent.GENCODE_OPENFILESEL.getCmd());
        saveBtn.setActionCommand(EnActionEvent.GENCODE_SAVE.getCmd());
        openBtn.setActionCommand(EnActionEvent.GENCODE_OPEN.getCmd());

        generateBtn.addActionListener(this);
        filechooserbtn.addActionListener(this);
        saveBtn.addActionListener(this);
        openBtn.addActionListener(this);



        //tableName.setSize(new Dimension(180, 40));
        // 这里就是设置JComboBox宽度的代码
        tableName.setPreferredSize(new Dimension(200, 20));
        dtoParent.setPreferredSize(new Dimension(150, 20));
        //dtoParent.setSize(new Dimension(180, 40));

        JPanel headPanel =new JPanel();

        JPanel infoPanel =new JPanel();
        JPanel infoPanel1 =new JPanel();

        infoPanel1.add(tableNamelabel);
        infoPanel1.add(tableName);

        infoPanel1.add(dtoNamelabel);
        infoPanel1.add(dtoName);

        JPanel infoPanel2 =new JPanel();
        infoPanel2.add(dtoParentlabel);
        infoPanel2.add(dtoParent);

        infoPanel2.add(packageStrlabel);
        infoPanel2.add(packageStr);

        infoPanel2.add(userNameStrlabel);
        infoPanel2.add(userNameStr);

        infoPanel.setLayout(new BorderLayout());
        infoPanel.add(infoPanel1,BorderLayout.NORTH);
        infoPanel.add(infoPanel2,BorderLayout.CENTER);


        JPanel filePanel=new JPanel();
        filePanel.add(generateBtn);
        filePanel.add(filepath);
        filePanel.add(filechooserbtn);
        filePanel.add(saveBtn);
        filePanel.add(openBtn);

        headPanel.setLayout(new BorderLayout());
        headPanel.add(infoPanel,BorderLayout.NORTH);
        headPanel.add(filePanel,BorderLayout.CENTER);


        this.setLayout(new BorderLayout());
        this.add(headPanel,BorderLayout.NORTH);
        //设置边框
        dtoText.setFont(FontFactory.getSqlInputFootFont());
        dtoText.setLineWrap(true);        //激活自动换行功能
        dtoText.setWrapStyleWord(true);            // 激活断行不断字功能
        JScrollPane dtoTextScrollPane = new JScrollPane(dtoText);
        dtoTextScrollPane.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));

        daoText.setFont(FontFactory.getSqlInputFootFont());
        daoText.setLineWrap(true);        //激活自动换行功能
        daoText.setWrapStyleWord(true);            // 激活断行不断字功能
        JScrollPane daoTextScrollPane = new JScrollPane(daoText);
        daoTextScrollPane.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));

        daoImplText.setFont(FontFactory.getSqlInputFootFont());
        daoImplText.setLineWrap(true);        //激活自动换行功能
        daoImplText.setWrapStyleWord(true);            // 激活断行不断字功能
        JScrollPane  daoImplTextScrollPane = new JScrollPane(daoImplText);
        daoImplTextScrollPane.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));

        beanText.setFont(FontFactory.getSqlInputFootFont());
        beanText.setLineWrap(true);        //激活自动换行功能
        beanText.setWrapStyleWord(true);            // 激活断行不断字功能
        JScrollPane  beanTextScrollPane = new JScrollPane(beanText);
        beanTextScrollPane.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));

        jTabbedPane.add("1",dtoTextScrollPane);
        jTabbedPane.add("2",daoTextScrollPane);
        jTabbedPane.add("3",daoImplTextScrollPane);
        jTabbedPane.add("bean",beanTextScrollPane);

        jTabbedPane.setTabComponentAt(jTabbedPane.indexOfComponent(dtoTextScrollPane),dtoTextTitle);
        jTabbedPane.setTabComponentAt(jTabbedPane.indexOfComponent(daoTextScrollPane),daoTextTitle);
        jTabbedPane.setTabComponentAt(jTabbedPane.indexOfComponent(daoImplTextScrollPane),daoImplTextTitle);

        jTabbedPane.setSelectedIndex(3);

        this.add(jTabbedPane,BorderLayout.CENTER);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(EnActionEvent.GENCODE_GEN.getCmd())){
            gen();
        } else if(e.getActionCommand().equals(EnActionEvent.GENCODE_OPENFILESEL.getCmd())){
            String filePath= MainFrame.openFileSelect();
            if(StringUtils.isNotNullAndNotEmpty(filePath)){
                String fileName=filePath+ File.separator;
                if(File.separator.equals("/")){
                    fileName=fileName.replaceAll("\\\\","/");
                }else{
                    fileName=fileName.replaceAll("/","\\\\");
                }
                filepath.setText(fileName);
            }
        }else if(e.getActionCommand().equals(EnActionEvent.GENCODE_SAVE.getCmd())){
            //<editor-fold desc="保存文件">
            String filePathstr=filepath.getText();
            if (StringUtils.isNullOrEmpty(filePathstr)) {
                showWarningMsg("保存目录必填！");
                return;
            }
            FileInfo dto=new FileInfo(dtoTextTitle.getText()+".java",filePathstr,dtoText.getText());
            FileInfo dao=new FileInfo(daoTextTitle.getText()+".java",filePathstr,daoText.getText());
            FileInfo daoImpl=new FileInfo(daoImplTextTitle.getText()+".java",filePathstr,daoImplText.getText());
            List<FileInfo> fileInfoList=new ArrayList<>();
            fileInfoList.add(dto);
            fileInfoList.add(dao);
            fileInfoList.add(daoImpl);
            try {
                FileUtil.saveFile(this,fileInfoList);
            } catch (Exception e1) {
                e1.printStackTrace();
                showWarningMsg("保存异常,异常信息："+e1.getMessage());
            }
            //</editor-fold>
        }else if(e.getActionCommand().equals(EnActionEvent.GENCODE_OPEN.getCmd())){
            //<editor-fold desc="打开文件夹">
            String filePathNamestr=filepath.getText();
            String filePathstr="";
            if (StringUtils.isNullOrEmpty(filePathNamestr)) {
                showWarningMsg("保存目录必填！");
                return;
            }
            String separator="/";
            if(File.separator.equals("/")){
                separator="/";
            }else{
                separator="\\";
            }
            if(StringUtils.isNotNullAndNotEmpty(filePathNamestr)){

                filePathNamestr=filePathNamestr.substring(0,filePathNamestr.lastIndexOf(separator));
            }else{
                filePathNamestr=SystemUtil.getSvcDirectory();
            }

            if(File.separator.equals("/")){
                filePathstr=filePathNamestr.replaceAll("\\\\",separator);
            }else{
                filePathstr=filePathNamestr.replaceAll("/","\\\\");
            }
            String[] cmd = new String[5];
            cmd[0] = "cmd";
            cmd[1] = "/c";
            cmd[2] = "start";
            cmd[3] = " ";
            cmd[4] = filePathstr;
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e1) {
                showWarningMsg("无法打开目录："+filePathstr+",异常信息："+e1.getMessage());
            }
            //</editor-fold>
        } else if(e.getActionCommand().equals(EnActionEvent.GENCODE_TABLECHOOSED.getCmd())){
           String[] table=((String)tableName.getSelectedItem()).split("_");
            String clsName=(String)tableName.getSelectedItem();
           if(table.length>=3){
               clsName=table[2];
           }
            clsName=StringUtils.upperFirstChar(clsName.toLowerCase());
           dtoName.setText(clsName);
           packageStr.setText("com.fingard.ats.core.db."+clsName.toLowerCase());
        }

    }

    @Override
    public void onFocus(boolean refresh) {
        super.onFocus(refresh);
        String filePathNamestr=filepath.getText();
        if (StringUtils.isNullOrEmpty(filePathNamestr)) {
            String filePath= SystemUtil.getDbDirectory();
            if(File.separator.equals("/")){
                filePath=filePath.replaceAll("\\\\","/");
            }else{
                filePath=filePath.replaceAll("/","\\\\");
            }
            filepath.setText(filePath);
        }

    }

    @Override
    public void close() {

    }

    private void showWarningMsg(String msg){
        JOptionPane.showMessageDialog(this,msg,"提示",JOptionPane.WARNING_MESSAGE);

    }

    /**
     * 调用生成，组合所有类
     */
    private void gen(){
        GenCodeViewConfig config=new GenCodeViewConfig();
        config.setTableName((String)tableName.getSelectedItem());
        config.setClassName(dtoName.getText());
        config.setDtoExtendClassName((String)dtoParent.getSelectedItem());
        config.setPackageName(packageStr.getText());
        config.setUserName(userNameStr.getText());
        config.setFilePath(filepath.getText());
        Map<String, String> classcode= null;
        try {
            classcode = generateCodeService.getTableCode(config);
        } catch (Exception e) {
            showWarningMsg("生成失败："+e.getMessage());
            logger.error("生成失败",e);
            return;
        }
        if(classcode!=null){
            String  describe=classcode.get("describe");
            for(Map.Entry<String,String> entry : classcode.entrySet()){
                if(entry.getKey().contains("DaoImpl")){
                    daoImplTextTitle.setText(entry.getKey());
                    daoImplText.setText(entry.getValue());
                    daoImplText.setCaretPosition(0);
                }else if(entry.getKey().contains("Dao")){
                    daoTextTitle.setText(entry.getKey());
                    daoText.setText(entry.getValue());
                    daoText.setCaretPosition(0);
                }else if(entry.getKey().contains(config.getClassName())){
                    dtoTextTitle.setText(entry.getKey());
                    dtoText.setText(entry.getValue());
                    dtoText.setCaretPosition(0);
                }
            }
            String beanCls=config.getPackageName()+".dao.impl."+config.getClassName()+"DaoImpl";
            String beanId=config.getClassName()+"Dao";
            String beanXml=generateCodeService.getBeanXml(beanId,beanCls,describe);
            beanText.setText(beanXml);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        ActionEvent ae=new ActionEvent(e.getSource(),ActionEvent.ACTION_PERFORMED,EnActionEvent.GENCODE_TABLECHOOSED.getCmd());
        this.actionPerformed(ae);
    }
}
