package com.roman.Metro.Metrosystems;


import com.roman.Metro.Change;
import com.roman.Metro.MetroStation;
import com.roman.Util.MapRead;
import com.roman.Util.Table;

import java.awt.*;
import java.util.*;

public abstract class MetroSystem {
    private Table<MetroStation, Change> Routes;
    protected static final int STATIONRAD = 20;
    protected MapRead Reader;


    //инициализация матрицы смежности
    //чтение файла с расстояниями в stationsInit();
    public MetroSystem(String FilePath){
        Routes = new Table<>();
        Reader = new MapRead(FilePath, Routes);
        stationsInit();
    }

    //индивидуальная настройка карты
    protected abstract void stationsInit();

    //отрисовка карты метро целиком
    //сначала вершины, потом маршруты
    public void graphicInit(Graphics g){
        routeDraw(g, Routes);
        stationsDraw(g, Routes);
    }


    //отрисовка вершин графа
    //выполняется единожды в методе graphicsInit(Graphics g)
    private void stationsDraw(Graphics g, Table<MetroStation, Change> Metro){
        boolean Draw;
        MetroStation m1;
        for (Map.Entry<MetroStation, Map<MetroStation, Change>> column: Metro.entrySet()) {
            Draw = true;
            if(column.getValue().size() == 1) {
                stationIconDraw(column.getValue(), column.getKey(), g);
                continue;
            }
            m1 = column.getKey();
            for(MetroStation m2: column.getValue().keySet()) {
                if(!m1.getLocation().equals(m2.getLocation())){ continue; }
                else if(m1.getPosition() < m2.getPosition()){
                    Draw = false;
                    break;
                }
            }
            if(Draw){stationIconDraw(column.getValue(), column.getKey(), g);}
        }

        /*for (MetroStation m:Metro.keySet()) {
            m.draw(g, STATIONRAD);
        }*/
    }

    private void stationIconDraw(Map<MetroStation, Change> value, MetroStation key, Graphics g){
        int numOfColors = 1;
        int startDegree = 0, deltaDegree;
        if(value.size() > 2)
            System.out.println("Hello");
        for(MetroStation m:value.keySet()){
            if(key.getColor() != m.getColor()) numOfColors++;
        }
        deltaDegree = (int)360/numOfColors;
        key.draw(g, key, STATIONRAD, startDegree, startDegree+deltaDegree);
        startDegree+=deltaDegree;
        for(MetroStation m:value.keySet()){
            if(key.getColor() == m.getColor()) continue;
            else{
                m.draw(g, key, STATIONRAD, startDegree, deltaDegree);
                startDegree+=deltaDegree;
            }
        }
    }


    //отрисовка маршрутов
    //выполняется в методе graphicsInit(Graphics g)
    private void routeDraw(Graphics g, Table<MetroStation, Change> Metro){
        int LastPosition = -1;
        for (Map.Entry<MetroStation, Map<MetroStation, Change>> column: Metro.entrySet()) {
            for (MetroStation line: column.getValue().keySet()) {
                if(line.getPosition() > LastPosition){ lineDraw(g, column.getKey(), line); }
                else break;
            }
            LastPosition = column.getKey().getPosition();
        }
    }

    //Отрисовка как раз отрисовка самих линий
    //вызывается в методе routeDraw()
    private void lineDraw(Graphics g, MetroStation m1, MetroStation m2){
        int delta = STATIONRAD/2;
        int x1 = m1.getX()+delta;
        int x2 = m2.getX()+delta;
        int y1 = m1.getY()+delta;
        int y2 = m2.getY()+delta;
        if(x1 == x2 && y1 == y2) return;

        g.setColor(m2.getColor());
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(x1, y1, x2, y2);
    }
}