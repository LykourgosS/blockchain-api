package com.lykourgoss.blockchainapi.gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;

    public ControlPanel() {
        setTitle("My Form");
        setPreferredSize(new Dimension(420, 650));
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
