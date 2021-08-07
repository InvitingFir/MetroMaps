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

import static java.awt.Color.LIGHT_GRAY;

public class Settings extends JPanel {
    public static final int WIDTH = MainFrame.WIDTH - 500;
    private static Settings Singleton;
    private MetroSystem metro;
    private Graphics graphics;
    private float scale = 1;
    private MetroStation start, finish;
    private JLabel pathLength;
    private JComboBox<MetroStation> startBox;
    private JComboBox<MetroStation> finishBox;
    private String length = "Длина пути: null";
    private JButton settingsButton;

    private Settings(MetroSystem m) {
        metro = m;
        setBackground(LIGHT_GRAY);
        setPreferredSize(new Dimension(WIDTH, MainFrame.HEIGHT));
        pathLength = new JLabel(length);
        graphics = Graphics.getInstance();
        settingsButton = new JButton("Настройки");
        settingsButton.addActionListener(new SettingsButtonListener());
        startBox = createComboBox(new StartComboListener());
        finishBox = createComboBox(new FinishComboListener());
        add(zoomInit());
        add(pathPanelInit());
        add(PathPanel.getInstance());
    }

    public static Settings getInstance(MetroSystem m) {
        if (Singleton == null) {
            Singleton = new Settings(m);
        }
        return Singleton;
    }

    private JComboBox<MetroStation> createComboBox(ActionListener listener) {
        JComboBox<MetroStation> comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(WIDTH - 40, 20));
        comboBox.addActionListener(listener);
        comboBox.setEditable(false);
        return comboBox;
    }

    public void setMetro(MetroSystem m) {
        metro = m;
        setComboBoxes();
        PathPanel.getInstance().setPath(null, null, null, 0);
    }

    private void setComboBoxes() {
        MetroStation[] stations = sortMetroStations();
        updateComboBoxModel(stations);
        start = (MetroStation) startBox.getSelectedItem();
        finish = (MetroStation) finishBox.getSelectedItem();
        revalidate();
        repaint();
    }

    private void updateComboBoxModel(MetroStation[] stations) {
        finishBox.setModel(new DefaultComboBoxModel<>(stations));
        startBox.setModel(new DefaultComboBoxModel<>(stations));
    }

    private MetroStation[] sortMetroStations() {
        MetroStation[] stations = metro.getStations();
        Arrays.sort(stations, new Comparator<>() {
            @Override
            public int compare(MetroStation o1, MetroStation o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        return stations;
    }

    //Инициализация панели с маршрутом
    private JPanel pathPanelInit() {
        setComboBoxes();
        JButton CalculateButton = new JButton("Расчитать");
        CalculateButton.addActionListener(new CalculateListener());
        JButton CancelButton = new JButton("x");
        CancelButton.addActionListener(new CancelListener());
        JPanel PathPanel = new JPanel();
        PathPanel.setPreferredSize(new Dimension(Settings.WIDTH, MainFrame.HEIGHT / 5));
        PathPanel.setBackground(LIGHT_GRAY);
        PathPanel.add(new JLabel("Из:"));
        PathPanel.add(startBox);
        PathPanel.add(new JLabel("В:"));
        PathPanel.add(finishBox);
        PathPanel.add(CalculateButton);
        PathPanel.add(CancelButton);
        return PathPanel;
    }

    private JPanel zoomInit() {
        JPanel ZoomPanel = new JPanel();
        ZoomPanel.setPreferredSize(new Dimension(Settings.WIDTH, MainFrame.HEIGHT / 6));
        JButton ZoomIn = new JButton("+");
        ZoomIn.addActionListener(new ZoomInListener());
        JButton ZoomOut = new JButton("-");
        ZoomOut.addActionListener(new ZoomOutListener());
        ZoomPanel.setBackground(LIGHT_GRAY);
        ZoomPanel.add(settingsButton);
        ZoomPanel.add(new JLabel("Масштаб: "));
        ZoomPanel.add(ZoomIn);
        ZoomPanel.add(ZoomOut);
        return ZoomPanel;
    }

    private class SettingsButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pathLength.setText("Длина пути: null");
            MainPanel mp = MainPanel.getInstance();
            mp.setPanel();
        }
    }

    private class ZoomInListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (scale != 2) {
                scale *= 2;
                MetroStation.rescale(scale);
                graphics.repaint();
            }
        }
    }

    private class ZoomOutListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (scale != 0.5) {
                scale *= 0.5;
                MetroStation.rescale(scale);
                graphics.repaint();
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
                int temp = metro.CalculatePath(start, finish);
                PathPanel.getInstance().setPath(metro.getPath(), start, finish, temp);
                graphics.repaint();
            }
        }
    }

    private class CancelListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            metro.pathClear();
            PathPanel.getInstance().setPath(null, null, null, 0);
            pathLength.setText("Длина пути: null");
            graphics.repaint();
        }
    }
}
