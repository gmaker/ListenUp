package Pitches;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.*;

import javax.sound.sampled.LineUnavailableException;
import java.util.ArrayList;

/**
 * Created by Sean Murphy on 10/20/2017.
 */
public class FrequencyDetector{

    private float pitch = 0.0f;
    private boolean notes = false;
    private boolean stop = true;
    private PitchComparison comp;
    private AudioDispatcher adp;


    public FrequencyDetector(PitchComparison pc) {
        comp = pc;
    }

    public void start(){
        stop = false;
        startPitchAnalysis();
    }

    public void stopPitch(){
        if(!stop && !adp.isStopped()){
            adp.stop();
            notes = false;
        }
    }

    public void togglePitchComparison(){
        notes = true;
    }

    private void startPitchAnalysis(){
        PitchDetectionHandler handler = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
                pitch = pitchDetectionResult.getPitch();
                if(pitch != -1.0f) {
                    if (notes == false) {
                        System.out.println(pitch);
                    } else {
                        comp.determinePitch(pitch); //246.36871f
                    }
                }
            }
        };
        try {
            adp = AudioDispatcherFactory.fromDefaultMicrophone(2048, 0);
            adp.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.AMDF, 44100, 2048, handler));
            adp.run();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
