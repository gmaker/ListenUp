package Pitches;

import be.tarsos.dsp.*;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.*;

import javax.sound.sampled.LineUnavailableException;
import java.util.Scanner;

/**
 * Created by Sean Murphy on 10/20/2017.
 */
public class APITest {

    private static boolean bool = false;
    private static PitchComparison comp;

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        printOutOptions();
        String s = keyboard.next();
        if(s.equals("1")){
            startPitchAnalysis();
        } else if(s.equals("2")){
            comp = new PitchComparison("Pitches.txt");
            comp.create();
            bool = true;
            startPitchAnalysis();
        } else if(s.equals("3")){
            comp = new PitchComparison("C:\\Users\\smsea\\IdeaProjects\\capstone\\Pitches.txt");
            comp.create();
        }

    }

    private static void startPitchAnalysis(){
        PitchDetectionHandler handler = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
                float pitch = pitchDetectionResult.getPitch();
                if(pitch != -1.0){
                    if(bool==true){
                        System.out.print(pitch + " ");
                        String note = comp.determinePitch(pitch);
                        if(!note.equalsIgnoreCase("x")){
                            System.out.println(note);
                        }
                        System.out.println();
                    } else {
                        System.out.println(audioEvent.getTimeStamp() + " " + pitch);
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

    private static void printOutOptions(){
        System.out.println("Options: ");
        System.out.println("Press 1 for real-time pitch analysis with frequency numbers.\nPress 2 for real-time" +
                " pitch analysis with note values.");
    }
}
