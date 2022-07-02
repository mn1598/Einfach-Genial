package com.example.bachelorthesis.controller;

import com.example.bachelorthesis.ai.MCTS;
import com.example.bachelorthesis.view.Gui;

public class Controller {

    private final Gui gui;

    public Controller(Gui gui){
        this.gui = gui;
    }

    public void clickOnStart(){
        gui.reset();
        MCTS ai = new MCTS(gui);

        ai.start();
    }





}
