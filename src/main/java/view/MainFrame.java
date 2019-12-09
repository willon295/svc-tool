package view;

import bean.SystemData;
import constant.ENWarningLevel;
import control.MyActionListener;
import service.ServiceFactory;
import service.SvcService;
import service.impl.SvcServiceImpl;
import util.LogUtil;
import util.PreferencesUtil;
import util.StringUtils;
import view.centercontent.BaseJPanel;
import view.centercontent.GenCode;
import view.centercontent.PreferencesWin;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by lyd on 2017/1/4.
 */
public class MainFrame extends JFrame {
    static SvcService svcService= ServiceFactory.getSvcService();
    private MenuBar menuBar;
    private ToolBar toolBar;
    private FootBar footBar;
    private CenterPanel centerPanel;
    private MyActionListener myActionListener;
    JDialog msgDialog=new JDialog(this);
    private JTextArea msgArea=new JTextArea();

    private static MainFrame mainFrame;

    public MainFrame() throws HeadlessException {
        super("fingard devtool");
        initFrame();
        ImageIcon icon=new ImageIcon("img/log.png"); //
        if(icon!=null){
            this.setIconImage(icon.getImage());
        }
    }
    private  void initFrame(){
        setLayout(new BorderLayout());

        //region 菜单
        myActionListener =new MyActionListener(this);
        //设置服务里监听，用于后台提交信息到窗体
        svcService.setViewListener(myActionListener);
        menuBar=new MenuBar(myActionListener);
        this.setJMenuBar(menuBar);
        //endregion

        //region bar
        toolBar=new ToolBar(myActionListener);
        footBar=new FootBar();
        this.add(toolBar,BorderLayout.NORTH);
        this.add(footBar,BorderLayout.SOUTH);
        //endregion
        //region center中间
        centerPanel=new CenterPanel(myActionListener);
        this.add(centerPanel,BorderLayout.CENTER);
        //endregion





        //region frame基础设置
        setSize(SystemData.winWidth,SystemData.winHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        //endregion
        JScrollPane msgPane=new JScrollPane(msgArea);
        msgDialog.add(msgPane);
    }
    public void addTab(String titleName){
        centerPanel.addTab(titleName);
    }
    public void closeAllTab(){
        centerPanel.closeAllTab();
        //关闭后增加一个默认的
        centerPanel.addTab("");
    }
    public static void main(String[] args) {
        svcService.initSystem();

        if(args!=null && args.length>1){
            String logFilePaht=args[0];
            LogUtil.setFileLogPath(logFilePaht);
        }
        mainFrame=new MainFrame();
        //增加一个默认窗口
        mainFrame.addTab("");
    }

    public void showMsg(String tltle, String msg, ENWarningLevel warningLevel){


        //JlabelSetText(msgLabel,msg);

        msgArea.setText(msg);
        msgDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        msgDialog.setLocationRelativeTo(null);
        msgDialog.setTitle(tltle);
        msgDialog.setSize(400,200);
        msgDialog.setVisible(true);

    }

    /**
     *
     * @param tltle
     * @param msg
     * @param warningLevel
     * @param closeTime 关闭时间，秒
     */
    public void showMsg( String tltle, String msg, ENWarningLevel warningLevel,final long closeTime){
        //JlabelSetText(msgLabel,msg);
        final String tltleStr=tltle+"--"+closeTime+"秒后会关闭";
        final String msgStr=msg;
        showMsg(tltleStr,msg,warningLevel);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(closeTime*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(msgArea.getText().equals(msgStr) && msgDialog.getTitle().equals(tltleStr)){
                    msgDialog.setVisible(false);
                }
            }
        }).start();

    }
    void JlabelSetText(JLabel jLabel, String longString) {
        StringBuilder builder = new StringBuilder("<html>");
        char[] chars = longString.toCharArray();
        FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
        int start = 0;
        int len = 0;
        while (start + len < longString.length()) {
            while (true) {
                len++;
                if (start + len > longString.length())break;
                if (fontMetrics.charsWidth(chars, start, len)
                        > jLabel.getWidth()) {
                    break;
                }
            }
            builder.append(chars, start, len-1).append("<br/>");
            start = start + len - 1;
            len = 0;
        }
        builder.append(chars, start, longString.length()-start);
        builder.append("</html>");
        jLabel.setText(builder.toString());
    }


    public static String openFileSelect(){
        String  filePahtName=null;

       /* FileDialog fileDialog  = new FileDialog(mainFrame, "Open File", FileDialog.LOAD);
        fileDialog.setVisible(true);

        if(fileDialog.getDirectory()!=null && fileDialog.getFile()!=null){
             filePahtName=fileDialog.getDirectory() + fileDialog.getFile();
        }*/

        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY  );
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        filePahtName=file.getAbsolutePath();
        return filePahtName;
    }
    public void changeFootMsg(String status,String msg){
        footBar.changeMsg(status,msg);
    }
    public void changeFootMsg(String conninfoStr,String status,String msg){
        footBar.changeMsg(conninfoStr,status,msg);
    }

    public void showNewWin(String title, BaseJPanel content){
        if(StringUtils.isNullOrEmpty(title)){
            title="新窗体";
        }
        JFrame newFrame=new JFrame(title);
        newFrame.setSize(850,600);
        newFrame.setLocationRelativeTo(null);
        newFrame.setVisible(true);
        newFrame.add(content);
        newFrame.validate();
        newFrame.repaint();
        ImageIcon icon=new ImageIcon("img/log.png"); //
        if(icon!=null){
            newFrame.setIconImage(icon.getImage());
        }

    }

    public void showGenCodeWin() {
        GenCode genCode=new GenCode(myActionListener);
        showNewWin("生成db代码",genCode);
        genCode.onFocus(true);
    }
    public void showPreferenceWin(){
        PreferencesWin preferencesWin=PreferencesWin.getInstance(myActionListener);
        showNewWin("偏好设置",preferencesWin);
        preferencesWin.onFocus(true);
    }

}
