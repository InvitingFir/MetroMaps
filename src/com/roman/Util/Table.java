package com.roman.Util;

import com.roman.Metro.Metrosystems.MetroSystem;

import java.util.*;

public class Table <K1, V> {
    private Map<K1, Map<K1, V>> Table;

    public Table(){ Table = new LinkedHashMap<>(); }

    public void put(K1 key1, K1 key2, V value){
        if(!Table.containsKey(key1)){
            Table.put(key1, new LinkedHashMap<>());
        }
        Table.get(key1).put(key2, value);
    }

    /*public void put(K1 key1, K1 key2, V value){
        if(value == null) return;
        else{ Table.get(key1).put(key2, value); }
    }*/

    public V get(K1 key1, K1 key2){
        if(Table.get(key1).get(key2) == null) return null;
        else return Table.get(key1).get(key2);
    }

    public Set<Map.Entry<K1, Map<K1, V>>> entrySet(){ return Table.entrySet(); }

    public Set<K1> keySet(){return Table.keySet();}

    public Collection<Map<K1, V>> values(){return Table.values();}

    public int size(){ return Table.size(); }

    public Map<K1, V> getValue(K1 key){return Table.get(key);}
}
