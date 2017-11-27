package gui;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sean Murphy on 11/27/2017.
 */
public class MenuPanel extends JPanel implements ActionListener{

    private JButton frequencyButton;
    private JButton noteButton;
    private JButton rhythmButton;

    private final Font BUTTONFONT = new Font(
            "Arial", Font.PLAIN, 25);

    public MenuPanel() {

        frequencyButton = new JButton("Frequencies");
        noteButton = new JButton("Notes");
        rhythmButton = new JButton("Rhythms");

        createButton(frequencyButton, this);
        createButton(noteButton, this);
        createButton(rhythmButton, this);
    }

    private void createButton(JButton b, JPanel p){
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.addActionListener(this);
        b.setFont(BUTTONFONT);
        b.setBackground(Color.CYAN);
        //setButtonDimension(b, p);
        p.add(b, BorderLayout.CENTER);
    }

    private void setButtonDimension(JComponent j, JPanel p){
        j.setMinimumSize(new Dimension(50, 25));
        j.setPreferredSize(new Dimension(50, 25));
        j.setMaximumSize(new Dimension(275, 50));
        //p.add(Box.createRigidArea(new Dimension(Short.MAX_VALUE, 60)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frequencyButton){

        } else if(e.getSource() == noteButton){

        } else if(e.getSource() == rhythmButton){

        }
    }
}
