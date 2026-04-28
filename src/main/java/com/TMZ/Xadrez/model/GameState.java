package com.TMZ.Xadrez.model;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final Board board;
    private PieceColor cor;

    private int capturasBrancas;
    private int capturasPretas;

    private final List<Piece> pecasCapturadasPelasBrancas;
    private final List<Piece> pecasCapturadasPelasPretas;


    public GameState(){
        this.board = new Board();
        this.cor = PieceColor.WHITE;
        this.capturasBrancas = 0;
        this.capturasPretas = 0;
        this.pecasCapturadasPelasBrancas = new ArrayList<>();
        this.pecasCapturadasPelasPretas = new ArrayList<>();

    }
    public  Board getBoard() {
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
    public void adicionarCapturas(PieceColor corDaPecaCapturada, Piece alvoCapturado) {
        if(corDaPecaCapturada == PieceColor.WHITE){
            capturasBrancas++;
            pecasCapturadasPelasBrancas.add(alvoCapturado);
        }else {
            capturasPretas++;
            pecasCapturadasPelasPretas.add(alvoCapturado);
        }

    }

}
