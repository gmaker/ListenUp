package Pitches;

import javax.sound.midi.*;
import java.util.ArrayList;

/**
 * Created by Sean Murphy on 4/19/2018.
 */
public class ExerciseCalc {

    private String[][] noteArray;
    private int noteCount = 0;
    private int n;
    private float tempPitch;
    private final int STORENUMBER = 15;
    private final int MAXNUMBER = 40;

    public ExerciseCalc(int num){
        n = num;
        noteArray = new String[n][MAXNUMBER];
    }

    /**
     * Method to add a note from the user into a data structure to hold the note values
     * Makes several checks to return certain int values to trigger different actions
     * Returns
     *  0: updates the circles in ExerciseFrame to tell the user to go to the next note
     *  1: increases first index of array by +1
     *  2: calls the exerciseNoteComparison method, meaning that the exercise has finished
     *  3: calls a responseFrame with the warning, telling the user to move on to the next note
     *
     * @param note the note value from the NoteListener interface
     * @param numNotes the first index value of the array
     * @param pitch the float value of the pitch (used to compare in certain exercises)
     * @return number status of the method after the call
     */
    public int addNote(String note, int numNotes, float pitch) {
        String substr = note.substring(0,2);
        int result = -1;
        System.out.printf("here is what you are looking for: %s, %s\n", noteArray.length-numNotes, noteCount);
        if(noteCount==STORENUMBER) {
            result = 0;
            noteArray[numNotes][noteCount] = note;
            noteCount++;
            tempPitch = pitch;
            System.out.println("number 1");
        } else if(noteCount>=STORENUMBER) {
            if(noteCount==MAXNUMBER) {
                noteCount = MAXNUMBER - 10;
                result = 3;
                System.out.println("number 2");
            } else if(noteArray.length-numNotes==1){
                result = 2;
                System.out.println("number 4");
            } else if(!substr.equals(noteArray[numNotes][STORENUMBER].substring(0, 2))){
                noteCount = 0;
                result = 1;
                System.out.println("number 3");
            } else if((tempPitch - pitch) > 60f || (pitch - tempPitch) > 60f){
                noteCount = 0;
                result = 1;
                System.out.println("number 5");
            } else {
                noteCount++;
                System.out.println("number 6");
            }

        } else {
            if(numNotes < n){
                System.out.printf("Adding %s to array at %s and %s\n with array size %s", note, numNotes, noteCount, noteArray.length);
                noteArray[numNotes][noteCount] = note;
                noteCount++;
                tempPitch = pitch;
                System.out.println("number 7");
            }
        }

        /*if(noteCount==STORENUMBER){
            noteArray[numNotes][noteCount] = note;
            noteCount++;
            result = 0;
        } else if(noteCount>STORENUMBER) {
            if(noteCount>0) {
                if (!substr.equals(noteArray[numNotes][noteCount - 1].substring(0, 2))) {
                    noteCount = 0;
                    result =  1;
                }
            } else if (noteCount >= STORENUMBER && noteArray.length - numNotes == 1) {
                result = 2;
            } else if (noteCount >= STORENUMBER && ((tempPitch - pitch) > 40 || (pitch - tempPitch) > 40)) {
                noteCount = 0;
                result = 1;
            }
        } else if(noteCount==50) {
            noteCount = 0;
            result = 5;
        } else {
            System.out.printf("Adding %s to array at %s and %s\n with array size %s", note, numNotes, noteCount, noteArray.length);
            noteArray[numNotes][noteCount] = note;
            noteCount++;
            tempPitch = pitch;
            result = -1;
        }*/
        return result;
    }

    public ArrayList<String> reportNotes(String notes){
        noteCount = 0;
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
            for(int j=0; j<STORENUMBER; j++) {
                notesSung.add(noteArray[i][j]);
                System.out.println("results " + noteArray[i][j]);
            }
            String temp = getAverageNote(notesSung);
            averageNotes.add(temp);
            System.out.println(temp);
        }

        String result = "true";
        for(int k=0; k<noteList.size(); k++){
            if(!averageNotes.get(k).equals(noteList.get(k))){
                result = "false";
            }
        }
        averageNotes.add(0, result);
        return averageNotes;
    }

    private String getAverageNote(ArrayList<String> notesSung) {
        String note;
        String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null;
        int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0;
        for (int i = 0; i < notesSung.size(); i++) {
            System.out.printf("temp1 %s, temp2 %s, temp3 %s, temp4 %s, temp5 %s\n", temp1, temp2, temp3, temp4, temp5);
            String temp = notesSung.get(i);
            if (i == 0) {
                temp1 = temp;
                count1++;
            } else if (temp.equals(temp1)) {
                count1++;
                System.out.println("+1 for " + temp1 + " with total: " + count1);
            } else if (temp.equals(temp2)) {
                count2++;
                System.out.println("+1 for " + temp2 + " with total: " + count2);
            } else if (temp.equals(temp3)) {
                count3++;
                System.out.println("+1 for " + temp3 + " with total: " + count3);
            } else if (temp.equals(temp4)) {
                count4++;
                System.out.println("+1 for " + temp4 + " with total: " + count4);
            } else if (temp.equals(temp5)) {
                count5++;
                System.out.println("+1 for " + temp5 + " with total: " + count5);
            }
            else {
                if (temp2 == null) {
                    temp2 = temp;
                    count2++;
                } else if (temp3 == null) {
                    temp3 = temp;
                    count3++;
                } else if (temp4 == null) {
                    temp4 = temp;
                    count4++;
                } else if (temp5 == null){
                    temp5 = temp;
                    count5++;
                }
            }
        }
        if (count1 >= count2 && count1 >= count3 && count1 >= count4 && count1 >= count5) {
            note = temp1;
        } else if (count2 >= count3 && count2 >= count4 && count2 >= count5) {
            note = temp2;
        } else if(count3 >= count4 && count3 >= count5){
            note = temp3;
        } else if(count4 >= count5){
            note = temp4;
        } else {
            note = temp5;
        }
        return note;
    }

    public void restart(){
        noteArray = new String[n][50];
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