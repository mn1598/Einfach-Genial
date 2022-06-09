package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.game.Game;
import com.example.bachelorthesis.game.Stone;
import javafx.scene.paint.Color;

import java.util.List;

public class Greedy implements AI {

    List<State> todo;
    State state;
    Game game;

    public Greedy() {
        state = new State();
        game = new Game();
    }

    @Override
    public void start() {
        Stone stone;
        while (!game.getFinished()) {
            stone = game.drawStone();
            System.out.println(stone.toString());
            Color color = state.getLowestColor();
            if (stone.containsColor(color)) {
                // use this to maximize points of lowest color
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
