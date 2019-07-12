package com.roman.Metro.Metrosystems;


import com.roman.Metro.Change;
import com.roman.Metro.Line;
import com.roman.Metro.MetroStation;
import com.roman.Util.MapRead;
import com.roman.Util.Table;

import java.awt.*;
import java.util.*;

public abstract class MetroSystem {
    protected Table<MetroStation, Change> Routes;
    protected MapRead Reader;
    private String StationsPath, RoutesPath;

    public MetroSystem(String path1, String path2){
        StationsPath = path1;
        RoutesPath = path2;
    }


    public Table<MetroStation, Change> readStationFile(Line [] Lines){
        Reader = new MapRead(StationsPath, RoutesPath,  Lines);
        return Reader.stationsInit();
    }

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
                column.getKey().drawStation(g, column.getKey(), 60, 360);
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
        int startDegree = 60, deltaDegree;
        for(MetroStation m:value.keySet()){
            if(key.getColor() != m.getColor()) numOfColors++;
        }
        deltaDegree = 360/numOfColors;
        key.drawStation(g, key, startDegree, deltaDegree);
        startDegree+=deltaDegree;
        for(MetroStation m:value.keySet()){
            if(key.getColor() == m.getColor()) continue;
            else{
                m.drawStation(g, key, startDegree, deltaDegree);
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
                if(line.getLocation().equals(column.getKey().getLocation())) continue;
                if (line.getPosition() > LastPosition) {
                    column.getKey().drawLine(g, line);
                } else break;
            }
            LastPosition = column.getKey().getPosition();
        }
    }
}