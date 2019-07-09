package com.roman.Metro.Metrosystems;


import com.roman.Metro.Change;
import com.roman.Metro.MetroStation;
import com.roman.Util.MapRead;
import com.roman.Util.Table;

import java.awt.*;
import java.util.*;

public abstract class MetroSystem {
    private Table<MetroStation, Change> Routes;
    private static final int STATIONRAD = 20;
    protected MapRead Reader;

    //инициализация матрицы смежности
    //чтение файла с расстояниями в stationsInit();
    public MetroSystem(String FilePath){
        Routes = new Table<>();
        Reader = new MapRead(FilePath, Routes);
        stationsInit();
        connection();
    }

    //индивидуальная настройка карты
    protected abstract void stationsInit();

    protected abstract void connection();

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
                column.getKey().drawStation(g, STATIONRAD);
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
    }

    //Отрисовка конкретных станций
    //Выполняется в StationDraw(Graphics g, Table<MetroStation, Change> Metro)
    private void stationIconDraw(Map<MetroStation, Change> value, MetroStation key, Graphics g){
        int numOfColors = 1;
        int startDegree = 0, deltaDegree;
        for(MetroStation m:value.keySet()){
            if(key.getColor() != m.getColor()) numOfColors++;
        }
        deltaDegree = 360/numOfColors;
        key.drawStation(g, key, STATIONRAD, startDegree, deltaDegree);
        startDegree+=deltaDegree;
        for(MetroStation m:value.keySet()){
            if(key.getColor() == m.getColor()) continue;
            else{
                m.drawStation(g, key, STATIONRAD, startDegree, deltaDegree);
                startDegree+=deltaDegree;
            }
        }
    }


    //отрисовка всех маршрутов
    //выполняется в методе graphicsInit(Graphics g)
    private void routeDraw(Graphics g, Table<MetroStation, Change> Metro) {
        int LastPosition = -1;
        for (Map.Entry<MetroStation, Map<MetroStation, Change>> column : Metro.entrySet()) {
            for (MetroStation line : column.getValue().keySet()) {
                if (line.getPosition() > LastPosition) {
                    column.getKey().drawLine(g, line, STATIONRAD);
                } else break;
            }
            LastPosition = column.getKey().getPosition();
        }
    }
}