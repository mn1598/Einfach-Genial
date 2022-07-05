package com.example.bachelorthesis.view;

import com.example.bachelorthesis.controller.Controller;
import com.example.bachelorthesis.model.ColorEnum;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashMap;
// todo anzeige für experiment mit 1000 spielen!!!!
public class SidePane extends AnchorPane {

    private int avgTime;
    private int maxTime;
    private int totalTime;

    private Label avgTimeLabel;
    private Label maxTimeLabel;
    private Label totalTimeLabel;
    private Label scoreLabel;

    private Gui gui;
    private Controller controller;

    public RadioButton greedyRadio;
    public RadioButton astartRadio;
    public RadioButton mctsRadio;

    public SidePane(Gui gui) {
        this.gui = gui;
        Label settingsLabel = new Label("MCTS Simulation");
        settingsLabel.setFont(new Font(20));
        this.getChildren().add(settingsLabel);
        settingsLabel.setLayoutX(20);
        this.setPrefWidth(180);
        this.setHeight(700);

        controller = new Controller(gui);

//        greedyRadio = new RadioButton("Greedy");
//        ToggleGroup algorithmGroup = new ToggleGroup();
//        greedyRadio.setToggleGroup(algorithmGroup);
//        greedyRadio.setLayoutY(30);
//        greedyRadio.setLayoutX(20);
//        greedyRadio.setSelected(true);
//        this.getChildren().add(greedyRadio);
//        astartRadio = new RadioButton(("A*"));
//        astartRadio.setLayoutY(50);
//        astartRadio.setLayoutX(20);
//        astartRadio.setToggleGroup(algorithmGroup);
//        this.getChildren().add(astartRadio);
//        mctsRadio = new RadioButton("MCTS");
//        mctsRadio.setLayoutY((70));
//        mctsRadio.setLayoutX(20);
//        mctsRadio.setToggleGroup(algorithmGroup);
//        this.getChildren().add(mctsRadio);

        Button startButton = new Button("Start Simulation");
        startButton.setLayoutX((20));
        startButton.setLayoutY(90);
        this.getChildren().add(startButton);
        startButton.setOnAction(x ->{
            controller.clickOnStart();
        });

        Label resultLabel = new Label("Results");
        resultLabel.setLayoutX(20);
        resultLabel.setLayoutY(130);
        resultLabel.setFont(new Font(22));
        this.getChildren().add(resultLabel);

        avgTimeLabel = new Label();
        avgTimeLabel.setLayoutY(170);
        avgTimeLabel.setLayoutX(20);
        maxTimeLabel = new Label();
        maxTimeLabel.setLayoutY(190);
        maxTimeLabel.setLayoutX(20);
        totalTimeLabel = new Label();
        totalTimeLabel.setLayoutY(210);
        totalTimeLabel.setLayoutX(20);
        scoreLabel = new Label();
        scoreLabel.setLayoutY(230);
        scoreLabel.setLayoutX(20);
        scoreLabel.setPrefHeight(300);

        Button resetButton = new Button("Reset");
        resetButton.setLayoutY(230);
        resetButton.setLayoutX(20);
        this.getChildren().addAll(avgTimeLabel, maxTimeLabel, totalTimeLabel, scoreLabel);


    }

    // todo delete avg time und max time, total time kann bleiben!
    public void setLabelText(int avg, int max, int total, HashMap<ColorEnum, Integer> scores) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                avgTimeLabel.setText("Average Time: " +  avg + "s");
                maxTimeLabel.setText("Max Time: " + max + "s");
                totalTimeLabel.setText("Total Time: " + total + "s");
                String scoreText = "";
                for(ColorEnum color: scores.keySet()){
                    scoreText += color + " :" + scores.get(color) + " Points\n";
                }
                scoreLabel.setText(scoreText);
            }
        });

    }
}
