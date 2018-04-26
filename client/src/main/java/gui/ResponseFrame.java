package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sean Murphy on 4/25/2018.
 */
public class ResponseFrame extends JFrame implements ActionListener{

    /**  **/
    private String response;

    /**  **/
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JLabel label;
    private JButton button1, button2;

    /**  **/
    private final Font LABELFONT = new Font(
            "Arial", Font.BOLD, 30);

    public ResponseFrame(String r) {
        response = r;

        mainPanel = new JPanel(new GridLayout(1,1,10,0));
        buttonPanel = new JPanel(new FlowLayout());
        label = new JLabel();

        setFrame();

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(LABELFONT);
        mainPanel.add(label);

        add(mainPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(new Dimension(300, 350));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void setFrame(){

        if(response.equals("warning")){
            this.setTitle("Warning");
            label.setText("<html><p style=\"text-align:center\">" +
                    "Please move on to the next note in the exercise!</p></html>");
            mainPanel.setBackground(Color.BLUE);
            buttonPanel.setBackground(Color.BLUE);
            //button1 = new JButton("OK");
            addButton1(button1, "OK");
        } else if(response.equals("success")){
            this.setTitle("Correct!");
            label.setText("<html><p style=\"text-align:center\">" +
                    "Good job! You passed the exercise!<br>On to the next one!</p></html>");
            mainPanel.setBackground(Color.GREEN);
            buttonPanel.setBackground(Color.GREEN);
            //button1 = new JButton("I'm Ready");
            addButton1(button1, "I'm ready");
        } else if(response.equals("fail")){
            this.setTitle("Wrong");
            label.setText("<html><p style=\"text-align:center\">" +
                    "You got the exercise wrong.<br>Would you like to try again?</p></html>");
            mainPanel.setBackground(Color.RED);
            buttonPanel.setBackground(Color.RED);
            button1 = new JButton("Try Again");
            button2 = new JButton("Move On");
            //addButton1(button1, "Try Again");
            addButton2(button2, "Move On");
        }
        //addButton(button1, button2);
    }

    private void addButton1(JButton b1, String text){
        b1 = new JButton(text);
        createButton(b1);
        buttonPanel.add(b1, BorderLayout.SOUTH);
    }

    private void addButton2(JButton b2, String text){
        System.out.println("creating button2" + this.getTitle());
        b2 = new JButton(text);
        createButton(b2);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(b2, BorderLayout.SOUTH);

    }

    private void createButton(JButton b){
        b.addActionListener(this);
        b.setPreferredSize(new Dimension(100, 40));
        b.setContentAreaFilled(true);
        b.setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("Try Again")){
            //TODO: add functionality once comparision is finished
        } else {

        }
        this.dispose();
    }

    public static void main(String[] args){
        new ResponseFrame("success");
    }
}
