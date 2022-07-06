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
        Game game = new Game();
//        testNextStates();
        testPointCalculation();
//        testRandomMove();
//        testIsFull();
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
        state.printBoard();
        System.out.println("Test mit Stein P,Y,rota1: " + (state.getColorScores().get(ColorEnum.YELLOW)) + "\n");

        state.calculatePoints(new Stone(Color.RED, Color.GREEN), Rotation.ONE_CLOCKWISE, 2, 0);
        System.out.println("Test mit Stein R,G, rota1:" + (state.getColorScores().get(ColorEnum.RED)) + ", " + (state.getColorScores().get(ColorEnum.GREEN)));
        state.putStoneOnField(new Stone(Color.RED, Color.GREEN), Rotation.ONE_CLOCKWISE, 2, 0);
        state.printBoard();

        state.calculatePoints(new Stone(Color.GREEN, Color.GREEN), Rotation.NONE, 0, 3);
        System.out.println("\nTest mit Stein G,G, rota0:" + (state.getColorScores().get(ColorEnum.GREEN)));
        state.putStoneOnField(new Stone(Color.GREEN, Color.GREEN), Rotation.NONE, 0, 3);
        state.printBoard();

        state.putStoneOnField(new Stone(Color.BLUE, Color.BLUE), Rotation.ONE_CLOCKWISE, 5, 9);
        state.calculatePoints(new Stone(Color.BLUE, Color.BLUE), Rotation.ONE_CLOCKWISE, 5, 9);
        state.printBoard();
        System.out.println("\nTest mit Stein B,B, rota1:" + (state.getColorScores().get(ColorEnum.BLUE)));

        state.putStoneOnField(new Stone(Color.RED, Color.RED), Rotation.THREE_CLOCKWISE, 0, 2);
        state.calculatePoints(new Stone(Color.RED, Color.RED), Rotation.THREE_CLOCKWISE, 0, 2);
        state.printBoard();
        System.out.println("\nTest mit Stein R,R, rota3: " + (state.getColorScores().get(ColorEnum.RED)));

        state.putStoneOnField(new Stone(Color.PURPLE, Color.RED), Rotation.FIVE_CLOCKWISE, 6, 0);
        state.calculatePoints(new Stone(Color.PURPLE, Color.RED), Rotation.FIVE_CLOCKWISE, 6, 0);
        state.printBoard();
        System.out.println("\nTest mit Stein P,R, rota5: " + (state.getColorScores().get(ColorEnum.PURPLE)));

        for (ColorEnum col : state.getColorScores().keySet()) {
            totalPoints.put(col, state.getColorScores().get(col) + totalPoints.get(col));
        }

        totalPoints.forEach((x, y) -> System.out.println(x + ": " + y));
    }

    public static void testNextStates() {
        State state = new State();

        //state.putStoneOnField(new Stone(Color.RED, Color.PURPLE), Rotation.TWO_CLOCKWISE, 3, 0);
//        state.putStoneOnField(new Stone(Color.PURPLE, Color.GREEN), Rotation.NONE, 4, 1);
//        state.putStoneOnField(new Stone(Color.GREEN, Color.YELLOW), Rotation.TWO_CLOCKWISE, 7, 2);
//        state.putStoneOnField(new Stone(Color.YELLOW, Color.BLUE), Rotation.NONE, 9, 1);

        state.printBoard();
        System.out.println("----------------------");
        List<State> states = state.nextState();
        System.out.println(states.size());
        states.forEach(x -> {
            x.printBoard();
            x.getColorScores().forEach((k, v) -> System.out.println(k + ": " + v));
            System.out.println("----------------------");
        });
    }

    public static void testRandomMove(){
        State initial = new State();
        initial.nextState();
        initial.randomMove();
        initial.nextState();
        initial.randomMove();
    }

    public static void testIsFull(){
        GameBoard gb1 = new GameBoard();
        System.out.println(gb1.isFull());

        GameBoard gb2 = new GameBoard();

        for(int i = 0; i < 11; i++){
            for(int j = 0; j < gb2.representation[i].length; j++){
                gb2.representation[i][j] = ColorEnum.BLUE;
            }
        }
        System.out.println(gb2.isFull());

        gb2.representation[5][4] = ColorEnum.NONE;
        System.out.println(gb2.isFull());

        gb2.representation[5][6] = ColorEnum.NONE;
        System.out.println(gb2.isFull());

        gb2.representation[4][4] = ColorEnum.NONE;
        System.out.println(gb2.isFull());
    }

}
