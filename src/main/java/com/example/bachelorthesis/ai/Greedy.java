package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.*;
import com.example.bachelorthesis.view.Gui;

import java.util.LinkedList;
import java.util.List;

public class Greedy extends AI {

    public Greedy(Gui gui) {
        state = new State();
        game = new Game();
        todo = new LinkedList<>();
        Stone stone = game.drawStone();
        this.gui = gui;
    }

    @Override
    public void start() {
        Stone stone = game.drawStone();
        //System.out.println(stone);
        //gui.getPane().putStoneOnField(0, 1, stone, Rotation.ONE_CLOCKWISE);
        while (!game.getFinished()) {
            stone = game.drawStone();
            System.out.println(stone.toString());
            ColorEnum color = state.getLowestColor();

            if (stone.containsColor(color)) {
                int i;
                int j = 0;
                A:
                for (i = 0; i < state.gameBoard.length; i++) {
                    for (j = 0; j < state.gameBoard[i].length; j++) {
                        if (state.gameBoard[i][j] == color.toString().charAt(0)) {
                            break A;
                        }
                    }
                }
                if (i == 0 && j == 0) {

                } else if (i == 0 && j == 5) {

                } else if (i == 0) {

                } else if (i == 5 && j == 0) {

                } else if (i == 5 && j == 10) {

                } else if (i == 10 && j == 0) {

                } else if (i == 10 && j == 5) {

                } else if (i == 10) {

                } else if (j == 0) {

                } else if (j == state.gameBoard[i].length - 1) {

                } else {

                }
            } // maximize next lowest color

        }
    }

    public String search() {
        while (true) {
            if (todo.isEmpty()) {

            } else {
                state = selectState(todo);
                if (isSolution(state)) {
                    return "solution found!";
                } else {
                    List expandedStates = expand(state);
                    todo.add(state);
                }
            }
        }
    }

    private List expand(State state) {
        return null;
    }

    private boolean isSolution(State state) {
        return true;
    }


    private State selectState(List todo) {
        return null;
    }

}
