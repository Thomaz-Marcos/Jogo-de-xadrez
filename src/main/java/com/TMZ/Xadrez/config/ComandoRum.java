package com.TMZ.Xadrez.config;

import com.TMZ.Xadrez.view.MainFrame;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.SwingUtilities;

@Component
public class ComandoRum implements CommandLineRunner {

    @Override
    public void run(String... args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}