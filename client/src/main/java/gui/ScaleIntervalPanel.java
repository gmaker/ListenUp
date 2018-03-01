package gui;

import Pitches.FrequencyDetector;
import Pitches.MultiThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sean Murphy on 2/25/2018.
 */
public class ScaleIntervalPanel extends JPanel implements ActionListener{

    /** Panels for the Card Layout **/
    private JPanel cards;
    private JPanel mainPanel;
    private JPanel scalePanel;
    private JPanel jazzScalePanel;
    private JPanel intervalPanel;

    /** Buttons for the Panels **/
    private JButton scaleButton;
    private JButton exoticScaleButton;
    private JButton intervalButton;

    /** Font and Style for Buttons in this Panel **/
    private final Font BUTTONFONT = new Font(
            "Arial", Font.PLAIN, 30);
    private final Font LABELFONT = new Font(
            "Arial", Font.BOLD, 35);

    /** Other items **/
    private JLabel label;


    public ScaleIntervalPanel() {

        cards = new JPanel(new CardLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.BLACK);

        scaleButton = new JButton("Scales");
        exoticScaleButton = new JButton("Exotic Scales");
        intervalButton = new JButton("Intervals");
        createButton(scaleButton, mainPanel);
        createButton(exoticScaleButton, mainPanel);
        createButton(intervalButton, mainPanel);

        scalePanel = new JPanel();
        jazzScalePanel = new JPanel();
        intervalPanel = new JPanel();

        label = new JLabel("Scales and Intervals");
        label.setFont(LABELFONT);
        label.add(Box.createRigidArea(new Dimension(0, 50)));
        label.setForeground(Color.GREEN);
        //label.setAlignmentX(Component.LEFT_ALIGNMENT);

        cards.add(mainPanel, "Main Panel");

        setPreferredSize(new Dimension(400, 250));
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
                Color.green, Color.black));
        //button.setBorderPainted(false);
        //button.setAlignmentX(Component.LEFT_ALIGNMENT);
        setButtonDimension(button, p);
        p.add(button);
    }

    private void setButtonDimension(JComponent j, JPanel p){
        //j.setPreferredSize(new Dimension(250, 50));
        j.setPreferredSize(j.getPreferredSize());
        //j.setMaximumSize(new Dimension(250, 50));
        p.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void changeCards(String str){
        CardLayout c = (CardLayout)(cards.getLayout());
        c.show(cards, str);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(e.getSource() == scaleButton){
            multiCardChange(command);
        } else if(e.getSource() == exoticScaleButton){
            multiCardChange(command);
        } else if(e.getSource() == intervalButton){
            multiCardChange(command);
        }

        else {
            System.err.println("ERROR IN ACTIONLISTENER IN ScaleIntervalPanel.java");
        }
    }

    private void multiCardChange(String command){
        //changeCards(command);
        //MainWindow.changeMainPanel(command);
        System.out.println(command);
    }
}
