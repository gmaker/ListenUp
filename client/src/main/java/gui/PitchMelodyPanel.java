package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sean Murphy on 2/28/2018.
 */
public class PitchMelodyPanel extends JPanel implements ActionListener {

    /** Panels for the Card Layout **/
    private JPanel cards;
    private JPanel mainPanel;
    private JPanel labelPanel;
    private JPanel pitchTuningPanel;
    private JPanel sightSingingPanel;

    /** Buttons for the Panels **/
    private JButton pitchTuningButton;
    private JButton sightSingingButton;

    /**Font and Style for Buttons in this Panel **/
    private final Font BUTTONFONT = new Font(
            "Arial", Font.PLAIN, 30);
    private final Font LABELFONT = new Font(
            "Arial", Font.BOLD, 35);

    /** Other Items **/
    private JLabel label;

    public PitchMelodyPanel(){

        setLayout(new BorderLayout());
        cards = new JPanel(new CardLayout());
        cards.setOpaque(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        labelPanel = new JPanel();
        labelPanel.setOpaque(false);

        pitchTuningButton = new JButton("Pitch Tuning");
        sightSingingButton = new JButton("Sight Singing");
        createButton(pitchTuningButton, mainPanel);
        createButton(sightSingingButton, mainPanel);

        pitchTuningPanel = new JPanel();
        sightSingingPanel = new JPanel();

        /*label = new JLabel("Pitch and Melody");
        label.setFont(LABELFONT);
        label.add(Box.createRigidArea(new Dimension(0, 50)));
        label.setForeground(Color.YELLOW);

        labelPanel.add(label, BorderLayout.CENTER);*/
        cards.add(mainPanel, "Main Panel");

        //setPreferredSize(new Dimension(500, 375));
        add(labelPanel, BorderLayout.NORTH);
        add(cards);
        setOpaque(false);
    }

    private void createButton(JButton button, JPanel p){
        button.addActionListener(this);
        button.setFont(BUTTONFONT);
        button.setForeground(Color.WHITE);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createBevelBorder(0,
                Color.yellow, new Color(0, 0, 0, 0)));
        setButtonDimension(button, p);
        p.add(button);
    }

    private void setButtonDimension(JComponent j, JPanel p){
        j.setPreferredSize(j.getPreferredSize());
        p.add(Box.createRigidArea(new Dimension(10, 15)));
    }

    private void changeCards(String str){
        CardLayout c = (CardLayout)(cards.getLayout());
        c.show(cards, str);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(e.getSource() == pitchTuningButton){
            exerciseFrameCommand(command);
        } else if(e.getSource() == sightSingingButton){
            exerciseFrameCommand(command);
        }

        else {
            System.err.println("ERROR IN ACTIONLISTENER IN RhythmPanel.java");
        }
    }

    private void exerciseFrameCommand(String command){
        //changeCards(command);
        MainWindow.addExerciseFrame(command);
        System.out.println(command);
    }
}
