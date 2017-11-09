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

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        printOutOptions();
        if(keyboard.next().equals("1")){
            startPitchAnalysis();
        } else if(keyboard.next().equals("2")){
            startPitchAnalysis();
            bool = true;
        }

    }

    private static void startPitchAnalysis(){
        PitchDetectionHandler handler = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
                float pitch = pitchDetectionResult.getPitch();
                if(pitch != -1.0){
                    System.out.println(audioEvent.getTimeStamp() + " ");
                    if(bool==true){
                        PitchComparison comp = new PitchComparison(pitch);
                        System.out.println(comp.determinePitch());
                    } else {
                        System.out.println(pitch);
                    }
                }
            }
        };
        try {
            AudioDispatcher adp = AudioDispatcherFactory.fromDefaultMicrophone(2048, 0);
            adp.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, 44100, 2048, handler));
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
