package com.roman.GUI;

import com.roman.GUI.MainPanel.MainPanel;
import javax.swing.*;
import java.awt.*;

public class PanelManager extends JPanel {
    private static PanelManager Singleton;
    private CardLayout Carder;

    public static final String mainPanelString = "MainPanel";

    private PanelManager(){
        this.setLayout(Carder = new CardLayout());
        this.add(MainPanel.getInstance(), mainPanelString);
        Carder.show(this, mainPanelString);
    }

    public static PanelManager getInstance(){
        if(Singleton == null) Singleton = new PanelManager();
        return Singleton;
    }
}
