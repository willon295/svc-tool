package control;

import bean.SystemData;
import constant.ENSystem;
import constant.ENWarningLevel;
import constant.EnActionEvent;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.SvcService;
import util.LogUtil;
import util.SvcUtil;
import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lyd on 2017/1/4.
 */
public class MyActionListener implements ActionListener {
    SvcService svcService= ServiceFactory.getSvcService();
    private static Logger logger= LogUtil.getLogger(MyActionListener.class);
    private MainFrame mainFrame;
    @Override
    public void actionPerformed(ActionEvent e) {
        //EnActionEvent enActionEvent=e.getActionCommand();
        if(e.getActionCommand().equals(EnActionEvent.NEWTABCLICK.getCmd())){
            mainFrame.addTab("");
        }else if(e.getActionCommand().equals(EnActionEvent.CLOSEALLTABCLICK.getCmd())){
            mainFrame.closeAllTab();
        }else if(e.getActionCommand().equals(EnActionEvent.EXCEPTION.getCmd())){
            EnActionEvent enActionEvent=(EnActionEvent)e.getSource();
            mainFrame.showMsg("错误",enActionEvent.getMsg(), ENWarningLevel.ERROR);
        }else if(e.getActionCommand().equals(EnActionEvent.INFO.getCmd())){
            EnActionEvent enActionEvent=(EnActionEvent)e.getSource();
            mainFrame.showMsg("消息",enActionEvent.getMsg(), ENWarningLevel.INFO);
        }else if(e.getActionCommand().equals(EnActionEvent.WARNING.getCmd())){
            EnActionEvent enActionEvent=(EnActionEvent)e.getSource();
            mainFrame.showMsg("警告",enActionEvent.getMsg(), ENWarningLevel.WARNING);
        }else if(e.getActionCommand().equals(EnActionEvent.REFRESHCACHE.getCmd())){
            //刷新缓存
            try {
                SvcUtil.refreshCache();
                mainFrame.showMsg("消息","刷新成功", ENWarningLevel.INFO);
            } catch (Exception e1) {
                logger.error("刷新缓存失败",e1);
                mainFrame.showMsg("警告","刷新缓存失败:"+e1.getMessage(), ENWarningLevel.WARNING);
            }
        }else if(e.getActionCommand().equals(EnActionEvent.SWITCHSYSTEM.getCmd())){
            EnActionEvent enActionEvent=(EnActionEvent)e.getSource();
            String systemStr=enActionEvent.getMsg();
            //若更换系统，则全部关闭重新打开界面
            if(systemStr.equals(ENSystem.ATS.getSystemName()) && !systemStr.equals(SystemData.getSystem().getSystemName())){
                SystemData.setSystem(ENSystem.ATS);
                mainFrame.closeAllTab();
            }else if(systemStr.equals(ENSystem.SAAS.getSystemName()) && !systemStr.equals(SystemData.getSystem().getSystemName())){
                SystemData.setSystem(ENSystem.SAAS);
                mainFrame.closeAllTab();

            }
        }else if(e.getActionCommand().equals(EnActionEvent.ABOUTCLICK.getCmd())){
            mainFrame.showMsg("关于","版本号:"+SystemData.versionNumber, ENWarningLevel.INFO);
        } else if(e.getActionCommand().equals(EnActionEvent.PREFERENCE.getCmd())){
            //偏好
            mainFrame.showPreferenceWin();
        } else if(e.getActionCommand().equals(EnActionEvent.OPENGENDBCODE.getCmd())){
            //生成代码
            mainFrame.showGenCodeWin();
        }else if(e.getActionCommand().equals(EnActionEvent.GENERATESQL_SUCSAVE.getCmd())){
            //保存成功
            mainFrame.showMsg("提示","保存成功", ENWarningLevel.INFO,2);
        }

    }

    public void actionPerformedFromService(ENWarningLevel warningLevel,ActionEvent e) {
        EnActionEvent enActionEvent=(EnActionEvent)e.getSource();
        if(enActionEvent==EnActionEvent.SYSTEM_SUCSTART){
            String jdbcurl=SystemData.getDataConnInfo().getJdbcurl();
            jdbcurl=jdbcurl.substring(jdbcurl.indexOf("@"),jdbcurl.length());
            mainFrame.changeFootMsg(SystemData.getDataConnInfo().getUsername()+"/"+
                    SystemData.getDataConnInfo().getPaswword()+"/"+jdbcurl,"连接成功","");
        }else if(enActionEvent==EnActionEvent.SYSTEM_FAILSTART) {
            mainFrame.showMsg("警告",enActionEvent.getMsg(), ENWarningLevel.WARNING);
        }else{
            mainFrame.showMsg("警告",enActionEvent.getMsg(), ENWarningLevel.WARNING);
        }
    }
    public MyActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * 新ActionEvent，source也来自enActionEvent
     * @param enActionEvent
     * @return
     */
    public static ActionEvent getActionEvent(EnActionEvent enActionEvent){
        ActionEvent actionEvent=new ActionEvent(enActionEvent,0,enActionEvent.getCmd());
        return actionEvent;
    }
    public static ActionEvent getActionEvent(EnActionEvent enActionEvent,String msg){
        enActionEvent.setMsg(msg);
        return getActionEvent( enActionEvent);
    }
}
