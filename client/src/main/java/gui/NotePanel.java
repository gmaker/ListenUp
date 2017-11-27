package gui;

import javax.swing.*;
import Pitches.*;

import java.awt.*;

/**
 * Created by Sean Murphy on 11/26/2017.
 */
public class NotePanel extends JPanel implements NoteListener {

    private String n;
    private JTextArea textArea;


    public NotePanel(){
        this.setPreferredSize(new Dimension(200, 500));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setCaretPosition(textArea.getDocument().getLength());

    }

    @Override
    public void noteChanged(String note, float pitch) {
        n = note;
        textArea.append(n);

    }



    /*@Override
    public void noteChanged(String note, float pitch) {
        this.note = note;
        textArea.append(note);
        repaint();
    }*/
}
