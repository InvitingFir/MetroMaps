package com.roman.GUI;

import javax.swing.*;

public class MainFrame extends JFrame{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public MainFrame(String Title){
        //Установка начальных значений
        super(Title);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.getContentPane().add(PanelManager.getInstance());
        this.setVisible(true);
    }
}
