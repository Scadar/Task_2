package ru.scadarnull.service;

import ru.scadarnull.entity.Pair;
import ru.scadarnull.entity.Triple;

import java.util.*;

public class MergeService {

    public static List<Triple> leftJoin(ArrayList<Pair> table1, ArrayList<Pair> table2){
        //Надо именно через такие структуры данных или можно через интерфейсы?
        ArrayList<Triple> result = new ArrayList<>();
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
        //Такую же логику можно и с ArrayList
        List<Triple> result = new LinkedList<>();
        Iterator<Pair> iter = table2.listIterator();
        Pair pair = table2.getFirst();//Обработать 1 элемент (его отсутствие)
        for(Pair line : table1){
            result.add(new Triple(line.getId(), line.getValue(), ""));
        }
        for(Triple triple : result){
            while (iter.hasNext() && triple.getId().compareTo(pair.getId()) > 0){
                pair = iter.next();
            }
            if(triple.getId().compareTo(pair.getId()) == 0){
                triple.setValue2(pair.getValue());
            }
        }
        return result;
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
