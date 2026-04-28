package com.TMZ.Xadrez.model;

public class Board {

    private final Piece[][] board;

    public Board() {
        this.board = new Piece[8][8];
    }

    public Piece getPiece(Positivo pos) {
        if (!isValidPosition(pos)) {
            return null;
        }
        return board[pos.getRow()][pos.getCol()];
    }

    public Piece getPiece(int row, int col) {
        if (!isValidPosition(row, col)) {
            return null;
        }
        return board[row][col];
    }

    public void setPiece(Positivo pos, Piece piece) {
        if (isValidPosition(pos)) {
            board[pos.getRow()][pos.getCol()] = piece;
        }
    }

    public void setPiece(int row, int col, Piece piece) {
        if (isValidPosition(row, col)) {
            board[row][col] = piece;
        }
    }

    public Piece removePiece(Positivo pos) {
        if (!isValidPosition(pos)) {
            return null;
        }

        Piece piece = board[pos.getRow()][pos.getCol()];
        board[pos.getRow()][pos.getCol()] = null;
        return piece;
    }

    public boolean isValidPosition(Positivo pos) {
        return pos != null && isValidPosition(pos.getRow(), pos.getCol());
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}