package com.roman.GUI.MainPanel;

import com.roman.GUI.AppSettings;
import com.roman.GUI.PanelManager;
import com.roman.Metro.Metrosystems.MetroSystem;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private static MainPanel singleton;
    private MetroSystem currentMetro;
    private PanelManager panelManager;
    private Graphics graphics;
    private Settings settings;

    private MainPanel(PanelManager manager) {
        panelManager = manager;
        currentMetro = AppSettings.getInstance().getCurrentMetro();
        graphics = Graphics.getInstance(currentMetro);
        settings = Settings.getInstance(currentMetro);
        this.setLayout(new BorderLayout());
        this.add(graphics, BorderLayout.CENTER);
        this.add(settings, BorderLayout.EAST);
    }

    //Todo
    public static MainPanel getInstance(PanelManager manager) {
        if (singleton == null) singleton = new MainPanel(manager);
        return singleton;
    }

    public static MainPanel getInstance() {
        return singleton;
    }

    //сменить карту метро
    public void setMetro(MetroSystem m) {
        if (!currentMetro.equals(m)) {
            currentMetro = m;
            settings.setMetro(currentMetro);
            graphics.setMetro(currentMetro);
            currentMetro.pathClear();
        }
    }

    //сменить основную панель
    public void setPanel() {
        panelManager.setPanel(PanelManager.APP_SETTINGS_STRING);
    }
}
