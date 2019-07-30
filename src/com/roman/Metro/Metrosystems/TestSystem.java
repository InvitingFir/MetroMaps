package com.roman.Metro.Metrosystems;

import com.roman.Metro.Line;

import java.awt.*;


public class TestSystem extends MetroSystem{
    private Line Red = new Line("Red", new Color(243, 29, 41));
    private Line Green = new Line("Green", new Color(6, 153, 83));
    private Line Blue = new Line("Blue", new Color(2, 85, 163));
    private Line Purple = new Line("Purple", new Color(185, 28, 141));
    private Line Orange = new Line("Orange", new Color(248, 170, 62));
    private Line Brown = new Line("Brown", new Color(120, 91, 49));
    private Line [] LineMas = {Red, Brown, Blue, Purple, Orange,Green};

    public TestSystem(){
        super("com/roman/Resource/TestMetro.stat", "com/roman/Resource/TestMetro.con");
        Routes = readStationFile(LineMas);
        connections();
    }

    private void connections(){
        Reader.put(0, 1);
        Reader.put(1, 2, 0);
        Reader.put(2, 5, 3, 1);
        Reader.put(3, 25, 12, 2);
        Reader.put(4, 21, 13, 5);
        Reader.put(5, 13, 7, 4, 2);
        Reader.put(6, 22, 14, 7);
        Reader.put(7, 8, 6, 5);
        Reader.put(8, 7);
        Reader.put(9, 10);
        Reader.put(10, 11, 9);
        Reader.put(11, 12, 10);
        Reader.put(12, 14, 11, 3);
        Reader.put(13, 15, 5, 4);
        Reader.put(14, 15, 12, 6);
        Reader.put(15, 16, 14, 13);
        Reader.put(16, 17, 15);
        Reader.put(17, 16);
        Reader.put(18, 25, 19);
        Reader.put(19, 20, 18);
        Reader.put(20, 19);
        Reader.put(21, 23, 22, 4);
        Reader.put(22, 25, 21, 6);
        Reader.put(23, 24, 21);
        Reader.put(24, 23);
        Reader.put(25, 22, 18, 3);
    }
}
