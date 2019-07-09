package com.roman.GUI.MainPanel;

import com.roman.Metro.MetroStation;
import com.roman.Metro.Metrosystems.MetroSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JPanel {
    private static Settings Singleton;
    private MetroSystem Metro;
    private JButton ZoomIn;
    private JButton ZoomOut;
    private Graphics g;
    private float Scale = 1;

    private Settings(MetroSystem m){
        Metro = m;
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(300, 600));
        g = Graphics.getInstance();

        ZoomIn = new JButton("+");
        ZoomIn.addActionListener(new ZoomInListener());
        ZoomOut = new JButton("-");
        ZoomOut.addActionListener(new ZoomOutListener());

        add(new JLabel("Zoom"));
        add(ZoomIn);
        add(ZoomOut);
    }

    public static Settings getInstance(MetroSystem m){
        if(Singleton ==  null) Singleton = new Settings(m);
        return Singleton;
    }

    private class ZoomInListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Scale!=2){
                Scale*=2;
                MetroStation.rescale(Scale);
                g.repaint();
            }
        }
    }

    private class ZoomOutListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Scale!=0.5){
                Scale*=0.5;
                MetroStation.rescale(Scale);
                g.repaint();
            }
        }
    }
}
