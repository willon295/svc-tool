import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public class AwtTest implements ActionListener {

    FileDialog d1 ;
    FileDialog d2 ;

    public AwtTest() {
        Frame f = new Frame("FileDialog Test");
         d1 = new FileDialog(f, "Open File", FileDialog.LOAD);
         d2 = new FileDialog(f, "Save File", FileDialog.SAVE);
        Button b1 = new Button("Open");
        Button b2 = new Button("Save");
        f.add(b1);
        f.add(b2, BorderLayout.SOUTH);

        f.pack();
        f.setVisible(true);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public static void main(String[] args) {

        AwtTest test=new AwtTest();





    }

    @Override
    public void actionPerformed(ActionEvent e) {
        d1.setVisible(true);
        System.out.println(d1.getDirectory() + d1.getFile());
    }
}
