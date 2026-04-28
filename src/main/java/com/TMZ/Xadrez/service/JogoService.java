package com.TMZ.Xadrez.service;

import com.TMZ.Xadrez.model.GameState;
import com.TMZ.Xadrez.model.Piece;
import com.TMZ.Xadrez.model.Positivo;

public class JogoService {

    private final Regras regrasDeService;

    public JogoService() {
        this.regrasDeService = new Regras();
    }

    public boolean movePeca(GameState games, Positivo from, Positivo to) {
        if (!regrasDeService.validacao(games, from, to)) {
            return false;
        }

        Piece piece = games.getBoard().getPiece(from);
        Piece alvoCapturado = games.getBoard().getPiece(to);

        if (alvoCapturado != null) {
            games.adicionarCapturas(piece.getColor(), alvoCapturado);
        }

        games.getBoard().setPiece(to, piece);
        games.getBoard().setPiece(from, null);
        games.switchCor();
        return true;
    }
}