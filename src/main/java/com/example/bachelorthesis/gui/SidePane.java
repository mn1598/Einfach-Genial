package com.example.bachelorthesis.gui;

import com.example.bachelorthesis.ai.AI;
import com.example.bachelorthesis.ai.AStar;
import com.example.bachelorthesis.ai.Greedy;
import com.example.bachelorthesis.ai.MCTS;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class SidePane extends AnchorPane {

    private int avgTime;
    private int maxTime;
    private int totalTime;

    private Label avgTimeLabel;
    private Label maxTimeLabel;
    private Label totalTimeLabel;
    private Label scoreLabel;

    public SidePane() {
        Label settingsLabel = new Label("Choose Search Method");
        settingsLabel.setFont(new Font(20));
        this.getChildren().add(settingsLabel);
        settingsLabel.setLayoutX(20);
        this.setPrefWidth(180);
        this.setHeight(700);

        RadioButton greedyRadio = new RadioButton("Greedy");
        ToggleGroup algorithmGroup = new ToggleGroup();
        greedyRadio.setToggleGroup(algorithmGroup);
        greedyRadio.setLayoutY(30);
        greedyRadio.setLayoutX(20);
        greedyRadio.setSelected(true);
        this.getChildren().add(greedyRadio);
        RadioButton astartRadio = new RadioButton(("A*"));
        astartRadio.setLayoutY(50);
        astartRadio.setLayoutX(20);
        astartRadio.setToggleGroup(algorithmGroup);
        this.getChildren().add(astartRadio);
        RadioButton mctsRadio = new RadioButton("MCTS");
        mctsRadio.setLayoutY((70));
        mctsRadio.setLayoutX(20);
        mctsRadio.setToggleGroup(algorithmGroup);
        this.getChildren().add(mctsRadio);

        Button startButton = new Button("Start Simulation");
        startButton.setLayoutX((20));
        startButton.setLayoutY(90);
        this.getChildren().add(startButton);
        startButton.setOnAction(x ->{
            System.out.println("started simulation.");
            AI ai = null;
            if(greedyRadio.isSelected()){
                ai = new Greedy();
            } else if (astartRadio.isSelected()){
                ai = new AStar();
            } else if (mctsRadio.isSelected()){
                ai = new MCTS();
            }
            ai.start();
        });

        Label resultLabel = new Label("Results");
        resultLabel.setLayoutX(20);
        resultLabel.setLayoutY(130);
        resultLabel.setFont(new Font(22));
        this.getChildren().add(resultLabel);

        avgTimeLabel = new Label();
        avgTimeLabel.setLayoutY(150);
        avgTimeLabel.setLayoutX(20);
        maxTimeLabel = new Label();
        maxTimeLabel.setLayoutY(170);
        maxTimeLabel.setLayoutX(20);
        totalTimeLabel = new Label();
        totalTimeLabel.setLayoutY(190);
        totalTimeLabel.setLayoutX(20);
        scoreLabel = new Label();
        scoreLabel.setLayoutY(210);
        scoreLabel.setLayoutX(20);

        Button resetButton = new Button("Reset");
        resetButton.setLayoutY(230);
        resetButton.setLayoutX(20);
        resetButton.setOnAction(x -> {

        });
        this.getChildren().addAll(avgTimeLabel, maxTimeLabel, totalTimeLabel, scoreLabel, resetButton);


    }

    public void setLabelText(int avg, int max, int total, int score) {
        avgTimeLabel.setText("Average Time: " +  avg + "s");
        maxTimeLabel.setText("Max Time: " + max + "s");
        totalTimeLabel.setText("Total Time: " + total + "s");
        scoreLabel.setText("Score: " + score + "pts");
    }
}
