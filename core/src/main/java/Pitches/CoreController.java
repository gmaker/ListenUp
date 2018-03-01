package Pitches;

import java.util.ArrayList;

/**
 * Created by Sean Murphy on 11/27/2017.
 */
public class CoreController {

    private static CoreController cc;
    private ArrayList<String> actions;
    private FrequencyDetector fq = new FrequencyDetector();
    private NoteMap nm = new NoteMap();
    private PitchComparison pc = new PitchComparison();

    public CoreController() {
        //actions = new ArrayList<>();
        //nm.create("Pitches.txt");
        //cc = this;
    }
    public static CoreController getCoreController() {
        return cc;
    }

    public void callAction(String action){
        if(action.equals("fq_start")){
            fq.start();
        } else if(action.equals("pc_detectPitch")) {
            //fq.addListener(pc);
            fq.togglePitchComparison();
            fq.start();
        }
    }



}
