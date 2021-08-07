package com.roman.util;

import com.roman.Metro.Change;
import com.roman.Metro.Line;
import com.roman.Metro.MetroStation;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;


//служебный класс для легкого заполенения матрицы с файла.

public class MapRead {
    private Table<MetroStation, Change> Metro;
    private Line [] LinesMas;
    private String StationsPath;
    private Scanner RouteScan;

    public MapRead(String Stationspath, String RoutesPath, Line [] Lines){
        LinesMas = Lines;
        this.StationsPath = Stationspath;
        Metro = new Table<MetroStation, Change>();
        try{RouteScan = new Scanner(getClass().getClassLoader().getResourceAsStream(RoutesPath));}
        catch(Exception e){e.printStackTrace();}
    }

    public Table<MetroStation, Change> stationsInit() {
        Scanner sc;
        String name;
        int X, Y;
        Line line;
        try {
            sc = new Scanner(getClass().getClassLoader().getResourceAsStream(StationsPath), StandardCharsets.UTF_8);
            while (sc.hasNextLine()) {
                name = sc.next();
                X = sc.nextInt();
                Y = sc.nextInt();
                line = LinesMas[sc.nextInt()];
                Metro.putColumn(new MetroStation(name, X, Y, line), null);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return Metro;
    }

    public void put(int pos1, int pos2){
        int []mas = {pos1, pos2};
        readStations(mas);
    }

    public void put(int pos1, int pos2, int pos3){
        int []mas = {pos1, pos2, pos3};
        readStations(mas);
    }

    public void put(int pos1, int pos2, int pos3, int pos4){
        int []mas = {pos1, pos2, pos3, pos4};
        readStations(mas);
    }

    public void put(int pos1, int pos2, int pos3, int pos4, int pos5){
        int []mas = {pos1, pos2, pos3, pos4, pos5};
        readStations(mas);
    }

    public void putLine(int start, int end){
        if(end > Metro.size() || end < 0) return;
        else if(start > Metro.size() || start < 0) return;
        MetroStation current = null;
        String nextScan = "0";
        String pastScan = "0";
        MetroStation past = null;
        for (MetroStation m: Metro.keySet()) {
            if(m.getPosition() >= start){
                if(current!= null){
                    nextScan = RouteScan.next();
                    Metro.putRow(current, m, new Change(Integer.parseInt(nextScan)));
                }
                if(past != null){
                    Metro.putRow(current, past, new Change(Integer.parseInt(pastScan)));
                }
                past = current;
                current = m;
                pastScan = nextScan;
                if(m.getPosition() == end) break;
            }
        }
        Metro.putRow(current, past, new Change(Integer.parseInt(pastScan)));
    }

    private void readStations(int [] mas){
       String scan;
       MetroStation head;
       MetroStation [] stations = new MetroStation[mas.length];
       int i = mas.length - 1;
        for (MetroStation m: Metro.keySet()) {
            if(m.getPosition() == mas[i]){
                stations[i] = m;
                if(i == 0) break;
                i--;
            }
        }
        for (int j = 1; j < mas.length; j++) {
            scan = RouteScan.next();
            Metro.putRow(stations[0], stations[j], new Change(Integer.parseInt(scan)));
            Metro.putRow(stations[j], stations[0], new Change(Integer.parseInt(scan)));
        }
    }
}
