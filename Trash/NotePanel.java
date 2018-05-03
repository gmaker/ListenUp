
import javax.swing.*;
import java.awt.*;
import Pitches.*;

/**TODO: DELETE THIS FILE - IT IS NOW OBSOLETE **/

/**
 * Created by Sean Murphy on 11/26/2017.
 */
public class NotePanel extends JPanel implements NoteListener {

    //private static NotePanel np;
    private String n;
    private static JTextArea textArea;

    public NotePanel(){

        this.setPreferredSize(new Dimension(200, 500));

        textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setCaretPosition(textArea.getDocument().getLength());

        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setLayout(new BorderLayout());
        this.add(textArea);

        this.setVisible(true);
    }

    /** Does not work currently **/
    @Override
    public void noteChanged(String note, float pitch) {
        System.out.println("noteChanged in notePanel: " + note);
        n = note;
        textArea.append(n + " " + pitch+ " hello listener");
    }

    /** Temporary fix for appending note data to the text area **/
    public static void appendTextArea(String str){
        System.out.println("append text area");
        System.out.println(str);
        textArea.append(str + "\n");
    }

    /*@Override
    public void noteChanged(String note, float pitch) {
        this.note = note;
        textArea.append(note);
        repaint();
    }*/
}
