package com.roman.GUI.MainPanel;


import com.roman.GUI.MainFrame;
import com.roman.Metro.MetroStation;
import com.roman.Metro.Metrosystems.MetroSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Graphics extends JPanel {
    public static final int HEIGHT = MainFrame.HEIGHT-70;
    public static final int WIDTH = MainFrame.WIDTH-Settings.WIDTH-50;
    private JScrollBar HorizontalScroll;
    private JScrollBar VerticalScroll;
    private static Graphics Singleton;
    private MetroSystem Metro;

    private Graphics(MetroSystem m){
        Metro = m;
        HorizontalScroll = new JScrollBar(JScrollBar.HORIZONTAL, WIDTH/2, 1, 0, WIDTH);
        HorizontalScroll.addAdjustmentListener(new HorizontalListener());
        VerticalScroll = new JScrollBar(JScrollBar.VERTICAL, HEIGHT/2, 1, 0, HEIGHT);
        VerticalScroll.addAdjustmentListener(new VerticalListener());

        this.setLayout(new BorderLayout());
        this.add(HorizontalScroll, BorderLayout.SOUTH);
        this.add(VerticalScroll, BorderLayout.EAST);
    }

    public static Graphics getInstance(MetroSystem m){
        if(Singleton ==  null) Singleton = new Graphics(m);
        return Singleton;
    }

    public static Graphics getInstance(){ return Singleton; }

    //Метод для смены на другую карту метро. Вызывается из MainPanel
    public void setMetro(MetroSystem m){ Metro = m;}

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Metro.graphicInit(g);
    }

    private class HorizontalListener implements AdjustmentListener{
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            JScrollBar scroll = (JScrollBar) e.getSource();
            MetroStation.setCenterX(scroll.getValue());
            repaint();
        }
    }

    private class VerticalListener implements AdjustmentListener{
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            JScrollBar scroll = (JScrollBar) e.getSource();
            MetroStation.setCenterY(scroll.getValue());
            repaint();
        }
    }
}
