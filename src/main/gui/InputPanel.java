package gui;

import javax.swing.*;

public class InputPanel extends JPanel {
    private CaesarCipherGUI mainFrame;
    private JPanel inputPanel;

    public InputPanel(CaesarCipherGUI mainFrame, JPanel inputPanel) {
        this.mainFrame = mainFrame;
        this.inputPanel = inputPanel;
    }
}
