package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sean Murphy on 4/22/2018.
 */
public class CirclePanel extends JPanel{

    private int numCircle;
    private int numCircleFill = -1;
    private int[] fillArray;

    public CirclePanel(int num){
        numCircle = num;
        fillArray = new int[numCircle];
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.RED);
        for(int i=0; i<numCircle; i++) {
            int x = (i*35)+10;
            g.drawOval(x, 20, 25, 25);
            //g.fillOval(x, 20, 25, 25);
        }
        if(numCircleFill != -1){
            g.setColor(Color.BLUE);
            int temp = (numCircleFill*35)+10;
            fillArray[numCircleFill] = temp;
            for(int k=0; k<fillArray.length; k++) {
                if(fillArray[k]!=0)
                    g.fillOval(fillArray[k], 20, 26, 26);
            }
            numCircleFill = -1;
        }
    }

    public void setNumCircleFill(int num){
        numCircleFill = num;
    }
    public void resetFillArray() {
        fillArray = new int[numCircle];
    }

}
