package ru.scadarnull.service;

import ru.scadarnull.entity.Pair;
import ru.scadarnull.entity.Triple;

import java.util.*;

public class MergeService {

    public static List<Triple> leftJoin(ArrayList<Pair> table1, ArrayList<Pair> table2){
        ArrayList<Triple> result = new ArrayList<>();
        for(Pair pair1 : table1){
            boolean single = true;
            for(Pair pair2 : table2){
                if(pair1.getId().equals(pair2.getId())){
                    result.add(new Triple(pair1.getId(), pair1.getValue(), pair2.getValue()));
                    single = false;
                }
            }
            if(single){
                result.add(new Triple(pair1.getId(), pair1.getValue(), ""));
            }
        }
        return result;
    }

//    public static List<Triple> leftJoin(LinkedList<Pair> table1, LinkedList<Pair> table2){
//
//        List<Triple> result = new LinkedList<>();
//        Iterator<Pair> iter1 = table1.listIterator();
//        Iterator<Pair> iter2 = table2.listIterator();
//        if(table2.size() == 0){
//            for(Pair pair : table1){
//                result.add(new Triple(pair.getId(), pair.getValue(), ""));
//            }
//            return result;
//        }
//        Pair firstPair = table2.getFirst();
//
//        for(Pair pair : table1){
//            while (iter1.hasNext() && pair.getId().compareTo(firstPair.getId()) > 0){
//                firstPair = iter1.next();
//            }
//            if(pair.getId().compareTo(firstPair.getId()) == 0){
//                result.add(new Triple(pair.getId(), pair.getValue(), firstPair.getValue()));
//            }else{
//                result.add(new Triple(pair.getId(), pair.getValue(), ""));
//            }
//        }
//
//        return result;
//    }

    public static List<Triple> leftJoin(LinkedList<Pair> table1, LinkedList<Pair> table2){

        List<Triple> result = new LinkedList<>();
        ListIterator<Pair> iter1 = table1.listIterator();
        ListIterator<Pair> iter2 = table2.listIterator();
        Map<Long, List<String>> map = new HashMap<>();
        while (iter2.hasNext()){
            Pair pair = iter2.next();
            if(map.containsKey(pair.getId())){
                map.get(pair.getId()).add(pair.getValue());
            }else{
                List<String> temp = new ArrayList<>();
                temp.add(pair.getValue());
                map.put(pair.getId(), temp);
            }
        }
        while (iter1.hasNext()){
            Pair pair = iter1.next();
            if(map.containsKey(pair.getId())){
                for(String str : map.get(pair.getId())){
                    result.add(new Triple(pair.getId(), pair.getValue(), str));
                }
            }else{
                result.add(new Triple(pair.getId(), pair.getValue(), ""));
            }
        }
        return result;
    }

    public static List<Triple> leftJoin(Map<Long, List<String>> table1, Map<Long, List<String>> table2){
        List<Triple> result = new ArrayList<>();

        for(Map.Entry<Long, List<String>> pair: table1.entrySet()){
            if(table2.containsKey(pair.getKey())){
                for(String value1 : pair.getValue()){
                    for(String value2 : table2.get(pair.getKey())){
                        result.add(new Triple(pair.getKey(), value1, value2));
                    }
                }
            }else{
                for(String value : pair.getValue()){
                    result.add(new Triple(pair.getKey(), value, ""));

                }
            }
        }
        return result;
    }
}
