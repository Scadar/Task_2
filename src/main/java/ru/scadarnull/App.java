package ru.scadarnull;

import ru.scadarnull.db.DB;

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
        }
    }
}
