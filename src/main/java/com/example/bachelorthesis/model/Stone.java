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

    private Color replaceColor(ColorEnum color) {
        return switch (color) {
            case RED -> Color.RED;
            case GREEN -> Color.GREEN;
            case BLUE -> Color.BLUE;
            case ORANGE -> Color.ORANGE;
            case YELLOW -> Color.YELLOW;
            case PURPLE -> Color.PURPLE;
            default -> null;
        };
    }

    public Color[] getColors() {
        return new Color[]{replaceColor(stoneColor[0]), replaceColor(stoneColor[1])};
    }

    public String toString() {
        return stoneColor[0] + ", " + stoneColor[1];
    }
}
