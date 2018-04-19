package Pitches;

import javax.sound.midi.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sean Murphy on 4/19/2018.
 */
public class ExerciseCalc {

    Timer timer;
    private String[][] noteArray;
    private int noteCount = 0;
    private int noteNum = 0;

    public ExerciseCalc(){
        noteArray = new String[8][50];
    }

    public void addNote(String note){
        if(noteCount<50){
            System.out.println(note + " inside addNote " + noteNum + " " + noteCount);
            noteArray[noteNum][noteCount] = note;
        }
        noteCount++;
    }

    public void reportNotes(){
        for(int i=0; i<noteArray.length; i++){
            for(int j=0; j<noteArray[i].length; j++){
                System.out.print("note: " + noteArray[i][j]+ " ");
            }
            System.out.println();
        }
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

    public void startTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new NoteTimer(), 500, 1500);
    }

    class NoteTimer extends TimerTask {
        public void run(){
            System.out.println("New Note " + noteNum);
            if(noteNum==7){
                timer.cancel();
            } else {
                noteNum++;
            }
        }
    }

}



