package gui;

import Pitches.FrequencyDetector;
import Pitches.NoteMap;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sean Murphy on 11/25/2017.
 */
public class MainWindow extends JFrame {

    /** The panel containing notes from the microphone input **/
    private NotePanel notePanel;

    /** The panel containing buttons for application processes **/
    private MenuPanel menuPanel;

    /** Creating the Note Map for Pitch to Note comparison **/
    private NoteMap map;


    public MainWindow() {

        setTitle("Ear Training and Sight Singing Application");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(500, 200);

        JLabel emptyLabel = new JLabel();
        emptyLabel.setPreferredSize(new Dimension(500, 500));

        map = new NoteMap();
        map.create("Pitches.txt");


        this.setJMenuBar(new AppMenuBar(this));

        notePanel = new NotePanel();
        menuPanel = new MenuPanel();


        this.add(notePanel, BorderLayout.EAST);
        this.add(menuPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }
}
