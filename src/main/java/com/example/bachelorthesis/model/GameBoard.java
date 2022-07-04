package com.example.bachelorthesis.model;

import java.net.Inet4Address;
import java.util.HashMap;

public class GameBoard {

    public ColorEnum[][] representation;

    public GameBoard(){
        initGameBoard();
    }

    public void initGameBoard() {
        representation = new ColorEnum[11][];
        representation[0] = new ColorEnum[6];
        representation[1] = new ColorEnum[7];
        representation[2] = new ColorEnum[8];
        representation[3] = new ColorEnum[9];
        representation[4] = new ColorEnum[10];
        representation[5] = new ColorEnum[11];
        representation[6] = new ColorEnum[10];
        representation[7] = new ColorEnum[9];
        representation[8] = new ColorEnum[8];
        representation[9] = new ColorEnum[7];
        representation[10] = new ColorEnum[6];


        for (int i = 0; i < representation.length; i++) {
            for (int j = 0; j < representation[i].length; j++) {
                representation[i][j] = ColorEnum.NONE;
            }
        }
        representation[0][0] = ColorEnum.RED;
        representation[0][5] = ColorEnum.GREEN;
        representation[5][0] = ColorEnum.PURPLE;
        representation[5][10] = ColorEnum.BLUE;
        representation[10][0] = ColorEnum.YELLOW;
        representation[10][5] = ColorEnum.ORANGE;
    }
    // todo
    public boolean full(){
        return false;
    }
}
