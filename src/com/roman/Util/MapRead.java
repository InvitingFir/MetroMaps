package com.roman.Util;

import com.roman.Metro.Change;
import com.roman.Metro.Line;
import com.roman.Metro.MetroStation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


//служебный класс для легкого заполенения матрицы.

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

    public Table<MetroStation, Change> stationsInit(){
        Scanner sc;
        String name;
        int X, Y;
        Line line;

        try{ sc = new Scanner(getClass().getClassLoader().getResourceAsStream(StationsPath));
            while(sc.hasNextLine()){
                name = sc.next();
                X = sc.nextInt();
                Y = sc.nextInt();
                line = lineChoice(sc.next());
                Metro.putColumn(new MetroStation(name, X, Y, line));
            }
        }
        catch(Exception e){e.printStackTrace();}
        return Metro;
    }

    private Line lineChoice(String lineName){
        for (Line l:LinesMas) {
            if(lineName.equals(l.getName())){
                return l;
            }
        }
        return null;
    }

    public void put(int pos1, int pos2){
        int []mas = {pos1, pos2};
        readRoutes(mas);
    }

    public void put(int pos1, int pos2, int pos3){
        int []mas = {pos1, pos2, pos3};
        readRoutes(mas);
    }

    public void put(int pos1, int pos2, int pos3, int pos4){
        int []mas = {pos1, pos2, pos3, pos4};
        readRoutes(mas);
    }

    public void put(int pos1, int pos2, int pos3, int pos4, int pos5){
        int []mas = {pos1, pos2, pos3, pos4, pos5};
        readRoutes(mas);
    }


    //заполнение матрицы для станции со степенью 1
    private void readRoutes(int [] mas){
        String scan;
        MetroStation [] MetrostatMas = new MetroStation[mas.length];
        Map<MetroStation, Change> temp = new LinkedHashMap<>();
        int j = 0;

        for(int position:mas){
            for(Map.Entry<MetroStation, Map<MetroStation, Change>> col:Metro.entrySet()){
                if(col.getKey().getPosition() == position){
                    MetrostatMas[j] = col.getKey();
                    j++;
                    break;
                }
            }
        }

        try{
            j=1;
            while(!(scan = RouteScan.next()).equals(";")){
                temp.put(MetrostatMas[j], new Change(Integer.parseInt(scan)));
                j++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Metro.setColumn(MetrostatMas[0], temp);
    }
}
