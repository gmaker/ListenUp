package Pitches;

import java.util.ArrayList;

/**
 * Created by Sean Murphy on 11/27/2017.
 */
public class CoreController {

    /**  **/
    private FrequencyDetector fq;
    private PitchComparison pc;
    private ExerciseData d;
    private NoteMap nm = new NoteMap();
    private ArrayList<NoteListener> noteListeners;

    /**
     * Constructor for CoreController
     * automatically creates the NoteMap from the NoteData.txt file
     */
    public CoreController() {
        System.out.println("core controller initiated");
        noteListeners = new ArrayList<>();
        pc = new PitchComparison(noteListeners);
        fq = new FrequencyDetector(pc);
        d = new ExerciseData();
        nm.create("NoteDataNew.txt");
    }

    public void callAction(String action){
        System.out.println("callAction called with: " + action);
        /** Methods for Developer Actions **/
        if(action.equals("Frequencies")){ //TODO: Get rid of frequency option later
            multiThread(action);
        } else if(action.equals("Notes")) {
            startPitchAnalysis(action);
        } else if(action.equals("Stop")){
            stopPitchAnalysis();
        } else if(action.equals("flat")){
            pc.setFlatSharpToggle(false);
        }
        /** Actual Methods for Use (include some above) **/
        /*else if(eNum!=-1){
            getExerciseData(action, eNum);
            startPitchAnalysis(action);

        } else if(action.equals("flat")){
            pc.setFlatSharpToggle(false);
        }
        else {
            System.err.println("INVALID COMMAND");
        }*/
    }

    private void startPitchAnalysis(String action){
        fq.togglePitchComparison();
        new MultiThread(action, fq).go();
    }

    private void stopPitchAnalysis(){
        fq.stopPitch();
    }

    private void multiThread(String name){
        new MultiThread(name, fq).go();
    }

    public void addListener(NoteListener toAdd){
        noteListeners.add(toAdd);
    }

}
