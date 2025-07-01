package com.lykourgoss.blockchainapi.example.gui;

import com.lykourgoss.blockchainapi.core.helpers.ApplicationContextHelper;
import com.lykourgoss.blockchainapi.core.helpers.jsonizer.GsonJsonizer;
import com.lykourgoss.blockchainapi.example.Product;
import com.lykourgoss.blockchainapi.persistence.BlockService;
import com.lykourgoss.blockchainapi.core.helpers.reflection.FieldGetter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel {
    private final ControlPanelView view;
    private final BlockService service;
    private final List<Product> productsToAdd;

    public ControlPanel() {
        view = new ControlPanelView();
        service = ApplicationContextHelper.getBean(BlockService.class);
        productsToAdd = new ArrayList<>();
        registerEventHandlers();
    }

    public void start(){
        view.start();
    }

    private void registerEventHandlers() {
        view.buttonClearAllTemp.addActionListener(x -> clearAllTemp());
        view.buttonAddAllTemp.addActionListener(x -> addAllTemp());
        view.buttonAddTemp.addActionListener(x -> addTemp());
        view.buttonAdd.addActionListener(x -> addNewBlock());
        view.buttonSearch.addActionListener(x -> search());
        view.buttonValidate.addActionListener(x -> validateBlockchain());
        view.buttonClearText.addActionListener(x -> clearAllText());
        view.buttonDeleteBlockchain.addActionListener(x -> deleteBlockchain());
    }

    private Float tryGetPrice(boolean showMessage) {
        Float price = null;
        try {
            if (view.textFieldPrice.getText().isEmpty()) {
                price = 0F;
            } else {
                price = Float.parseFloat(view.textFieldPrice.getText());
            }
        } catch (Exception e) {
            if (showMessage) {
                JOptionPane.showMessageDialog(view, e.toString());
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
                    .code(view.textFieldCode.getText())
                    .title(view.textFieldTitle.getText())
                    .description(view.textFieldDescription.getText())
                    .category(view.textFieldCategory.getText())
                    .price(tryGetPrice(false))
                    .build();
        } else {
            return null;
        }
    }

    private void updateTextAreaBlocks(List<?> list) {
        view.textAreaBlocks.setText(GsonJsonizer.INSTANCE.toPrettyJson(list));
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
        fetchAll();
    }

    private void fetchAll() {
        updateTextAreaBlocks(service.getAll());
    }

    private void addNewBlock() {
        addTemp();
        addAllTemp();
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
        JOptionPane.showMessageDialog(view, "Blockchain Validate:" + isValid);
    }

    private void clearAllText() {
        int result = JOptionPane.showConfirmDialog(view, "Clear temp add products / result blocks?");
        if (result == JOptionPane.YES_OPTION) {
            view.textAreaBlocks.setText("");
        }
    }

    private void deleteBlockchain() {
        int deleteResult = JOptionPane.showConfirmDialog(view, "Delete all blocks from blockchain?");
        if (deleteResult == JOptionPane.YES_OPTION) {
            service.deleteAll();
            fetchAll();
        }
    }
}
