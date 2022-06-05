package com.example.bachelorthesis.gui;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class SidePane extends AnchorPane {

    private int avgTime;
    private int maxTime;
    private int totalTime;

    private Label avgTimeLabel;
    private Label maxTimeLabel;
    private Label totalTimeLabel;
    private Label scoreLabel;

    public SidePane() {
        this.setPrefWidth(100);
        this.setHeight(700);
        RadioButton greedyRadio = new RadioButton("Greedy");
        ToggleGroup algorithmGroup = new ToggleGroup();
        greedyRadio.setToggleGroup(algorithmGroup);
        this.getChildren().add(greedyRadio);
        RadioButton astartRadio = new RadioButton(("A*"));
        astartRadio.setLayoutY(20);
        astartRadio.setToggleGroup(algorithmGroup);
        this.getChildren().add(astartRadio);

        avgTimeLabel = new Label();
        maxTimeLabel = new Label();
        totalTimeLabel = new Label();
        scoreLabel = new Label();
    }

    public void setLabelText(int avg, int max, int total, int score) {
        avgTimeLabel.setText("Average Time: " +  avg + "s");
        maxTimeLabel.setText("Max Time: " + max + "s");
        totalTimeLabel.setText("Total Time: " + total + "s");
        scoreLabel.setText("Score: " + score + "pts");
    }
}
