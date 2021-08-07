package com.roman.Metro.Metrosystems;

import com.roman.Metro.Line;

import java.awt.*;

public class SaintPetersburg extends MetroSystem {
    private String MetroName = "Метро Санкт-Петербурга";
    private Line Green = new Line("Невско-Василеостровкая", new Color(6, 153, 83));
    private Line Purple = new Line("Фрунзенско-Преморская", new Color(185, 28, 141));
    private Line Blue = new Line("Московско-Петроградская", new Color(2, 85, 163));
    private Line Red = new Line("Кировско-Выборгская", new Color(243, 29, 41));
    private Line Orange = new Line("Правобережская", new Color(248, 170, 62));
    private Line [] Lines = {Green, Purple, Blue, Red, Orange};

    public SaintPetersburg() {
        super("com/roman/Resource/SaintPetersburg.stat", "com/roman/Resource/SaintPetersburg.con", "Метро Санкт-Петербурга");
        routesTable = readStationFile(Lines);
        connections();
    }

    private void connections() {
        //Рыжая ветка
        reader.putLine(61, 68);
        reader.put(64, 6);
        reader.put(62, 52);
        reader.put(61, 33, 18);
        //Красная ветка
        reader.putLine(42, 60);
        reader.put(54, 34);
        reader.put(53, 19);
        reader.put(51, 5);
        //Синяя ветка
        reader.putLine(24, 41);
        reader.put(33, 18);
        reader.put(32, 4);
        //Фиолетовая ветка
        reader.putLine(12, 23);
        reader.putLine(0, 11);
    }
}