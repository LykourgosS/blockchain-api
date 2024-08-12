package com.lykourgoss.blockchainapi.example.gui;

import com.lykourgoss.blockchainapi.core.Blockable;
import com.lykourgoss.blockchainapi.core.helpers.ApplicationContextHelper;
import com.lykourgoss.blockchainapi.core.persistence.BlockService;
import com.lykourgoss.blockchainapi.example.Product;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class ControlPanel extends JFrame {
    private final BlockService<Product> blockService;
    private final BlockService<Student> blockServiceStudent;
    private JPanel mainPanel;
    private JButton addButton;

    public ControlPanel() {
        this.blockService = ApplicationContextHelper.getBean(BlockService.class);
        this.blockServiceStudent = ApplicationContextHelper.getBean(BlockService.class);
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
        Product product = new Product.Builder("123")
                .title("Test product")
                .description("Only for testing")
                .category("dairy")
                .price(1.05F)
                .build();

        Product product2 = new Product.Builder("1234")
                .title("Test product 2")
                .description("Only for testing the previous one")
                .category("not dairy")
                .price(1)
                .build();

        var student = new Student("Anna", 24);

        blockService.deleteAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        System.out.println(sdf.format(Instant.now().toEpochMilli()));
        blockService.add(product);
        System.out.println(sdf.format(Instant.now().toEpochMilli()));
        blockService.add(product2);
        System.out.println(sdf.format(Instant.now().toEpochMilli()));
        blockServiceStudent.add(student);

        System.out.println(blockService.toJson());
        System.out.println(blockService.validate());
    }

    static class Student implements Blockable {

        private final String name;
        private final int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String getBlockData() {
            return name + age;
        }
    }
}
