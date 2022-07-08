package com.example.bachelorthesis.view;

import com.example.bachelorthesis.controller.Controller;
import com.example.bachelorthesis.model.ColorEnum;
import javafx.application.Platform;
import javafx.scene.control.*;
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
    public Label scoreLabel;

    private Gui gui;
    private Controller controller;

    public RadioButton ranExp;
    public RadioButton mctsExp;

    public HashMap<ColorEnum, Integer> scores;

    public SidePane(Gui gui) {
        this.gui = gui;
        this.setPrefWidth(180);
        this.setHeight(700);

        controller = new Controller(gui);

        Label mctsLabel = new Label("Simulation");
        mctsLabel.setFont(new Font(22));
        this.getChildren().add(mctsLabel);
        mctsLabel.setLayoutX(20);

        Button startButton = new Button("Start MCTS");
        startButton.setLayoutX((20));
        startButton.setLayoutY(40);
        this.getChildren().add(startButton);
        startButton.setOnAction(x -> {
            controller.clickOnStart();
        });

        Button startRandom = new Button("Start Random Game");
        startRandom.setLayoutX(20);
        startRandom.setLayoutY(80);
        this.getChildren().add(startRandom);
        startRandom.setOnAction(x -> {
            controller.clickOnRandom();
        });

        Label resultLabel = new Label("Results");
        resultLabel.setLayoutX(20);
        resultLabel.setLayoutY(120);
        resultLabel.setFont(new Font(16));
        this.getChildren().add(resultLabel);

        totalTimeLabel = new Label();
        totalTimeLabel.setLayoutY(150);
        totalTimeLabel.setLayoutX(20);
        scoreLabel = new Label();
        scoreLabel.setLayoutY(170);
        scoreLabel.setLayoutX(20);

        Label expLabel = new Label("Experiment");
        expLabel.setFont(new Font(22));
        this.getChildren().add(expLabel);
        expLabel.setLayoutY(280);
        expLabel.setLayoutX(20);

        ranExp = new RadioButton("Random");
        mctsExp = new RadioButton("MCTS");
        ToggleGroup expGroup = new ToggleGroup();
        ranExp.setToggleGroup(expGroup);
        mctsExp.setToggleGroup(expGroup);
        mctsExp.setSelected(true);
        ranExp.setLayoutX(20);
        ranExp.setLayoutY(355);
        mctsExp.setLayoutX(100);
        mctsExp.setLayoutY(355);
        this.getChildren().addAll(ranExp, mctsExp);

        Button experimentButton = new Button("Start Experiment");
        experimentButton.setLayoutY(380);
        experimentButton.setLayoutX(20);

        TextField number = new TextField();
        number.setPromptText("set number of rounds");
        number.setLayoutY(320);
        number.setLayoutX(20);
        int n = 1;
        try {
            n = Integer.parseInt(number.getText());
        } catch (Exception e) {

        }
        int finalN = n;
        experimentButton.setOnAction(x -> controller.clickOnExperiment(finalN));
        this.getChildren().addAll(totalTimeLabel, scoreLabel, experimentButton, number);


    }

    public void setLabelScore(HashMap<ColorEnum, Integer> scores) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String scoreText = "";
                for (ColorEnum color : scores.keySet()) {
                    if (color == ColorEnum.RED) {
                        scoreText += color + ":\t\t\t" + scores.get(color) + " Points\n";
                    } else {
                        scoreText += color + ":\t\t" + scores.get(color) + " Points\n";
                    }
                }
                scoreLabel.setText(scoreText);
            }
        });
    }


    public void updateTime(double runningTime) {
        totalTimeLabel.setText("Total Time:\t" + runningTime + "s");
    }
}
