package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sean Murphy on 11/25/2017.
 */
public class MainWindow extends JFrame {

    public MainWindow() {

        setTitle("Ear Training and Sight Singing Application");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel emptyLabel = new JLabel();
        emptyLabel.setPreferredSize(new Dimension(500, 500));

        this.setJMenuBar(new AppMenuBar(this));

        getContentPane().add(emptyLabel, BorderLayout.CENTER);
        setLocation(500, 200);
        pack();
        setVisible(true);
    }
}
