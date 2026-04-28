package com.TMZ.Xadrez.controller;

import com.TMZ.Xadrez.model.GameState;
import com.TMZ.Xadrez.model.Move;
import com.TMZ.Xadrez.model.Piece;
import com.TMZ.Xadrez.model.PieceColor;
import com.TMZ.Xadrez.model.Positivo;
import com.TMZ.Xadrez.service.JogoService;
import com.TMZ.Xadrez.service.RegrasIA;
import com.TMZ.Xadrez.view.MainFrame;
import com.TMZ.Xadrez.view.SquareButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameController implements ActionListener {

    private final MainFrame mainFrame;
    private final GameState gameState;
    private final JogoService jogoService;
    private SquareButton selectedSquare;

    public GameController(MainFrame mainFrame, GameState gameState) {
        this.mainFrame = mainFrame;
        this.gameState = gameState;
        this.jogoService = new JogoService();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

        mainFrame.getBoardPanel().renderBoard(gameState.getBoard());

        String turno = gameState.getCor() == PieceColor.WHITE ? "brancas" : "pretas";
        mainFrame.atualizarStatus("Vez das peças " + turno);

        mainFrame.atualizarCapturas(
                formatarCapturas(gameState.getPecasCapturadasPelasBrancas()),
                formatarCapturas(gameState.getPecasCapturadasPelasPretas())
        );

        if (gameState.getCor() == PieceColor.BLACK) {
            RegrasIA ia = new RegrasIA();
            Move movimentoIA = ia.escolher(gameState);

            if (movimentoIA != null) {
                jogoService.movePeca(gameState, movimentoIA.getFrom(), movimentoIA.getTo());

                mainFrame.getBoardPanel().renderBoard(gameState.getBoard());

                String turnoDepoisIA = gameState.getCor() == PieceColor.WHITE ? "brancas" : "pretas";
                mainFrame.atualizarStatus("Vez das peças " + turnoDepoisIA);

                mainFrame.atualizarCapturas(
                        formatarCapturas(gameState.getPecasCapturadasPelasBrancas()),
                        formatarCapturas(gameState.getPecasCapturadasPelasPretas())
                );
            }
        }

        selectedSquare = null;
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