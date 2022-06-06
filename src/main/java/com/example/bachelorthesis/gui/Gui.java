package com.example.bachelorthesis.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane borderPane = new BorderPane();
        SidePane sidePane = new SidePane();
        borderPane.setLeft(sidePane);
        int height = 6;
        int width = 4;
        CentrePane pane = new CentrePane();
        borderPane.setCenter(pane);
        Scene content = new Scene(borderPane, 1200, 900);
        borderPane.setTop(new AnchorPane());
        ((AnchorPane) borderPane.getTop()).setPrefHeight(20);
        stage.setScene(content);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}