package com.TMZ.Xadrez.model;

public class Move {

    private final Positivo from;
    private final Positivo to;

    public Move(Positivo from, Positivo to) {
        this.from = from;
        this.to = to;
    }

    public Positivo getFrom() {
        return from;
    }

    public Positivo getTo() {
        return to;
    }
}