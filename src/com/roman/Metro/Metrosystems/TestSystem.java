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
        super("com/roman/Resource/TestMetro.stat", "com/roman/Resource/TestMetro.con", "Тестовое метро");
        Routes = readStationFile(LineMas);
        connections();
    }

    private void connections(){
        //Кольцевая
        Reader.put(25, 24, 20);
        Reader.putLine(20, 24);
        Reader.put(20, 8);
        Reader.put(21, 11);
        Reader.put(22, 4);
        Reader.put(23, 17);
        Reader.put(24, 13);
        Reader.put(25, 2);
        //Оранжевая
        Reader.putLine(16, 19);
        Reader.put(16, 12, 3);
        //Синяя
        Reader.putLine(13, 15);
        //Красная
        Reader.putLine(9, 12);
        Reader.put(12, 3);
        //Зеленая
        Reader.putLine(6, 8);
        //Фиолетовая
        Reader.putLine(0, 5);
    }
}
