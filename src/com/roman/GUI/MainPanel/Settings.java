package com.roman.GUI.MainPanel;

import com.roman.GUI.MainFrame;
import com.roman.Metro.MetroStation;
import com.roman.Metro.Metrosystems.MetroSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;


public class Settings extends JPanel {
    public static final int WIDTH = MainFrame.WIDTH-500;
    private static Settings Singleton;
    private MetroSystem Metro;
    private Graphics g;
    private float Scale = 1;
    private MetroStation start, finish;
    private JLabel PathLength;
    private String length = "Длина пути: null";


    private Settings(MetroSystem m){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Metro = m;
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(WIDTH, MainFrame.HEIGHT));
        PathLength = new JLabel(length);
        g = Graphics.getInstance();

        add(ZoomInit());
        add(PathPanelInit());
        add(PathLength);
    }

    private JPanel PathPanelInit(){
        MetroStation[] stations = Metro.getStations();
        Arrays.sort(stations, new Comparator<>() {
            @Override
            public int compare(MetroStation o1, MetroStation o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        JComboBox<MetroStation> StartBox = new JComboBox<>(stations);
        StartBox.setPreferredSize(new Dimension(WIDTH-40, 20));
        StartBox.addActionListener(new StartComboListener());
        StartBox.setEditable(true);
        start = (MetroStation)StartBox.getSelectedItem();

        JComboBox<MetroStation> FinishBox = new JComboBox<>(stations);
        FinishBox.setPreferredSize(new Dimension(WIDTH-40, 20));
        FinishBox.addActionListener(new FinishComboListener());
        FinishBox.setEditable(true);
        finish = (MetroStation)FinishBox.getSelectedItem();


        JButton CalculateButton = new JButton("Расчитать");
        CalculateButton.addActionListener(new CalculateListener());

        JButton CancelButton = new JButton("x");
        CancelButton.addActionListener(new CancelListener());

        JPanel PathPanel = new JPanel();
        PathPanel.setBackground(Color.LIGHT_GRAY);
        PathPanel.add(new JLabel("Из:"));
        PathPanel.add(StartBox);
        PathPanel.add(new JLabel("В:"));
        PathPanel.add(FinishBox);
        PathPanel.add(CalculateButton);
        PathPanel.add(CancelButton);
        return PathPanel;
    }

    private JPanel ZoomInit(){
        JPanel ZoomPanel = new JPanel();
        JButton ZoomIn = new JButton("+");
        ZoomIn.addActionListener(new ZoomInListener());
        JButton ZoomOut = new JButton("-");
        ZoomOut.addActionListener(new ZoomOutListener());
        ZoomPanel.setBackground(Color.LIGHT_GRAY);
        ZoomPanel.add(new JLabel("Zoom"));
        ZoomPanel.add(ZoomIn);
        ZoomPanel.add(ZoomOut);
        return ZoomPanel;
    }

    public static Settings getInstance(MetroSystem m){
        if(Singleton ==  null) Singleton = new Settings(m);
        return Singleton;
    }

    private class ZoomInListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(Scale!=2){
                Scale*=2;
                MetroStation.rescale(Scale);
                g.repaint();
            }
        }
    }

    private class ZoomOutListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(Scale!=0.5){
                Scale*=0.5;
                MetroStation.rescale(Scale);
                g.repaint();
            }
        }
    }

    private class StartComboListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JComboBox<MetroStation> combo = (JComboBox<MetroStation>) e.getSource();
            start = (MetroStation) combo.getSelectedItem();
        }
    }
    private class FinishComboListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JComboBox<MetroStation> combo = (JComboBox<MetroStation>) e.getSource();
            finish = (MetroStation) combo.getSelectedItem();
        }
    }

    private class CalculateListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(!start.equals(finish)) {
                int temp = Metro.CalculatePath(start, finish);
                length = String.format("Длина пути: %d", temp);
                PathLength.setText(length);
            }
            g.repaint();
        }
    }

    private class CancelListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Metro.pathClear();
            g.repaint();
        }
    }
}
