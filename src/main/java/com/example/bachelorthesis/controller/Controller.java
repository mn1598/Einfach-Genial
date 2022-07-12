package com.example.bachelorthesis.controller;

import com.example.bachelorthesis.ai.MCTS;
import com.example.bachelorthesis.model.ColorEnum;
import com.example.bachelorthesis.model.GameBoard;
import com.example.bachelorthesis.view.Gui;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.controlsfx.control.action.ActionUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller {

    private final Gui gui;
    private Label totalTimeLabelSim;
    private Label simScores;
    private Label expScores;
    public ArrayList<Integer> results;

    public Controller(Gui gui) {
        this.gui = gui;
    }

    public void clickOnStart(ActionEvent event) {
        MCTS ai = new MCTS(this, gui);
        results = new ArrayList<>();
        gui.reset();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ai.start();
    }

    public void clickOnRandom(ActionEvent event) {
        gui.reset();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        results = new ArrayList<>();
        MCTS ai = new MCTS(this, gui);
        ai.randomGame();
    }

    public void clickOnExperiment(ActionEvent event) {
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
            gui.reset();
        }
        // get maxPoints, minPoints, avgPoints, totalTime
        time = (System.currentTimeMillis() - time) / 1000;
        double avg = 0.0;
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int x : results) {
            avg += (double) x;
            if (x < min) {
                min = x;
            }
            if (x > max) {
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

    public void reset() {
        gui.reset();
    }
}
