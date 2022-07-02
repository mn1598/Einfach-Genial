package com.example.bachelorthesis.Test;

import com.example.bachelorthesis.model.*;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;

/*
 * this class is meant to test some methods of the gameplay
 *
 * are point correctly calculated, are next states successfully generated?
 */
public class Test {

    public static void main(String[] args) {
        testNextStates();
        testPointCalculation();
    }

    public static void testPointCalculation() {
        State state = new State();
        state.printBoard();
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
        state.printBoard();
        state.putStoneOnField(new Stone(Color.PURPLE, Color.YELLOW), Rotation.ONE_CLOCKWISE, 6, 3);
        state.calculatePoints(new Stone(Color.PURPLE, Color.YELLOW), Rotation.ONE_CLOCKWISE, 6, 3);
        System.out.println("Test mit Stein P,Y,rota1: " + (state.colorScores.get(ColorEnum.YELLOW)) + "\n");

        state.calculatePoints(new Stone(Color.RED, Color.GREEN), Rotation.ONE_CLOCKWISE, 2, 0);
        System.out.println("Test mit Stein R,G, rota1:" + (state.colorScores.get(ColorEnum.RED)) + ", " + (state.colorScores.get(ColorEnum.GREEN)));
        state.putStoneOnField(new Stone(Color.RED, Color.GREEN), Rotation.ONE_CLOCKWISE, 2, 0);

        state.calculatePoints(new Stone(Color.GREEN, Color.GREEN), Rotation.NONE, 0, 3);
        System.out.println("\nTest mit Stein G,G, rota0:" + (state.colorScores.get(ColorEnum.GREEN)));
        state.putStoneOnField(new Stone(Color.GREEN, Color.GREEN), Rotation.NONE, 0, 3);

        state.putStoneOnField(new Stone(Color.BLUE, Color.BLUE), Rotation.ONE_CLOCKWISE, 5, 9);
        state.calculatePoints(new Stone(Color.BLUE, Color.BLUE), Rotation.ONE_CLOCKWISE, 5, 9);
        System.out.println("\nTest mit Stein B,B, rota1:" + (state.colorScores.get(ColorEnum.BLUE)));

        state.putStoneOnField(new Stone(Color.RED, Color.RED), Rotation.THREE_CLOCKWISE, 0, 2);
        state.calculatePoints(new Stone(Color.RED, Color.RED), Rotation.THREE_CLOCKWISE, 0, 2);
        System.out.println("\nTest mit Stein R,R, rota3: " + (state.colorScores.get(ColorEnum.RED)));

        state.putStoneOnField(new Stone(Color.PURPLE, Color.RED), Rotation.FIVE_CLOCKWISE, 6, 0);
        state.calculatePoints(new Stone(Color.PURPLE, Color.RED), Rotation.FIVE_CLOCKWISE, 6, 0);
        System.out.println("\nTest mit Stein P,R, rota5: " + (state.colorScores.get(ColorEnum.PURPLE)));

        for (ColorEnum col : state.colorScores.keySet()) {
            totalPoints.put(col, state.colorScores.get(col) + totalPoints.get(col));
        }

        totalPoints.forEach((x, y) -> System.out.println(x + ": " + y));
    }

    public static void testNextStates() {
        State state = new State();

        state.putStoneOnField(new Stone(Color.RED, Color.PURPLE), Rotation.TWO_CLOCKWISE, 3, 0);
        state.putStoneOnField(new Stone(Color.PURPLE, Color.GREEN), Rotation.NONE, 4, 1);
        state.putStoneOnField(new Stone(Color.GREEN, Color.YELLOW), Rotation.TWO_CLOCKWISE, 7, 2);
        state.putStoneOnField(new Stone(Color.YELLOW, Color.BLUE), Rotation.NONE, 9, 1);

        state.printBoard();
        System.out.println("----------------------");
        List<State> states = state.nextState();

        states.forEach(x -> {
            x.printBoard();
            x.colorScores.forEach((k, v) -> System.out.println(k + ": " + v));
            System.out.println("----------------------");
        });
    }

}
