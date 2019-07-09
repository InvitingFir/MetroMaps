package com.roman.Metro;

import javax.swing.*;
import java.awt.*;

public class MetroStation{
    private static int Position = 0;
    private int StationID;
    private String StationName;
    private Line line;
    private Location location;
    private Font f;
    private JLabel StationNameLabel;
    private static final int CENTER_X = 250;
    private static final int CENTER_Y = 300;

    public MetroStation(String StationName, int X, int Y, Line myLine){
        StationID = Position;
        Position++;
        this.StationName = StationName;
        location = new Location(X, Y);
        this.line = myLine;
        f = new Font("FreeSet", Font.PLAIN, 10);
        StationNameLabel = new JLabel(StationName);
        StationNameLabel.setFont(f);
        StationNameLabel.setForeground(Color.LIGHT_GRAY);
    }

    private String getStationName(){return this.StationName;}
    private int getX(){return CENTER_X+location.getX();}
    public int getPosition(){ return StationID;}
    private int getY(){return CENTER_Y+location.getY();}
    public Location getLocation(){return location;}
    public Color getColor(){return line.getLineColor();}

    public void drawStation(Graphics g, int Radius){
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString(StationName, getX()+Radius, getY());
        g.setColor(line.getLineColor());
        g.fillOval(getX(), getY(), Radius, Radius);
    }

    public void drawStation(Graphics g, MetroStation point, int Radius, int startAngle, int endAngle){
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString(point.getStationName(), getX()+Radius, getY());
        g.setColor(line.getLineColor());
        g.fillArc(point.getX(), point.getY(), Radius, Radius, startAngle, endAngle);
    }

    public void drawLine(Graphics g, MetroStation m, int Radius){
        int delta = Radius/2;
        int x1 = this.getX()+delta;
        int x2 = m.getX()+delta;
        int y1 = this.getY()+delta;
        int y2 = m.getY()+delta;
        if(x1 == x2 && y1 == y2) return;

        g.setColor(m.getColor());
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(x1, y1, x2, y2);
    }
}
