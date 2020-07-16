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
            db.readFromFiles();
            List<Triple> triples = MergeService.leftJoin(db.getTable1AsArrayList(), db.getTable2AsArrayList());
            for(Triple triple : triples){
                System.out.println(triple.getId() + "  " + triple.getValue1() + "  " + triple.getValue2());
            }
        }
    }
}
