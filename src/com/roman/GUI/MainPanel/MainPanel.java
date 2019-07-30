package com.roman.GUI.MainPanel;

import com.roman.Metro.Metrosystems.MetroSystem;
import com.roman.Metro.Metrosystems.SaintPetersburg;
import com.roman.Metro.Metrosystems.TestSystem;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private static MainPanel Singleton;
    private MetroSystem Metro;

    private MainPanel(){
        Metro = new SaintPetersburg();
        this.setLayout(new BorderLayout());
        this.add(Graphics.getInstance(Metro), BorderLayout.CENTER);
        this.add(Settings.getInstance(Metro), BorderLayout.EAST);
    }

    public static MainPanel getInstance(){
        if(Singleton == null) Singleton = new MainPanel();
        return Singleton;
    }
}
