package com.roman.util;

import java.util.*;

public class Table <K1, V> {
    private Map<K1, Map<K1, V>> Table;

    public Table(){ Table = new LinkedHashMap<>(); }

    public void putColumn(K1 key, Map<K1, V> value){ Table.put(key, value); }

    public void putRow(K1 key1, K1 key2, V value){
        if(Table.get(key1) == null){
            Map<K1, V> temp = new LinkedHashMap<>();
            temp.put(key2, value);
            Table.put(key1, temp);
        }
        Table.get(key1).put(key2, value);
    }

    public V getRow(K1 key1, K1 key2){
        if(Table.get(key1).get(key2) == null) return null;
        else return Table.get(key1).get(key2);
    }

    public Map<K1, V> getColumn(K1 key){
        if(Table.get(key) == null) return null;
        else return Table.get(key);
    }

    public Set<Map.Entry<K1, Map<K1, V>>> entrySet(){ return Table.entrySet(); }

    public Set<K1> keySet(){return Table.keySet();}

    public Collection<Map<K1, V>> values(){return Table.values();}

    public int size(){ return Table.size(); }

    public void fill(List<K1> list){
        for (K1 key:list) { Table.put(key, null); }
    }
}
