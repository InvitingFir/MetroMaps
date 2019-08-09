package com.roman.Metro.Metrosystems;

import com.roman.Metro.Change;
import com.roman.Metro.Line;
import com.roman.Metro.MetroStation;
import com.roman.Util.MapRead;
import com.roman.Util.Table;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class MetroSystem {
    protected Table<MetroStation, Change> Routes;
    protected MapRead Reader;
    private String StationsPath, RoutesPath;
    private Table<MetroStation, Change> Path;
    private String MetroName;

    public MetroSystem(String path1, String path2, String name){
        StationsPath = path1;
        RoutesPath = path2;
        MetroName = name;
        MetroStation.nullPosition();
    }

    @Override
    public String toString() {
        return MetroName;
    }

    public MetroStation[] getStations(){ return Routes.keySet().toArray(new MetroStation[0]); }

    protected Table<MetroStation, Change> readStationFile(Line [] Lines){
        Reader = new MapRead(StationsPath, RoutesPath,  Lines);
        return Reader.stationsInit();
    }

    public void pathClear(){ Path = null;}

    public Table<MetroStation, Change> getPath(){return Path;}

    //отрисовка карты метро целиком
    //сначала вершины, потом маршруты
    public void graphicInit(Graphics g) {
        routeDraw(g, Routes);
        stationsDraw(g, Routes);
        if(Path!=null){
            Line.setChosen(true);
            routeDraw(g, Path);
            stationsDraw(g, Path);
            Line.setChosen(false);
        }
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
                if((m1.getLocation().equals(m2.getLocation()) && m1.getPosition() < m2.getPosition())){
                    Draw = false;
                    break;
                }
            }
            if(Draw) stationIconDraw(column.getValue(), column.getKey(), g);
        }
    }

    //Отрисовка конкретных станций
    //Выполняется в StationDraw(Graphics g, Table<MetroStation, Change> Metro)
    private void stationIconDraw(Map<MetroStation, Change> value, MetroStation key, Graphics g){
        int numOfColors = 1;
        int startDegree = 45, deltaDegree;
        for(MetroStation m:value.keySet()){ if(key.getColor() != m.getColor()) numOfColors++; }
        deltaDegree = 360/numOfColors;
        key.drawStation(g, key, startDegree, deltaDegree);
        startDegree+=deltaDegree;
        for(MetroStation m:value.keySet()){
            if(key.getColor() != m.getColor()){
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
                if (line.getPosition() > LastPosition) column.getKey().drawLine(g, line);
                else break;
            }
            LastPosition = column.getKey().getPosition();
        }
    }

    public int CalculatePath(MetroStation start, MetroStation finish){
        int inf = Integer.MAX_VALUE;
        int n = Routes.size();
        boolean [] checked = new boolean[n];
        Map<MetroStation, Integer> length = new HashMap<>();
        Map<MetroStation, MetroStation> predators = new HashMap<>();
        Map.Entry<MetroStation, Integer> smallest;

        for(MetroStation m:Routes.keySet()){ length.put(m, inf); }
        length.put(start, 0);
        for (int i = 0; i < n; i++) {
            smallest = null;
            for (Map.Entry<MetroStation, Integer> entry: length.entrySet())
                if(!checked[entry.getKey().getPosition()] && (smallest == null || smallest.getValue() > entry.getValue()))
                    smallest = entry;
            if(smallest.getValue() == inf) break;
            checked[smallest.getKey().getPosition()] = true;
            for (Map.Entry<MetroStation, Change> entry: Routes.getColumn(smallest.getKey()).entrySet()) {
                MetroStation to = entry.getKey();
                int len = entry.getValue().getTime();
                if(length.get(smallest.getKey())+len < length.get(to)){
                    length.put(to, length.get(smallest.getKey())+len);
                    predators.put(to, smallest.getKey());
                }
            }
        }
        pathReconstruction(start, finish, predators);
        return length.get(finish);
    }

    private void pathReconstruction(MetroStation start, MetroStation finish, Map<MetroStation, MetroStation> path) {
        Path = new Table<>();
        List<MetroStation> list = new LinkedList<>();
        int i = 0;
        MetroStation temp1 = finish;
        list.add(finish);
        if(finish.getPosition()>start.getPosition()) list.add(0, start);
        else list.add(start);
        while (path.get(temp1) != start) {
            temp1 = path.get(temp1);
            for (MetroStation m:list) {
                if(i<list.size()-1 && m.getPosition()<temp1.getPosition()){
                    i++;
                    continue;
                }
                list.add(i, temp1);
                i = 0;
                break;
            }
        }
        Path.fill(list);
        for (i = list.size()-1; i>=0;i--) {
            if(path.get(list.get(i))!=null){
                Path.putRow(list.get(i), path.get(list.get(i)), null);
                Path.putRow(path.get(list.get(i)), list.get(i), null);
            }
        }
    }
}