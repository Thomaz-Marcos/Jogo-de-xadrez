package com.TMZ.Xadrez.model;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final Board board;
    private PieceColor cor;

    private int capturasBrancas;
    private int capturasPretas;

    private GameMode gameMode;
    private boolean whiteIsAI;
    private boolean blackIsAI;

    private final List<Piece> pecasCapturadasPelasBrancas;
    private final List<Piece> pecasCapturadasPelasPretas;

    public GameState() {
        this.board = new Board();
        this.cor = PieceColor.WHITE;
        this.capturasBrancas = 0;
        this.capturasPretas = 0;
        this.pecasCapturadasPelasBrancas = new ArrayList<>();
        this.pecasCapturadasPelasPretas = new ArrayList<>();

        this.gameMode = GameMode.PLAYER_VS_PLAYER;
        this.whiteIsAI = false;
        this.blackIsAI = false;
    }

    public Board getBoard() {
        return board;
    }

    public PieceColor getCor() {
        return cor;
    }

    public void switchCor() {
        cor = (cor == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public int getCapturasBrancas() {
        return capturasBrancas;
    }

    public int getCapturasPretas() {
        return capturasPretas;
    }

    public List<Piece> getPecasCapturadasPelasBrancas() {
        return pecasCapturadasPelasBrancas;
    }

    public List<Piece> getPecasCapturadasPelasPretas() {
        return pecasCapturadasPelasPretas;
    }

    public void adicionarCaptura(PieceColor corDeQuemCapturou, Piece alvoCapturado) {
        if (corDeQuemCapturou == PieceColor.WHITE) {
            capturasBrancas++;
            pecasCapturadasPelasBrancas.add(alvoCapturado);
        } else {
            capturasPretas++;
            pecasCapturadasPelasPretas.add(alvoCapturado);
        }
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public boolean isWhiteAI() {
        return whiteIsAI;
    }

    public void setWhiteAI(boolean whiteIsAI) {
        this.whiteIsAI = whiteIsAI;
    }

    public boolean isBlackAI() {
        return blackIsAI;
    }

    public void setBlackAI(boolean blackIsAI) {
        this.blackIsAI = blackIsAI;
    }

    public boolean isTurnoDaIA() {
        return (cor == PieceColor.WHITE && whiteIsAI) ||
                (cor == PieceColor.BLACK && blackIsAI);
    }

    public void configurarModo(GameMode gameMode) {
        this.gameMode = gameMode;

        switch (gameMode) {
            case PLAYER_VS_PLAYER:
                this.whiteIsAI = false;
                this.blackIsAI = false;
                break;
            case PLAYER_VS_AI:
                this.whiteIsAI = false;
                this.blackIsAI = true;
                break;
            case AI_VS_AI:
                this.whiteIsAI = true;
                this.blackIsAI = true;
                break;
        }
    }
}