package Rhythm;

import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Sean Murphy on 11/27/2017.
 */

//public class MinimMethod extends Application{

    /* Used to trick minim constructor (needs an object with those methods) */
    public class MinimMethod extends Application{

        String sketchPath( String fileName ) {
            return "";
        }
        InputStream createInput(String fileName) {
            return new InputStream() {
                @Override
                public int read() throws IOException {
                    return 0;
                }
            };
        };


    @Override
    public void start(Stage stage) throws Exception {

        /*...
        //Some gui logic here!
        ...*/

        //stage.setScene(scene);
        //stage.show();

        /* Beat detect thread */
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                Minim minim = new Minim(new MinimMethod());

                AudioInput input = minim.getLineIn(Minim.STEREO);
                BeatDetect beatDetect = new BeatDetect(1024, 44100.0f);
                beatDetect.setSensitivity(5000); //Tried different values here
                //input.enableMonitoring();

                int i=0;

                while (true) {
                    beatDetect.detect(input.mix);

                    if(beatDetect.isHat()) {
                        System.out.print("HAT");
                    }

                    if(beatDetect.isSnare()) {
                        System.out.print("SNARE");
                    }

                    if (beatDetect.isKick()) {
                        System.out.print("KICK");
                    }
                }
            }
        });

        th.start();
    }
}
