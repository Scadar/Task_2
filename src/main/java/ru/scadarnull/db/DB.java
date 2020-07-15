package ru.scadarnull.db;

import ru.scadarnull.entity.Line;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private List<Line> table1;
    private List<Line> table2;

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

    private boolean readFromFile(String file, List<Line> table){
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

    private void readLineFromFile(String line, List<Line> table) {
        String[] fullInfo = line.split(" ");
        table.add(new Line(Long.parseLong(fullInfo[0]), fullInfo[1]));
    }
}
