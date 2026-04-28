package com.TMZ.Xadrez.service;

import com.TMZ.Xadrez.model.Board;
import com.TMZ.Xadrez.model.GameState;
import com.TMZ.Xadrez.model.Piece;
import com.TMZ.Xadrez.model.PieceColor;
import com.TMZ.Xadrez.model.PieceType;
import com.TMZ.Xadrez.model.Positivo;

public class Regras {

    public boolean validacao(GameState game, Positivo from, Positivo to) {
        if (game == null || from == null || to == null) {
            return false;
        }

        Board board = game.getBoard();

        if (!board.isValidPosition(from) || !board.isValidPosition(to)) {
            return false;
        }

        if (from.equals(to)) {
            return false;
        }

        Piece piece = board.getPiece(from);
        if (piece == null) {
            return false;
        }

        if (piece.getColor() != game.getCor()) {
            return false;
        }

        Piece targetPiece = board.getPiece(to);
        if (targetPiece != null && targetPiece.getColor() == piece.getColor()) {
            return false;
        }

        return validaMovimentoPeca(board, piece, from, to);
    }

    private boolean validaMovimentoPeca(Board board, Piece piece, Positivo from, Positivo to) {
        PieceType type = piece.getType();

        switch (type) {
            case TORRE:
                return validaMovimentoTorre(board, from, to);
            case CAVALO:
                return validaMovimentoCavalo(from, to);
            case BISPO:
                return validaMovimentoBispo(board, from, to);
            case RAINHA:
                return validaMovimentoRainha(board, from, to);
            case REI:
                return validaMovimentoRei(from, to);
            case PEAO:
                return validaMovimentoPeao(board, piece, from, to);
            default:
                return false;
        }
    }

    private boolean isCaminhoLivre(Board board, Positivo from, Positivo to) {
        int rowDir = Integer.compare(to.getRow(), from.getRow());
        int colDir = Integer.compare(to.getCol(), from.getCol());

        int currRow = from.getRow() + rowDir;
        int currCol = from.getCol() + colDir;

        while (currRow != to.getRow() || currCol != to.getCol()) {
            if (board.getPiece(currRow, currCol) != null) {
                return false;
            }
            currRow += rowDir;
            currCol += colDir;
        }

        return true;
    }

    private boolean validaMovimentoTorre(Board board, Positivo from, Positivo to) {
        if (from.getRow() != to.getRow() && from.getCol() != to.getCol()) {
            return false;
        }
        return isCaminhoLivre(board, from, to);
    }

    private boolean validaMovimentoCavalo(Positivo from, Positivo to) {
        int dr = Math.abs(from.getRow() - to.getRow());
        int dc = Math.abs(from.getCol() - to.getCol());
        return (dr == 2 && dc == 1) || (dr == 1 && dc == 2);
    }

    private boolean validaMovimentoBispo(Board board, Positivo from, Positivo to) {
        int dr = Math.abs(from.getRow() - to.getRow());
        int dc = Math.abs(from.getCol() - to.getCol());

        if (dr != dc) {
            return false;
        }

        return isCaminhoLivre(board, from, to);
    }

    private boolean validaMovimentoRainha(Board board, Positivo from, Positivo to) {
        return validaMovimentoTorre(board, from, to) || validaMovimentoBispo(board, from, to);
    }

    private boolean validaMovimentoRei(Positivo from, Positivo to) {
        int dr = Math.abs(from.getRow() - to.getRow());
        int dc = Math.abs(from.getCol() - to.getCol());
        return dr <= 1 && dc <= 1;
    }

    private boolean validaMovimentoPeao(Board board, Piece piece, Positivo from, Positivo to) {
        int direcao = piece.getColor() == PieceColor.WHITE ? -1 : 1;

        int dr = to.getRow() - from.getRow();
        int dc = to.getCol() - from.getCol();

        Piece destino = board.getPiece(to);

        if (dc == 0) {
            if (dr == direcao && destino == null) {
                return true;
            }

            if (dr == 2 * direcao && destino == null) {
                if (piece.getColor() == PieceColor.WHITE && from.getRow() == 6) {
                    Positivo meio = new Positivo(from.getRow() - 1, from.getCol());
                    return board.getPiece(meio) == null;
                }

                if (piece.getColor() == PieceColor.BLACK && from.getRow() == 1) {
                    Positivo meio = new Positivo(from.getRow() + 1, from.getCol());
                    return board.getPiece(meio) == null;
                }
            }
        }

        if (Math.abs(dc) == 1 && dr == direcao && destino != null && destino.getColor() != piece.getColor()) {
            return true;
        }

        return false;
    }
}