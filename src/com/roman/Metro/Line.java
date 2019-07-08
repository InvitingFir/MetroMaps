package com.roman.Metro;

import java.awt.*;

public class Line {
    private Color lineColor;

    public Line(Color LineColor){
        this.lineColor = LineColor;
    }

    public Color getLineColor() { return lineColor; }

    public void setLineColor(Color lineColor) { this.lineColor = lineColor; }
}
