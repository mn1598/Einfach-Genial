package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.Game;
import com.example.bachelorthesis.model.State;
import com.example.bachelorthesis.view.Gui;

import java.util.List;

public abstract class AI {

    public State state;
    //public char[][] gameBoard;

    List<State> todo;
    Game game;
    Gui gui;

    public abstract void start();

    public void reset() {

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


    // heuristik implementieren
}
