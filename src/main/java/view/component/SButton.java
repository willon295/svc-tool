package view.component;

import view.factory.FontFactory;

import javax.swing.*;

/**
 * 功能说明: 自定义button，为后续美化做预备<br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-10<br>
 * <br>
 */
public class SButton extends JButton {
    public SButton(String text) {
        super(text);
        this.setFont(FontFactory.getBtnFont());
    }
}
