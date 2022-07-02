package com.example.bachelorthesis.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    public static ArrayList<Stone> stones;
    private Color[][] gameBoard;

    public Game() {
        stones = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            if (i < 5) {
                stones.add(new Stone(Color.BLUE, Color.BLUE));
            } else if (i >= 5 && i < 10) {
                stones.add(new Stone(Color.GREEN, Color.GREEN));
            } else if (i >= 10 && i < 15) {
                stones.add(new Stone(Color.PURPLE, Color.PURPLE));
            } else if (i >= 15 && i < 20) {
                stones.add(new Stone(Color.RED, Color.RED));
            } else if (i >= 20 && i < 25) {
                stones.add(new Stone(Color.ORANGE, Color.ORANGE));
            } else if (i >= 25 && i < 30) {
                stones.add(new Stone(Color.YELLOW, Color.YELLOW));
            } else if (i >= 30 && i < 36) {
                stones.add(new Stone(Color.RED, Color.YELLOW));
            } else if (i >= 36 && i < 42) {
                stones.add(new Stone(Color.RED, Color.BLUE));
            } else if (i >= 42 && i < 48) {
                stones.add(new Stone(Color.RED, Color.GREEN));
            } else if (i >= 48 && i < 54) {
                stones.add(new Stone(Color.RED, Color.ORANGE));
            } else if (i >= 54 && i < 60) {
                stones.add(new Stone(Color.RED, Color.PURPLE));
            } else if (i >= 60 && i < 66) {
                stones.add(new Stone(Color.YELLOW, Color.ORANGE));
            } else if (i >= 66 && i < 72) {
                stones.add(new Stone(Color.YELLOW, Color.PURPLE));
            } else if (i >= 72 && i < 78) {
                stones.add(new Stone(Color.YELLOW, Color.BLUE));
            } else if (i >= 78 && i < 84) {
                stones.add(new Stone(Color.YELLOW, Color.GREEN));
            } else if (i >= 84 && i < 90) {
                stones.add(new Stone(Color.GREEN, Color.PURPLE));
            } else if (i >= 90 && i < 96) {
                stones.add(new Stone(Color.GREEN, Color.BLUE));
            } else if (i >= 96 && i < 102) {
                stones.add(new Stone(Color.GREEN, Color.ORANGE));
            } else if (i <= 102 && i > 108) {
                stones.add(new Stone(Color.ORANGE, Color.PURPLE));
            } else if (i >= 108 && i < 114) {
                stones.add(new Stone(Color.ORANGE, Color.BLUE));
            } else if (i >= 114 && i < 120) {
                stones.add(new Stone(Color.BLUE, Color.PURPLE));
            }
        }

        // gameBoard array of all colors
        gameBoard = new Color[11][];
        for (int i = 0; i < 6; i++) {
            gameBoard[i] = new Color[5 + i];
            gameBoard[11 - i - 1] = new Color[5 + i];
        }
    }

    public boolean getFinished() {
        return stones.size() == 0? true: false;
    }

    public static Stone drawStone() {
        Random rdm = new Random();
        if(stones.size() == 0){
            return null;
        }
        return stones.remove(rdm.nextInt(stones.size()));
    }
}
