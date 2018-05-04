package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sean Murphy on 2/27/2018.
 */
public class ChordPanel extends JPanel implements ActionListener
{

    /** Panels for the ChordPanel class **/
    private JPanel mainPanel;

    /** Buttons for the panels for the ChordPanel class **/
    private JButton chordButton;
    private JButton tetrachordButton;

    /** Font and Style for Buttons in this class **/
    private final Font BUTTONFONT = new Font(
            "Arial", Font.PLAIN, 35);

    public ChordPanel() {

        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        chordButton = new JButton("Chords");
        tetrachordButton = new JButton("Tetrachords");
        createButton(chordButton, mainPanel);
        createButton(tetrachordButton, mainPanel);

        add(mainPanel);
        setOpaque(false);
    }

    private void createButton(JButton button, JPanel p){
        button.addActionListener(this);
        button.setFont(BUTTONFONT);
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createBevelBorder(0,
                Color.blue, new Color(0, 0, 0, 0)));
        setButtonDimension(button, p);
        p.add(button);
    }

    private void setButtonDimension(JComponent j, JPanel p){
        j.setPreferredSize(j.getPreferredSize());
        p.add(Box.createRigidArea(new Dimension(30, 35)));
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(e.getSource() == chordButton){
            exerciseFrameCommand(command);
        } else if(e.getSource() == tetrachordButton){
            exerciseFrameCommand(command);
        }
        else {
            System.err.println("ERROR IN ACTIONLISTENER IN ChordPanel.java");
        }
    }

    private void exerciseFrameCommand(String command){
        MainWindow.addLevelFrame(command);
        System.out.println(command);
    }
}
