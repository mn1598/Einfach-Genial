package com.example.bachelorthesis.model;

public class Field {

/*    public final Field NEIGHBOR_NE;
    public final Field NEIGHBOR_E;
    public final Field NEIGHBOR_SE;
    public final Field NEIGHBOR_SW;
    public final Field NEIGHBOR_W;
    public final Field NEIGHBOR_NW;
*/
    public final int X;
    public final int Y;

    private boolean isFree;
    private char content;
/*
    public Field(final Field NEIGHBOR_NE, final Field NEIGHBOR_E, final Field NEIGHBOR_SE, final Field NEIGHBOR_SW,
                 final Field NEIGHBOR_W, final Field NEIGHBOR_NW, final int X, final int Y) {
        this.NEIGHBOR_NE = NEIGHBOR_NE;
        this.NEIGHBOR_E = NEIGHBOR_E;
        this.NEIGHBOR_SE = NEIGHBOR_SE;
        this.NEIGHBOR_SW = NEIGHBOR_SW;
        this.NEIGHBOR_W = NEIGHBOR_W;
        this.NEIGHBOR_NW = NEIGHBOR_NW;
        this.X = X;
        this.Y = Y;
        this.isFree = false;
        content = '-';
    }
*/
    public Field(int x, int y) {
        X = x;
        Y = y;
    }
}
