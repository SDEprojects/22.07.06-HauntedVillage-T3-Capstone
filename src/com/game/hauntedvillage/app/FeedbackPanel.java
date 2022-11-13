package com.game.hauntedvillage.app;

import com.game.hauntedvillage.controller.Controller;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class FeedbackPanel {
    JPanel feedbackPanel() throws UnsupportedAudioFileException, IOException {
        GameFrame gameFrameInstance = GameFrame.getInstance();
        Controller controllerInstance = Controller.getInstance();
        String feedbackTitleString = "";
        JTextArea feedbackWrap = new JTextArea();
        feedbackWrap.setFont(new Font("Chiller", Font.PLAIN, 20));
        feedbackWrap.setForeground(Color.RED);
        feedbackWrap.setBackground(Color.black);
        feedbackWrap.setLineWrap(true);
        feedbackWrap.setWrapStyleWord(true);
        if(controllerInstance.getPlayerUpdate().size() > 0) {
            if (Math.random() < .2) {
                feedbackTitleString = "The wind seems to mutter...";
            }
            else if (Math.random() < .4) {
                feedbackTitleString = "A distant, aching moan groans...";
            }
            else if (Math.random() <.6){
                feedbackTitleString = "A strange deep voice in your head says...";
            }
            else if (Math.random() <.8) {
                feedbackTitleString = "Behind you, a faint whisper says...";
            }
            else {
                feedbackTitleString = "Something is wrong...";
            }

            String convert = controllerInstance.getPlayerUpdate().toString();
            if(convert.contains("You used the matches to light the candle.\nThe illumination reveals a triangular amulet, this may come in handy.")) {
                gameFrameInstance.setUnlit(false);
            }
            feedbackWrap.setText(convert.substring(1, convert.length() - 1));
        }

        JPanel panelTextFeedback = new JPanel();
        panelTextFeedback.setLayout(new BorderLayout());
        panelTextFeedback.setBackground(Color.BLACK);
        panelTextFeedback.setBounds(410, 555, 265, 200);

        String textFeedbackTitleString = feedbackTitleString;
        JLabel feedbackTitle = new JLabel(textFeedbackTitleString);
        feedbackTitle.setForeground(Color.GRAY);
        feedbackTitle.setFont(new Font("Chiller", Font.ITALIC, 24));
        feedbackTitle.setHorizontalAlignment(SwingConstants.CENTER);

        // FEEDBACK
        feedbackTitle.add(feedbackWrap);
        panelTextFeedback.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        panelTextFeedback.add(feedbackTitle, BorderLayout.PAGE_START);
        panelTextFeedback.add(feedbackWrap);

        return panelTextFeedback;
    }
}