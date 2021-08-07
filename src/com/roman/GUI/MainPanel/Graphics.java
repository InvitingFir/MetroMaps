package com.roman.GUI.MainPanel;


import com.roman.GUI.MainFrame;
import com.roman.Metro.MetroStation;
import com.roman.Metro.Metrosystems.MetroSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Graphics extends JPanel {
    public static final int HEIGHT = MainFrame.HEIGHT - 70;
    public static final int WIDTH = MainFrame.WIDTH - Settings.WIDTH - 50;
    private JScrollBar horizontalScroll;
    private JScrollBar verticalScroll;
    private static Graphics singleton;
    private MetroSystem metro;

    private Graphics(MetroSystem m) {
        metro = m;
        horizontalScroll = new JScrollBar(JScrollBar.HORIZONTAL, WIDTH / 2, 1, 0, WIDTH);
        horizontalScroll.addAdjustmentListener(new HorizontalListener());
        verticalScroll = new JScrollBar(JScrollBar.VERTICAL, HEIGHT / 2, 1, 0, HEIGHT);
        verticalScroll.addAdjustmentListener(new VerticalListener());

        this.setLayout(new BorderLayout());
        this.add(horizontalScroll, BorderLayout.SOUTH);
        this.add(verticalScroll, BorderLayout.EAST);
    }

    //TODO
    public static Graphics getInstance(MetroSystem m) {
        if (singleton == null) singleton = new Graphics(m);
        return singleton;
    }

    public static Graphics getInstance() {
        return singleton;
    }

    //Метод для смены на другую карту метро. Вызывается из MainPanel
    public void setMetro(MetroSystem m) {
        metro = m;
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        metro.graphicInit(g);
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
