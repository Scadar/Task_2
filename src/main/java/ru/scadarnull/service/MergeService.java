package ru.scadarnull.service;

import ru.scadarnull.entity.Pair;
import ru.scadarnull.entity.Triple;

import java.util.*;

public class MergeService {

    public static List<Triple> leftJoin(List<Pair> table1, List<Pair> table2){
        //test
        List<Triple> result = new ArrayList<>();
        for(Pair line : table1){
            result.add(new Triple(line.getId(), line.getValue(), ""));
        }
        for(Triple triple : result){
            for(Pair line2 : table2){
                if(triple.getId().equals(line2.getId())){
                    triple.setValue2(line2.getValue());
                    break;
                }
            }
        }
        return result;
    }

    public static List<Triple> leftJoin(LinkedList<Pair> table1, LinkedList<Pair> table2){
        //Отсортированны по id, двусвязный список a->b->c
        //List<Triple> result = new ArrayList<>();
        return null;
        //return result;
    }

    public static List<Triple> leftJoin(Map<Long, String> table1, Map<Long, String> table2){
        //Проблема с ключами(нет повторений)
        List<Triple> result = new ArrayList<>();
        for(Map.Entry<Long, String> left : table1.entrySet()){
            result.add(new Triple(left.getKey(), left.getValue(), ""));
        }
        for(Triple triple : result){
            if(table2.containsKey(triple.getId())){
                triple.setValue2(table2.get(triple.getId()));
            }
        }
        return result;
    }
}
