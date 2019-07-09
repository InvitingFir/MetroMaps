package com.roman.GUI.MainPanel;


import com.roman.Metro.MetroStation;
import com.roman.Metro.Metrosystems.MetroSystem;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Graphics extends JPanel {
    private JScrollBar HorizontalScroll;
    private JScrollBar VerticalScroll;
    private static Graphics Singleton;
    private MetroSystem Metro;
    private float ScrollerScale = 1;

    private Graphics(){}

    private Graphics(MetroSystem m){
        Metro = m;
        HorizontalScroll = new JScrollBar(JScrollBar.HORIZONTAL, 250, 1, 0, 450);
        HorizontalScroll.addAdjustmentListener(new HorizontalListener());
        VerticalScroll = new JScrollBar(JScrollBar.VERTICAL, 300, 1, 0, 530);
        VerticalScroll.addAdjustmentListener(new VerticalListener());

        this.setLayout(new BorderLayout());
        this.add(HorizontalScroll, BorderLayout.SOUTH);
        this.add(VerticalScroll, BorderLayout.EAST);
    }


    public static Graphics getInstance(MetroSystem m){
        if(Singleton ==  null) Singleton = new Graphics(m);
        return Singleton;
    }

    public static Graphics getInstance(){
        if(Singleton ==  null) Singleton = new Graphics();
        return Singleton;
    }

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
