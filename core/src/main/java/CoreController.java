import Pitches.FrequencyDetector;

import java.util.ArrayList;

/**
 * Created by Sean Murphy on 11/27/2017.
 */
public class CoreController {

    private ArrayList<String> actions;

    public CoreController() {
        actions = new ArrayList<String>();
    }

    public void callAction(String action){
        if(action.equals("frequencyDetector")){
            createFrequencyDetector();
        } else if(action.equals("pitchComparison")) {

        } else {

        }
    }

    private void createFrequencyDetector(){

    }
}
