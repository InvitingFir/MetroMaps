package com.roman.GUI;

import com.roman.GUI.MainPanel.MainPanel;
import com.roman.Metro.Metrosystems.MetroSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppSettings extends JPanel {
    private static AppSettings Singleton;
    private PanelManager panelManager;
    private MainPanel mainPanel;
    private MetroSystem [] MetroSystems;
    private JComboBox<MetroSystem> MetroSysytemsBox;
    private MetroSystem CurrentSystem;
    private JButton Credits;
    private JButton Save;
    private JButton Exit;

    private AppSettings(PanelManager manager){
        panelManager = manager;
        mainPanel = MainPanel.getInstance();
        MetroSystems = mainPanel.getMetroSystems();
        MetroSysytemsBox = new JComboBox<>(MetroSystems);
        MetroSysytemsBox.addActionListener(new BoxListener());
        CurrentSystem = (MetroSystem) MetroSysytemsBox.getSelectedItem();
        Credits = new JButton("Справка");
        Credits.addActionListener(new CreditsButtonListener());
        Save = new JButton("Сохранить");
        Save.addActionListener(new SaveButtonListener());
        Exit = new JButton("Назад");
        Exit.addActionListener(new ExitButtonListener());
        this.add(MetroSysytemsBox);
        this.add(Save);
        this.add(Exit);
        this.add(Credits);
    }

    public static AppSettings getInstance(PanelManager manager){
        if(Singleton == null) Singleton = new AppSettings(manager);
        return Singleton;
    }

    private class BoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox<MetroSystem> combo = (JComboBox<MetroSystem>) e.getSource();
            CurrentSystem = (MetroSystem) combo.getSelectedItem();
        }
    }

    private class SaveButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) { mainPanel.setMetro(CurrentSystem); }
    }

    private class ExitButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) { panelManager.setPanel(PanelManager.MAINPANELSTRING); }
    }

    private class CreditsButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Автор программы Низов Роман");
        }
    }
}
