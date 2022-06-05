package com.example.bachelorthesis.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;

public class CentrePane extends GridPane {

    public CentrePane() {
        Polygon[][] rows = new Polygon[][]{
                {new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon()},
                {new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon()},
                {new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon()},
                {new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon()},
                {new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon()},
                {new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon()},
                {new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon()},
                {new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon()},
                {new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon()},
                {new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon()},
                {new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon(), new Polygon()}
        };
        int[] offset = new int[11];
        /*
        0 = 10
        1 = 9
        2 = 8
        3 = 7
        4 = 6
        5 = 5
         */
        for (int i = 0; i < 6; i++) {
            offset[i] = offset.length - i - 6;
            offset[11 - i - 1] = offset[i];
        }

//        for(int i = 0; i < 6; i++){
//            rows[0][i].getPoints().addAll(new Double[]{
//                    40.0 + i*10 + offset[0] * 20, 0.0,
//                    80.0 + i*10 + offset[0] * 20, 20.0,
//                    80.0 + i*10 + offset[0] * 20, 70.0,
//                    40.0 + i*10 + offset[0] * 20, 90.0,
//                    0.0 + i*10 + offset[0] * 20, 70.0,
//                    0.0 + i*10 + offset[0] * 20, 20.0,
//            });
//            rows[0][i].setStroke(Color.BLACK);
//            rows[0][i].setFill(Color.WHITE);
//        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                rows[i][j].getPoints().addAll(new Double[]{
                        40.0 + j * 10, 0.0,
                        80.0 + j * 10, 20.0,
                        80.0 + j * 10, 70.0,
                        40.0 + j * 10, 90.0,
                        0.0 + j * 10, 70.0,
                        0.0 + j * 10, 20.0,
                });
                rows[i][j].setTranslateX(offset[i] * 40);
                rows[i][j].setStroke(Color.BLACK);
                rows[i][j].setFill(Color.WHITE);
            }
            this.addRow(i, rows[i]);
        }
        this.setGridLinesVisible(false);
        this.setVgap(-20);
    }
}
