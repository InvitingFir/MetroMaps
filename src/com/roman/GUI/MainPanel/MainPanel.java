package com.roman.GUI.MainPanel;

import com.roman.Metro.Metrosystems.MetroSystem;
import com.roman.Metro.Metrosystems.TestSystem;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private static MainPanel Singleton;
    private MetroSystem Metro;

    private MainPanel(){
        Metro = new TestSystem("com/roman/Resource/TestMetro.stat", "com/roman/Resource/TestMetro.con");
        this.setLayout(new BorderLayout());
        this.add(Graphics.getInstance(Metro), BorderLayout.CENTER);
        this.add(Settings.getInstance(Metro), BorderLayout.EAST);
    }

    public static MainPanel getInstance(){
        if(Singleton == null) Singleton = new MainPanel();
        return Singleton;
    }
}
