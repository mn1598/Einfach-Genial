package com.example.bachelorthesis.Test;

import com.example.bachelorthesis.model.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Test {

    static GameBoard board = new GameBoard();

    public static void main(String[] args) {
        Game game = new Game();
//        testNextStates();
        testPointCalculation();
    }

    public static void testPointCalculation() {
        State state = new State(board);

        state.putStoneOnField(new Stone(Color.RED, Color.PURPLE), Rotation.TWO_CLOCKWISE, 3, 0);
        state.putStoneOnField(new Stone(Color.PURPLE, Color.GREEN), Rotation.NONE, 4, 1);
        state.putStoneOnField(new Stone(Color.GREEN, Color.YELLOW), Rotation.ONE_CLOCKWISE, 7, 2);
        state.putStoneOnField(new Stone(Color.YELLOW, Color.BLUE), Rotation.NONE, 9, 1);
        HashMap<ColorEnum, Integer> totalPoints = new HashMap<>();
        totalPoints.put(ColorEnum.RED, 0);
        totalPoints.put(ColorEnum.GREEN, 0);
        totalPoints.put(ColorEnum.BLUE, 0);
        totalPoints.put(ColorEnum.ORANGE, 0);
        totalPoints.put(ColorEnum.YELLOW, 0);
        totalPoints.put(ColorEnum.PURPLE, 0);

        state.putStoneOnField(new Stone(Color.PURPLE, Color.YELLOW), Rotation.ONE_CLOCKWISE, 6, 3);
        HashMap<ColorEnum, Integer> points = state.calculatePoints(new Stone(Color.PURPLE, Color.YELLOW), Rotation.ONE_CLOCKWISE, 6, 3);
        System.out.println("Test mit Stein P,Y,rota1: " + (points.get(ColorEnum.YELLOW)) + "\n");

        for(ColorEnum col: points.keySet()){
            totalPoints.put(col, points.get(col) + totalPoints.get(col));
        }

        points = state.calculatePoints(new Stone(Color.RED, Color.GREEN), Rotation.ONE_CLOCKWISE, 2, 0);
        System.out.println("Test mit Stein R,G, rota1:" + (points.get(ColorEnum.RED)) + ", " + (points.get(ColorEnum.GREEN)));
        state.putStoneOnField(new Stone(Color.RED, Color.GREEN), Rotation.ONE_CLOCKWISE, 2, 0);

        for(ColorEnum col: points.keySet()){
            totalPoints.put(col, points.get(col) + totalPoints.get(col));
        }

        points = state.calculatePoints(new Stone(Color.GREEN, Color.GREEN), Rotation.NONE, 0, 3);
        System.out.println("\nTest mit Stein G,G, rota0:" + (points.get(ColorEnum.GREEN)));
        state.putStoneOnField(new Stone(Color.GREEN, Color.GREEN), Rotation.NONE, 0, 3);

        for(ColorEnum col: points.keySet()){
            totalPoints.put(col, points.get(col) + totalPoints.get(col));
        }

        state.putStoneOnField(new Stone(Color.BLUE, Color.BLUE), Rotation.ONE_CLOCKWISE, 5,9);
        points = state.calculatePoints(new Stone(Color.BLUE, Color.BLUE), Rotation.ONE_CLOCKWISE, 5,9);
        System.out.println("\nTest mit Stein B,B, rota1:" + (points.get(ColorEnum.BLUE)));

        for(ColorEnum col: points.keySet()){
            totalPoints.put(col, points.get(col) + totalPoints.get(col));
        }

        totalPoints.forEach((x, y) ->{
            System.out.println(x + ": " + y);
        });
    }

    public static void testNextStates() {
        State state = new State(board);

        state.putStoneOnField(new Stone(Color.RED, Color.PURPLE), Rotation.TWO_CLOCKWISE, 3, 0);
        state.putStoneOnField(new Stone(Color.PURPLE, Color.GREEN), Rotation.NONE, 4, 1);
        state.putStoneOnField(new Stone(Color.GREEN, Color.YELLOW), Rotation.TWO_CLOCKWISE, 7, 2);
        state.putStoneOnField(new Stone(Color.YELLOW, Color.BLUE), Rotation.NONE, 9, 1);

        state.printBoard();
        System.out.println("----------------------");
        List<State> states = state.nextState();

        states.forEach(x -> {
            x.printBoard();
            System.out.println("----------------------");
        });
    }

}
