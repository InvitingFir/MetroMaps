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
        Reader.put(0, 1);
        Reader.put(1, 2, 0);
        Reader.put(2, 3, 1);
        Reader.put(3, 4, 2);
        Reader.put(4, 32, 5, 3);
        Reader.put(5, 51, 6, 4);
        Reader.put(6, 64, 7, 5);
        Reader.put(7, 8, 6);
        Reader.put(8, 9, 7);
        Reader.put(9, 10, 8);
        Reader.put(10, 11, 9);
        Reader.put(11, 10);

        Reader.put(12, 13);
        Reader.put(13, 14, 12);
        Reader.put(14, 15, 13);
        Reader.put(15, 16, 14);
        Reader.put(16, 17, 15);
        Reader.put(17, 18, 16);
        Reader.put(18, 61, 33, 19, 17);
        Reader.put(19, 53, 20, 18);
        Reader.put(20, 21, 19);
        Reader.put(21, 22, 20);
        Reader.put(22, 23, 21);
        Reader.put(23, 22);

        Reader.put(24, 25);
        Reader.put(25, 26, 24);
        Reader.put(26, 27, 25);
        Reader.put(27, 28, 26);
        Reader.put(28, 29, 27);
        Reader.put(29, 30, 28);
        Reader.put(30, 31, 29);
        Reader.put(31, 32, 30);
        Reader.put(32, 33, 31, 4);
        Reader.put(33, 61, 34, 32, 18);
        Reader.put(34, 54, 35, 33);
        Reader.put(35, 36, 34);
        Reader.put(36, 37, 35);
        Reader.put(37, 38, 36);
        Reader.put(38, 39, 37);
        Reader.put(39, 40, 38);
        Reader.put(40, 41, 39);
        Reader.put(41, 40);

        Reader.put(42, 43);
        Reader.put(43, 44, 42);
        Reader.put(44, 45, 43);
        Reader.put(45, 46, 44);
        Reader.put(46, 47, 45);
        Reader.put(47, 48, 46);
        Reader.put(48, 49, 47);
        Reader.put(49, 50, 48);
        Reader.put(50, 51, 49);
        Reader.put(51, 52, 50, 5);
        Reader.put(52, 62, 53, 51);
        Reader.put(53, 54, 52, 19);
        Reader.put(54, 55, 53, 34);
        Reader.put(55, 56, 54);
        Reader.put(56, 57, 55);
        Reader.put(57, 58, 56);
        Reader.put(58, 59, 57);
        Reader.put(59, 60, 58);
        Reader.put(60, 59);

        Reader.put(61, 62, 33, 18);
        Reader.put(62, 63, 61, 52);
        Reader.put(63, 64, 62);
        Reader.put(64, 65, 63, 6);
        Reader.put(65, 66, 64);
        Reader.put(66, 67, 65);
        Reader.put(67, 68, 66);
        Reader.put(68, 67);
    }
}
