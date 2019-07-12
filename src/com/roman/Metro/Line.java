package com.roman.Metro;

import java.awt.*;

public class Line {
    private Color lineColor;
    private String Name;

    public Line(String name, Color LineColor){
        this.Name = name;
        this.lineColor = LineColor;
    }

    public Color getLineColor() { return lineColor; }
    public String getName(){return Name;}
}
