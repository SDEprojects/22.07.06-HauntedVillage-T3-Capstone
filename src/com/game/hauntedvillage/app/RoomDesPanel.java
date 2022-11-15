package com.game.hauntedvillage.app;

import com.game.hauntedvillage.controller.Controller;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class RoomDesPanel {
    private JPanel panelRoomDescription;
    private String oldLocation = "";
    private JTextArea gameText;
    JPanel roomDesPanel() throws UnsupportedAudioFileException, IOException {
        Controller controllerInstance = Controller.getInstance();
        // ROOM DESCRIPTION panel
        panelRoomDescription = new JPanel();
        panelRoomDescription.setBackground(Color.BLACK);
        panelRoomDescription.setLayout(new BorderLayout());
        panelRoomDescription.setBounds(5, 555, 400, 200);

        String roomTitleString = controllerInstance.playerLocation();
        JLabel roomTitle = new JLabel(roomTitleString);
        roomTitle.setForeground(Color.GREEN);
        roomTitle.setFont(new Font("Courier", Font.PLAIN, 20));
        roomTitle.setHorizontalAlignment(SwingConstants.CENTER);

        String roomDescription = "This room is empty and dark";
        JLabel labelRoomDescription = new JLabel(roomDescription);
        //labelRoomDescription.setFont();
        labelRoomDescription.setForeground(Color.GREEN);
        gameText = new JTextArea();

        // gameText = new JTextArea(controller.showAreaDescription());
        gameText.setBounds(5, 555, 400, 200);
        gameText.setBackground(Color.black);
        gameText.setForeground(Color.red);
        gameText.setEditable(false);
        gameText.setLineWrap(true);
        gameText.setWrapStyleWord(true);
        gameText.setFont(new Font("SANS_SERIF", Font.ITALIC, 16));

        if(controllerInstance.getPlayerUpdate().size() > 0) {
            gameText.setText(getOldLocation());
        }
        else {
            setOldLocation(controllerInstance.showAreaDescription());
            gameText.setText(getOldLocation());
        }

        // this decides where in borderlayout they are positioned...
        panelRoomDescription.add(roomTitle, BorderLayout.PAGE_START);
        panelRoomDescription.add(labelRoomDescription);
        panelRoomDescription.add(gameText);
        panelRoomDescription.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN));

        setOldLocation(controllerInstance.showAreaDescription());

        return panelRoomDescription;
    }

    private String getOldLocation() {
        return oldLocation;
    }

    private void setOldLocation(String oldLocation) {
        this.oldLocation = oldLocation;
    }
}