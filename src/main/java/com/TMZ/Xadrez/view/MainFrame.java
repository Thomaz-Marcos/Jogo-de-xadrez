package com.TMZ.Xadrez.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class MainFrame extends JFrame {

    private final JLabel statusLabel;
    private final JLabel modoLabel;
    private final BoardPanel boardPanel;

    private final JLabel capturasBrancasLabel;
    private final JLabel capturasPretasLabel;

    private final JButton trocarModoButton;

    public MainFrame() {
        setTitle("Jogo de Xadrez");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new BoardPanel();

        JLabel titulo = new JLabel("Xadrez", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));

        modoLabel = new JLabel("Modo: Jogador vs Jogador", JLabel.CENTER);
        modoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        modoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        trocarModoButton = new JButton("Trocar modo");

        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.add(titulo);
        topPanel.add(modoLabel);
        topPanel.add(trocarModoButton);

        statusLabel = new JLabel("Vez das peças brancas", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        capturasBrancasLabel = new JLabel("Brancas capturaram: -", JLabel.CENTER);
        capturasBrancasLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        capturasPretasLabel = new JLabel("Pretas capturaram: -", JLabel.CENTER);
        capturasPretasLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel bottomPanel = new JPanel(new GridLayout(3, 1));
        bottomPanel.add(statusLabel);
        bottomPanel.add(capturasBrancasLabel);
        bottomPanel.add(capturasPretasLabel);

        add(topPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void atualizarStatus(String mensagem) {
        statusLabel.setText(mensagem);
    }

    public void atualizarModo(String modo) {
        modoLabel.setText("Modo: " + modo);
    }

    public void atualizarCapturas(String capturasBrancas, String capturasPretas) {
        capturasBrancasLabel.setText("Brancas capturaram: " + capturasBrancas);
        capturasPretasLabel.setText("Pretas capturaram: " + capturasPretas);
    }

    public JButton getTrocarModoButton() {
        return trocarModoButton;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }
}