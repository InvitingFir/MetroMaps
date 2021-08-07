package com.roman.Metro;

import java.awt.*;

public class Line {
    private Color lineColor;
    private String name;
    private static boolean chosen;

    public Line(String name, Color LineColor) {
        this.name = name;
        this.lineColor = LineColor;
    }

    public Color getLineColor() {
        if (chosen) {
            return lineColor.darker().darker();
        } else {
            return lineColor;
        }
    }

    public String getName() {
        return name;
    }

    public static void setChosen(boolean chosen) {
        Line.chosen = chosen;
    }

    public static boolean isChosen() {
        return chosen;
    }
}
