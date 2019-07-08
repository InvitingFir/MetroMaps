package com.roman.GUI.MainPanel;

import com.roman.Metro.Metrosystems.MetroSystem;

import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {
    private static Settings Singleton;
    private MetroSystem Metro;

    private Settings(MetroSystem m){
        Metro = m;
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(300, 600));
    }

    public static Settings getInstance(MetroSystem m){
        if(Singleton ==  null) Singleton = new Settings(m);
        return Singleton;
    }
}
