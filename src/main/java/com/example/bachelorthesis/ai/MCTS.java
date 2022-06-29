package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.Game;
import com.example.bachelorthesis.model.State;
import com.example.bachelorthesis.view.Gui;

import java.util.List;
import java.util.Random;

public class MCTS {

    Gui gui;
    Node root;
    Random random = new Random();
    Game game;

    public MCTS(Gui gui) {
        this.gui = gui;
        root = new Node();
        game = new Game();
    }

    public void start() {

    }


    public int[] randomPosition() {
        int x = random.nextInt(11);
        int y;
        if (x <= 5) {
            y = random.nextInt(x + 6);
        } else {
            y = random.nextInt(16 - x);
        }
        int[] position = {x, y};
        return position;
    }

    public State simulate(State state){
        int maxPoints = Integer.MAX_VALUE;
        for(int i = 0; i < 1000; i++){
           // int currentPoi
            // if playout(state);
        }
        return null;
    }

    public int playout(State state) {
        if (state.gameBoard.isFull) {
            return state.lowestColorScore;
        }
        List<State> nextStates = state.nextState();
        State move = nextStates.get(random.nextInt(nextStates.size()));
        return playout(move);
    }
}
