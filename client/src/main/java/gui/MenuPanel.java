package gui;

import Pitches.FrequencyDetector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sean Murphy on 11/27/2017.
 */
public class MenuPanel extends JPanel implements ActionListener{

    /** Panels for the Card Layout **/
    private JPanel cards;
    private JPanel startPanel;
    private JPanel exercisePanel;
    private JPanel optionsPanel;
    private JPanel helpPanel;

    /** Buttons for the Panels **/
    private JButton exerciseButton;
    private JButton optionsButton;
    private JButton helpButton;
    private JButton frequencyButton;
    private JButton noteButton;
    private JButton rhythmButton;
    private JButton back1, back2;


    private final Font BUTTONFONT = new Font(
            "Arial", Font.PLAIN, 25);

    /** Temporary fix - please debug and use CoreController instead **/
    private FrequencyDetector fq = new FrequencyDetector();

    public MenuPanel() {

        cards = new JPanel(new CardLayout());

        startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));

        exerciseButton = new JButton("Exercises");
        optionsButton = new JButton("Options");
        helpButton = new JButton("Help");
        createButton(exerciseButton, startPanel);
        createButton(optionsButton, startPanel);
        createButton(helpButton, startPanel);

        exercisePanel = new JPanel();
        exercisePanel.setLayout(new BoxLayout(exercisePanel, BoxLayout.PAGE_AXIS));

        frequencyButton = new JButton("Frequencies");
        noteButton = new JButton("Notes");
        rhythmButton = new JButton("Rhythms");
        back1 = new JButton("Main Menu");
        createButton(frequencyButton, exercisePanel);
        createButton(noteButton, exercisePanel);
        createButton(rhythmButton, exercisePanel);
        createButton(back1, exercisePanel);

        helpPanel = new JPanel();
        helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.PAGE_AXIS));

        back2 = new JButton("Main Menu");
        createButton(back2, helpPanel);

        cards.add(startPanel, "Main Menu");
        cards.add(exercisePanel, "Exercises");
        cards.add(helpPanel, "Help");

        //this.setBackground(Color.BLACK);
        this.add(cards, BorderLayout.CENTER);
    }

    private void createButton(JButton button, JPanel p){
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        button.setFont(BUTTONFONT);
        setButtonDimension(button, p);
        //p.setBackground(Color.BLACK);
        p.add(button, BorderLayout.CENTER);
    }

    private void setButtonDimension(JComponent j, JPanel p){
        j.setPreferredSize(new Dimension(100, 50));
        j.setMaximumSize(new Dimension(250, 50));
        p.add(Box.createRigidArea(new Dimension(450, 60)));
    }

    private void changeCards(String str){
        CardLayout c = (CardLayout)(cards.getLayout());
        c.show(cards, str);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frequencyButton){
            fq.start();
        } else if(e.getSource() == noteButton){
            fq.togglePitchComparison();
            fq.start();
        } else if(e.getSource() == rhythmButton){
            NotePanel.appendTextArea("hello");
        } else {
            changeCards(e.getActionCommand());
        }
    }
}
