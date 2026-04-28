package com.TMZ.Xadrez.util;

import com.TMZ.Xadrez.model.Piece;
import com.TMZ.Xadrez.model.PieceColor;
import com.TMZ.Xadrez.model.PieceType;

public class PieceSymbolUtil {

    public static String getSymbol(Piece piece) {
        if (piece == null) return "";

        boolean white = piece.getColor() == PieceColor.WHITE;

        return switch (piece.getType()) {
            case REI -> white ? "♔" : "♚";
            case RAINHA -> white ? "♕" : "♛";
            case TORRE -> white ? "♖" : "♜";
            case BISPO -> white ? "♗" : "♝";
            case CAVALO -> white ? "♘" : "♞";
            case PEAO -> white ? "♙" : "♟";
        };
    }
}