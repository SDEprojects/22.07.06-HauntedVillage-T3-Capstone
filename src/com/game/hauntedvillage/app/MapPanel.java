package com.game.hauntedvillage.app;

import com.game.hauntedvillage.controller.Controller;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class MapPanel {
    private JPanel panelMap;
    JPanel mapPanel() throws UnsupportedAudioFileException, IOException {
        Controller controllerInstance = Controller.getInstance();
        // MAP panel
        panelMap = new JPanel();
        panelMap.setBackground(Color.BLACK);
        panelMap.setSize(50, 300);
        panelMap.setBounds(680, 50, 300, 300);
        JLabel labelMap = new JLabel("Map");
        labelMap.setForeground(Color.GREEN);
        labelMap.setFont(new Font("Chiller", Font.PLAIN, 24));
        panelMap.add(labelMap);
        JList mapContent = new JList(controllerInstance.gameMap().toArray());
        mapContent.setForeground(Color.RED);
        mapContent.setBackground(Color.black);
        mapContent.setFont(new Font("Chiller", Font.PLAIN, 14));
        panelMap.add(mapContent);

        return panelMap;
    }
}