package com.TMZ.Xadrez.view;

import javax.swing.*;
import java.awt.*;

public class SquareButton extends JButton {
    private final int row;
    private final int col;

    public SquareButton(int row, int col) {
        this.row = row;
        this.col = col;

        setFocusPainted(false);
        setFont(new Font("Segoe UI Symbol", Font.PLAIN, 32));
        setPreferredSize(new Dimension(80, 80));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    public void setSquareColor(boolean isDark) {
        setBackground(isDark ? new Color(118, 150, 86) : new Color(238, 238, 210));
        setOpaque(true);
        setBorderPainted(false);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
