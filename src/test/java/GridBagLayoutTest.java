import javax.swing.*;
import java.awt.*;

/**
 * Created by lyd on 2017/5/12.
 */
public class GridBagLayoutTest {

    public static void main(String[] args) {
        JButton bt1=new JButton("bt1");
        JButton bt2=new JButton("bt2");
        JButton bt3=new JButton("bt3");
        JButton bt4=new JButton("bt4");
        JButton bt5=new JButton("bt5");
        JButton bt6=new JButton("bt6");
        JButton bt7=new JButton("bt7");
        JButton bt8=new JButton("bt8");
        JButton bt9=new JButton("bt9");
        JButton bt10=new JButton("bt10");
        JButton bt11=new JButton("bt11");

        JFrame frame=new JFrame();
        GridBagLayout layout=new GridBagLayout();
        frame.setLayout(layout);

        GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill=GridBagConstraints.BOTH;
        constraints.weightx=0.0;

        layout.setConstraints(bt1,constraints);
       // constraints.gridwidth=2;
        constraints.weightx=1.0;
        layout.setConstraints(bt2,constraints);
        //constraints.gridwidth=GridBagConstraints.REMAINDER;
        constraints.weightx=0.0;
        layout.setConstraints(bt3,constraints);

        constraints.weightx=1.0;

        layout.setConstraints(bt4,constraints);



        frame.add(bt1);
        frame.add(bt2);
        frame.add(bt3);
        frame.add(bt4);
       /* frame.add(bt5);
        frame.add(bt6);
        frame.add(bt7);
        frame.add(bt8);
        frame.add(bt9);
        frame.add(bt10);
        frame.add(bt11);*/

        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
