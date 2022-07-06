package com.example.bachelorthesis.controller;

import com.example.bachelorthesis.ai.MCTS;
import com.example.bachelorthesis.model.ColorEnum;
import com.example.bachelorthesis.model.GameBoard;
import com.example.bachelorthesis.view.Gui;
import javafx.application.Platform;

import java.util.HashMap;

public class Controller {

    private final Gui gui;

    public Controller(Gui gui){
        this.gui = gui;
    }

    public void clickOnStart(){
        gui.reset();
        MCTS ai = new MCTS(this);

        ai.start();
    }

    public void clickOnExperiment(){
        // todo hier wird das experiment mit 1000 spielen durchgeführt
    }

    public void update(GameBoard gameBoard, HashMap<ColorEnum, Integer> colorScores) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gui.getSidePane().setLabelScore(colorScores);
                gui.getPane().updateBoard(gameBoard);
            }
        });
    }

    public void update(double runningTime) {
        gui.getSidePane().updateTime(runningTime);
    }
}
