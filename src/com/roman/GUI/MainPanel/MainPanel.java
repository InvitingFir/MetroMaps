package com.roman.GUI.MainPanel;

import com.roman.GUI.PanelManager;
import com.roman.Metro.Metrosystems.MetroSystem;
import com.roman.Metro.Metrosystems.SaintPetersburg;
import com.roman.Metro.Metrosystems.TestSystem;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private static MainPanel Singleton;
    private MetroSystem [] Metro = {new SaintPetersburg(), new TestSystem()};
    private MetroSystem CurrentMetro = Metro[0];
    private PanelManager panelManager;
    private Graphics graphics;
    private Settings settings;

    private MainPanel(PanelManager manager){
        panelManager = manager;
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

    //получить массив ссылок на загруженные карты метро
    public MetroSystem [] getMetroSystems(){ return Metro; }

    //сменить карту метро
    public void setMetro(MetroSystem m){
        if(!CurrentMetro.equals(m)){
            CurrentMetro = m;
            settings.setMetro(CurrentMetro);
            graphics.setMetro(CurrentMetro);
        }
    }

    //сменить основную панель
    public void setPanel(){ panelManager.setPanel(PanelManager.APPSETTINGSSTRING); }
}
