package com.roman.Metro;

import java.awt.*;

public class Line {
    private Color lineColor;
    private String Name;
    private static boolean Chosen;

    public Line(String name, Color LineColor){
        this.Name = name;
        this.lineColor = LineColor;
    }

    public Color getLineColor() {
        if(Chosen) return lineColor.darker().darker();
        else return lineColor;
    }
    public String getName(){return Name;}

    public static void setChosen(boolean chosen) { Chosen = chosen; }

    public static boolean isChosen() { return Chosen; }
}
