package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.ColorEnum;
import com.example.bachelorthesis.model.Game;
import com.example.bachelorthesis.model.State;
import com.example.bachelorthesis.model.Stone;
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
        // gui.getPane().putStoneOnField(0,1, game.drawStone(), Rotation.NONE);
        Stone stone;
        while (!game.getFinished()) {
            stone = game.drawStone();
            System.out.println(stone.toString());
            ColorEnum color = state.getLowestColor();
            if (stone.containsColor(color)) {

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
