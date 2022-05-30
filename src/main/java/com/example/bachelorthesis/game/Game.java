package com.example.bachelorthesis.game;

public class Game {

    private Game instance;
    private Stone[] stones;

    private Game() {
        stones = new Stone[120];
        for (int i = 0; i < 120; i++) {
            if (i < 5) {
                stones[i] = new Stone(Color.BLUE, Color.BLUE);
            } else if (i >= 5 && i < 10) {
                stones[i] = new Stone(Color.GREEN, Color.GREEN);
            } else if (i >= 10 && i < 15) {
                stones[i] = new Stone(Color.PURPLE, Color.PURPLE);
            } else if (i >= 15 && i < 20) {
                stones[i] = new Stone(Color.RED, Color.RED);
            } else if (i >= 20 && i < 25) {
                stones[i] = new Stone(Color.ORANGE, Color.ORANGE);
            } else if (i >= 25 && i < 30) {
                stones[i] = new Stone(Color.YELLOW, Color.YELLOW);
            } else if (i >= 30 && i < 36) {
                stones[i] = new Stone(Color.RED, Color.YELLOW);
            } else if (i >= 36 && i < 42) {
                stones[i] = new Stone(Color.RED, Color.BLUE);
            } else if (i>= 42 && i < 48){
                stones[i] = new Stone(Color.RED, Color.GREEN);
            } else if (i >= 48 && i < 54){
                stones[i] = new Stone(Color.RED, Color.ORANGE);
            } else if (i >= 54 && i < 60){
                stones[i] = new Stone(Color.RED, Color.PURPLE);
            } else if (i >= 60 && i < 66) {
                stones[i] = new Stone(Color.YELLOW, Color.ORANGE);
            } else if (i >= 66 && i < 72) {
                stones[i] = new Stone(Color.YELLOW, Color.PURPLE);
            } else if(i >= 72 && i < 78) {
                stones[i] = new Stone(Color.YELLOW, Color.BLUE);
            } else if (i >= 78 && i < 84){
                stones[i] = new Stone(Color.YELLOW, Color.GREEN);
            } else if ( i >= 84 && i < 90) {
                stones[i] = new Stone(Color.GREEN, Color.PURPLE);
            } else if (i >= 90 && i < 96){
                stones[i] = new Stone(Color.GREEN, Color.BLUE);
            } else if (i >= 96 && i < 102){
                stones[i] = new Stone(Color.GREEN, Color.ORANGE);
            } else if (i <= 102 && i > 108) {
                stones[i] = new Stone(Color.ORANGE, Color.PURPLE);
            } else if (i >= 108 && i < 114){
                stones[i] = new Stone(Color.ORANGE, Color.BLUE);
            } else if ( i >= 114 && i < 120){
                stones[i] = new Stone (Color.BLUE, Color.PURPLE);
            }
        }
    }

    public Game getInstance() {
        return instance;
    }
}
