package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.game.Game;
import com.example.bachelorthesis.gui.Gui;

import java.util.List;

public abstract class AI {

    public State state;
    public char[][] gameBoard;

    List<State> todo;
    Game game;
    Gui gui;

    public abstract void start();

    public void reset() {

    }
}
