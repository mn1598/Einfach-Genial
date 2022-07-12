package com.example.bachelorthesis.controller;

import com.example.bachelorthesis.ai.MCTS;
import com.example.bachelorthesis.model.ColorEnum;
import com.example.bachelorthesis.model.GameBoard;
import com.example.bachelorthesis.view.Gui;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    private final Gui gui;
    public static ArrayList<Integer> results;

    public Controller(Gui gui) {
        this.gui = gui;
    }

    public void clickOnStart(ActionEvent event) {
        gui.reset();
        MCTS ai = new MCTS(this, gui);
        results = new ArrayList<>();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                ai.start();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
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
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                ai.randomGame();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    public void clickOnExperiment(ActionEvent event) {
        gui.reset();
        int n = 1;
        try {
            n = Integer.parseInt(gui.getSidePane().number.getText());
        } catch (Exception e) {

        }

        boolean randomGame = gui.getSidePane().ranExp.isSelected();
        gui.getSidePane().experimentButton.setDisable(true);
        // hier wird das experiment mit 1000 spielen durchgeführt
        results = new ArrayList<>();
        long time = System.currentTimeMillis();
        MCTS ai = new MCTS(this, gui);
        for (int i = 0; i < n; i++) {
            if (randomGame) {
                // hier zufallsspiele ausfuehren
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        ai.randomGame();
                        updateExperimentResult(time);
                        return null;
                    }
                };
                Thread thread = new Thread(task);
                thread.setDaemon(true);
                thread.start();
                while(thread.isAlive());
            } else {
                // hier mcts spiele ausfuehren
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        ai.start();
                        return null;
                    }
                };
                Thread thread = new Thread(task);
                thread.setDaemon(true);
                thread.start();
                while(thread.isAlive());
            }
            Platform.runLater(() -> {
                gui.reset();
            });
        }
    }

    public void updateExperimentResult(long time){
        Platform.runLater(()->{
            // get maxPoints, minPoints, avgPoints, totalTime
            long total = (System.currentTimeMillis() - time) / 1000;
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
            gui.getSidePane().updateExperimentLabel(min, max, avg, total);
            gui.getSidePane().experimentButton.setDisable(false);
        });
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
