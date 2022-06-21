package com.example.bachelorthesis.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class State {
    private int lowestColorScore; // heuristic value
    private ColorEnum lowestColor;
    public char[][] gameBoard;
    private HashMap<ColorEnum, Integer> colorScores;

    private int lowestColorLongestSequence; // bisherige Kosten fuer A*
    private int estimatedTotalCosts;

    private boolean first;
    private boolean terminal;

    private int tilesDrawed; // brauch ich das?
    private int fieldLeft;

    public State() {
        lowestColorScore = 0;
        gameBoard = new char[11][];
        initGameBoard(); // nur initialzustand, sonst als kind betrachten

        colorScores = new HashMap<ColorEnum, Integer>();
        colorScores.put(ColorEnum.RED, 0);
        colorScores.put(ColorEnum.BLUE, 0);
        colorScores.put(ColorEnum.GREEN, 0);
        colorScores.put(ColorEnum.YELLOW, 0);
        colorScores.put(ColorEnum.ORANGE, 0);
        colorScores.put(ColorEnum.PURPLE, 0);
    }

    private void initGameBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            gameBoard[i] = new char[5 + i];
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = '-';
            }
        }
    }

    public ColorEnum getLowestColor() {
        int min = Collections.min(colorScores.values());
        this.lowestColorScore = min;

        Map.Entry<ColorEnum, Integer> minimum = null;
        for (Map.Entry<ColorEnum, Integer> entry : colorScores.entrySet()) {
            if (minimum == null || minimum.getValue() > entry.getValue()) {
                minimum = entry;
            }
        }
        lowestColor = minimum.getKey();
        return lowestColor;
    }

    private void printBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void newDraw(int x, int y, Stone stone, Rotation rotation) {
        tilesDrawed++;
        int tmp = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == '-') ;
                tmp++;
            }
        }
        fieldLeft = tmp;
    }
}
