package com.roman.Metro.Metrosystems;

import com.roman.Metro.Line;

import java.awt.*;

public class SaintPetersburg extends MetroSystem {
    private Line Green = new Line("Невско-Василеостровкая", new Color(6, 153, 83));
    private Line Purple = new Line("Фрунзенско-Преморская", new Color(185, 28, 141));
    private Line Blue = new Line("Московско-Петроградская", new Color(2, 85, 163));
    private Line Red = new Line("Кировско-Выборгская", new Color(243, 29, 41));
    private Line Orange = new Line("Правобережская", new Color(248, 170, 62));
    private Line [] Lines = {Green, Purple, Blue, Red, Orange};

    public SaintPetersburg() {
        super("com/roman/Resource/SaintPetersburg.stat", "com/roman/Resource/SaintPetersburg.con");
        Routes = readStationFile(Lines);
        connections();
    }
    private void connections(){
        //Рыжая ветка
        Reader.putLine(61, 68);
        Reader.put(64, 6);
        Reader.put(62, 52);
        Reader.put(61, 33, 18);

        //Красная ветка
        Reader.putLine(42, 60);
        Reader.put(54, 34);
        Reader.put(53, 19);
        Reader.put(51, 5);

        //Синяя ветка
        Reader.putLine(24, 41);
        Reader.put(33, 18);
        Reader.put(32, 4);

        //Фиолетовая ветка
        Reader.putLine(12, 23);
        Reader.putLine(0, 11);
    }
}