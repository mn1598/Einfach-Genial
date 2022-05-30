package com.example.bachelorthesis.game;

public class Stone {

    private Color[] stoneColor;

    public Stone(Color color1, Color color2){
        stoneColor = new Color[] {color1, color2};
    }

    public Color[] getColors(){
        return stoneColor;
    }

}
