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
        super("com/roman/resource/TestMetro.stat", "com/roman/resource/TestMetro.con", "Тестовое метро");
        routesTable = readStationFile(LineMas);
        connections();
    }

    private void connections() {
        //Кольцевая
        reader.put(25, 24, 20);
        reader.putLine(20, 24);
        reader.put(20, 8);
        reader.put(21, 11);
        reader.put(22, 4);
        reader.put(23, 17);
        reader.put(24, 13);
        reader.put(25, 2);
        //Оранжевая
        reader.putLine(16, 19);
        reader.put(16, 12, 3);
        //Синяя
        reader.putLine(13, 15);
        //Красная
        reader.putLine(9, 12);
        reader.put(12, 3);
        //Зеленая
        reader.putLine(6, 8);
        //Фиолетовая
        reader.putLine(0, 5);
    }
}
