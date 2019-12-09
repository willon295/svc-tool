package view.component;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2017-10-13<br>
 * <br>
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

/**
 * description:自定义的Document
 * 可以控制最大行数
 *     默认最大为10行
 *     超过最大行时，上面的一行将被截取
 *
 *
 */
public class LimitativeDocument extends PlainDocument {
    private JTextComponent textComponent;
    private int lineMax = 10;//最大行数
    private int removeLength=0;//清理行数
    ResizeCallback callback;//清理后回调

    /**
     *
     * @param tc
     * @param lineMax 最大行数
     * @param removeLength  每次超行数后清理行数
     */
    public   LimitativeDocument(JTextComponent tc, int lineMax, int removeLength){
        textComponent = tc;
        this.lineMax = lineMax;
        this.removeLength=removeLength;
    }
    public   LimitativeDocument(JTextComponent tc, int lineMax, int removeLength, ResizeCallback callback){
        textComponent = tc;
        this.lineMax = lineMax;
        this.removeLength=removeLength;
        this.callback=callback;
    }
    public   LimitativeDocument(JTextComponent tc){
        textComponent = tc;
    }
    public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {

        String value =   textComponent.getText();
        String[] valueSplit=value.split("\\n");
        if(value!=null  && valueSplit.length>=lineMax){
            int index=value.indexOf(valueSplit[removeLength]);
            super.remove(0, index+valueSplit[removeLength].length()+1);
            super.insertString(offset-(index+valueSplit[removeLength].length()+1),   s,   attributeSet);
            if(callback!=null){
                callback.callback();
            }
        }else{
            super.insertString(offset,   s,   attributeSet);
        }
    }
    public interface ResizeCallback{
        /**
         * 输入框清理，回调用于界面更改显示坐标
         */
        void callback();
    }

    public void setCallback(ResizeCallback callback) {
        this.callback = callback;
    }
}