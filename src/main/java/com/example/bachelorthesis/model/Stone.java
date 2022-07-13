package com.example.bachelorthesis.model;

import javafx.scene.paint.Color;

public class Stone {

    public ColorEnum[] stoneColor;

    public Stone(Color color1, Color color2) {
        ColorEnum c1 = replaceColor(color1);
        ColorEnum c2 = replaceColor(color2);
        stoneColor = new ColorEnum[]{c1, c2};
    }

    private ColorEnum replaceColor(Color color) {
        if (color.equals(Color.RED)) {
            return ColorEnum.RED;
        }
        if (color.equals(Color.GREEN)) {
            return ColorEnum.GREEN;
        }
        if (color.equals(Color.BLUE)) {
            return ColorEnum.BLUE;
        }
        if (color.equals(Color.ORANGE)) {
            return ColorEnum.ORANGE;
        }
        if (color.equals(Color.YELLOW)) {
            return ColorEnum.YELLOW;
        }
        if (color.equals(Color.PURPLE)) {
            return ColorEnum.PURPLE;
        }
        return null;
    }

    public String toString() {
        return "Stone: " + stoneColor[0] + ", " + stoneColor[1];
    }
}
