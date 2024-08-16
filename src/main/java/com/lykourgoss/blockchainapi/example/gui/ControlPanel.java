package com.lykourgoss.blockchainapi.example.gui;

import com.lykourgoss.blockchainapi.core.helpers.ApplicationContextHelper;
import com.lykourgoss.blockchainapi.persistence.BlockService;
import com.lykourgoss.blockchainapi.example.Product;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class ControlPanel extends JFrame {
    private final BlockService service;
    private JPanel mainPanel;
    private JButton addButton;

    public ControlPanel() {
        this.service = ApplicationContextHelper.getBean(BlockService.class);
    }

    public void start() {
        setTitle("My Form");
        setPreferredSize(new Dimension(420, 650));
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        test();
    }

    private void test() {
        Product product = Product.builder()
                .code("123")
                .title("Test product")
                .description("Only for testing")
                .category("dairy")
                .price(1.05F)
                .build();

        Product product2 = Product.builder()
                .code("1234")
                .title("Test product 2")
                .description("Only for testing the previous one")
                .category("not dairy")
                .price(1)
                .build();

        service.deleteAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        System.out.println(sdf.format(Instant.now().toEpochMilli()));
        service.addWithData(product);
        System.out.println(sdf.format(Instant.now().toEpochMilli()));
        service.addWithData(product2);
        System.out.println(sdf.format(Instant.now().toEpochMilli()));
        System.out.println(service.toJson());
        System.out.println(service.validate());
    }
}
