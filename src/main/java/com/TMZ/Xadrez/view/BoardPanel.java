package com.TMZ.Xadrez.view;

import com.TMZ.Xadrez.model.Board;
import com.TMZ.Xadrez.model.Piece;
import com.TMZ.Xadrez.util.PieceSymbolUtil;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private final SquareButton[][] squares;

    public BoardPanel() {
        setLayout(new GridLayout(8, 8));
        squares = new SquareButton[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                SquareButton button = new SquareButton(row, col);

                if ((row + col) % 2 == 0) {
                    button.setBackground(new Color(0, 0, 0));
                } else {
                    button.setBackground(new Color(255, 255, 255));
                }

                button.setOpaque(true);
                button.setBorderPainted(false);

                squares[row][col] = button;
                add(button);
            }
        }
    }

    public void renderBoard(Board board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                squares[row][col].setText(PieceSymbolUtil.getSymbol(piece));
            }
        }
    }

    public SquareButton[][] getSquares() {
        return squares;
    }
}