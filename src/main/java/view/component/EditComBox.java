package view.component;

import constant.EnActionEvent;
import util.StringUtils;
import view.component.ui.MyComboBoxUI;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明: 可以编辑的下拉框,和配合使用ComboBoxMapModel<br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-01<br>
 * <br>
 */
public class EditComBox extends JComboBox   implements KeyListener,ItemListener,Cloneable {
    List<ActionListener> dataChangeListener=new ArrayList<ActionListener>();
    private  String previousValue=null;
    private List<FocusListener> focusListenerList;

    private boolean firingActionEvent = false;
    private boolean focusGained = false;
    private boolean isusematch = true;
    public EditComBox() {
        super();
        init();
    }

    public EditComBox(boolean isusematch) {
        super(new ComboBoxMapModel(new ArrayList<String>()));
        this.isusematch = isusematch;
        init();
    }

    public EditComBox(ComboBoxModel aModel) {
        super(aModel);
        init();
    }
    public EditComBox(ComboBoxModel aModel,boolean isusematch) {
        super(aModel);
        this.isusematch=isusematch;
        init();
    }

    public EditComBox(Object[] items) {
        super(items);
        init();
    }
    private void init(){
       focusListenerList=new ArrayList<>();
        setEditable(true);
        setUI(new MyComboBoxUI());
        setFont(FontFactory.getEditComBoxFont());
        ((MyComboBoxUI)getUI()).tips();

        if (isusematch) {
            JTextComponent editor = (JTextComponent) getEditor().getEditorComponent();
            editor.setDocument(new FixedAutoSelection((ComboBoxMapModel)getModel()));
        }
        //addActionListener(this);
        addItemListener(this);
        //添加按键监听，当按键释放则对控件赋值，这个值也会赋值到table里
        getEditor().getEditorComponent().addKeyListener(this);


        ComBoxFocusListener listener=new ComBoxFocusListener();
        getEditor().getEditorComponent().addFocusListener(listener);

        //this.addFocusListener(listener);
        //setFocusable(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //System.out.println(previousValue);
        //System.out.println(e.getStateChange()+","+e.getItem()+"||--||"+getEditor().getItem().toString());
        if(ItemEvent.SELECTED==e.getStateChange()){
            fireActionEvent(false);
            if(StringUtils.isNullOrEmpty(e.getItem().toString())){
                previousValue=getEditor().getItem().toString();
                return ;
            }
            if(previousValue==null){
                previousValue=getEditor().getItem().toString();
                Object source=this;
                if(this.getModel() instanceof ComboBoxMapModel){
                    source=((ComboBoxMapModel)this.getModel()).getKey(e.getItem().toString());
                }
                if(source==null){
                    source=this;
                }
                ActionEvent event=new ActionEvent(source,1,"EditComBoxDataChange");
                fireDataChangeListener(event);
            }else{
                if(!previousValue.equals(getEditor().getItem().toString())){
                    Object source=this;
                    if(this.getModel() instanceof ComboBoxMapModel){
                        source=((ComboBoxMapModel)this.getModel()).getKey(e.getItem().toString());
                        if(source==null){
                            source=this;
                        }
                    }
                    ActionEvent event=new ActionEvent(source,1,"EditComBoxDataChange");
                    fireDataChangeListener(event);

                    previousValue=getEditor().getItem().toString();

                }
            }
        }else{
            //System.out.println("focusGained"+focusGained);
            if(focusGained){
                if(previousValue==null){
                    if(StringUtils.isNotNullAndNotEmpty(e.getItem().toString())){
                        previousValue=getEditor().getItem().toString();
                    }
                }
                focusGained=false;

            }
        }


       // System.out.println(e);
    }

    class FixedAutoSelection  extends PlainDocument{
        ComboBoxMapModel model;
        // flag to indicate if setSelectedItem has been called
        // subsequent calls to remove/insertString should be ignored
        boolean selecting=false;
        public FixedAutoSelection(ComboBoxMapModel model) {
            this.model = model;
        }
        public void remove(int offs, int len) throws BadLocationException {
            // return immediately when selecting an item
            if (selecting) return;
            super.remove(offs, len);
        }

        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            // return immediately when selecting an item
            if (selecting) return;
            //System.out.println("insert " + str + " at " + offs);
            // insert the string into the document
            super.insertString(offs, str, a);
            // get the resulting string
            String content = getText(0, getLength());
            // lookup a matching item
            Object item = lookupItem(content);
            // select the item (or deselect if null)
            //if(item!=model.getSelectedItem()) System.out.println("Selecting '" + item + "'");
            selecting = true;
            model.setSelectedItem(item);
            selecting = false;
           /* if(item!=null && StringUtils.isNotNullAndNotEmpty(item.toString())){
               if( getEditor().getItem().toString().equals(item)){
                   selecting = false;
               }
            }else{
                selecting = false;
            }*/
        }

        private Object lookupItem(String pattern) {
            /**
             * 匹配思路
             * 多匹配结果，按照匹配度（similarity）进行。匹配度为一个数字（0-100）
             * 1、完全相同，similarity=100
             * 2、包含：similarity=90。超出查询字段个数N，则：similarity=90-N
             * 考虑中：3、按照文本匹配符合个数M,similarity=M
             */
            Object matchResult=null;
            int lastsimilarity=0;
            for (int i=0, n=model.getSize(); i < n; i++) {
                Object currentItem = model.getElementAt(i);
                int similarity=0;
                String patternUpper=pattern.toUpperCase();
                String currentItemUpper=currentItem.toString().toUpperCase();
                if(currentItemUpper.equals(patternUpper)){
                    //similarity=100;
                    matchResult=currentItem;
                    //可直接结束
                    break;
                }else  if (currentItemUpper.contains(patternUpper)){
                    similarity=90-(currentItemUpper.length()-patternUpper.length());
                    if(similarity>lastsimilarity){
                        matchResult=currentItem;
                    }
                }else{

                }
            }
            // no item starts with the pattern => return null
            return matchResult;
        }
    }


    public void addDataChangeActionListener(ActionListener l) {
        dataChangeListener.add(l);
    }
    private void fireDataChangeListener(ActionEvent e){
        for(ActionListener listener:dataChangeListener){
            listener.actionPerformed(e);
        }
    }
    protected void fireActionEvent(){
        fireActionEvent(!isusematch);
    }
    protected void fireActionEvent(boolean stopCellEditing){
        if (!firingActionEvent) {
            // Set flag to ensure that an infinite loop is not created
            firingActionEvent = true;
            ActionEvent e = null;
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            long mostRecentEventTime = EventQueue.getMostRecentEventTime();
            int modifiers = 0;
            AWTEvent currentEvent = EventQueue.getCurrentEvent();
            if (currentEvent instanceof InputEvent) {
                modifiers = ((InputEvent)currentEvent).getModifiers();
            } else if (currentEvent instanceof ActionEvent) {
                modifiers = ((ActionEvent)currentEvent).getModifiers();
            }
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for ( int i = listeners.length-2; i>=0; i-=2 ) {
                if ( listeners[i]==ActionListener.class ) {
                    //DefaultCellEditor里事件会关闭下拉框
                    if(!stopCellEditing){
                        if((listeners[i+1]).getClass().getName().contains(DefaultCellEditor.class.getName())){
                            continue;
                        }
                    }
                    // Lazily create the event:
                    e = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,
                            getActionCommand(),
                            mostRecentEventTime, modifiers);
                    ((ActionListener)listeners[i+1]).actionPerformed(e);
                }
            }
            firingActionEvent = false;
        }
    }
    class ComBoxFocusListener implements FocusListener{
        @Override
        public void focusGained(FocusEvent e) {
            focusGained=true;
            previousValue=null;
            //System.out.println("清空");
           /* if(!getEditor().getItem().toString().equals(previousValue)){
                previousValue=null;
                System.out.println("清空");
            }*/
        }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        getModel().setSelectedItem(getEditor().getItem().toString());
        //enter，触发事件，结束编辑
        if(e.getKeyChar()==KeyEvent.VK_ENTER){
            fireActionEvent(true);
        }
    }
    public void setSelectedItem(Object anObject) {
        //回填选择的值
        if(anObject!=null || StringUtils.isNotNullAndNotEmpty(anObject.toString())){
            getEditor().setItem(anObject.toString());
        }
        super.setSelectedItem(anObject);
    }

    public  Object getSelectedItemReminder(){
        return selectedItemReminder;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public int getSelectedIndex() {
        Object sObject =dataModel.getSelectedItem();
        int i,c;
        Object obj;

        for ( i=0,c=dataModel.getSize();i<c;i++ ) {
            obj = dataModel.getElementAt(i);
            if ( obj != null && obj.equals(sObject) )
                return i;
            obj = ((ComboBoxMapModel)dataModel).getElementValueAt(i);
            if ( obj != null && obj.equals(sObject) )
                return i;
        }
        return -1;
    }

    public boolean isIsusematch() {
        return isusematch;
    }

    public void setIsusematch(boolean isusematch) {
        this.isusematch = isusematch;
    }
}
