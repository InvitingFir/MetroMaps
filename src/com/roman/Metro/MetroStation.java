package com.roman.Metro;

import java.awt.*;

public class MetroStation{
    private static int Position = 0;
    private int StationID;
    private String StationName;
    private Line line;
    private Location location;
    private static Font f;
    private static int FontSize = 11;
    private static int CENTER_X = com.roman.GUI.MainPanel.Graphics.WIDTH/2;
    private static int CENTER_Y = com.roman.GUI.MainPanel.Graphics.HEIGHT/2;
    private static final int STATIONRAD = 20;
    private static float Scale = 1;


    public MetroStation(String StationName, int X, int Y, Line myLine){
        StationID = Position;
        Position++;
        this.StationName = StationName;
        location = new Location(X, Y);
        this.line = myLine;
        f = new Font("FreeSet", Font.PLAIN, (int)(FontSize/1.3));
    }

    public static void nullPosition(){Position = 0;}
    public static void setCenterX(int X){CENTER_X = X;}
    public static void setCenterY(int Y){CENTER_Y = Y;}
    private int getX(){return location.getX();}
    public int getPosition(){ return StationID;}
    private int getY(){return location.getY();}
    public Location getLocation(){return location;}
    public Color getColor(){return line.getLineColor();}
    public static void rescale(float S){
        Scale = S;
        f = new Font("FreeSet", Font.PLAIN, (int)(FontSize*S/1.3));
    }

    @Override
    public String toString() { return StationName; }

    public void drawStation(Graphics g, MetroStation point, int startAngle, int endAngle){
        int radius = Math.round(STATIONRAD*Scale);
        int X = CENTER_X + Math.round(point.getX()*Scale);
        int Y = CENTER_Y + Math.round(point.getY()*Scale);
        g.setColor(line.getLineColor());
        g.fillArc(X, Y, radius, radius, startAngle, endAngle);
        if(!Line.isChosen()){
            g.setColor(Color.BLACK);
            g.setFont(f);
            int StringX = (int)(X+STATIONRAD/2*Scale + Scale*STATIONRAD*Math.cos(2*Math.PI - Math.toRadians(startAngle+endAngle/2)));
            int StringY = (int)(Y+STATIONRAD/2*Scale + Scale*STATIONRAD*Math.sin(2*Math.PI - Math.toRadians(startAngle+endAngle/2)));
            g.drawString(StationName, StringX, StringY);
        }
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
