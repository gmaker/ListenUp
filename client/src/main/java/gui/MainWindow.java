package gui;

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

    /** Different Exercise Panels **/
    private ScaleIntervalPanel SIPanel;
    private ChordPanel CPanel;
    private RhythmPanel RPanel;
    private PitchMelodyPanel PMPanel;

    /** Panels for Main Menu Screen **/
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel southPanel;

    /** Creating the Note Map for Pitch to Note comparison **/
    private NoteMap map;

    /** Creating the CardLayout for different menus and exercises **/
    private JPanel cards;


    public MainWindow() {

        setTitle("Ear Training and Sight Singing Application");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //JLabel emptyLabel = new JLabel();
        //emptyLabel.setPreferredSize(new Dimension(500, 500));

        map = new NoteMap();
        map.create("NoteData.txt");

        this.setJMenuBar(new AppMenuBar(this));

        cards = new JPanel(new CardLayout());
        mainPanel = new JPanel(new BorderLayout());

        SIPanel = new ScaleIntervalPanel();
        CPanel = new ChordPanel();
        RPanel = new RhythmPanel();
        PMPanel = new PitchMelodyPanel();

        northPanel = new JPanel(new BorderLayout());
        northPanel.add(SIPanel, BorderLayout.WEST);
        northPanel.add(CPanel, BorderLayout.EAST);

        southPanel = new JPanel(new BorderLayout());
        southPanel.add(RPanel, BorderLayout.WEST);
        southPanel.add(PMPanel, BorderLayout.EAST);

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        cards.add(mainPanel, BorderLayout.CENTER);


        /*menuPanel = new MenuPanel();

        cards = new JPanel(new BorderLayout());
        cards.add(menuPanel, BorderLayout.CENTER);
        */
        this.add(cards);

        setSize(new Dimension(800, 550));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Method to change the cards of the MainWindow
     * Called from sub-panel classes
     */
    public static void changeMainPanel(String str){
        System.out.println("Change Main Panel " + str);
    }
}
