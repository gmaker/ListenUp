package Pitches;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sean Murphy on 5/2/2018.
 */
public class LevelData {

    private final String FILENAME = "Levels.txt";
    private Scanner scan;
    private ArrayList<String> levelData;

    public LevelData() {
        try {
            scan = new Scanner(new File(FILENAME));
            createLevelData(scan);
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }

    private void createLevelData(Scanner scanner){
        levelData = new ArrayList<>();
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            levelData.add(s);
        }
    }

    public ArrayList<String> getLevelData(String type){

        ArrayList<String> temp = new ArrayList<>();
        if(levelData.isEmpty()){
            System.err.println("Level Data ArrayList is empty!!");
        }
        for(int i = 0; i< levelData.size(); i++){
            if(levelData.get(i).equals(type)){
                int tempNum = Character.getNumericValue(levelData.get(i+1).charAt(0));
                for(int j=i; j<=tempNum+i; j++){
                    temp.add(levelData.get(j+2));
                }

            }
        }

        return temp;
    }

    public String[] parseLevels(String str, int size){
        String[] e = new String[size];
        int tempNum = 0;
        for(String temp : str.split(";")){
            e[tempNum] = temp;
            tempNum++;
        }

        return e;
    }
}
