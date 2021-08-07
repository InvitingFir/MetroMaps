package com.roman.GUI;

import com.roman.GUI.MainPanel.MainPanel;

import javax.swing.*;
import java.awt.*;

public class PanelManager extends JPanel {

    public static final String MAIN_PANEL_STRING = "MainPanel";
    public static final String APP_SETTINGS_STRING = "AppSettings";

    private static PanelManager Singleton;
    private static CardLayout carder;

    private PanelManager() {
        this.setLayout(carder = new CardLayout());
        this.add(AppSettings.getInstance(this), APP_SETTINGS_STRING);
        this.add(MainPanel.getInstance(this), MAIN_PANEL_STRING);
        carder.show(this, MAIN_PANEL_STRING);
    }

    public static PanelManager getInstance() {
        if (Singleton == null) {
            Singleton = new PanelManager();
        }
        return Singleton;
    }

    public void setPanel(String panelName) {
        carder.show(this, panelName);
        revalidate();
        repaint();
    }
}
