package ru.scadarnull.service;

import ru.scadarnull.entity.Pair;
import ru.scadarnull.entity.Triple;

import java.util.*;

public class MergeService {

    public static List<Triple> leftJoin(ArrayList<Pair> table1, ArrayList<Pair> table2){
        //Надо именно через такие структуры данных или можно через интерфейсы?
        ArrayList<Triple> result = new ArrayList<>();
        for(Pair pair1 : table1){
            Triple temp = new Triple(pair1.getId(), pair1.getValue(), "");
            result.add(temp);
            for(Pair pair2 : table2){
                if(pair1.getId().equals(pair2.getId())){
                    temp.setValue2(pair2.getValue());
                    break;
                }
            }
        }
        return result;
    }

    public static List<Triple> leftJoin(LinkedList<Pair> table1, LinkedList<Pair> table2){
        //Такую же логику можно и с ArrayList(только надо отсортировать)
        List<Triple> result = new LinkedList<>();
        Iterator<Pair> iter = table2.listIterator();
        if(table2.size() == 0){
            for(Pair pair : table1){
                result.add(new Triple(pair.getId(), pair.getValue(), ""));
            }
            return result;
        }
        Pair firstPair = table2.getFirst();

        for(Pair pair : table1){
            while (iter.hasNext() && pair.getId().compareTo(firstPair.getId()) > 0){
                firstPair = iter.next();
            }
            if(pair.getId().compareTo(firstPair.getId()) == 0){
                result.add(new Triple(pair.getId(), pair.getValue(), firstPair.getValue()));
            }else{
                result.add(new Triple(pair.getId(), pair.getValue(), ""));
            }
        }

        return result;
    }

    public static List<Triple> leftJoin(Map<Long, String> table1, Map<Long, String> table2){
        //Проблема с ключами(нет повторений)
        //Есть ли подобие multiMap? Будет ли он работать так же быстро как тут на hash?
        List<Triple> result = new ArrayList<>();
        for(Map.Entry<Long, String> pair: table1.entrySet()){
            Triple temp = new Triple(pair.getKey(), pair.getValue(), "");
            result.add(temp);
            if(table2.containsKey(pair.getKey())){
                temp.setValue2(table2.get(pair.getKey()));
            }
        }
        return result;
    }
}
