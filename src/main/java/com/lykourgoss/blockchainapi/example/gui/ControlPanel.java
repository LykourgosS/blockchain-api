package com.lykourgoss.blockchainapi.example.gui;

import com.lykourgoss.blockchainapi.core.helpers.ApplicationContextHelper;
import com.lykourgoss.blockchainapi.core.helpers.jsonizer.GsonJsonizer;
import com.lykourgoss.blockchainapi.example.Product;
import com.lykourgoss.blockchainapi.persistence.BlockService;
import com.lykourgoss.blockchainapi.reflection.FieldGetter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel extends JFrame {
    private final BlockService service;
    private JPanel mainPanel;
    private JTextField textFieldCode;
    private JTextField textFieldTitle;
    private JTextField textFieldDescription;
    private JTextField textFieldCategory;
    private JTextField textFieldPrice;
    private JButton buttonClearAllTemp;
    private JButton buttonAddAllTemp;
    private JButton buttonAddTemp;
    private JButton buttonAdd;
    private JButton buttonSearch;
    private JButton buttonValidate;
    private JTextArea textAreaBlocks;
    private JButton buttonClearText;
    private JButton buttonDeleteBlockchain;

    private final List<Product> productsToAdd;

    public ControlPanel() {
        this.service = ApplicationContextHelper.getBean(BlockService.class);
        productsToAdd = new ArrayList<>();
    }

    public void start() {
        setTitle("My Form");
        setPreferredSize(new Dimension(420, 650));
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        buttonClearAllTemp.addActionListener(x -> clearAllTemp());
        buttonAddAllTemp.addActionListener(x -> addAllTemp());
        buttonAddTemp.addActionListener(x -> addTemp());
        buttonAdd.addActionListener(x -> addNewBlock());
        buttonSearch.addActionListener(x -> search());
        buttonValidate.addActionListener(x -> validateBlockchain());
        buttonClearText.addActionListener(x -> clearAllText());
        buttonDeleteBlockchain.addActionListener(x -> deleteBlockchain());
    }

    private Float tryGetPrice(boolean showMessage) {
        Float price = null;
        try {
            if (textFieldPrice.getText().isEmpty()) {
                price = 0F;
            } else {
                price = Float.parseFloat(textFieldPrice.getText());
            }
        } catch (Exception e) {
            if (showMessage) {
                JOptionPane.showMessageDialog(this, e.toString());
            }
        }
        return price;
    }

    private boolean isPriceValid() {
        return tryGetPrice(true) != null;
    }

    private Product tryGetProduct() {
        if (isPriceValid()) {
            return Product.builder()
                    .code(textFieldCode.getText())
                    .title(textFieldTitle.getText())
                    .description(textFieldDescription.getText())
                    .category(textFieldCategory.getText())
                    .price(tryGetPrice(false))
                    .build();
        } else {
            return null;
        }
    }

    private void updateTextAreaBlocks(List<?> list) {
        textAreaBlocks.setText(GsonJsonizer.INSTANCE.toPrettyJson(list));
    }

    private void clearAllTemp() {
        if (!productsToAdd.isEmpty()) {
            productsToAdd.clear();
            updateTextAreaBlocks(productsToAdd);
        }
    }

    private void addTemp() {
        Product product = tryGetProduct();
        if (product != null) {
            productsToAdd.add(product);
            updateTextAreaBlocks(productsToAdd);
        }
    }

    private void addAllTemp() {
        if (!productsToAdd.isEmpty()) {
            for (Product product : productsToAdd) {
                service.addWithData(product);
            }
            clearAllTemp();
        }
    }

    private void fetchAll() {
        updateTextAreaBlocks(service.getAll());
    }

    private void addNewBlock() {
        addTemp();
        addAllTemp();
        fetchAll();
    }

    private void search() {
        Product product = tryGetProduct();
        if (FieldGetter.INSTANCE.hasInitializedFields(product)) {
            updateTextAreaBlocks(service.getAllLike(product));
        } else {
            fetchAll();
        }
    }

    private void validateBlockchain() {
        boolean isValid = service.validate();
        JOptionPane.showMessageDialog(this, "Blockchain Validate:" + isValid);
    }

    private void clearAllText() {
        int result = JOptionPane.showConfirmDialog(this, "Clear temp add products / result blocks?");
        if (result == JOptionPane.YES_OPTION) {
            textAreaBlocks.setText("");
        }
    }

    private void deleteBlockchain() {
        int deleteResult = JOptionPane.showConfirmDialog(this, "Delete all blocks from blockchain?");
        if (deleteResult == JOptionPane.YES_OPTION) {
            service.deleteAll();
            fetchAll();
        }
    }

}
