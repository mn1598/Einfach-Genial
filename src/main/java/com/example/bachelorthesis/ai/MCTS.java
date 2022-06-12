package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.gui.Gui;

public class MCTS extends AI{

    public MCTS (Gui gui){
        this.gui = gui;
    }
    @Override
    public void start() {

    }

    public double calculateUCT(){
        return 0.0;
    }
}
