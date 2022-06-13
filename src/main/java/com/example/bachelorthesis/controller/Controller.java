package com.example.bachelorthesis.controller;

import com.example.bachelorthesis.ai.AI;
import com.example.bachelorthesis.ai.AStar;
import com.example.bachelorthesis.ai.Greedy;
import com.example.bachelorthesis.ai.MCTS;
import com.example.bachelorthesis.view.Gui;

public class Controller {

    private Gui gui;

    public Controller(Gui gui){
        this.gui = gui;
    }

    public void clickOnStart(){
        System.out.println("started simulation.");
        AI ai = null;
        if(gui.getSidePane().greedyRadio.isSelected()){
            ai = new Greedy(gui);
        } else if (gui.getSidePane().astartRadio.isSelected()){
            ai = new AStar(gui);
        } else if (gui.getSidePane().mctsRadio.isSelected()){
            ai = new MCTS(gui);
        }
        ai.start();
    }

    public void clickOnReset(){
        gui.reset();
    }



}
