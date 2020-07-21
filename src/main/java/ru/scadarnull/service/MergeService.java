package ru.scadarnull.service;

import ru.scadarnull.entity.Pair;
import ru.scadarnull.entity.Triple;

import java.awt.*;
import java.util.*;
import java.util.List;

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


    public static List<Triple> leftJoin(LinkedList<Pair> table1, LinkedList<Pair> table2){

        List<Triple> result = new LinkedList<>();
        ListIterator<Pair> iter1 = table1.listIterator();
        ListIterator<Pair> iter2 = table2.listIterator();
        List<Triple> temp = new ArrayList<>();
        Pair pairOfTable2 = iter2.next();
        while (iter1.hasNext()){

            Pair pairOfTable1 = iter1.next();

            if(!temp.isEmpty() && pairOfTable1.getId().compareTo(temp.get(0).getId()) == 0){
                for(Triple triple : temp){
                    result.add(new Triple(triple.getId(), pairOfTable1.getValue(), triple.getValue2()));
                }
                continue;
            }else{
                temp.clear();
            }

            while (iter2.hasNext() && pairOfTable2.getId().compareTo(pairOfTable1.getId()) < 0){
                pairOfTable2 = iter2.next();
            }

            while (iter2.hasNext() && pairOfTable1.getId().compareTo(pairOfTable2.getId()) == 0){
                temp.add(new Triple(pairOfTable1.getId(), pairOfTable1.getValue(), pairOfTable2.getValue()));
                pairOfTable2 = iter2.next();
            }

            if(!iter2.hasNext() && pairOfTable1.getId().compareTo(pairOfTable2.getId()) == 0){
                temp.add(new Triple(pairOfTable1.getId(), pairOfTable1.getValue(), pairOfTable2.getValue()));
            }

            if(!temp.isEmpty()){
                result.addAll(temp);
            }else{
                result.add(new Triple(pairOfTable1.getId(), pairOfTable1.getValue(), ""));
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
