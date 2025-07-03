package com.lykourgoss.blockchainapi.example;

import com.lykourgoss.blockchainapi.example.gui.ControlPanel;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.swing.*;

@SpringBootApplication
public class BlockchainApiExampleApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BlockchainApiExampleApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        // now it's safe to access context-dependent things
        SwingUtilities.invokeLater(() -> {
            ControlPanel panel = new ControlPanel();
            panel.start();
        });
    }
}
