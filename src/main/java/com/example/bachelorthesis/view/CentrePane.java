package com.example.bachelorthesis.view;

import com.example.bachelorthesis.model.GameBoard;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class CentrePane extends GridPane {

    private Polygon[][] rows;

    public CentrePane() {
        rows = new Polygon[][]{
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

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                rows[i][j].getPoints().addAll(40.0 + j * 10, 0.0,
                        80.0 + j * 10, 20.0,
                        80.0 + j * 10, 70.0,
                        40.0 + j * 10, 90.0,
                        0.0 + j * 10, 70.0,
                        0.0 + j * 10, 20.0);
                rows[i][j].setTranslateX(offset[i] * 40);
                rows[i][j].setStroke(Color.BLACK);
                rows[i][j].setStrokeWidth(2.0);
                rows[i][j].setFill(Color.WHITE);
            }
            this.addRow(i, rows[i]);
        }
        this.setGridLinesVisible(false);
        this.setVgap(-22);
        this.initGameBoard();
    }

    public void initGameBoard() {
        for (Polygon[] row : rows) {
            for (Polygon polygon : row) {
                polygon.setFill(Color.WHITE);
            }
        }
        rows[0][0].setFill(Color.RED);
        rows[0][5].setFill(Color.GREEN);
        rows[5][0].setFill(Color.PURPLE);
        rows[5][10].setFill(Color.BLUE);
        rows[10][0].setFill(Color.YELLOW);
        rows[10][5].setFill(Color.ORANGE);
    }

    public void updateBoard(GameBoard board) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                switch (board.representation[i][j]) {
                    case RED -> rows[i][j].setFill(Color.RED);
                    case GREEN -> rows[i][j].setFill(Color.GREEN);
                    case BLUE -> rows[i][j].setFill(Color.BLUE);
                    case ORANGE -> rows[i][j].setFill(Color.ORANGE);
                    case YELLOW -> rows[i][j].setFill(Color.YELLOW);
                    case PURPLE -> rows[i][j].setFill(Color.PURPLE);
                }
            }
        }
    }
}
