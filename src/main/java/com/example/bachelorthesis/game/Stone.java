package com.example.bachelorthesis.game;

import javafx.scene.paint.Color;

public class Stone {

    private Color[] stoneColor;
    private Rotation rotation;

    public Stone(Color color1, Color color2){
        stoneColor = new Color[] {color1, color2};
    }

    public Color[] getColors(){
        return stoneColor;
    }

    public void rotate(){
        if(rotation == Rotation.FIVE_CLOCKWISE){
            rotation = Rotation.NONE;
        } else {
            rotation = Rotation.values()[rotation.ordinal() + 1];
        }
    }

}
