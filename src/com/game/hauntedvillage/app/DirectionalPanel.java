package com.game.hauntedvillage.app;

import com.game.hauntedvillage.controller.Controller;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class DirectionalPanel {
//    private final JPanel centerPanel = new JPanel();
//    private final JPanel southPanel = new JPanel();
//    private final JPanel westPanel = new JPanel();
//    private final JPanel eastPanel = new JPanel();
//    private final JPanel northPanel = new JPanel();
    private JPanel centerPanel, southPanel, westPanel, eastPanel, northPanel;
//    private final JButton northButton = new JButton("N");
//    private final JButton eastButton = new JButton("E");
//    private final JButton westButton = new JButton("W");
//    private final JButton southButton = new JButton("S");
    private JButton northButton, eastButton, westButton, southButton;
    private final JPanel panelNav = new JPanel();

    DirectionalPanel() throws UnsupportedAudioFileException, IOException {
    }

    JPanel createDirectionButtons() throws UnsupportedAudioFileException, IOException {
        GameFrame gameFrameInstance = GameFrame.getInstance();
        Controller controllerInstance = Controller.getInstance();
//        panelNav = new JPanel();
        panelNav.setBackground(Color.BLACK);
        panelNav.setLayout(new BorderLayout());
        panelNav.setBounds(780, 585, 120, 120);
        JLabel labelNav = new JLabel("Navigation");
        panelNav.add(labelNav);
        northButton = new JButton("N");
        eastButton = new JButton("E");
        westButton = new JButton("W");
        southButton = new JButton("S");
//        // Navigation Buttons
//        // NORTH
        northButton.addActionListener(e -> {
            try {
                controllerInstance.userPrompt("go north");
                gameFrameInstance.initialize();
                gameFrameInstance.createScreen();
            } catch (IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
        });
        northButton.setFocusable(false); // gets rid of box around button
        northButton.setText("N");
        northButton.setHorizontalTextPosition(JButton.CENTER);
        northButton.setVerticalTextPosition(JButton.CENTER);
        northButton.setFont(new Font("Courier", Font.ITALIC, 30));
        northButton.setPreferredSize(new Dimension(40, 40));
        northButton.setSize(40, 40);
        northButton.setForeground(Color.red);  //(new Color(0x8A0303));
        northButton.setBackground(Color.DARK_GRAY);
        northButton.setBorder(BorderFactory.createEtchedBorder());

        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(40, 40));
        northPanel.add(northButton);
        northPanel.setBackground(Color.BLACK);

        // EAST
        eastButton.addActionListener(e -> {
            try {
                controllerInstance.userPrompt("go east");
                gameFrameInstance.initialize();
                gameFrameInstance.createScreen();
            } catch (IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
        });
        eastButton.setPreferredSize(new Dimension(40, 40));
        eastButton.setFocusable(false); // gets rid of box around button
        eastButton.setText("E");
        //button.setIcon(icon);// adds pic to button
        eastButton.setHorizontalTextPosition(JButton.CENTER);
        eastButton.setVerticalTextPosition(JButton.CENTER);
        eastButton.setFont(new Font("Courier", Font.ITALIC, 30));
        eastButton.setSize(40, 40);
        eastButton.setForeground(Color.red);  //(new Color(0x8A0303));
        eastButton.setBackground(Color.DARK_GRAY);
        eastButton.setBorder(BorderFactory.createEtchedBorder());

        eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(40, 40));
        eastPanel.add(eastButton);
        eastPanel.setBackground(Color.BLACK);


        // WEST
        westButton.addActionListener(e -> {
            try {
                controllerInstance.userPrompt("go west");
                gameFrameInstance.initialize();
                gameFrameInstance.createScreen();
            } catch (IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
        });
        westButton.setPreferredSize(new Dimension(40, 40));
        westButton.setFocusable(false); // gets rid of box around button
        westButton.setText("W");
        //button.setIcon(icon);// adds pic to button
        westButton.setHorizontalTextPosition(JButton.RIGHT);
        westButton.setVerticalTextPosition(JButton.CENTER);
        westButton.setFont(new Font("Courier", Font.ITALIC, 30));
        westButton.setForeground(Color.red);  //(new Color(0x8A0303));
        westButton.setBackground(Color.DARK_GRAY);
        westButton.setBorder(BorderFactory.createEtchedBorder());

        westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(40, 40));
        westPanel.add(westButton);
        westPanel.setBackground(Color.BLACK);


        // South
        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
        southButton.addActionListener(e -> {
            try {
                controllerInstance.userPrompt("go south");
                gameFrameInstance.initialize();
                gameFrameInstance.createScreen();
            } catch (IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
        });
        southButton.setPreferredSize(new Dimension(40, 40));
        southButton.setFocusable(false); // gets rid of box around button
        southButton.setText("S");
        //button.setIcon(icon);// adds pic to button
        southButton.setHorizontalTextPosition(JButton.RIGHT);
        southButton.setVerticalTextPosition(JButton.CENTER);
        southButton.setFont(new Font("Courier", Font.ITALIC, 30));
        southButton.setForeground(Color.red);  //(new Color(0x8A0303));
        southButton.setBackground(Color.DARK_GRAY);
        southButton.setBorder(BorderFactory.createEtchedBorder());

        southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(40, 40));
        southPanel.add(southButton);
        southPanel.setBackground(Color.BLACK);


        // CENTER PANEL
        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(40, 40));
//        eastPanel.add(EastButton);
        centerPanel.setBackground(Color.BLACK);
        panelNav.add(northPanel, BorderLayout.PAGE_START);
        panelNav.add(eastPanel, BorderLayout.EAST);
        panelNav.add(westPanel, BorderLayout.WEST);
        panelNav.add(southPanel, BorderLayout.PAGE_END);
        panelNav.add(centerPanel);

        return panelNav;
    }
}