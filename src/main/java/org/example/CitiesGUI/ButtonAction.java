package org.example.CitiesGUI;

import org.example.GameServices.CheckWord;

import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonAction implements ActionListener {


        JTextField textField;
        JLabel label;
        CheckWord check = new CheckWord();



        public ButtonAction(JTextField textField, JLabel label) throws IOException {
            this.textField = textField;
            this.label = label;
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        String city = textField.getText().trim().toLowerCase();
        if(!check.isValid(city)){
            JOptionPane.showMessageDialog(null, "Слово включає не валідні символи");
            return;
        }
        if(check.isGiveUp(city)){
            String message = "Ваш рахунок: " + check.getScore();
            JOptionPane.showMessageDialog(null, message);
            textField.setText("");
            label.setText("");
            check.clear();
            return;

        }
        if (!check.checkFirstLetter(city)){
            JOptionPane.showMessageDialog(null, "Не вірна перша літера");
            return;
        }
        if (check.isRepeat(city)){
            JOptionPane.showMessageDialog(null, "Місто вже було");
            return;
        }
        if(check.findAndMatch(city)){
            check.incrementScore();
            String computerWord = check.findSuitableWord(city);
            if(computerWord != null){
                label.setText(computerWord);
            }else{
                String message = "Ви перемогли! Ваш рахунок: " + check.getScore();
                JOptionPane.showMessageDialog(null, message, "Зустрічаємо переможця", JOptionPane.PLAIN_MESSAGE);
            }
        }else {
                JOptionPane.showMessageDialog(null, "Слова не має в нашому обширному списку міст");
        }
    }
}

