package Pitches;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sean Murphy on 3/6/2018.
 */
public class ExerciseData {

    private final String FILENAME = "ExerciseData.txt";
    private static ArrayList<String> data;


    public ExerciseData() {
        try {
            Scanner scan = new Scanner(new File(FILENAME));
            createDataList(scan);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void createDataList(Scanner scan){
        data = new ArrayList<>();
        while(scan.hasNextLine()){
            String s = scan.nextLine();
            data.add(s);
        }
        System.out.println("exercise data created");
    }

    /**
     * Data structure is as follows:
     * First number is the level, second number is the exercise number, third item
     * is name of exercise, fourth items and beyond are notes and corresponding
     * pitches that pertain to the exercise (amount of notes depends on the type
     * of exercise)
     *
     * @param type
     * @param level
     * @return
     */
    public static ArrayList<String> getData(String type, int level){
        System.out.printf("get Data called with %s and %s\n", type, level);
        ArrayList<String> tempData = new ArrayList<>();
        if(data.isEmpty()){
            System.err.println("Data ArrayList is empty!!");
        }
        for(int i=0; i<data.size(); i++){
            if(data.get(i).equals(type)) {
                for(int j=i+1; j<data.size(); j++){
                    int tempNum = Character.getNumericValue(data.get(j).charAt(0));
                    if(tempNum==level) {
                        tempData.add(data.get(j));
                    } else {
                        j=data.size();
                    }
                }
            }
        }

        return tempData;
    }
}
