package com.lykourgoss.blockchainapi;

import com.lykourgoss.blockchainapi.gui.ControlPanel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.EventQueue;

@SpringBootApplication
public class BlockchainApiApplication {

    public static void main(String[] args) {
        var context = new SpringApplicationBuilder(BlockchainApiApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        EventQueue.invokeLater(() -> {
            var form = context.getBean(ControlPanel.class);
            form.setVisible(true);
        });
    }

}
