package com.game.hauntedvillage.app;

import com.game.hauntedvillage.controller.Controller;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class SettingsButton {
    JButton settingsButton(ImageIcon icon) throws UnsupportedAudioFileException, IOException {
        Controller controllerInstance = Controller.getInstance();
        JButton settingsButton = new JButton("Settings");
        // SETTINGS Button
        settingsButton.addActionListener(e -> System.out.println("foo"));
        settingsButton.setPreferredSize(new Dimension(273, 40));
        settingsButton.setFocusable(false); // gets rid of box around button
        settingsButton.setText("Settings");
        settingsButton.setHorizontalTextPosition(JButton.RIGHT);
        settingsButton.setVerticalTextPosition(JButton.CENTER);
        settingsButton.setFont(new Font("Chiller", Font.ITALIC, 40));
        settingsButton.setForeground(Color.red);  //(new Color(0x8A0303));
        settingsButton.setBackground(Color.DARK_GRAY);
        settingsButton.setBorder(BorderFactory.createEtchedBorder());


        // Section: POP UP for Settings, button click
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // The Frame
                JFrame frame = new JFrame("Settings");
                frame.setLayout(new GridLayout(4,2,2,2));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setBounds(300,300,300,300);
                frame.setVisible(true);
                frame.getContentPane().setBackground(Color.BLACK);
                frame.setForeground(Color.GREEN);
                frame.setFont(new Font("Chiller", Font.PLAIN, 18));
                //ICON
                frame.setIconImage(icon.getImage()); // change icon in upper left
                // Section: The Panel

                // create buttons and JLabels
                // SFX ON: label and button
                JLabel labelSFXON = new JLabel("   SFX On");
                labelSFXON.setForeground(Color.GREEN);
                labelSFXON.setBackground(Color.BLACK);
                labelSFXON.setFont(new Font("SANS_SERIF", Font.PLAIN, 14));
                JButton bSFXOn = new JButton();
                bSFXOn.setText("ON");
                bSFXOn.setFocusable(false); // gets rid of box around button
                bSFXOn.setHorizontalTextPosition(JButton.RIGHT);
                bSFXOn.setVerticalTextPosition(JButton.CENTER);
                bSFXOn.setPreferredSize(new Dimension(73, 40));
                bSFXOn.setFont(new Font("Chiller", Font.ITALIC, 40));
                bSFXOn.setForeground(Color.red);  //(new Color(0x8A0303));
                bSFXOn.setBackground(Color.DARK_GRAY);
                bSFXOn.setBorder(BorderFactory.createEtchedBorder());
                bSFXOn.addActionListener(SFXOn -> System.out.println("SFX on"));
                bSFXOn.addActionListener(bSFXOnE -> {
                    try {
                        controllerInstance.SFXAccessOn();
                    }
                    catch (UnsupportedAudioFileException ex) {
                        ex.printStackTrace();
                    }
                    catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                // SFX OFF: Label and Button
                JLabel labelSFXOFF = new JLabel("   SFX Off");
                labelSFXOFF.setForeground(Color.GREEN);
                labelSFXOFF.setBackground(Color.BLACK);
                labelSFXOFF.setFont(new Font("SANS_SERIF", Font.PLAIN, 14));
                JButton bSFXOff = new JButton();
                bSFXOff.setText("OFF");
                bSFXOff.setFocusable(false); // gets rid of box around button
                bSFXOff.setHorizontalTextPosition(JButton.RIGHT);
                bSFXOff.setVerticalTextPosition(JButton.CENTER);
                bSFXOff.setPreferredSize(new Dimension(73, 40));
                bSFXOff.setFont(new Font("Chiller", Font.ITALIC, 40));
                bSFXOff.setForeground(Color.red);  //(new Color(0x8A0303));
                bSFXOff.setBackground(Color.DARK_GRAY);
                bSFXOff.setBorder(BorderFactory.createEtchedBorder());
                bSFXOff.addActionListener(SFXOff -> System.out.println("SFX off"));
                bSFXOff.addActionListener(bSFXOffE -> {
                    try {
                        controllerInstance.SFXAccessOff();
                    }
                    catch (UnsupportedAudioFileException ex) {
                        ex.printStackTrace();
                    }
                    catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                // Music Button ON
                JLabel labelMusicOn = new JLabel("   Music On");
                labelMusicOn.setForeground(Color.GREEN);
                labelMusicOn.setBackground(Color.BLACK);
                labelMusicOn.setFont(new Font("SANS_SERIF", Font.PLAIN, 14));
                JButton bMusicOn = new JButton();
                bMusicOn.setText("ON");
                bMusicOn.setHorizontalTextPosition(JButton.RIGHT);
                bMusicOn.setVerticalTextPosition(JButton.CENTER);
                bMusicOn.setPreferredSize(new Dimension(73, 40));
                bMusicOn.setFont(new Font("Chiller", Font.ITALIC, 40));
                bMusicOn.setFocusable(false); // gets rid of box around button
                bMusicOn.setForeground(Color.red);  //(new Color(0x8A0303));
                bMusicOn.setBackground(Color.DARK_GRAY);
                bMusicOn.setBorder(BorderFactory.createEtchedBorder());

                bMusicOn.addActionListener(musicOnE -> System.out.println("Music on"));
//                bMusicOn.addActionListener(musicOn -> sound.runMusic("C"));
                bMusicOn.addActionListener(musicOnE -> {
                    try {
                        controllerInstance.musicAccessOn();
                    }
                    catch (UnsupportedAudioFileException ex) {
                        ex.printStackTrace();
                    }
                    catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                // Music Button OFF
                JLabel labelMusicOff = new JLabel("   Music Off");
                labelMusicOff.setForeground(Color.GREEN);
                labelMusicOff.setBackground(Color.BLACK);
                labelMusicOff.setFont(new Font("SANS_SERIF", Font.PLAIN, 14));
                JButton bMusicOff = new JButton();
                bMusicOff.setText("OFF");
                bMusicOff.setHorizontalTextPosition(JButton.RIGHT);
                bMusicOff.setVerticalTextPosition(JButton.CENTER);
                bMusicOff.setPreferredSize(new Dimension(73, 40));
                bMusicOff.setFont(new Font("Chiller", Font.ITALIC, 40));
                bMusicOff.setFocusable(false); // gets rid of box around button
                bMusicOff.setForeground(Color.red);  //(new Color(0x8A0303));
                bMusicOff.setBackground(Color.DARK_GRAY);
                bMusicOff.setBorder(BorderFactory.createEtchedBorder());

                bMusicOff.addActionListener(musicOffE -> System.out.println("Music off"));
                bMusicOff.addActionListener(musicOffE -> {
                    try {
                        controllerInstance.musicAccessOff();
                    }
                    catch (UnsupportedAudioFileException ex) {
                        ex.printStackTrace();
                    }
                    catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });


                frame.add(labelSFXON);
                frame.add(bSFXOn);
                frame.add(labelSFXOFF);
                frame.add(bSFXOff);
                frame.add(labelMusicOn);
                frame.add(bMusicOn);
                frame.add(labelMusicOff);
                frame.add(bMusicOff);

            }
        });
        return settingsButton;
    }
}