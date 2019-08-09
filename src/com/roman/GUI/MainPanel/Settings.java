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
    private JComboBox<MetroStation> StartBox;
    private JComboBox<MetroStation> FinishBox;
    private String length = "Длина пути: null";
    private JButton SettingsButton;


    private Settings(MetroSystem m){
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Metro = m;
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(WIDTH, MainFrame.HEIGHT));
        PathLength = new JLabel(length);
        g = Graphics.getInstance();
        SettingsButton = new JButton("Настройки");
        SettingsButton.addActionListener(new SettingsButtonListener());

        StartBox = new JComboBox<>();
        StartBox.setPreferredSize(new Dimension(WIDTH-40, 20));
        StartBox.addActionListener(new StartComboListener());
        StartBox.setEditable(false);

        FinishBox = new JComboBox<>();
        FinishBox.setPreferredSize(new Dimension(WIDTH-40, 20));
        FinishBox.addActionListener(new FinishComboListener());
        FinishBox.setEditable(false);

        add(ZoomInit());
        add(PathPanelInit());
        add(PathPanel.getInstance());
    }


    public static Settings getInstance(MetroSystem m){
        if(Singleton ==  null) Singleton = new Settings(m);
        return Singleton;
    }

    //Метод для смены на другую карту метро. Вызывается из MainPanel
    public void setMetro(MetroSystem m){
        Metro = m;
        setComboBoxes();
    }

    //Меняет списки со станциями при смене карты метро
    private void setComboBoxes(){
        MetroStation[] stations = Metro.getStations();
        Arrays.sort(stations, new Comparator<>() {
            @Override
            public int compare(MetroStation o1, MetroStation o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        StartBox.setModel(new DefaultComboBoxModel<>(stations));
        start = (MetroStation)StartBox.getSelectedItem();

        FinishBox.setModel(new DefaultComboBoxModel<>(stations));
        finish = (MetroStation)FinishBox.getSelectedItem();

        revalidate();
        repaint();
    }

    //Инициализация панели с маршрутом
    private JPanel PathPanelInit(){
        setComboBoxes();
        JButton CalculateButton = new JButton("Расчитать");
        CalculateButton.addActionListener(new CalculateListener());
        JButton CancelButton = new JButton("x");
        CancelButton.addActionListener(new CancelListener());
        JPanel PathPanel = new JPanel();
        PathPanel.setPreferredSize(new Dimension(Settings.WIDTH, MainFrame.HEIGHT/5));
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
        ZoomPanel.setPreferredSize(new Dimension(Settings.WIDTH, MainFrame.HEIGHT/6));
        JButton ZoomIn = new JButton("+");
        ZoomIn.addActionListener(new ZoomInListener());
        JButton ZoomOut = new JButton("-");
        ZoomOut.addActionListener(new ZoomOutListener());
        ZoomPanel.setBackground(Color.LIGHT_GRAY);
        ZoomPanel.add(SettingsButton);
        ZoomPanel.add(new JLabel("Масштаб: "));
        ZoomPanel.add(ZoomIn);
        ZoomPanel.add(ZoomOut);
        return ZoomPanel;
    }

    private class SettingsButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            PathLength.setText("Длина пути: null");
            MainPanel mp = MainPanel.getInstance();
            mp.setPanel();
        }
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
                PathPanel.getInstance().setPath(Metro.getPath(), start, finish, temp);
                g.repaint();
            }
        }
    }

    private class CancelListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Metro.pathClear();
            PathPanel.getInstance().setPath(null, null, null, 0);
            PathLength.setText("Длина пути: null");
            g.repaint();
        }
    }
}
