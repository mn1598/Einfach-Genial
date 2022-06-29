package com.example.bachelorthesis.controller;

import com.example.bachelorthesis.ai.MCTS;
import com.example.bachelorthesis.view.Gui;

public class Controller {

    private Gui gui;

    public Controller(Gui gui){
        this.gui = gui;
    }

    public void clickOnStart(){
        System.out.println("started simulation.");
        MCTS ai = new MCTS(gui);

        ai.start();
        for (int i = 0; i < 50; i++){
            int[] array = ai.randomPosition();
            System.out.println(array[0] + " " + array[1]);
        }
    }

    public void clickOnReset(){
        gui.reset();
    }



}
