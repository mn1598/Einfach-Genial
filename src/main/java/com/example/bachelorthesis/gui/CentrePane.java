package com.example.bachelorthesis.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;

public class CentrePane extends GridPane {

    public CentrePane(){
        Polygon[] row0 = new Polygon[]{new Polygon(20, 20, 20, 20, 20, 20), new Polygon(20, 20, 20, 20, 20, 20), new Polygon(20, 20, 20, 20, 20, 20), new Polygon(20, 20, 20, 20, 20, 20), new Polygon(20, 20, 20, 20, 20, 20), new Polygon(20, 20, 20, 20, 20, 20)};
        row0[0].getPoints().addAll(new Double[]{
                200.0, 50.0,
                400.0, 50.0,
                450.0, 150.0,
                400.0, 250.0,
                200.0, 250.0,
                150.0, 150.0,
        });
        this.addRow(0, row0);

        this.setGridLinesVisible(true);

        Polygon field[][] = new Polygon[12][8];
/*
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageView tile = new ImageView(HEXAGON_IMAGE);
                GridPane.setConstraints(tile, x, y);
                pane.getChildren().add(tile);
            }
        }*/
    }
}
