package com.roman.GUI;

import com.roman.GUI.MainPanel.MainPanel;
import com.roman.Metro.Metrosystems.MetroSystem;
import com.roman.Metro.Metrosystems.SaintPetersburg;
import com.roman.Metro.Metrosystems.TestSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppSettings extends JPanel {
    private static AppSettings Singleton;
    private PanelManager panelManager;
    private MetroSystem[] metroSystems = {new SaintPetersburg(), new TestSystem()};
    private JComboBox<MetroSystem> metroSystemsComboBox;
    private MetroSystem currentSystem;

    private AppSettings(PanelManager manager) {
        panelManager = manager;
        metroSystemsComboBox = new JComboBox<>(metroSystems);
        metroSystemsComboBox.addActionListener(new BoxListener());
        currentSystem = (MetroSystem) metroSystemsComboBox.getSelectedItem();
        this.add(metroSystemsComboBox);
        buttonsSetup();
    }

    private void buttonsSetup() {
        JButton creditsButton = new JButton("Справка");
        creditsButton.addActionListener(new CreditsButtonListener());
        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(new SaveButtonListener());
        JButton exitButton = new JButton("Назад");
        exitButton.addActionListener(new ExitButtonListener());
        this.add(saveButton);
        this.add(exitButton);
        this.add(creditsButton);
    }

    public static AppSettings getInstance(PanelManager manager) {
        if (Singleton == null) {
            Singleton = new AppSettings(manager);
        }
        return Singleton;
    }

    public static AppSettings getInstance() {
        return getInstance(null);
    }

    public MetroSystem getCurrentMetro() {
        return currentSystem;
    }

    private class BoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox<MetroSystem> combo = (JComboBox<MetroSystem>) e.getSource();
            currentSystem = (MetroSystem) combo.getSelectedItem();
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentSystem = (MetroSystem) metroSystemsComboBox.getSelectedItem();
            MainPanel.getInstance().setMetro(currentSystem);
        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panelManager.setPanel(PanelManager.MAIN_PANEL_STRING);
        }
    }

    private class CreditsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Я люблю Танюшу!!!");
        }
    }
}
