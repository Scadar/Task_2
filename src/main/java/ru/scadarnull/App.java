package ru.scadarnull;

import ru.scadarnull.db.DB;
import ru.scadarnull.entity.Triple;
import ru.scadarnull.service.MergeService;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        if(args.length < 2){
            System.out.println("Неверные аргументы программы");
        }else{
            String file1 = args[0];
            String file2 = args[1];
            DB db = new DB(file1, file2);
            if(db.readFromFiles()){
                List<Triple> triples1 = MergeService.leftJoin(db.getTable1AsArrayList(), db.getTable2AsArrayList());
                List<Triple> triples2 = MergeService.leftJoin(db.getTable1AsLinkedList(), db.getTable2AsLinkedList());
                List<Triple> triples3 = MergeService.leftJoin(db.getTable1AsHashMap(), db.getTable2AsHashMap());
                for(Triple triple : triples1){
                    System.out.println(triple.getId() + "  " + triple.getValue1() + "  " + triple.getValue2());
                }
                System.out.println(" ");
                for(Triple triple : triples2){
                    System.out.println(triple.getId() + "  " + triple.getValue1() + "  " + triple.getValue2());
                }
                System.out.println(" ");
                for(Triple triple : triples3){
                    System.out.println(triple.getId() + "  " + triple.getValue1() + "  " + triple.getValue2());
                }
            }

        }
    }
}
