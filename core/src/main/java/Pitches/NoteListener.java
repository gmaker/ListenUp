package Pitches;

import java.util.EventListener;

/**
 * Created by Sean Murphy on 11/26/2017.
 */
public interface NoteListener extends EventListener{
    void noteChanged(String note, float pitch);
}
