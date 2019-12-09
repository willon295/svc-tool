package view.component.ui;



import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-07<br>
 * <br>
 */
public class MyComboBoxUI extends BasicComboBoxUI {
    public MyComboBoxUI() {
        super();
    }

    public void tips() {
        if (listBox != null) {
            listBox.addMouseMotionListener(new MouseMotionListener() {
                Component oldCom;
                Component curCom;

                public void mouseMoved(MouseEvent e) {
                    curCom = listBox.getCellRenderer()
                            .getListCellRendererComponent(listBox, null, 0,
                                    true, true);
                    if (oldCom == null || oldCom != curCom) {
                        oldCom = curCom;
                    }
                    if (oldCom instanceof JComponent) {
                        oldCom.setFont(FontFactory.getDataTabtitleName());
                        ((JComponent) oldCom)
                                .setToolTipText(""+listBox.getSelectedValue());

                    }
                }

                public void mouseDragged(MouseEvent e) {
                }
            });
        }
    }
}
