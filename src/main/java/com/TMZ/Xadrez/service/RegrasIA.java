package com.TMZ.Xadrez.service;

import com.TMZ.Xadrez.model.GameState;
import com.TMZ.Xadrez.model.Move;
import com.TMZ.Xadrez.model.Piece;
import com.TMZ.Xadrez.model.Positivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegrasIA {

    private final Regras regras = new Regras();
    private final Random random = new Random();

    public Move escolher(GameState gameState) {
        List<Move> movimentosValidos = new ArrayList<>();
        List<Move> capturas = new ArrayList<>();

        for (int fromRow = 0; fromRow < 8; fromRow++) {
            for (int fromCol = 0; fromCol < 8; fromCol++) {
                Piece piece = gameState.getBoard().getPiece(fromRow, fromCol);

                if (piece == null || piece.getColor() != gameState.getCor()) {
                    continue;
                }

                for (int toRow = 0; toRow < 8; toRow++) {
                    for (int toCol = 0; toCol < 8; toCol++) {
                        Positivo from = new Positivo(fromRow, fromCol);
                        Positivo to = new Positivo(toRow, toCol);

                        if (regras.validacao(gameState, from, to)) {
                            Move move = new Move(from, to);
                            movimentosValidos.add(move);

                            Piece alvo = gameState.getBoard().getPiece(toRow, toCol);
                            if (alvo != null && alvo.getColor() != piece.getColor()) {
                                capturas.add(move);
                            }
                        }
                    }
                }
            }
        }

        if (!capturas.isEmpty()) {
            return capturas.get(random.nextInt(capturas.size()));
        }

        if (!movimentosValidos.isEmpty()) {
            return movimentosValidos.get(random.nextInt(movimentosValidos.size()));
        }

        return null;
    }
}