package Pitches;

import javax.sound.midi.*;
import java.util.ArrayList;

/**
 * Created by Sean Murphy on 4/19/2018.
 */
public class ExerciseCalc {

    private String[][] noteArray;
    private int noteCount = 0;

    public ExerciseCalc(int n){
        noteArray = new String[n][25];
    }

    public int addNote(String note, int numNotes){
        if(noteCount==8){
            noteArray[numNotes][noteCount] = note;
            noteCount++;
            return 0;
        } else if(noteCount>8 && !note.equals(noteArray[numNotes][noteCount-1])){
            noteCount = 0;
            System.out.println("" + numNotes%noteArray.length);
            return 1;
        } else if(noteCount>8 && noteArray.length-numNotes==1){
            return 2;
        } else {
            System.out.printf("Adding %s to array at %s and %s\n", note, numNotes, noteCount);
            noteArray[numNotes][noteCount] = note;
            noteCount++;
            return -1;
        }
    }

    public boolean reportNotes(String notes){
        ArrayList<String> noteList = new ArrayList<>();
        int count = 0;
        for(String temp : notes.split(",")){
            noteList.add(temp);
            count++;
        }

        ArrayList<String> averageNotes = new ArrayList<>();
        System.out.println(noteCount);
        for(int i=0; i<count; i++){
            ArrayList<String> notesSung = new ArrayList<>();
            for(int j=0; j<8; j++) {
                notesSung.add(noteArray[i][j]);
            }
            String temp = getAverageNote(notesSung);
            averageNotes.add(temp);
        }

        for(int k=0; k<noteList.size(); k++){
            if(!averageNotes.get(k).equals(noteList.get(k))){
                return false;
            }
        }
        return true;
    }

    private String getAverageNote(ArrayList<String> notesSung){
        String note;
        String temp1=null, temp2=null, temp3=null;
        int count1=0, count2=0, count3=0;
        for(int i=0; i<notesSung.size(); i++){
            String temp = notesSung.get(i);
            if(i==0){
                temp1 = temp;
                count1++;
            } else if(temp.equals(temp1)){
                count1++;
            } else if(temp.equals(temp2)){
                count2++;
            } else if(temp.equals(temp3)){
                count3++;
            } else {
                if(temp2==null){
                    temp2 = temp;
                    count2++;
                } else if(temp3==null){
                    temp3 = temp;
                    count3++;
                }
            }
        }
        if(count1 >= count2 && count1 >= count3){
            note = temp1;
        } else if(count2 >= count3){
            note = temp2;
        } else {
            note = temp3;
        }
        return note;
    }

    public void restart(){
        for(int i=0; i<noteArray.length; i++){
            for(int j=0; j<noteArray[0].length; j++){
                noteArray[i][j] = null;
            }
        }
        noteCount=0;
    }

    public void playNote(int noteNumber){
        try{
    /* Create a new Sythesizer and open it. Most of
     * the methods you will want to use to expand on this
     * example can be found in the Java documentation here:
     * https://docs.oracle.com/javase/7/docs/api/javax/sound/midi/Synthesizer.html
     */
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();
            //get and load default instrument and channel lists
            Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
            MidiChannel[] mChannels = midiSynth.getChannels();

            midiSynth.loadInstrument(instr[0]);//load an instrument


            mChannels[0].noteOn(noteNumber, 100);//On channel 0, play note number 60 with velocity 100
            try { Thread.sleep(1000);// wait time in milliseconds to control duration
            } catch( InterruptedException e ) { }
            mChannels[0].noteOff(noteNumber);//turn of the note

        } catch (MidiUnavailableException e) {}
    }

}