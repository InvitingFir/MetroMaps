package com.roman.Util;

import com.roman.Metro.Change;
import com.roman.Metro.Line;
import com.roman.Metro.MetroStation;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
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
        Metro = new Table<>();
        try{RouteScan = new Scanner(getClass().getClassLoader().getResourceAsStream(RoutesPath));}
        catch(Exception e){e.printStackTrace();}
    }

    public Table<MetroStation, Change> stationsInit() {
        Scanner sc;
        String name;
        int X, Y;
        Line line;

        try {
            sc = new Scanner(getClass().getClassLoader().getResourceAsStream(StationsPath));
            while (sc.hasNextLine()) {
                name = sc.next();
                X = sc.nextInt();
                Y = sc.nextInt();
                line = LinesMas[sc.nextInt()];
                Metro.putColumn(new MetroStation(name, X, Y, line), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void putLine(int from, int to){
        int j = 0;
        int []mas = new int[to-from];
        for (int i = from; i <= to; i++, j++) { mas[j] = i; }
        readStations(mas);
    }

    private void readLines(){
        MetroStation next = null;
        MetroStation previous = null;

    }

    private void readStations(int [] mas){
        String scan;
        MetroStation head = null;
        Map<MetroStation, Change> temp = new LinkedHashMap<>();
        for(Map.Entry<MetroStation, Map<MetroStation, Change>> col:Metro.entrySet()) {
            if(col.getKey().getPosition() == mas[0]){
                head = col.getKey();
                break;
            }
        }
        for(int i = 1;i<mas.length;i++){
            for(Map.Entry<MetroStation, Map<MetroStation, Change>> col:Metro.entrySet()){
                if(col.getKey().getPosition() == mas[i]){
                    scan = RouteScan.next();
                    temp.put(col.getKey(), new Change(Integer.parseInt(scan)));
                    break;
                }
            }
        }
        Metro.putColumn(head, temp);
    }
}
