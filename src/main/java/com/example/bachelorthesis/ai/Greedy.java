package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.game.Game;
import com.example.bachelorthesis.game.Stone;
import javafx.scene.paint.Color;

public class Greedy implements AI {

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
            if(stone.containsColor(color)){
                // use this to maximize points of lowest color
            } // maximize next lowest color
        }
    }


}
