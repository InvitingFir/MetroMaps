package com.roman.Metro;

import java.awt.*;

public class MetroStation{
    private static int Position = 0;
    private int StationID;
    private String StationName;
    private Line line;
    private Location location;
    private Font f;
    private static int CENTER_X = 250;
    private static int CENTER_Y = 300;
    private static final int STATIONRAD = 20;
    private static float Scale = 1;
    public MetroStation(String StationName, int X, int Y, Line myLine){
        StationID = Position;
        Position++;
        this.StationName = StationName;
        location = new Location(X, Y);
        this.line = myLine;
        f = new Font("FreeSet", Font.PLAIN, 14);
    }

    public static void setCenterX(int X){CENTER_X = X;}
    public static int getCenterX(){return CENTER_X;}
    public static void setCenterY(int Y){CENTER_Y = Y;}
    public static int getCenterY(){return CENTER_Y;}
    private String getStationName(){return this.StationName;}
    private int getX(){return location.getX();}
    public int getPosition(){ return StationID;}
    private int getY(){return location.getY();}
    public Location getLocation(){return location;}
    public Color getColor(){return line.getLineColor();}

    public static void rescale(float S){Scale = S;}

    public void drawStation(Graphics g){
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString(StationName, CENTER_X + Math.round((getX()+STATIONRAD)*Scale), CENTER_Y + Math.round(getY()*Scale));
        g.setColor(line.getLineColor());
        int temp = Math.round(STATIONRAD*Scale);
        g.fillOval(CENTER_X + Math.round(getX()*Scale), CENTER_Y + Math.round(getY()*Scale), temp, temp);
    }

    public void drawStation(Graphics g, MetroStation point, int startAngle, int endAngle){
        int radius = Math.round(STATIONRAD*Scale);
        int X = CENTER_X + Math.round(point.getX()*Scale);
        int Y = CENTER_Y + Math.round(point.getY()*Scale);
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString(point.getStationName(), CENTER_X + Math.round((getX()+STATIONRAD)*Scale), CENTER_Y + Math.round(getY()*Scale));
        g.setColor(line.getLineColor());
        g.fillArc(X, Y, radius, radius, startAngle, endAngle);
    }

    public void drawLine(Graphics g, MetroStation m){
        int delta = Math.round(STATIONRAD*Scale/2);
        int x1 = CENTER_X + Math.round(this.getX()*Scale)+delta;
        int x2 = CENTER_X + Math.round(m.getX()*Scale)+delta;
        int y1 = CENTER_Y + Math.round(this.getY()*Scale)+delta;
        int y2 = CENTER_Y + Math.round(m.getY()*Scale)+delta;
        if(x1 == x2 && y1 == y2) return;

        g.setColor(m.getColor());
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(x1, y1, x2, y2);
    }
}
