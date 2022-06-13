package com.example.bachelorthesis.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Gui extends Application {

    private CentrePane pane;
    private SidePane sidePane;

    @Override
    public void start(Stage stage)  {
        BorderPane borderPane = new BorderPane();
        sidePane = new SidePane(this);
        borderPane.setLeft(sidePane);
        int height = 6;
        int width = 4;
        pane = new CentrePane(this);
        borderPane.setCenter(pane);
        Scene content = new Scene(borderPane, 1200, 900);
        borderPane.setTop(new AnchorPane());
        ((AnchorPane) borderPane.getTop()).setPrefHeight(20);
        stage.setScene(content);

        stage.show();
    }

    public CentrePane getPane(){
        return pane;
    }

    public SidePane getSidePane() {
        return sidePane;
    }

    public void reset(){
        this.pane.initGameBoard();
    }

    public static void main(String[] args) {
        launch();
    }
}