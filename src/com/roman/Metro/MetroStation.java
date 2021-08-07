package com.roman.Metro;

import java.awt.*;

public class MetroStation {
    private static int CENTER_X = com.roman.GUI.MainPanel.Graphics.WIDTH / 2;
    private static int CENTER_Y = com.roman.GUI.MainPanel.Graphics.HEIGHT / 2;
    private static final int FONT_SIZE = 11;
    private static final int STATION_RAD = 20;
    private static int position = 0;
    private int id;
    private String name;
    private Line line;
    private Location location;
    private static Font f;

    private static float scale = 1;

    public MetroStation(String stationName, int X, int Y, Line myLine) {
        id = position;
        position++;
        this.name = stationName;
        location = new Location(X, Y);
        this.line = myLine;
        f = new Font("FreeSet", Font.PLAIN, (int) (FONT_SIZE / 1.3));
    }

    public static void nullPosition() {
        position = 0;
    }

    public static void setCenterX(int X) {
        CENTER_X = X;
    }

    public static void setCenterY(int Y) {
        CENTER_Y = Y;
    }

    private int getX() {
        return location.getX();
    }

    public int getPosition() {
        return id;
    }

    private int getY() {
        return location.getY();
    }

    public Location getLocation() {
        return location;
    }

    public Color getColor() {
        return line.getLineColor();
    }

    public static void rescale(float scale) {
        MetroStation.scale = scale;
        f = new Font("FreeSet", Font.PLAIN, (int) (FONT_SIZE * scale / 1.3));
    }

    @Override
    public String toString() {
        return name;
    }

    public void drawStation(Graphics g, MetroStation point, int startAngle, int endAngle) {
        int radius = Math.round(STATION_RAD * scale);
        int x = CENTER_X + Math.round(point.getX() * scale);
        int y = CENTER_Y + Math.round(point.getY() * scale);
        g.setColor(line.getLineColor());
        g.fillArc(x, y, radius, radius, startAngle, endAngle);
        if (!Line.isChosen()) {
            g.setColor(Color.BLACK);
            g.setFont(f);
            double a = 2 * Math.PI - Math.toRadians(startAngle + (double) (endAngle / 2));
            int StringX = (int) (x + STATION_RAD / 2 * scale + scale * STATION_RAD * Math.cos(a));
            int StringY = (int) (y + STATION_RAD / 2 * scale + scale * STATION_RAD * Math.sin(a));
            g.drawString(name, StringX, StringY);
        }
    }

    public void drawLine(Graphics g, MetroStation m) {
        int delta = Math.round(STATION_RAD * scale / 2);
        int x1 = CENTER_X + Math.round(this.getX() * scale) + delta;
        int x2 = CENTER_X + Math.round(m.getX() * scale) + delta;
        int y1 = CENTER_Y + Math.round(this.getY() * scale) + delta;
        int y2 = CENTER_Y + Math.round(m.getY() * scale) + delta;
        if (x1 == x2 && y1 == y2) {
            return;
        }

        g.setColor(m.getColor());
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(x1, y1, x2, y2);
    }
}
