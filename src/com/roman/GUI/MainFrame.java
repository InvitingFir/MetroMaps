package com.roman.GUI;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public MainFrame(String title) {
        super(title);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.getContentPane().add(PanelManager.getInstance());
        this.setVisible(true);
    }
}
