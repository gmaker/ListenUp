package Pitches;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sean Murphy on 11/26/2017.
 */
public class NoteMap{

    private static NoteMap n;
    private ArrayList<String> notes;
    private float[][] pitchArray;
    private final int PITCHRANGE = 4;

    public NoteMap(){
        n = this;
    }

    public static NoteMap getNoteMap() {return n;}
    public float[][] getPitchArray() {return pitchArray;}
    public ArrayList<String> getNotes() {return notes;}

    public void create(String filename){
        try {
            Scanner scan = new Scanner(new File(filename));
            createPitchMap(scan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPitchMap(Scanner scan){
        notes = new ArrayList<>();
        ArrayList<Float> frequencies = new ArrayList<>();
        int noteAmount = 0, temp = 0;
        while(scan.hasNext()){
            String s = scan.next();
            if(s.substring(0,1).matches("\\d+")){
                frequencies.add(Float.parseFloat(s));
            } else {
                notes.add(s);
                noteAmount++;
            }
        }
        pitchArray = new float[noteAmount][PITCHRANGE];
        for(int i=0; i<pitchArray.length; i++){
            for(int j=0; j<pitchArray[i].length; j++){
                pitchArray[i][j] = frequencies.get(temp);
                temp++;
            }
        }
    }
}
