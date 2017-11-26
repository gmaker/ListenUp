package Pitches;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sean Murphy on 10/30/2017.
 */

public class PitchComparison {

    private String filename;
    //private static Map<Float, String> pitchMap = new HashMap<>();
    private float[][] pitchArray;
    private ArrayList<String> notes;
    private final int PITCHRANGE = 4;

    public PitchComparison(String f){
        //System.out.println("constructor");
        filename = f;
    }

    public String determinePitch(float p) {
        //System.out.println("determinePitch");

        if(validPitch(p).equals("yes")) {
            int range = determineRange(p);
            String note = comparePitchValues(p, range);
            return note;
        } else {
            return "Not valid pitch. Try Again";
        }
    }

    private String validPitch(float p){
        if(p<pitchArray[0][0] || p>pitchArray[pitchArray.length-1][PITCHRANGE-1]){
            return "no";
        } else {
            return "yes";
        }
    }

    private int determineRange(float p) {
        //System.out.println("determineRange");
        /*float down = pitchAccuracy(p, "down");
        float up = pitchAccuracy(p, "up");

        if(down>= 65.41 && up<= 127.14999){
            return 0;
        } else if(down>= 127.15 && up<= 254.28999){
            return 1;
        } else if(down>= 254.29 && up<= 508.56999){
            return 2;
        } else if(down>= 508.57 && up<= 987.77){
            return 3;
        } else {
            return -1;
        }*/

        /**
         * temp = [0][n] - [pA.length-1][n-1];
         * sum = temp/2;
         * ([0][n] + sum) - 0.00001;
         * ([pA.length-1][n-1] - sum);
         */
        /*
        int l = pitchArray.length - 1;
        float below = 61.74f;
        float above = 1046.5f;
        int range = -1;
        float p0, p1, p2, p3, temp1, temp2;
        for(int i=0; i<PITCHRANGE; i++){
            if(i==0){
                p0 = below;
                p3 = pitchArray[0][i+1];
            } else if(i==3){
                p0 = pitchArray[l][i-1];
                p3 = above;
            } else {
                p0 = pitchArray[l][i-1];
                p3 = pitchArray[0][i+1];
            }
            p1 = pitchArray[0][i];
            p2 = pitchArray[l][i];
            temp1 = (p1 + ((p1 - p0)/2));
            temp2 = (p2 + ((p3 - p2)/2)) - 0.00001f;
            System.out.println(temp1 + " " + temp2);
            if(p >= temp1 && p <= temp2){
                range = i;
            }
        }
        System.out.println(range);
        return range;*/


        if(p>= 65.41 && p<=127.14999){ //[0][0] && [pitchArray.length-1][0]
            return 0;
        } else if(p>= 127.15 && p<= 254.28999){ //[0][1] && [pitchArray.length-1][1]
            return 1;
        } else if(p>= 254.29 && p<= 508.56999){ //[0][2] && [pitchArray.length-1][2]
            return 2;
        } else if(p>= 508.57 && p<= 987.77){ //[0][3] && [pitchArray.length-1][3]
            return 3;
        } else {
            return -1;
        }
    }

    private float pitchAccuracy(float p, String d){
        if(d.equals("down")){
            return p * .99318182f;
        } else {
            return p * 1.00681818f;
        }
    }

    private String comparePitchValues(float p, int range){
        float pBot = 0, pTop = 0;
        int marker = -1;
        for(int i=0; i<pitchArray.length; i++) {
            if(p<pitchArray[i][range] && i>0){
                pBot = pitchArray[i-1][range];
                pTop = pitchArray[i][range];
                marker = i;
                i = pitchArray.length;
            } else if(p<pitchArray[i][range] && i==0){
                pBot = pitchArray[pitchArray.length-1][range-1];
                pTop = pitchArray[i][range];
                marker = i;
                i = pitchArray.length;
            } else {
                if(p>pitchArray[i][range] && i==pitchArray.length-1){
                    pBot = pitchArray[i][range];
                    pTop = pitchArray[0][range+1];
                    marker = i;
                    i = pitchArray.length;
                }
            }
        }
        //System.out.println(pBot + " " + pTop + " " + p);
        if(marker==16){
            if ((pBot / p) >= .99318182f) {
                return notes.get(marker);
            } else if ((pTop / p) <= 1.00681818f) {
                return notes.get(0);
            } else {
                return "X";
            }
        } else {
            if ((pBot / p) >= .99318182f) {
                return notes.get(marker - 1);
            } else if ((pTop / p) <= 1.00681818f) {
                return notes.get(marker);
            } else {
                return "X";
            }
        }

    }

    public void create(){
        Path filePath = Paths.get(filename);
        Scanner scan = null;
        try {
            scan = new Scanner(filePath);
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
        //System.out.println(pitchArray.length + " " + pitchArray[0].length);
    }
}

/*enum Pitch {
    CN (65.41),
    CS (69.3),
    DF (69.3),
    DN (73.42),
    DS (77.78),
    EF (77.78),
    EN (82.41),
    FN (87.31),
    FS (92.50),
    GF (92.50),
    GN (98.0),
    GS (103.83),
    AF (103.83),
    AN (110.0),
    AS (116.54),
    BF (116.54),
    BN (123.47);

    private final double pitchBase;

    Pitch(double pitch){
        pitchBase = pitch;
    }
}*/


/**
 * Pitch frequency ratios, where f is the original frequency:
 *
 * whole tone 9/8
 * half tone 256/243
 *
 * minor 2nd       256/243f
 * major 2nd       9/8f
 * minor 3rd       6/5f
 * major 3rd       5/4f
 * perfect 4th     4/3f
 * tritone         64/45f OR 45/32f OR 4073/2880f (last one is most accurate)
 * perfect 5th     3/2f
 * minor 6th       8/5f
 * major 6th       5/3f OR 27/16f
 * minor 7th       16/9f OR 9/5f (first one seems to be more accurate)
 * major 7th       15/8f
 * perfect octave  2f
 *
 * RANGES:
 *
 * Bass - G1-E4
 * Tenor - A2-D5
 * Alto - C3-C6
 * Soprano - G3-Gf6
 *
 * .99318182 * f = accuracy range down
 * 1.00681818 * f = accuracy range up
 */
