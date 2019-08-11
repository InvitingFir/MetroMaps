package com.roman.GUI.MainPanel;

import com.roman.GUI.AppSettings;
import com.roman.GUI.PanelManager;
import com.roman.Metro.Metrosystems.MetroSystem;
import com.roman.Metro.Metrosystems.SaintPetersburg;
import com.roman.Metro.Metrosystems.TestSystem;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private static MainPanel Singleton;
    private MetroSystem CurrentMetro;
    private PanelManager panelManager;
    private Graphics graphics;
    private Settings settings;

    private MainPanel(PanelManager manager){
        panelManager = manager;
        CurrentMetro = AppSettings.getInstance().getCurrentMetro();
        graphics = Graphics.getInstance(CurrentMetro);
        settings = Settings.getInstance(CurrentMetro);
        this.setLayout(new BorderLayout());
        this.add(graphics, BorderLayout.CENTER);
        this.add(settings, BorderLayout.EAST);
    }

    public static MainPanel getInstance(PanelManager manager){
        if(Singleton == null) Singleton = new MainPanel(manager);
        return Singleton;
    }

    public static MainPanel getInstance(){return Singleton;}

    //сменить карту метро
    public void setMetro(MetroSystem m){
        if(!CurrentMetro.equals(m)){
            CurrentMetro = m;
            settings.setMetro(CurrentMetro);
            graphics.setMetro(CurrentMetro);
            CurrentMetro.pathClear();
        }
    }

    //сменить основную панель
    public void setPanel(){ panelManager.setPanel(PanelManager.APPSETTINGSSTRING); }
}
