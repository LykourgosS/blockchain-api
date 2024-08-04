package com.lykourgoss.blockchainapi;

import com.lykourgoss.blockchainapi.core.Blockchain;
import com.lykourgoss.blockchainapi.gui.ControlPanel;
import com.lykourgoss.blockchainapi.miners.SingleThreadMiner;
import com.lykourgoss.example.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.Instant;

@SpringBootApplication
public class BlockchainApiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BlockchainApiApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            // Ensure Swing components are created on the EDT
            SwingUtilities.invokeLater(() -> {
                ControlPanel form = new ControlPanel();
                form.setVisible(true);
            });
            test();
        };
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

        Blockchain<Product> chain = new Blockchain<>(new SingleThreadMiner(), 5);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        System.out.println(sdf.format(Instant.now().toEpochMilli()));
        chain.addBlock(product);
        System.out.println(sdf.format(Instant.now().toEpochMilli()));
        chain.addBlock(product2);
        System.out.println(sdf.format(Instant.now().toEpochMilli()));

        System.out.println(chain.toJson());
        System.out.println(chain.validate());
    }
}
