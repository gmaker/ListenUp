import be.tarsos.dsp.*;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.*;

import javax.sound.sampled.LineUnavailableException;

/**
 * Created by Sean Murphy on 10/20/2017.
 */
public class APITest {

    public static void main(String[] args){
        System.out.println("hello");
        PitchDetectionHandler handler = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
                System.out.println(audioEvent.getTimeStamp() + " " + pitchDetectionResult.getPitch());

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
}
