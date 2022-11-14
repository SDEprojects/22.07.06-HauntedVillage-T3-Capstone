package com.game.hauntedvillage.app;

import com.game.hauntedvillage.controller.Controller;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Map;

class NpcImage {
    void createNpc(String talk, String fight) throws UnsupportedAudioFileException, IOException {
        GameFrame gameFrameInstance = GameFrame.getInstance();
        Controller controllerInstance = Controller.getInstance();
        Map<String, String> showNPCs = controllerInstance.areaNPCs();
        JMenuItem[] dropMenu = new JMenuItem[4];
        String location = controllerInstance.playerLocation();

        if(showNPCs.size() > 0) {
            if (showNPCs.get("0") != null) {
                JPopupMenu npc1 = new JPopupMenu();
                dropMenu[1] = new JMenuItem(talk);
                dropMenu[1].addActionListener(e -> {
                    try {
                        controllerInstance.userPrompt(String.format("%s %s", talk, showNPCs.get("0")));
                        npc1.setVisible(false);
                        gameFrameInstance.initialize();
                        gameFrameInstance.createScreen();
                    }
                    catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                npc1.add(dropMenu[1]);
                dropMenu[2] = new JMenuItem(fight);
                dropMenu[2].addActionListener(e -> {
                    try {
                        controllerInstance.userPrompt(String.format("%s %s", fight, showNPCs.get("0")));
                        gameFrameInstance.initialize();
                        gameFrameInstance.createScreen();
                    }
                    catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                npc1.add(dropMenu[2]);
                ImageIcon npc1Icon = new ImageIcon(showNPCs.get("0"));
                JLabel npc1Label = new JLabel();
                if(location.equals("Center Courtyard")) {
                    npc1Label.setBounds(300, 300, 180, 100);
                }
                else if(location.equals("Farm")) {
                    npc1Label.setBounds(310, 300, 280, 150);
                }
                else if(location.equals("Church")) {
                    npc1Label.setBounds(560, 220, 180, 180);
                }
                else if(location.equals("Northern Square")) {
                    npc1Label.setBounds(10, 60, 280, 150);
                }
                else if(location.equals("Woods")) {
                    npc1Label.setBounds(250, 360, 280, 150);
                }
                else if(location.equals("Town hall")) {
                    npc1Label.setBounds(500, 400, 280, 150);
                }
                else if(location.equals("Tavern")) {
                    npc1Label.setBounds(400, 180, 280, 350);
                }

                npc1Label.setIcon(npc1Icon);
                npc1Label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent event) {

                    }

                    @Override
                    public void mousePressed(MouseEvent event) {
                        if (SwingUtilities.isRightMouseButton(event)) {
                            npc1.show(npc1Label, event.getX(), event.getY());
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent event) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent event) {
                    }

                    @Override
                    public void mouseExited(MouseEvent event) {
                    }
                });
                gameFrameInstance.addPanel(npc1Label);
            }
            if (showNPCs.get("1") != null) {
                JPopupMenu npc2 = new JPopupMenu();
                dropMenu[1] = new JMenuItem(talk);
                dropMenu[1].addActionListener(e -> {
                    try {
                        controllerInstance.userPrompt(String.format("%s %s", talk, showNPCs.get("1")));
                        npc2.setVisible(false);
                        gameFrameInstance.initialize();
                        gameFrameInstance.createScreen();
                    } catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                npc2.add(dropMenu[1]);
                dropMenu[2] = new JMenuItem(fight);
                dropMenu[2].addActionListener(e -> {
                    try {
                        controllerInstance.userPrompt(String.format("%s %s", fight, showNPCs.get("1")));
                        if(showNPCs.get("1").equals("images/catDemon.png")) {
                            gameFrameInstance.quitGame("win");
                        }
                        else {
                            gameFrameInstance.initialize();
                            gameFrameInstance.createScreen();
                        }
                    } catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                npc2.add(dropMenu[2]);
                ImageIcon npc2Icon = new ImageIcon(showNPCs.get("1"));
                JLabel npc2Label = new JLabel();
                if(location.equals("Farm")) {
                    npc2Label.setBounds(20, 380, 180, 150);
                }
                else if(location.equals("Woods")) {
                    npc2Label.setBounds(250, 250, 241, 300);
                }
                npc2Label.setIcon(npc2Icon);
                npc2Label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent event) {

                    }

                    @Override
                    public void mousePressed(MouseEvent event) {
                        if (SwingUtilities.isRightMouseButton(event)) {
                            npc2.show(npc2Label, event.getX(), event.getY());
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent event) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent event) {
                    }

                    @Override
                    public void mouseExited(MouseEvent event) {
                    }
                });
                gameFrameInstance.addPanel(npc2Label);
            }
        }
    }
}