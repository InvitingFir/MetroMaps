package com.roman.Util;

import com.roman.Metro.Change;
import com.roman.Metro.MetroStation;

import java.io.*;


//служебный класс для легкого заполенения матрицы в одну команду.

public class MapRead {
    private String Path;
    private Table<MetroStation, Change> Metro;
    private FileReader reader;
    private BufferedReader in;

    public MapRead(String path, Table metro){
        this.Path = path;
        this.Metro = metro;
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        in = new BufferedReader(new InputStreamReader(inputStream));
    }

    //заполнение матрицы для станции со степенью 1
    public void put(MetroStation head, MetroStation change1){
        String data;
        int IntData = 0;
        try{
            data = in.readLine();
            IntData = Integer.parseInt(data);
        }
        catch(Exception e){e.printStackTrace();}
        Metro.put(head, change1, new Change(IntData));
    }

    //заполнение матрицы для станции со степенью 2
    public void put(MetroStation head, MetroStation change1, MetroStation change2){
        int i = 0;
        char [] CharData = new char[256];
        String data;
        int IntData = 0;
        try{
            data = in.readLine();
            CharData = data.toCharArray();
        }
        catch(Exception e){e.printStackTrace();}
        while(CharData[i]!=' '){
            IntData = IntData*10+Character.getNumericValue(CharData[i]);
            i++;
        }
        Metro.put(head, change1, new Change(IntData));
        IntData = 0;
        i++;
        while(i < CharData.length){
            IntData = IntData*10+Character.getNumericValue(CharData[i]);
            i++;
        }
        Metro.put(head, change2, new Change(IntData));
    }

    //заполнение матриц для станции со степенью 3
    public void put(MetroStation head, MetroStation change1, MetroStation change2, MetroStation change3){
        int i = 0;
        char [] CharData = new char[256];
        String data;
        int IntData = 0;
        try{
            data = in.readLine();
            CharData = data.toCharArray();
        }
        catch(Exception e){e.printStackTrace();}
        while(CharData[i]!=' '){
            IntData = IntData*10+Character.getNumericValue(CharData[i]);
            i++;
        }
        Metro.put(head, change1, new Change(IntData));
        i++;
        IntData = 0;

        while(CharData[i]!=' '){
            IntData = IntData*10+Character.getNumericValue(CharData[i]);
            i++;
        }
        Metro.put(head, change2, new Change(IntData));
        i++;
        IntData = 0;

        while(i < CharData.length){
            IntData = IntData*10+Character.getNumericValue(CharData[i]);
            i++;
        }
        Metro.put(head, change3, new Change(IntData));
    }

    public void put(MetroStation head, MetroStation change1, MetroStation change2, MetroStation change3, MetroStation change4){
        int i = 0;
        char [] CharData = new char[256];
        String data;
        int IntData = 0;
        try{
            data = in.readLine();
            CharData = data.toCharArray();
        }
        catch(Exception e){e.printStackTrace();}
        while(CharData[i]!=' '){
            IntData = IntData*10+Character.getNumericValue(CharData[i]);
            i++;
        }
        Metro.put(head, change1, new Change(IntData));
        i++;
        IntData = 0;

        while(CharData[i]!=' '){
            IntData = IntData*10+Character.getNumericValue(CharData[i]);
            i++;
        }
        Metro.put(head, change2, new Change(IntData));
        i++;
        IntData = 0;

        while(CharData[i]!=' '){
            IntData = IntData*10+Character.getNumericValue(CharData[i]);
            i++;
        }
        Metro.put(head, change3, new Change(IntData));
        i++;
        IntData = 0;

        while(i < CharData.length){
            IntData = IntData*10+Character.getNumericValue(CharData[i]);
            i++;
        }
        Metro.put(head, change4, new Change(IntData));
    }


}
