package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.game.Rotation;
import com.example.bachelorthesis.game.Stone;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class State {
    private int lowestScore;
    private char[][] gameBoard;
    private HashMap<Color, Integer> colorScores;
    private int tilesDrawed;
    private int fieldLeft;

    public State() {
        lowestScore = 0;
        gameBoard = new char[11][];
        initGameBoard();

        colorScores = new HashMap<Color, Integer>();
        colorScores.put(Color.RED, 0);
        colorScores.put(Color.BLUE, 0);
        colorScores.put(Color.GREEN, 0);
        colorScores.put(Color.YELLOW, 0);
        colorScores.put(Color.ORANGE, 0);
        colorScores.put(Color.PURPLE, 0);
    }

    private void initGameBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            gameBoard[i] = new char[5 + i];
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = '-';
            }
        }
    }

    public Color getLowestColor() {
        int min = Collections.min(colorScores.values());
        this.lowestScore = min;

        Map.Entry<Color, Integer> minimum = null;
        for (Map.Entry<Color, Integer> entry : colorScores.entrySet()) {
            if (minimum == null || minimum.getValue() > entry.getValue()) {
                minimum = entry;
            }
        }
        return minimum.getKey();
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
