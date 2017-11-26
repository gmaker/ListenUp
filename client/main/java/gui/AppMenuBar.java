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
    private JMenuItem userProfileMenuItem;
    private JMenuItem quitMenuItem;

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
        JMenu menu = new JMenu("File");

        userProfileMenuItem = new JMenuItem("User Profile");
        userProfileMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        userProfileMenuItem.addActionListener(this);
        menu.add(userProfileMenuItem);

        menu.addSeparator();

        quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        quitMenuItem.addActionListener(this);
        menu.add(quitMenuItem);

        return menu;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == quitMenuItem){
            System.exit(0);
        } else if(e.getSource() == userProfileMenuItem){

        }
    }
}
