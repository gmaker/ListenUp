package Pitches;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.*;
import gui.NotePanel;

import javax.sound.sampled.LineUnavailableException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sean Murphy on 10/20/2017.
 */
public class FrequencyDetector {

    private float pitch = 0.0f;
    private static boolean bool = false;
    private static PitchComparison comp;
    private ArrayList<NoteListener> noteListeners = new ArrayList<>();

    /*public static void main(String[] args){
        FrequencyDetector fq = new FrequencyDetector();

        System.out.println("What's your number: ");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.next();
        if(input.equals("2")){
            bool = true;
        }
        NoteMap map = new NoteMap();
        map.create("Pitches.txt");
        comp = new PitchComparison();
        fq.addListener(comp);
        fq.start();

    }*/

    public FrequencyDetector() {}

    public void start(){
        startPitchAnalysis();
    }
    public void togglePitchComparison(){
        comp = new PitchComparison();
        addListener(comp);
        bool = true;
    }

    private void startPitchAnalysis(){
        PitchDetectionHandler handler = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
                pitch = pitchDetectionResult.getPitch();
                if(pitch != -1.0f) {
                    if (bool == false) {
                        System.out.println(pitch);
                        //NotePanel.appendTextArea(pitch + "");
                    } else {
                        String note = comp.determinePitch(pitch);
                        if(!note.equals("X")){
                            System.out.println(note);
                            callListener(note, pitch);
                            //NotePanel.appendTextArea(note + " " + pitch);
                        }
                    }
                }
            }
        };
        try {
            AudioDispatcher adp = AudioDispatcherFactory.fromDefaultMicrophone(2048, 0);
            adp.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.AMDF, 44100, 2048, handler));
            adp.run();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void addListener(NoteListener toAdd){
        noteListeners.add(toAdd);
        System.out.println(toAdd);
    }
    private void callListener(String n, float p){
        for(NoteListener n1 : noteListeners){
            n1.noteChanged(n, p);
        }
    }
}
