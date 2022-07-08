package com.example.bachelorthesis.controller;

import com.example.bachelorthesis.ai.MCTS;
import com.example.bachelorthesis.model.ColorEnum;
import com.example.bachelorthesis.model.GameBoard;
import com.example.bachelorthesis.view.Gui;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final Gui gui;
    private Label totalTimeLabelSim;
    private Label simScores;
    private Label expScores;
    public ArrayList<Integer> results;

    public Controller(Gui gui) {
        this.gui = gui;
    }

    public void clickOnStart() {
        gui.reset();
        this.totalTimeLabelSim = gui.getSidePane().totalTimeLabel;
        this.expScores = gui.getSidePane().resExpLabel;
        this.simScores = gui.getSidePane().scoreLabel;
        MCTS ai = new MCTS(this, gui);

        Platform.setImplicitExit(false);
        Platform.runLater(() ->{
            ai.start();
        });

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

    public void clickOnRandom() {
        gui.reset();
        MCTS ai = new MCTS(this, gui);
        ai.randomGame();
    }

    public void clickOnExperiment() {
        int n = 1;
        try {
            n = Integer.parseInt(gui.getSidePane().number.getText());
        } catch (Exception e) {

        }

        boolean randomGame = gui.getSidePane().ranExp.isSelected();
        gui.getSidePane().experimentButton.setDisable(true);
        // hier wird das experiment mit 1000 spielen durchgeführt
        results = new ArrayList<>();
        double time = System.currentTimeMillis();
        MCTS ai = new MCTS(this, gui);
        for (int i = 0; i < n; i++) {
            if (randomGame) {
                // hier zufallsspiele ausfuehren
                ai.randomGame();
            } else {
                // hier mcts spiele ausfuehren
                ai.start();
            }
        }
        // get maxPoints, minPoints, avgPoints, totalTime
        time = (System.currentTimeMillis() - time) / 1000;
        double avg = 0.0;
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int x: results){
            avg += (double) x;
            if(x < min){
                min = x;
            }
            if(x > max){
                max = x;
            }
        }
        avg /= results.size();

        gui.getSidePane().updateExperimentLabel(min, max, avg, time);
        gui.getSidePane().experimentButton.setDisable(false);
    }

    public void update(GameBoard gameBoard, HashMap<ColorEnum, Integer> colorScores) {
        gui.getSidePane().setLabelScore(colorScores);
        gui.getPane().updateBoard(gameBoard);
    }

    public void update(double runningTime) {
        gui.getSidePane().updateTime(runningTime);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
