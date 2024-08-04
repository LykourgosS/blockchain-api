package com.lykourgoss.blockchainapi;

import com.lykourgoss.blockchainapi.gui.ControlPanel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

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
        };
    }
}
