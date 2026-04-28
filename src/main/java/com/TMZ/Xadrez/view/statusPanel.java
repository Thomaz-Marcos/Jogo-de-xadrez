package com.TMZ.Xadrez.view;

import javax.swing.*;
import java.awt.*;

public class statusPanel extends JPanel {
    private final JLabel statusLabel;
    public statusPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        statusLabel = new JLabel("Vez das peças brancas",JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        add(statusLabel,BorderLayout.CENTER);
    }

    public void setStatus(String status){
        statusLabel.setText(status);
    }
    public JLabel getStatusLabel(){
        return statusLabel;
    }

}
