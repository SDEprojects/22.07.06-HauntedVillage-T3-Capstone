package com.game.hauntedvillage.app;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class QuitButton {
    JButton quitButton() throws UnsupportedAudioFileException, IOException {
        GameFrame gameFrameInstance = GameFrame.getInstance();
        JButton quitButton = new JButton("Exit");
        quitButton.addActionListener(e -> {
            gameFrameInstance.quitGame("quit");
        });
        quitButton.setPreferredSize(new Dimension(273, 40));
//        button.addActionListener(this);
        quitButton.setFocusable(false); // gets rid of box around button
        quitButton.setText("Exit");
        //button.setIcon(icon);// adds pic to button
        quitButton.setHorizontalTextPosition(JButton.RIGHT);
        quitButton.setVerticalTextPosition(JButton.CENTER);
        quitButton.setFont(new Font("Courier", Font.ITALIC, 40));
//        settingsButton.setIconTextGap(5);
        quitButton.setForeground(Color.red);  //(new Color(0x8A0303));
        quitButton.setBackground(Color.DARK_GRAY);
        quitButton.setBorder(BorderFactory.createEtchedBorder());

        return quitButton;
    }
}