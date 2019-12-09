import java.awt.event.*;

import javax.swing.*;



public class tab extends JFrame implements ActionListener{

    JMenuItem mi;

    static JTabbedPane pane;

    public tab()

    {

        super("选项卡窗格");

        setVisible(true);

        setSize(800,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        mi=new JMenuItem("增加");

        JMenu menu=new JMenu("增加");

        menu.add(mi);

        JMenuBar bar=new JMenuBar();

        bar.add(menu);

        setJMenuBar(bar);

        pane = new JTabbedPane();

        add(pane);

        new addTab();

        mi.addActionListener(this);

    }

    public static void main(String[] args) throws Exception

    {

        new tab();
    }

    @Override

    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub

        if(e.getSource()==mi){

            new addTab();

        }

    }

}  