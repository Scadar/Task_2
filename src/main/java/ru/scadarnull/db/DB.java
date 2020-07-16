package ru.scadarnull.db;

import ru.scadarnull.entity.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DB {

    private List<Pair> table1;
    private List<Pair> table2;

    private String inputFile1;
    private String inputFile2;

    public DB(String inputFile1, String inputFile2) {
        this.inputFile1 = inputFile1;
        this.inputFile2 = inputFile2;

        table1 = new ArrayList<>();
        table2 = new ArrayList<>();
    }

    public boolean readFromFiles(){
        boolean isExist1 = readFromFile(inputFile1, table1);
        boolean isExist2 = readFromFile(inputFile2, table2);
        if(!isExist1){
            System.out.println("Файла " + inputFile1 + "не существует");
            return false;
        }
        if(!isExist2){
            System.out.println("Файла " + inputFile2 + "не существует");
            return false;
        }
        return true;
    }

    private boolean readFromFile(String file, List<Pair> table){
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line = reader.readLine();
            while (line != null) {
                readLineFromFile(line, table);
                line = reader.readLine();
            }
        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println(fileNotFoundException.getMessage());
            return false;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return true;
    }

    private void readLineFromFile(String line, List<Pair> table) {
        String[] fullInfo = line.split(" ");
        table.add(new Pair(Long.parseLong(fullInfo[0]), fullInfo[1]));
    }

    public ArrayList<Pair> getTable1AsArrayList() {
        return new ArrayList<>(table1);
    }

    public ArrayList<Pair> getTable2AsArrayList() {
        return new ArrayList<>(table2);
    }

    public LinkedList<Pair> getTable1AsLinkedList() {
        LinkedList<Pair> result = new LinkedList<>(table1);
        result.sort(Comparator.comparing(Pair::getId));
        return result;
    }

    public LinkedList<Pair> getTable2AsLinkedList() {
        LinkedList<Pair> result = new LinkedList<>(table2);
        result.sort(Comparator.comparing(Pair::getId));
        return result;
    }

    public HashMap<Long, String> getTable1AsHashMap() {
        HashMap<Long, String> result = new HashMap<>();
        for(Pair line : table1){
            result.put(line.getId(), line.getValue());
        }
        return result;
    }

    public HashMap<Long, String> getTable2AsHashMap() {
        HashMap<Long, String> result = new HashMap<>();
        for(Pair line : table2){
            result.put(line.getId(), line.getValue());
        }
        return result;
    }
}
