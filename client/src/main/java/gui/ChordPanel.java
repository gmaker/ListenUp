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

    /** Panels for the Card Layout for ChordPanel class **/
    private JPanel cards;
    private JPanel mainPanel;
    private JPanel chordPanel;
    private JPanel tetrachordPanel;

    /** Buttons for the panels for the ChordPanel class **/
    private JButton chordButton;
    private JButton tetrachordButton;

    /** Font and Style for Buttons in this class **/
    private final Font BUTTONFONT = new Font(
            "Arial", Font.PLAIN, 30);
    private final Font LABELFONT = new Font(
            "Arial", Font.BOLD, 35);

    /** Other Items **/
    private JLabel label;

    public ChordPanel() {

        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        cards = new JPanel(new CardLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.BLACK);

        chordButton = new JButton("Chords");
        tetrachordButton = new JButton("Tetrachords");
        createButton(chordButton, mainPanel);
        createButton(tetrachordButton, mainPanel);

        chordPanel = new JPanel();
        tetrachordPanel = new JPanel();

        label = new JLabel("Chords");
        label.setFont(LABELFONT);
        label.add(Box.createRigidArea(new Dimension(0, 50)));
        label.setForeground(Color.BLUE);

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
                Color.blue, Color.black));
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
        if(e.getSource() == chordButton){
            multiCardChange(command);
        } else if(e.getSource() == tetrachordButton){
            multiCardChange(command);
        }
        else {
            System.err.println("ERROR IN ACTIONLISTENER IN ChordPanel.java");
        }
    }

    private void multiCardChange(String command){
        //changeCards(command);
        //MainWindow.changeMainPanel(command);
        System.out.println(command);
    }
}
