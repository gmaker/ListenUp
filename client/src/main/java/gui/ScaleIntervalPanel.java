package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sean Murphy on 2/25/2018.
 */
public class ScaleIntervalPanel extends JPanel implements ActionListener{

    /** Panels for the Card Layout **/
    private JPanel mainPanel;

    /** Buttons for the Panels **/
    private JButton scaleButton;
    private JButton intervalButton;

    /** Font and Style for Buttons in this Panel **/
    private final Font BUTTONFONT = new Font(
            "Arial", Font.PLAIN, 35);


    public ScaleIntervalPanel() {

        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        scaleButton = new JButton("Scales");
        intervalButton = new JButton("Intervals");
        createButton(scaleButton, mainPanel);
        createButton(intervalButton, mainPanel);

        add(mainPanel);
        setOpaque(false);
    }

    private void createButton(JButton button, JPanel p){
        button.addActionListener(this);
        button.setFont(BUTTONFONT);
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createBevelBorder(0,
                Color.green, new Color(0, 0, 0, 0)));
        setButtonDimension(button, p);
        p.add(button);
    }

    private void setButtonDimension(JComponent j, JPanel p){
        j.setPreferredSize(j.getPreferredSize());
        p.add(Box.createRigidArea(new Dimension(30, 45)));
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(e.getSource() == scaleButton){
            exerciseFrame(command);
        } else if(e.getSource() == intervalButton){
            exerciseFrame(command);
        }

        else {
            System.err.println("ERROR IN ACTIONLISTENER IN ScaleIntervalPanel.java");
        }
    }

    private void exerciseFrame(String command){
        MainWindow.addExerciseFrame(command);
        System.out.println(command);
    }
}
