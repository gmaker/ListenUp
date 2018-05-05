package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResultsFrame extends JFrame implements ActionListener{

    private ArrayList<String> micInput;
    private String exerciseNotes;

    private JButton button;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JTextArea textArea;
    private String labelText = null;

    public ResultsFrame(ArrayList<String> al, String s){
        micInput = al;
        exerciseNotes = s;

        setTitle("Results of Exercise");

        mainPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 25));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setSize(new Dimension(300, 300));
        textArea.setAlignmentX(SwingConstants.CENTER);
        textArea.setEditable(false);
        setLabelText();
        mainPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(textArea, SwingConstants.CENTER);


        button = new JButton("OK");
        button.addActionListener(this);
        button.setPreferredSize(button.getPreferredSize());
        button.setContentAreaFilled(false);
        button.setBackground(Color.WHITE);
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(button);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setBackground(Color.WHITE);
        setSize(new Dimension(400, 400));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setLabelText(){
        ArrayList<String> tempList = new ArrayList<>();
        for(String tempString : exerciseNotes.split(",")){
            tempList.add(tempString);
        }

        labelText = "What was needed:\n\n";
        for(int i=0; i<tempList.size(); i++){
            labelText += tempList.get(i);
            if(i!=tempList.size()-1){
                labelText += ", ";
            }
        }
        labelText += "\n\nWhat was heard:\n\n";
        for(int j=0; j<micInput.size(); j++){
            labelText += micInput.get(j);
            if(j!=micInput.size()-1){
                labelText += ", ";
            }
        }
        textArea.setText(labelText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("OK")){
            this.dispose();
        }
    }
}
