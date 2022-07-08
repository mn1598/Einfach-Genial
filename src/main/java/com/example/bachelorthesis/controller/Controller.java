package com.example.bachelorthesis.controller;

import com.example.bachelorthesis.ai.MCTS;
import com.example.bachelorthesis.model.ColorEnum;
import com.example.bachelorthesis.model.GameBoard;
import com.example.bachelorthesis.view.Gui;
import javafx.application.Platform;
import javafx.scene.control.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    private final Gui gui;
    private final boolean PLAY_AI = false; // false = random play, true = mcts play

    public Controller(Gui gui){
        this.gui = gui;
    }

    public void clickOnStart(){
        gui.reset();
        MCTS ai = new MCTS(gui);

        ai.start();

//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                String scoreText = "";
//                for(ColorEnum color: gui.getSidePane().scores.keySet()){
//                    scoreText += color + " :" + gui.getSidePane().scores.get(color) + " Points\n";
//                }
//                gui.getSidePane().scoreLabel.setText(scoreText);
//            }
//        });
    }

    public void clickOnRandom(){
        gui.reset();
        MCTS ai = new MCTS(gui);
        ai.randomGame();
    }

    public void clickOnExperiment(int n){
        boolean randomGame = gui.getSidePane().ranExp.isSelected();
        System.out.println(randomGame);
        // todo hier wird das experiment mit 1000 spielen durchgeführt
        ArrayList<Integer> results = new ArrayList<>();
        for(int i = 0; i < n; i++) {

        }
        // todo get maxPoints, minPoints, avgPoints, totalTime
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
