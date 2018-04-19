package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Sean Murphy on 11/26/2017.
 */
public class AppMenuBar extends JMenuBar implements ActionListener {

    /** The Main Window **/
    private MainWindow gui;

    /** Menu Items **/
    private JMenuItem aboutMenuItem;
    private JMenuItem quitMenuItem;
    private JMenuItem helpMenuItem;
    private JMenuItem frequencyMenuItem;
    private JMenuItem notesMenuItem;
    private JMenuItem stopMenuItem;

    /**
     *
     * @param gui
     */
    public AppMenuBar(MainWindow gui){
        this.gui = gui;

        this.add(buildMenu());
    }

    /**
     *
     * @return
     */
    private JMenu buildMenu() {
        JMenu menu = new JMenu("Menu");
        JMenu optionsMenu = new JMenu("Options");
        JMenu devMenu = new JMenu("Developer");

        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        aboutMenuItem.addActionListener(this);
        optionsMenu.add(aboutMenuItem);

        helpMenuItem = new JMenuItem("Help");
        helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        helpMenuItem.addActionListener(this);
        optionsMenu.add(helpMenuItem);

        optionsMenu.addSeparator();

        quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        quitMenuItem.addActionListener(this);
        optionsMenu.add(quitMenuItem);

        frequencyMenuItem = new JMenuItem("Frequencies");
        frequencyMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        frequencyMenuItem.addActionListener(this);
        devMenu.add(frequencyMenuItem);

        notesMenuItem = new JMenuItem("Notes");
        notesMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        notesMenuItem.addActionListener(this);
        devMenu.add(notesMenuItem);

        stopMenuItem = new JMenuItem("Stop Pitch Detection");
        stopMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        stopMenuItem.addActionListener(this);
        devMenu.add(stopMenuItem);

        menu.add(optionsMenu);
        menu.add(devMenu);

        return menu;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == quitMenuItem){
            System.exit(0);
        } else if(e.getSource() == aboutMenuItem){

        } else if(e.getSource() == frequencyMenuItem){
            MainWindow.callCoreCommand("Frequencies");
        } else if(e.getSource() == notesMenuItem){
            MainWindow.callCoreCommand("Notes");
        } else if(e.getSource() == stopMenuItem){
            MainWindow.callCoreCommand("Stop");
        }
    }
}
