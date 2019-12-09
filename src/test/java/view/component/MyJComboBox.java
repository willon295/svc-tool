package view.component;

/**
 * java 监视JComboBox改变
 * 解答时间：2010-04-07 22:25:00
 * 做了一个可编辑的JComboBox,怎么监听其文本的改变
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class MyJComboBox extends JFrame {
    private JComboBox comBox;
    private String[] str = new String[]{"ITEM1", "ITEM2"};

    public MyJComboBox() {
        init();
    }

    private void init() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        comBox = new JComboBox(str);
        comBox.getEditor().getEditorComponent().addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
                System.out.println(comBox.getEditor().getItem().toString());
            }

            public void keyTyped(KeyEvent e) {

            }
        });
        comBox.setEditable(true);
        add(comBox);
    }

    public static void main(String[] args) {
        MyJComboBox main = new MyJComboBox();
        main.setSize(100, 70);
        main.setVisible(true);
    }
}

