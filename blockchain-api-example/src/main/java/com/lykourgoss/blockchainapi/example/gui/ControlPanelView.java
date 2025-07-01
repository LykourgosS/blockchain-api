package com.lykourgoss.blockchainapi.example.gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanelView  extends JFrame {
    public JPanel mainPanel;
    public JTextField textFieldCode;
    public JTextField textFieldTitle;
    public JTextField textFieldDescription;
    public JTextField textFieldCategory;
    public JTextField textFieldPrice;
    public JButton buttonClearAllTemp;
    public JButton buttonAddAllTemp;
    public JButton buttonAddTemp;
    public JButton buttonAdd;
    public JButton buttonSearch;
    public JButton buttonValidate;
    public JTextArea textAreaBlocks;
    public JButton buttonClearText;
    public JButton buttonDeleteBlockchain;

    public ControlPanelView() throws HeadlessException {
        initUI();
    }

    public void start() {
        setTitle("Blockchain Control Panel");
        setPreferredSize(new Dimension(420, 650));
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initUI() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // ==== Top Form Panel ====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Block Information"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(formPanel, gbc);

        textFieldCode = new JTextField(15);
        textFieldTitle = new JTextField(15);
        textFieldDescription = new JTextField(15);
        textFieldCategory = new JTextField(15);
        textFieldPrice = new JTextField(15);

        addFormRow(formPanel, 0, "Code", textFieldCode);
        addFormRow(formPanel, 1, "Title", textFieldTitle);
        addFormRow(formPanel, 2, "Description", textFieldDescription);
        addFormRow(formPanel, 3, "Category", textFieldCategory);
        addFormRow(formPanel, 4, "Price", textFieldPrice);

        // ==== Button Panel ====
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        buttonClearAllTemp = new JButton("Clear All Temp");
        buttonAddAllTemp = new JButton("Add All Temp");
        buttonAddTemp = new JButton("Add Temp");
        buttonAdd = new JButton("Add");
        buttonSearch = new JButton("Search");
        buttonValidate = new JButton("Validate");

        buttonPanel.add(buttonClearAllTemp);
        buttonPanel.add(buttonAddAllTemp);
        buttonPanel.add(buttonAddTemp);
        buttonPanel.add(buttonAdd);
        buttonPanel.add(buttonSearch);
        buttonPanel.add(buttonValidate);

        gbc.gridy = 1;
        mainPanel.add(buttonPanel, gbc);

        // ==== Text Area ====
        textAreaBlocks = new JTextArea(10, 40);
        textAreaBlocks.setEditable(false);
        textAreaBlocks.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textAreaBlocks);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        mainPanel.add(scrollPane, gbc);

        // ==== Bottom Buttons ====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonClearText = new JButton("Clear Text");
        buttonDeleteBlockchain = new JButton("Delete Blockchain");
        bottomPanel.add(buttonClearText);
        bottomPanel.add(buttonDeleteBlockchain);

        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        mainPanel.add(bottomPanel, gbc);
    }

    private void addFormRow(JPanel panel, int row, String labelText, JTextField textField) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel label = new JLabel(labelText);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(textField, gbc);
    }
}
