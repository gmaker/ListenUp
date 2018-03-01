package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sean Murphy on 2/27/2018.
 */
public class RhythmPanel extends JPanel implements ActionListener {

    /** Panels for the Card Layout **/
    private JPanel cards;
    private JPanel mainPanel;
    private JPanel rhythmElementsPanel;
    private JPanel rhythmDictationPanel;

    /** Buttons for the Panels **/
    private JButton rhythmElementsButton;
    private JButton rhythmDictationButton;

    /**Font and Style for Buttons in this Panel **/
    private final Font BUTTONFONT = new Font(
            "Arial", Font.PLAIN, 30);
    private final Font LABELFONT = new Font(
            "Arial", Font.BOLD, 35);

    /** Other Items **/
    private JLabel label;

    public RhythmPanel(){

        cards = new JPanel(new CardLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.BLACK);

        rhythmElementsButton = new JButton("Rhythm Elements");
        rhythmDictationButton = new JButton("Rhythm Dictation");
        createButton(rhythmElementsButton, mainPanel);
        createButton(rhythmDictationButton, mainPanel);

        rhythmElementsPanel = new JPanel();
        rhythmDictationPanel = new JPanel();

        label = new JLabel("Rhythm");
        label.setFont(LABELFONT);
        label.add(Box.createRigidArea(new Dimension(0, 50)));
        label.setForeground(Color.RED);

        cards.add(mainPanel, "Main Panel");

        setPreferredSize(new Dimension(400, 250));
        add(label);
        add(cards);
        setBackground(Color.BLACK);
    }

    private void createButton(JButton button, JPanel p){
        button.addActionListener(this);
        button.setFont(BUTTONFONT);
        button.setForeground(Color.WHITE);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createBevelBorder(0,
                Color.red, Color.black));
        setButtonDimension(button, p);
        p.add(button);
    }

    private void setButtonDimension(JComponent j, JPanel p){
        //j.setPreferredSize(new Dimension(100, 50));
        //j.setMaximumSize(new Dimension(250, 50));
        j.setPreferredSize(j.getPreferredSize());
        p.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void changeCards(String str){
        CardLayout c = (CardLayout)(cards.getLayout());
        c.show(cards, str);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(e.getSource() == rhythmElementsButton){
            multiCardChange(command);
        } else if(e.getSource() == rhythmDictationButton){
            multiCardChange(command);
        }

        else {
            System.err.println("ERROR IN ACTIONLISTENER IN RhythmPanel.java");
        }
    }

    private void multiCardChange(String command){
        //changeCards(command);
        //MainWindow.changeMainPanel(command);
        System.out.println(command);
    }
}
