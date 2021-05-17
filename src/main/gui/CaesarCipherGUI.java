package gui;

import model.CipherAlgorithm;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CaesarCipherGUI {
    private static final String FONT_NAME = "Helvetica";
    private static final int MAIN_PANEL_HEIGHT = 1750;
    private static final int MAIN_PANEL_WIDTH = MAIN_PANEL_HEIGHT + 500;
    private static final Color BORDER_COLOR = Color.BLACK;
    private static final Font INPUT_FONT = new Font(FONT_NAME, Font.BOLD, 75);
    private static final Border PANEL_BORDER = BorderFactory.createLineBorder(BORDER_COLOR, 1);

    private JFrame frame;
    private JPanel mainPanel;
    private SidePanel sidePanel;
    private InputPanel inputPanel;
    private CipherAlgorithm cipherAlgorithm;

    //MODIFIES: this
    //EFFECTS: CaesarCipherGUI constructor
    public CaesarCipherGUI() {
        frame = new JFrame();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        inputPanel = new InputPanel(this, mainPanel);
        sidePanel = new SidePanel(this, mainPanel);

        mainPanel.add(inputPanel);
        mainPanel.add(sidePanel);

        createFrame();
    }

    //REQUIRES: mainPanel
    //MODIFIES: this
    //EFFECTS: Creates the GUI Frame
    private void createFrame() {
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(MAIN_PANEL_HEIGHT, MAIN_PANEL_WIDTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
