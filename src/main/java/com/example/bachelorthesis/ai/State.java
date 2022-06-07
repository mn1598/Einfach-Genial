package com.example.bachelorthesis.ai;

import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class State {
    private int lowestScore;
    private int[][] gameBoard;
    private HashMap<Color, Integer> colorScores;
    private int tilesDrawed;
    private int fieldLeft;

    public State(){
        lowestScore = 0;
        gameBoard = new int[11][];

        colorScores = new HashMap<Color, Integer>();
        colorScores.put(Color.RED, 0);
        colorScores.put(Color.BLUE, 0);
        colorScores.put(Color.GREEN, 0);
        colorScores.put(Color.YELLOW, 0);
        colorScores.put(Color.ORANGE, 0);
        colorScores.put(Color.PURPLE, 0);

    }

    public Color getLowestColor(){
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
}
