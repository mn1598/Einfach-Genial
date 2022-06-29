package com.example.bachelorthesis.model;

import javafx.scene.paint.Color;

public class Stone {

    public ColorEnum[] stoneColor;
    public Rotation rotation;

    public Stone(Color color1, Color color2) {
        ColorEnum c1 = replaceColor(color1);
        ColorEnum c2 = replaceColor(color2);
        stoneColor = new ColorEnum[]{c1, c2};
        rotation = Rotation.NONE;
    }

    private ColorEnum replaceColor(Color color) {
        if(color.equals(Color.RED)){
            return ColorEnum.RED;
        }
        if(color.equals(Color.GREEN)){
            return ColorEnum.GREEN;
        }
        if(color.equals(Color.BLUE)){
            return ColorEnum.BLUE;
        }
        if(color.equals(Color.ORANGE)){
            return ColorEnum.ORANGE;
        }
        if(color.equals(Color.YELLOW)){
            return ColorEnum.YELLOW;
        }
        if(color.equals(Color.PURPLE)){
            return ColorEnum.PURPLE;
        }
        return null;
    }

    private Color replaceColor(ColorEnum color){
        switch (color){
            case RED: return Color.RED;
            case GREEN: return Color.GREEN;
            case BLUE: return Color.BLUE;
            case ORANGE: return Color.ORANGE;
            case YELLOW: return Color.YELLOW;
            case PURPLE: return Color.PURPLE;
        }
        return null;
    }

    public Color[] getColors() {
        return new Color[]{replaceColor(stoneColor[0]), replaceColor(stoneColor[1])};
    }

    public void rotate() {
        if (rotation == Rotation.FIVE_CLOCKWISE) {
            rotation = Rotation.NONE;
        } else {
            rotation = Rotation.values()[rotation.ordinal() + 1];
        }
    }

    public String toString() {
        return stoneColor[0] + ", " + stoneColor[1] + ": " + rotation;
    }

    public boolean containsColor(ColorEnum color){
        if(stoneColor[0].equals(color) || stoneColor[1].equals(color)){
            return true;
        }
        return false;
    }

}
