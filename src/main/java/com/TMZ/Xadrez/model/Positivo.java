package com.TMZ.Xadrez.model;

import java.util.Objects;

public class Positivo {
    private final int row;
    private final int col;

    public Positivo(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Positivo)) return false;
        Positivo positivo = (Positivo) o;
        return row == positivo.row && col == positivo.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}