package com.TMZ.Xadrez.controller;

import com.TMZ.Xadrez.model.GameMode;
import com.TMZ.Xadrez.model.GameState;
import com.TMZ.Xadrez.model.Move;
import com.TMZ.Xadrez.model.Piece;
import com.TMZ.Xadrez.model.PieceColor;
import com.TMZ.Xadrez.model.Positivo;
import com.TMZ.Xadrez.service.JogoService;
import com.TMZ.Xadrez.service.RegrasIA;
import com.TMZ.Xadrez.view.MainFrame;
import com.TMZ.Xadrez.view.SquareButton;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameController implements ActionListener {

    private final MainFrame mainFrame;
    private final GameState gameState;
    private final JogoService jogoService;
    private final RegrasIA regrasIA;
    private SquareButton selectedSquare;

    public GameController(MainFrame mainFrame, GameState gameState) {
        this.mainFrame = mainFrame;
        this.gameState = gameState;
        this.jogoService = new JogoService();
        this.regrasIA = new RegrasIA();

        mainFrame.getTrocarModoButton().addActionListener(e -> escolherModo());

        atualizarTela();
        mainFrame.atualizarModo(textoModo(gameState.getGameMode()));
    }

    private void escolherModo() {
        String[] opcoes = {
                "Jogador vs Jogador",
                "Jogador vs IA",
                "IA vs IA"
        };

        String escolha = (String) JOptionPane.showInputDialog(
                mainFrame,
                "Escolha o modo de jogo:",
                "Modo de jogo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (escolha == null) {
            return;
        }

        switch (escolha) {
            case "Jogador vs Jogador" -> alterarModo(GameMode.PLAYER_VS_PLAYER);
            case "Jogador vs IA" -> alterarModo(GameMode.PLAYER_VS_AI);
            case "IA vs IA" -> alterarModo(GameMode.AI_VS_AI);
        }
    }

    public void alterarModo(GameMode modo) {
        selectedSquare = null;
        gameState.configurarModo(modo);
        mainFrame.atualizarModo(textoModo(modo));
        atualizarTela();

        if (gameState.isTurnoDaIA()) {
            executarTurnoIASeNecessario();
        }
    }

    private String textoModo(GameMode modo) {
        return switch (modo) {
            case PLAYER_VS_PLAYER -> "Jogador vs Jogador";
            case PLAYER_VS_AI -> "Jogador vs IA";
            case AI_VS_AI -> "IA vs IA";
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState.isTurnoDaIA()) {
            mainFrame.atualizarStatus("Aguarde, turno da IA...");
            return;
        }

        if (!(e.getSource() instanceof SquareButton clickedSquare)) {
            return;
        }

        int row = clickedSquare.getRow();
        int col = clickedSquare.getCol();
        Piece clickedPiece = gameState.getBoard().getPiece(row, col);

        if (selectedSquare == null) {
            if (clickedPiece == null) {
                mainFrame.atualizarStatus("Nenhuma peça nessa casa");
                return;
            }

            if (clickedPiece.getColor() != gameState.getCor()) {
                mainFrame.atualizarStatus("Não é a vez dessa peça");
                return;
            }

            selectedSquare = clickedSquare;
            mainFrame.atualizarStatus("Selecionado: (" + row + ", " + col + ")");
            return;
        }

        if (selectedSquare == clickedSquare) {
            selectedSquare = null;
            mainFrame.atualizarStatus("Seleção cancelada");
            return;
        }

        Positivo from = new Positivo(selectedSquare.getRow(), selectedSquare.getCol());
        Positivo to = new Positivo(row, col);

        boolean moveu = jogoService.movePeca(gameState, from, to);

        if (!moveu) {
            mainFrame.atualizarStatus("Movimento inválido");
            selectedSquare = null;
            return;
        }

        selectedSquare = null;
        atualizarTela();
        executarTurnoIASeNecessario();
    }

    private void executarTurnoIASeNecessario() {
        if (!gameState.isTurnoDaIA()) {
            return;
        }

        Timer timer = new Timer(600, e -> {
            Move movimentoIA = regrasIA.escolher(gameState);

            if (movimentoIA == null) {
                mainFrame.atualizarStatus("Fim de jogo - IA sem movimentos");
                ((Timer) e.getSource()).stop();
                return;
            }

            jogoService.movePeca(gameState, movimentoIA.getFrom(), movimentoIA.getTo());
            atualizarTela();

            if (gameState.isTurnoDaIA()) {
                executarTurnoIASeNecessario();
            }

            ((Timer) e.getSource()).stop();
        });

        timer.setRepeats(false);
        timer.start();
    }

    private void atualizarTela() {
        mainFrame.getBoardPanel().renderBoard(gameState.getBoard());

        String turno = gameState.getCor() == PieceColor.WHITE ? "brancas" : "pretas";
        mainFrame.atualizarStatus("Vez das peças " + turno);

        mainFrame.atualizarCapturas(
                formatarCapturas(gameState.getPecasCapturadasPelasBrancas()),
                formatarCapturas(gameState.getPecasCapturadasPelasPretas())
        );
    }

    private String formatarCapturas(List<Piece> pecas) {
        if (pecas.isEmpty()) {
            return "-";
        }

        StringBuilder sb = new StringBuilder();
        for (Piece piece : pecas) {
            sb.append(piece.getType()).append(" ");
        }
        return sb.toString().trim();
    }
}