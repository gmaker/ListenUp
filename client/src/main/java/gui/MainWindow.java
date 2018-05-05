package gui;

import Pitches.CoreController;
import Pitches.NoteListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sean Murphy on 11/25/2017.
 */
public class MainWindow extends JFrame implements NoteListener{

    /** Different Exercise Panels **/
    private ScaleIntervalPanel SIPanel;
    private ChordPanel CPanel;
    //private RhythmPanel RPanel;
    //private PitchMelodyPanel PMPanel;
    private static ExerciseFrame ex;
    private static LevelFrame lf;

    /** Panels for Main Menu Screen **/
    private JPanel eastPanel;

    /** Creating the Note Map for Pitch to Note comparison **/
    private static CoreController controller;


    public MainWindow() {

        setTitle("Listen Up!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        controller = new CoreController();
        controller.addListener(this);

        this.setJMenuBar(new AppMenuBar(this));

        JLabel contentPane = new JLabel(new ImageIcon("bluemic8.jpg"));
        add(contentPane);
        contentPane.setLayout(new BorderLayout());

        SIPanel = new ScaleIntervalPanel();
        CPanel = new ChordPanel();
        //RPanel = new RhythmPanel();
        //PMPanel = new PitchMelodyPanel();

        eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.add(SIPanel);
        eastPanel.add(CPanel);
        //eastPanel.add(RPanel);
        //eastPanel.add(PMPanel);
        eastPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Listen Up!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 125));
        titleLabel.setForeground(Color.WHITE);

        contentPane.add(titleLabel, BorderLayout.NORTH);
        contentPane.add(eastPanel, BorderLayout.WEST);

        add(contentPane);

        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Method to create Exercise Frame for exercises
     * Called from sub-panel classes
     */
    public static void addLevelFrame(String str){
        System.out.println("Change Main Panel " + str);
        lf = new LevelFrame(str);
    }

    public static void addExerciseFrame(String str, int n){
        ex = new ExerciseFrame(str, n);
    }

    public static void callCoreCommand(String str) {
        controller.callAction(str);
    }

    @Override
    public void noteChanged(String note, float pitch) {
        if(pitch!=81.818184f) {
            //System.out.println(note + " " + pitch);
            ex.noteChanged(note, pitch);
        }
    }
}
