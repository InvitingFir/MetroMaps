package com.roman.GUI.MainPanel;

import com.roman.GUI.MainFrame;
import com.roman.Metro.Change;
import com.roman.Metro.MetroStation;
import java.awt.Graphics;
import com.roman.Util.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Map;


public class PathPanel extends JPanel {
    public final static int HEIGHT = MainFrame.HEIGHT/2;
    private int CenterY = 20;
    private int Y;
    private static PathPanel Singleton;
    private Table<MetroStation, Change> Path;
    private MetroStation Start;
    private MetroStation Finish;
    private JScrollBar scrollBar;
    private int time;

    private PathPanel(){
        this.setPreferredSize(new Dimension(Settings.WIDTH, MainFrame.HEIGHT/2));
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        scrollBar = new JScrollBar(JScrollBar.VERTICAL, 20, 1, 20, 21);
        scrollBar.addAdjustmentListener(new ScrollerListener());
        this.add(scrollBar, BorderLayout.EAST);
    }

    public static PathPanel getInstance(){
        if(Singleton == null) Singleton = new PathPanel();
        return Singleton;
    }

    public void setPath(Table<MetroStation, Change> temp, MetroStation start, MetroStation finish, int time){
        Path = temp;
        Start = start;
        Finish = finish;
        this.time = time;
        scrollBar.setMaximum(21);
        scrollBar.setValue(20);
        repaint();
    }

    private synchronized void drawPathComments(Graphics g){
        MetroStation tempStation = Start;
        MetroStation Last = null;
        g.setColor(Color.BLACK);
        g.setColor(tempStation.getColor());
        while(!tempStation.equals(Finish)){
            g.drawString(tempStation.toString(), 10, Y+CenterY);
            for (Map.Entry<MetroStation, Change> temp: Path.getColumn(tempStation).entrySet()) {
                if(!temp.getKey().equals(Last)){
                    Last = tempStation;
                    Y = drawArrow(g, tempStation, temp.getKey());
                    tempStation = temp.getKey();
                    break;
                }
            }
        }
        g.drawString(Finish.toString(), 10, Y+CenterY);
        if(HEIGHT<Y){
            scrollBar.setMaximum((Y-HEIGHT)+30);
        }
    }

    private int drawArrow(Graphics g, MetroStation start, MetroStation finish){
        int width = 5;
        int height = 10;
        Y+=5;
        g.setColor(start.getColor());
        g.fillRect(10, Y+CenterY, width, height);
        g.setColor(finish.getColor());
        g.fillRect(10, Y+CenterY+height, width, height);
        return Y+=2*height+15;
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        if(Path!=null){
            Y = 30;
            drawPathComments(g);
            g.setFont(new Font("Times New Roman", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString(String.format("Время в пути %d мин.", time), 10, CenterY+Y+20);
        }
        else g.drawString("Здесь отображаются детали маршрута", 0, 10);
    }

    private class ScrollerListener implements AdjustmentListener{
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            JScrollBar temp = (JScrollBar) e.getSource();
            CenterY = -temp.getValue();
            repaint();
        }
    }
}
