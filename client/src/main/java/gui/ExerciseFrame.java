package gui;

import Pitches.ExerciseData;
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
    private int level;
    private int exerciseNumR;
    private int noteNumber;
    private int circleNum;
    private int circleNumFill = 0;
    private int noteCount = 0;
    private String exerciseNotes;
    private int exerciseNoteAmount;
    private ArrayList<String> resultList;

    /**  **/
    private ArrayList<String> exerciseData;
    private ExerciseCalc ec;

    /**  **/
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel exerciseNameLabel;
    private CirclePanel circlePanel;

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


    public ExerciseFrame(String c, int l){

        command = c;
        level = l;
        setTitle(command + " Exercise");

        mainPanel = new JPanel(new GridLayout(5, 1, 10, 0));
        mainPanel.setPreferredSize(new Dimension(400, 350));

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(400, 50));
        buttonPanel.setBackground(Color.BLACK);

        startButton = new JButton("Start");
        startButton.setFocusPainted(true);
        replayButton = new JButton("Replay");

        createButton(startButton, buttonPanel);
        createButton(replayButton, buttonPanel);

        instructionLabel = new JLabel();
        instructionLabel.setFont(LABELFONT);


        exerciseName = new JTextField();
        exerciseName.setPreferredSize(new Dimension(275, 50));
        exerciseName.setFont(EXERCISEFONT);
        exerciseName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        exerciseName.setHorizontalAlignment(SwingConstants.CENTER);
        exerciseName.setEditable(false);

        exerciseNameLabel = new JPanel();
        exerciseNameLabel.add(exerciseName, BorderLayout.CENTER);

        guideLabel = new JLabel();
        String labelText = "<html><p style=\"text-align:center\">" +
                "Press start when you are ready.<br>Press replay if you would like to start over.</p></html>";
        guideLabel.setText(labelText);

        createLabel(new JLabel(), mainPanel);
        createLabel(instructionLabel, mainPanel);
        mainPanel.add(exerciseNameLabel);
        createLabel(guideLabel, mainPanel);

        createExerciseInformation();
        circlePanel = new CirclePanel(circleNum);
        createCirclePanel();

        add(mainPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(new Dimension(475, 475));
        //pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        ec = new ExerciseCalc(exerciseNoteAmount);
    }

    private void createCirclePanel(){
        circlePanel.setSize(400, 100);
        mainPanel.add(circlePanel, BorderLayout.SOUTH);
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
        exerciseData = ExerciseData.getData(command, level);
        exerciseInformation();
    }

    private void exerciseInformation(){
        String numExercises = exerciseData.get(0);
        getRandomNumber(numExercises);
        parseExerciseData();
    }

    private void parseExerciseData(){
        ArrayList<String> e = new ArrayList<>();
        String exercise = exerciseData.get(exerciseNumR);
        for(String temp : exercise.split(";")){
            e.add(temp);
        }
        exerciseName.setText(e.get(1));
        noteNumber = Integer.parseInt(e.get(2));
        if(e.get(3).equals("f")){
            MainWindow.callCoreCommand("flat");
        } else {
            MainWindow.callCoreCommand("sharp");
        }
        circleNum = Integer.parseInt(e.get(4));
        exerciseNoteAmount = circleNum;
        exerciseNotes = e.get(5);
    }

    private void getRandomNumber(String numExercises){
        Random r = new Random();
        int n = Integer.parseInt(numExercises.substring(2,4));
        exerciseNumR = r.nextInt(n) + 1;
        System.out.println("random number " + exerciseNumR);
    }

    private void setPanelText(){
        String rephrase = null;
        if(command != null && command.length() > 1 && command.charAt(command.length()-1)=='s'){
            rephrase = command.substring(0, command.length() - 1);
        }
        assert rephrase != null;
            rephrase = rephrase.toLowerCase();
        if(command.equals("Intervals")) {
            instructionLabel.setText("<html>Sing the " + rephrase + ".</html>");
        } else {
            instructionLabel.setText("<html><p style=\"text-align:center\">" +
                    "Sing the " + rephrase + " from the given starting note.</p></html>");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            startExercise();
        } else if(e.getSource() == replayButton){
            resetExerciseFrame();
        }
    }

    private void startExercise(){
        noteCount = 0;
        startButton.setText("Listening..");
        startButton.setEnabled(false);
        MainWindow.callCoreCommand("Notes");
        ec.playNote(noteNumber);
    }

    private void updateCircles(){
        circlePanel.setNumCircleFill(circleNumFill);
        circlePanel.repaint();
        circleNumFill++;
    }

    private void resetExerciseFrame(){
        resetStartButton();
        MainWindow.callCoreCommand("Stop");
        ec.restart();
        createNewCirclePanel();
        noteCount = 0;
    }

    private void createNewCirclePanel(){
        mainPanel.remove(circlePanel);
        createCirclePanel();
        circleNumFill=0;
        circlePanel.resetFillArray();
    }

    private void resetStartButton(){
        startButton.setText("Start");
        startButton.setEnabled(true);
    }

    public void noteChanged(String note, float pitch) {
        System.out.println("noteChanged in ExerciseFrame: " + note + " " + pitch);
        int temp = ec.addNote(note, noteCount, pitch);
        if(temp==1){
            noteCount++;
        } else if(temp==0){
            updateCircles();
        } else if(temp==2){
            exerciseNoteComparison();
        } else if(temp==3){
            new ResponseFrame("warning", this);
            MainWindow.callCoreCommand("Stop");
            noteCount++;
        }
    }

    private void exerciseNoteComparison(){
        MainWindow.callCoreCommand("Stop");
        resultList = ec.reportNotes(exerciseNotes);
        String result = resultList.get(0);
        resultList.remove(0);
        if(result.equals("true")){
            new ResponseFrame("success", this);
        } else {
            new ResponseFrame("fail", this);
        }
    }

    public ArrayList<String> getInputList(){
        assert !resultList.isEmpty();
            return resultList;
    }

    public String getNoteList(){
        assert !exerciseNotes.isEmpty();
            return exerciseNotes;
    }

    public void newExercise(){
        resetExerciseFrame();
        exerciseInformation();
    }

    public void resetCurrentExercise() {
        resetExerciseFrame();
    }
}
