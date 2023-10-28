package org.example.CitiesGUI;

import javax.swing.*;
import java.io.IOException;

public class GamePanel extends JFrame{

    JFrame frame = new JFrame("Міста");
    JTextField textfield = new JTextField();
    JLabel task = new JLabel("Введіть назву міста");
    JLabel computer = new JLabel("Компьютер: ");
    JLabel computerCity = new JLabel("");

    JButton button = new JButton("Зробити хід");


    public GamePanel() throws IOException {
        ImageIcon img = new ImageIcon("src/main/resources/icon.png");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 180);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);


        textfield.setBounds(20,15, 150, 35);

        task.setBounds(180, 15, 210, 35);

        button.setBounds(20, 80, 150, 35);
        button.addActionListener(new ButtonAction(textfield, computerCity));

        computer.setBounds(180, 80, 100, 35);

        computerCity.setBounds(260, 80, 90, 35);


        frame.add(textfield);
        frame.add(task);
        frame.add(button);
        frame.add(computer);
        frame.add(computerCity);

        frame.setVisible(true);
    }
}
