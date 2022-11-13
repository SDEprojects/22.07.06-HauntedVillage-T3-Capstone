package com.game.hauntedvillage.app;

import com.game.hauntedvillage.controller.Controller;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Map;

class ItemImage {
    void createItemImage(String Choice1, String Choice2) throws UnsupportedAudioFileException, IOException {
        GameFrame gameFrameInstance = GameFrame.getInstance();
        Controller controllerInstance = Controller.getInstance();
        Map<String, String> showItems;
        showItems = controllerInstance.areaItems();
        String location = controllerInstance.playerLocation();
        JMenuItem[] dropMenu = new JMenuItem[4];

        if(showItems.size() > 0) {
            if(showItems.get("0") != null ) {
                JPopupMenu item1 = new JPopupMenu();
                dropMenu[0] = new JMenuItem(Choice1);
                dropMenu[1] = new JMenuItem(Choice2);
                ImageIcon item1Icon = new ImageIcon(showItems.get("0"));
                JLabel item1Label = new JLabel();
                if(!location.equals("Church") && !location.equals("Well")) {
                    dropMenu[0].addActionListener(e-> {
                        try {
                            controllerInstance.userPrompt(String.format("%s %s", Choice1, showItems.get("0")));
                            item1.setVisible(false);
                            gameFrameInstance.initialize();
                            gameFrameInstance.createScreen();
                        } catch (IOException | UnsupportedAudioFileException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    dropMenu[1].addActionListener(e-> {
                        try {
                            controllerInstance.userPrompt(String.format("%s %s", Choice2, showItems.get("0")));
                            gameFrameInstance.initialize();
                            gameFrameInstance.createScreen();
                        } catch (IOException | UnsupportedAudioFileException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    item1.add(dropMenu[0]);
                    item1.add(dropMenu[1]);
                    item1Label.setBounds(400,200,100,100);
                }
                else if(location.equals("Church")){
                    if(gameFrameInstance.isUnlit()) {
                        item1Label.setBounds(50, 255, 100, 100);
                        dropMenu[2] = new JMenuItem("Light");
                        dropMenu[2].addActionListener(e-> {
                            try {
                                controllerInstance.userPrompt(String.format("%s %s", "light", showItems.get("0")));
                                item1.setVisible(false);
                                gameFrameInstance.initialize();
                                gameFrameInstance.createScreen();
                            } catch (IOException | UnsupportedAudioFileException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                        item1.add(dropMenu[2]);

                    }
                    else {
                        item1Icon = new ImageIcon(showItems.get("1"));
                        item1Label.setBounds(50, 240, 100, 100);
                    }
                }
                else {
                    dropMenu[2] = new JMenuItem();
                    dropMenu[2].setText("Search");
                    dropMenu[2].addActionListener(e-> {
                        try {
                            controllerInstance.userPrompt(String.format("%s %s", "search", showItems.get("0")));
                            item1.setVisible(false);
                            gameFrameInstance.initialize();
                            gameFrameInstance.createScreen();
                        } catch (IOException | UnsupportedAudioFileException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    dropMenu[3] = new JMenuItem();
                    dropMenu[3].setText("Use");
                    dropMenu[3].addActionListener(e-> {
                        try {
                            controllerInstance.userPrompt(String.format("%s %s", "use", "well"));
                            gameFrameInstance.initialize();
                            gameFrameInstance.createScreen();
                        } catch (IOException | UnsupportedAudioFileException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    item1.add(dropMenu[2]);
                    item1.add(dropMenu[3]);

                    item1Label.setBounds(280,130,200,200);
                }

                item1Label.setIcon(item1Icon);
                item1Label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent event) {

                    }

                    @Override
                    public void mousePressed(MouseEvent event) {
                        if (SwingUtilities.isRightMouseButton(event)) {
                            item1.show(item1Label, event.getX(), event.getY());
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
                gameFrameInstance.addPanel(item1Label);
            }
            if(showItems.get("1") != null ) {
                JPopupMenu item2 = new JPopupMenu();
                dropMenu[1] = new JMenuItem(Choice1);
                dropMenu[1].addActionListener(e -> {
                    try {
                        controllerInstance.userPrompt(String.format("%s %s", Choice1, showItems.get("1")));
                        item2.setVisible(false);
                        gameFrameInstance.initialize();
                        gameFrameInstance.createScreen();
                    } catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item2.add(dropMenu[1]);
                dropMenu[2] = new JMenuItem(Choice2);
                dropMenu[2].addActionListener(e -> {
                    try {
                        controllerInstance.userPrompt(String.format("%s %s", Choice2, showItems.get("1")));
                        gameFrameInstance.initialize();
                        gameFrameInstance.createScreen();
                    } catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item2.add(dropMenu[2]);
                ImageIcon item2Icon = new ImageIcon(showItems.get("1"));
                JLabel item2Label = new JLabel();
                if(!location.equals("Church")) {
                    item2Label.setBounds(50,150,100,100);
                }
                item2Label.setIcon(item2Icon);
                item2Label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent event) {

                    }

                    @Override
                    public void mousePressed(MouseEvent event) {
                        if (SwingUtilities.isRightMouseButton(event)) {
                            item2.show(item2Label, event.getX(), event.getY());
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
                gameFrameInstance.addPanel(item2Label);
            }
            if(showItems.get("2") != null ) {
                JPopupMenu item3 = new JPopupMenu();
                dropMenu[1] = new JMenuItem(Choice1);
                dropMenu[1].addActionListener(e -> {
                    try {
                        controllerInstance.userPrompt(String.format("%s %s", Choice1, showItems.get("2")));
                        item3.setVisible(false);
                        gameFrameInstance.initialize();
                        gameFrameInstance.createScreen();
                    } catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item3.add(dropMenu[1]);
                dropMenu[2] = new JMenuItem(Choice2);
                dropMenu[2].addActionListener(e -> {
                    try {
                        controllerInstance.userPrompt(String.format("%s %s", Choice2, showItems.get("2")));
                        gameFrameInstance.initialize();
                        gameFrameInstance.createScreen();
                    } catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item3.add(dropMenu[2]);
                ImageIcon item3Icon = new ImageIcon(showItems.get("2"));
                JLabel item3Label = new JLabel();
                item3Label.setBounds(175, 350, 100, 100);
                item3Label.setIcon(item3Icon);
                item3Label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent event) {

                    }

                    @Override
                    public void mousePressed(MouseEvent event) {
                        if (SwingUtilities.isRightMouseButton(event)) {
                            item3.show(item3Label, event.getX(), event.getY());
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
                gameFrameInstance.addPanel(item3Label);
            }
        }
    }
}