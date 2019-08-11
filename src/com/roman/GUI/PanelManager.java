package com.roman.GUI;

import com.roman.GUI.MainPanel.MainPanel;
import javax.swing.*;
import java.awt.*;

public class PanelManager extends JPanel {
    private static PanelManager Singleton;
    private static CardLayout Carder;

    public static final String MAINPANELSTRING = "MainPanel";
    public static final String APPSETTINGSSTRING = "AppSettings";

    private PanelManager(){
        this.setLayout(Carder = new CardLayout());
        this.add(AppSettings.getInstance(this), APPSETTINGSSTRING);
        this.add(MainPanel.getInstance(this), MAINPANELSTRING);
        Carder.show(this, MAINPANELSTRING);
    }

    public static PanelManager getInstance(){
        if(Singleton == null) Singleton = new PanelManager();
        return Singleton;
    }

    //Сменить панель
    public void setPanel(String s){
        Carder.show(this, s);
        revalidate();
        repaint();
    }
}
