package view.factory;

import bean.SystemData;

import java.awt.*;

/**
 * Created by lyd on 2017/5/11.
 */
public class FontFactory {

    public static Font getDefaultBoldFont(){
        Font font=new Font(SystemData.default_font_name,Font.BOLD, SystemData.default_font_size);
        return font;
    }
    public static Font getDefaultFont(){
        Font font=new Font(SystemData.default_font_name,Font.BOLD, SystemData.default_font_size);
        return font;
    }
    public static Font getEditComBoxFont(){
        Font font=new Font(SystemData.default_font_name,Font.PLAIN, SystemData.default_font_size);
        return font;
    }

    /**
     * 新窗口，上面home字体大小
     * @return
     */
    public static Font getTabtitleName(){
        Font font=new Font(SystemData.default_font_name,Font.BOLD, SystemData.default_font_size);
        return font;
    }

    /**
     * 展示数据的表头，上面home字体大小
     * @return
     */
    public static Font getDataTabtitleName(){
        Font font=new Font(SystemData.default_font_name,Font.BOLD, SystemData.default_font_size);
        return font;
    }
    /**
     * 新窗口，上面home旁边 x 大小
     * @return
     */
    public static Font getTabtitleX(){
        Font font=new Font(SystemData.default_font_name,Font.TYPE1_FONT,SystemData.default_font_size);
        return font;
    }
    /**
     * 新窗口，上面home旁边 x 大小
     * @return
     */
    public static Font getTabtitleXOn(){
        Font font=new Font(SystemData.default_font_name,Font.TYPE1_FONT,SystemData.default_font_size);
        return font;
    }
    /**
     * 新窗口，上面home旁边 x 鼠标离开后字体
     * @return
     */
    public static Font getTabtitleXOut(){
        Font font=new Font(SystemData.default_font_name,Font.BOLD,SystemData.default_font_size);
        return font;
    }
    public static Font getContentTabTitle(){
        Font font=new Font(SystemData.default_font_name,Font.BOLD,SystemData.default_font_size);
        return font;
    }

    /**
     * 获得显示table字体
     * @return
     */
    public static Font getJTableFont(){
        Font font=new Font(SystemData.default_font_name,Font.PLAIN,SystemData.default_font_size);
        return font;
    }

    /**
     * uc定义底部编辑字体
     * @return
     */
    public static Font getUcDefineFootFont(){
        Font font=new Font(SystemData.default_font_name,Font.PLAIN,SystemData.default_font_size);
        return font;
    }
    /**
     * 普通输入框字体
     * @return
     */
    public static Font getTxtInputFootFont(){
        Font font=new Font(SystemData.default_font_name,Font.PLAIN,SystemData.default_font_size);
        return font;
    }
    /**
     * sql定义输入框字体
     * @return
     */
    public static Font getSqlInputFootFont(){
        Font font=new Font("宋体",Font.PLAIN,SystemData.default_font_size);
        return font;
    }
    /**
     * sql定义输入框字体
     * @return
     */
    public static Font getBtnFont(){
        Font font=new Font(SystemData.default_font_name,Font.PLAIN,SystemData.default_font_size);
        return font;
    }
    /**
     * 普通输入框字体
     * @return
     */
    public static Font getGenSqlFilePathTxtInputFootFont(){
        int size=SystemData.default_font_size;
        if(size>20){
            size-=5;
        }
        Font font=new Font(SystemData.default_font_name,Font.PLAIN,size);
        return font;
    }
    public static Font getFootBar(){
        Font font=new Font(SystemData.default_font_name,Font.BOLD, 15);
        return font;
    }
}
