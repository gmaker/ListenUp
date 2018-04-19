package gui;

import Pitches.CoreController;
import Pitches.ExerciseData;
import Pitches.NoteListener;
import Pitches.ExerciseCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sean Murphy on 3/6/2018.
 */
public class ExerciseFrame extends JFrame implements ActionListener {

    /**  **/
    private String command;
    private int exerciseNumR;
    private int noteNumber;

    /**  **/
    private ArrayList<String> exerciseData;
    private ExerciseCalc ec;

    /**  **/
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel exerciseNameLabel;

    /**  **/
    private JLabel instructionLabel;
    private JLabel guideLabel;
    private JTextField exerciseName;

    private JButton startButton;
    private JButton replayButton;

    /**  **/
    private final Font BUTTONFONT = new Font(
            "Arial", Font.PLAIN, 25);
    private final Font LABELFONT = new Font(
            "Arial", Font.BOLD, 30);
    private final Font EXERCISEFONT = new Font(
            "Arial", Font.PLAIN, 15);


    public ExerciseFrame(String c){

        command = c;
        setTitle(command + " Exercise");

        mainPanel = new JPanel(new GridLayout(5, 1, 10, 0));
        mainPanel.setPreferredSize(new Dimension(400, 350));

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(400, 50));
        buttonPanel.setBackground(Color.BLACK);

        startButton = new JButton("Start");
        replayButton = new JButton("Replay");

        createButton(startButton, buttonPanel);
        createButton(replayButton, buttonPanel);

        instructionLabel = new JLabel();
        instructionLabel.setFont(LABELFONT);

        exerciseName = new JTextField();
        exerciseName.setPreferredSize(new Dimension(250, 50));
        exerciseName.setFont(EXERCISEFONT);
        exerciseName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        exerciseName.setHorizontalAlignment(SwingConstants.CENTER);
        exerciseName.setEditable(false);

        exerciseNameLabel = new JPanel();
        exerciseNameLabel.add(exerciseName, BorderLayout.CENTER);

        guideLabel = new JLabel("Press start or the spacebar when you are ready.");

        createLabel(new JLabel(), mainPanel);
        createLabel(instructionLabel, mainPanel);
        mainPanel.add(exerciseNameLabel);
        createLabel(guideLabel, mainPanel);

        createExerciseInformation();

        add(mainPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(new Dimension(400, 400));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        ec = new ExerciseCalc();
    }

    private void createButton(JButton button, JPanel p){
        button.addActionListener(this);
        button.setFont(BUTTONFONT);
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        setButtonDimension(button, p);
        p.add(button);
    }

    private void setButtonDimension(JButton b, JPanel p){
        b.setPreferredSize(new Dimension(150, 40));
        p.add(Box.createRigidArea(new Dimension(10, 0)));
    }

    private void createLabel(JLabel l, JPanel p){
        l.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(l);
    }

    private void createExerciseInformation(){
        setPanelText();
        exerciseData = ExerciseData.getData(command, 1);
        getRandomNumber();
        parseExerciseData();
    }

    private void parseExerciseData(){
        ArrayList<String> e = new ArrayList<>();
        String exercise = exerciseData.get(exerciseNumR);
        for(String temp : exercise.split(";")){
            e.add(temp);
        }
        System.out.println(exerciseNumR);
        exerciseName.setText(e.get(1));
        noteNumber = Integer.parseInt(e.get(2));
        if(e.get(3).equals("f")){
            MainWindow.callCoreCommand("flat");
        }
    }

    private void getRandomNumber(){
        Random r = new Random();
        exerciseNumR = r.nextInt(4);
    }

    private void setPanelText(){
        String rephrase = null;
        System.out.println(command + " " + command.length());
        if(command != null && command.length() > 1){
            rephrase = command.substring(0, command.length() - 1);
        }
        instructionLabel.setText("Sing the " + rephrase + ".");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            startButton.setText("Listening..");
            MainWindow.callCoreCommand("Notes");
            ec.playNote(noteNumber);
            ec.startTimer();
        } else if(e.getSource() == replayButton){
            startButton.setText("Start");
            MainWindow.callCoreCommand("Stop");
            ec.reportNotes();
        }
    }

    public void noteChanged(String note, float pitch) {
        //System.out.println("noteChanged in ExerciseFrame: " + note + " " + pitch);
        ec.addNote(note);
    }
}
