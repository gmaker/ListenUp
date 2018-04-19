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
    private JPanel mainPanel;

    /** Buttons for the Panels **/
    private JButton rhythmElementsButton;
    private JButton rhythmDictationButton;

    /**Font and Style for Buttons in this Panel **/
    private final Font BUTTONFONT = new Font(
            "Arial", Font.PLAIN, 30);

    public RhythmPanel(){

        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        rhythmElementsButton = new JButton("Rhythm Elements");
        rhythmDictationButton = new JButton("Rhythm Dictation");
        createButton(rhythmElementsButton, mainPanel);
        createButton(rhythmDictationButton, mainPanel);

        add(mainPanel);
        setOpaque(false);
    }

    private void createButton(JButton button, JPanel p){
        button.addActionListener(this);
        button.setFont(BUTTONFONT);
        button.setForeground(Color.WHITE);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createBevelBorder(0,
                Color.red, new Color(0, 0, 0, 0)));
        setButtonDimension(button, p);
        p.add(button);
    }

    private void setButtonDimension(JComponent j, JPanel p){
        j.setPreferredSize(j.getPreferredSize());
        p.add(Box.createRigidArea(new Dimension(10, 15)));
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
        //MainWindow.changeMainPanel(command);
        System.out.println(command);
    }
}
