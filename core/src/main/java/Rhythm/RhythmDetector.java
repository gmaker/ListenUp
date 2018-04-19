package Rhythm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

/**
 * Created by Sean Murphy on 11/27/2017.
 */
public class RhythmDetector implements KeyListener, ActionListener{

    private JTextField textField;
    private JFrame frame;

    private double bpm;
    private double bps;

    private long beatTimeBetween[] = new long[3];
    private long beats[] = new long[4];
    private int beatCounter = 0;
    private long beatTime;

    private Timer timer;

    public RhythmDetector() {


        //timer = new Timer(1000, this);

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        textField = new JTextField();
        textField.addKeyListener(this);

        frame.add(textField);

        frame.setSize(new Dimension(400, 400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        /*MinimMethod m = new MinimMethod();
        m.createInput("microphone");
        m.sketchPath("not sure");*/
        new RhythmDetector();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            long temp = System.currentTimeMillis();
            System.out.println(temp);
            calculateTempo(temp, beatCounter);
            beatCounter++;
        }
        else {
            System.out.println(0%4 + " " + 0%3 + " " + 7%4 + " " + 8%4);
        }
    }

    private void calculateTempo(long time, int count){
        int temp = count%4;
        //System.out.println(temp);
        beats[temp] = time;
        for(int i=0; i<beats.length; i++){
            System.out.println(beats[i]);
        }

        long tempSum, total = 0;
        if(count>=4){
            for(int i=1; i<beats.length; i++){
                tempSum = beats[i%4]-beats[(i%4)-1];
                System.out.println(beats[i] + " " + beats[i-1]);
                System.out.println("tempSum: " + tempSum);
                total += tempSum;

                System.out.println("total " + total);
            }
            bps = (double)(total/3)/1000;
            bpm = bps * 60;
            System.out.println("bps: " + bps + " bpm: " + bpm);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Timer started" + e.getSource());
    }
}
