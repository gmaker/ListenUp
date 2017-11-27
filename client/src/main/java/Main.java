import Pitches.FrequencyDetector;
import Pitches.NoteMap;
import Pitches.PitchComparison;
import gui.MainWindow;

/**
 * Created by Sean Murphy on 11/26/2017.
 */
public class Main {

    public static void main(String[] args) {

        // create the Main Window
        //new MainWindow();
        NoteMap map = new NoteMap();
        map.create("C:\\Users\\smsea\\IdeaProjects\\capstone\\resources\\Pitches.txt");
        PitchComparison comp = new PitchComparison();
        System.out.println(comp.determinePitch(440.43432f));

        //FrequencyDetector fq = new FrequencyDetector();
        //fq.start();
    }
}
