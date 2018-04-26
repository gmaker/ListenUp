package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sean Murphy on 4/22/2018.
 */
public class CirclePanel extends JPanel{

    private int numCircle;
    private int numCircleFill = -1;

    public CirclePanel(int num){
        numCircle = num;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.RED);
        for(int i=0; i<numCircle; i++) {
            int x = (i*35)+10;
            g.drawOval(x, 20, 25, 25);
        }
        if(numCircleFill != -1){
            int temp = (numCircleFill*35)+10;
            g.fillOval(temp, 20, 25, 25);
            numCircleFill = -1;
        }
    }

    public void setNumCircleFill(int num){
        numCircleFill = num;
    }

}
