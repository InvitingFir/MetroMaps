package com.roman.Metro;

import java.awt.*;
import java.util.Objects;

public class MetroStation{
    private static int Position = 0;
    private int StationID;
    private String StationName;
    private int X;
    private int Y;
    private Line line;
    private Location location;
    private Font f;

    public MetroStation(String StationName, int X, int Y, Line myLine){
        StationID = Position;
        Position++;
        this.StationName = StationName;
        this.X = X;
        this.Y = Y;
        location = new Location(X, Y);
        this.line = myLine;
        f = new Font("FreeSet", Font.PLAIN, 14);
    }

    public String getStationName(){return this.StationName;}
    public int getX(){return location.getX();}
    public int getPosition(){ return StationID;}
    public int getY(){return location.getY();}
    public Location getLocation(){return location;}
    public Color getColor(){return line.getLineColor();}

    public void setX(int X){location.setX(X);}
    public void setY(int Y){location.setY(Y);}
    public void setColor(Color c){line.setLineColor(c);}

    public void draw(Graphics g, int Radius){
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString(StationName, X+Radius, Y);
        g.setColor(line.getLineColor());
        g.fillOval(X, Y, Radius, Radius);
    }

    public void draw(Graphics g, MetroStation point, int Radius, double startAngle, double endAngle){
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString(point.getStationName(), X+Radius, Y);
        g.setColor(line.getLineColor());
        System.out.println((int)startAngle);
        g.fillArc(point.getX(), point.getY(), Radius, Radius, (int)startAngle, (int)endAngle);
    }
}
