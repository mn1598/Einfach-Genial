package com.example.bachelorthesis.model;

import javafx.scene.paint.Color;

public class Stone {

    private Color[] stoneColor;
    private Rotation rotation;

    public Stone(Color color1, Color color2) {
        stoneColor = new Color[]{color1, color2};
        rotation = Rotation.NONE;
    }

    public Color[] getColors() {
        return stoneColor;
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

    public boolean containsColor(Color color){
        if(stoneColor[0].equals(color) || stoneColor[1].equals(color)){
            return true;
        }
        return false;
    }

}
