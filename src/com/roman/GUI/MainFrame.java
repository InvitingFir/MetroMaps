package com.roman.GUI;

import javax.swing.*;

public class MainFrame extends JFrame{
    public MainFrame(){
        //Установка начальных значений
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.getContentPane().add(PanelManager.getInstance());

        this.setVisible(true);
    }
}
