package gui;

import javax.swing.*;

public class SidePanel extends JPanel {
    private CaesarCipherGUI mainFrame;
    private JPanel sidePanel;

    public SidePanel(CaesarCipherGUI mainFrame, JPanel sidePanel) {
        this.mainFrame = mainFrame;
        this.sidePanel = sidePanel;
    }
}
