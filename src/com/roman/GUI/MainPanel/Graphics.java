package com.roman.GUI.MainPanel;


import com.roman.Metro.Metrosystems.MetroSystem;

import javax.swing.*;

public class Graphics extends JPanel {
    private static Graphics Singleton;
    private MetroSystem Metro;

    private Graphics(MetroSystem m){
        Metro = m;
    }


    public static Graphics getInstance(MetroSystem m){
        if(Singleton ==  null) Singleton = new Graphics(m);
        return Singleton;
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Metro.graphicInit(g);
    }
}
