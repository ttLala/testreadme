package org.example.CitiesGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class WelcomePanel extends JOptionPane {
    private JButton button = new JButton("Ok");
    String title = "Вітаю";
//String title = "Вітаю";
    JPanel panel=new JPanel();
    JLabel labelMessage = new JLabel("Вітвємо вам у грі дитинства і всіх розумників!");
    public WelcomePanel() {
       button.addActionListener(new ButtonEventListener());
       panel.setBounds(0, 0, 360, 90);
       panel.add(labelMessage);
       panel.add(button);
       this.setBounds(0,0,400,100);
       this.showOptionDialog(this, panel, title,
                JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }

    class ButtonEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Window w = SwingUtilities.getWindowAncestor(button);
            w.setVisible(false);
            try {
                GamePanel game = new GamePanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
