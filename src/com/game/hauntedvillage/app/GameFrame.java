package com.game.hauntedvillage.app;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Map;

import com.game.hauntedvillage.controller.Controller;
import com.game.hauntedvillage.model.Player;
import com.game.hauntedvillage.model.Sound;


public class GameFrame extends JFrame {

    private JFrame frame;
    private JPanel panelButtons;
    private JPanel panelInventory;
    private JPanel panelNav;
    private JPanel panelMap;
    private JPanel panelRoomDescription;
    private JPanel panelTextFeedback;
    private JLabel label, labelNav, quitLabel, feedbackTitle, end;
    private ImageIcon icon, gameIcon;
    private JPanel boardGame;
    private JButton playButton, quitButton, exitButton;
    private JPopupMenu inspectKnife, inspectMatches, inspectCrucifix;
    private JLabel knifeObjLabel, matchesObjLabel, crucifixObjLabel;
    private JPopupMenu createMenu;
    private JMenuItem[] menuOptions;
    private JLabel[] labelVisual = new JLabel[10];
    private JPanel[] panelVisual = new JPanel[10];
    private JTextArea gameText, feedbackWrap;
    private JPanel centerPanel, southPanel, westPanel, eastPanel, northPanel;
    private JButton NorthButton, EastButton, WestButton, SouthButton;
    private Controller controller = new Controller();
    private String oldLocation = "";
    private boolean unlit = true;

    public GameFrame() throws IOException, UnsupportedAudioFileException {
    }

    public void titleScreen() {

        frame = new JFrame();
        label = new JLabel();  // create label
        ClassLoader classLoader = getClass().getClassLoader();
        //noinspection ConstantConditions
        ImageIcon image = new ImageIcon(new ImageIcon(classLoader.getResource("images/spookyVillageedited.jpg")).getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH)); // sets frame to size of image

        // JFrame
        frame.setTitle("Haunted Village"); //sets title of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
        frame.setSize(1000, 800); //sets the x-dimension, and y-dimension of frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); //make frame visible

        //ICON
        icon = new ImageIcon("images/eyes.png"); // change icon in upper left
        frame.setIconImage(icon.getImage()); // change icon in upper left

        label.setText("Haunted Village");
        label.setForeground(new Color(0x8A0303));  // text color
        label.setBackground(Color.black); // set background color
        label.setOpaque(true); // display background color
        label.setFont(new Font("Chiller", Font.PLAIN, 110));

        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        boardGame = new JPanel();
        boardGame.setLayout(new GridLayout(1, 2));
        boardGame.setBackground(Color.black);
        boardGame.setBounds(350, 650, 300, 45);
        setFont(new Font("Chiller", Font.ITALIC, 40));

        // BOARD START BUTTON
        playButton = new JButton("START GAME");
        boardGame.add(playButton);
        playButton.setFont(new Font("Chiller", Font.ITALIC, 20));
        playButton.setForeground(Color.BLACK);
        playButton.setOpaque(true);
        playButton.setBackground(Color.DARK_GRAY);
        playButton.setBorder(BorderFactory.createEtchedBorder());
        playButton.addActionListener(e -> {
            initialize();
            createScreen();
        });

        playButton.setFocusPainted(false);

        JButton endButton = new JButton("Quit");
        endButton.setFont(new Font("Chiller", Font.ITALIC, 30));
        endButton.setForeground(Color.BLACK);
        endButton.setOpaque(true);
        endButton.setBackground(Color.DARK_GRAY);
        endButton.setBorder(BorderFactory.createEtchedBorder());
        endButton.addActionListener(e -> {
            quitMainScreen();
        });
        boardGame.add(endButton);

        frame.add(boardGame);
        frame.add(label);
        frame.pack();
        setOldLocation(controller.showAreaDescription());
    }
    public void quitMainScreen(){
        frame.getContentPane().removeAll();
        frame.repaint();

        quitLabel = new JLabel();
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon image = new ImageIcon(new ImageIcon(classLoader.getResource("images/thankyouforplaying.jpg")).getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH)); // sets frame to size of image
        quitLabel.setBackground(Color.black); // set background color
        quitLabel.setIcon(image);

        frame.add(quitLabel);
        frame.setVisible(true);
    }

    public void initialize() {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        boardGame.setVisible(false);
        label.setVisible(false);

        frame.setTitle("Haunted Village");

        // Define the frame
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);  // 850 500
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.setResizable(false); // prevent from being resized

        //ICON
        ImageIcon icon = new ImageIcon("images/eyes.png"); // change icon in upper left
        frame.setIconImage(icon.getImage()); // change icon in upper left

        // Create panels *************************************************************************
        // Buttons panel: Settings, Help Quit
        panelButtons = new JPanel(new FlowLayout());
        panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 5));
        panelButtons.setBackground(Color.BLACK);
        panelButtons.setSize(1000, 50);

        // MAP panel
        panelMap = new JPanel();
        panelMap.setBackground(Color.BLACK);
        panelMap.setSize(50, 300);
        panelMap.setBounds(680, 50, 300, 300);
        JLabel labelMap = new JLabel("Map");
        labelMap.setForeground(Color.GREEN);
        labelMap.setFont(new Font("Chiller", Font.PLAIN, 24));
        panelMap.add(labelMap);
        JList mapContent = new JList(controller.gameMap().toArray());
        mapContent.setForeground(Color.RED);
        mapContent.setBackground(Color.black);
        mapContent.setFont(new Font("Chiller", Font.PLAIN, 14));
        panelMap.add(mapContent);

        // INVENTORY panel
        panelInventory = new JPanel(new BorderLayout());
        panelInventory.setBackground(Color.BLACK);
        panelInventory.setBounds(680, 355, 300, 195);

        String inventoryTitleString = "Inventory";
        JLabel labelInventoryTitle = new JLabel(inventoryTitleString);
        labelInventoryTitle.setForeground(Color.GREEN);
        labelInventoryTitle.setFont(new Font("Chiller", Font.PLAIN, 24));
        labelInventoryTitle.setHorizontalAlignment(SwingConstants.CENTER);

        if(controller.gameInventory().size() > 0 && controller.gameInventory().get(0) != null) {
            ImageIcon inventoryItem1 = new ImageIcon(controller.gameInventory().get(0));
            JLabel item1Label = new JLabel(inventoryItem1);
            item1Label.setBounds(10, 30, 60, 100);

            panelInventory.add(item1Label);
        }
        if(controller.gameInventory().size() > 1 && controller.gameInventory().get(1) != null) {
            ImageIcon inventoryItem2 = new ImageIcon(controller.gameInventory().get(1));
            JLabel item2Label = new JLabel(inventoryItem2);
            item2Label.setBounds(70, 30, 60, 100);

            panelInventory.add(item2Label);
        }
        if(controller.gameInventory().size() > 2 && controller.gameInventory().get(2) != null) {
            ImageIcon inventoryItem3 = new ImageIcon(controller.gameInventory().get(2));
            JLabel item3Label = new JLabel(inventoryItem3);
            item3Label.setBounds(170, 30, 60, 100);

            panelInventory.add(item3Label);
        }
        if(controller.gameInventory().size() > 3 && controller.gameInventory().get(3) != null) {
            ImageIcon inventoryItem4 = new ImageIcon(controller.gameInventory().get(3));
            JLabel item4Label = new JLabel(inventoryItem4);
            item4Label.setBounds(230, 30, 60, 100);

            panelInventory.add(item4Label);
        }
        if(controller.gameInventory().size() > 4 && controller.gameInventory().get(4) != null) {
            ImageIcon inventoryItem5 = new ImageIcon(controller.gameInventory().get(4));
            JLabel item5Label = new JLabel(inventoryItem5);
            item5Label.setBounds(10, 120, 60, 100);

            panelInventory.add(item5Label);
        }
        if(controller.gameInventory().size() > 5 && controller.gameInventory().get(5) != null) {
            ImageIcon inventoryItem6 = new ImageIcon(controller.gameInventory().get(5));
            JLabel item6Label = new JLabel(inventoryItem6);

            panelInventory.add(item6Label);
        }
        if(controller.gameInventory().size() > 6 && controller.gameInventory().get(6) != null) {
            ImageIcon inventoryItem7 = new ImageIcon(controller.gameInventory().get(6));
            JLabel item7Label = new JLabel(inventoryItem7);

            panelInventory.add(item7Label);
        }
        panelInventory.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
        panelInventory.add(labelInventoryTitle, BorderLayout.PAGE_START);

        // ROOM DESCRIPTION panel
        panelRoomDescription = new JPanel();
        panelRoomDescription.setBackground(Color.BLACK);
        panelRoomDescription.setLayout(new BorderLayout());
        panelRoomDescription.setBounds(5, 555, 400, 200);

        String roomTitleString = controller.playerLocation();
        JLabel roomTitle = new JLabel(roomTitleString);
        roomTitle.setForeground(Color.GREEN);
        roomTitle.setFont(new Font("Chiller", Font.PLAIN, 20));
        roomTitle.setHorizontalAlignment(SwingConstants.CENTER);

        String roomDescription = "This room is empty and dark";
        JLabel labelRoomDescription = new JLabel(roomDescription);
//        labelRoomDescription.setFont();
        labelRoomDescription.setForeground(Color.GREEN);
        gameText = new JTextArea();

        // Section: Title of Feedback
        String feedbackTitleString = "";
//        JLabel labelTextFeedback = new JLabel();
        feedbackWrap = new JTextArea();
        feedbackWrap.setFont(new Font("Chiller", Font.PLAIN, 20));
        feedbackWrap.setForeground(Color.RED);
        feedbackWrap.setBackground(Color.black);
        feedbackWrap.setLineWrap(true);
        feedbackWrap.setWrapStyleWord(true);
        if(controller.getPlayerUpdate().size() > 0) {
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

            String convert = controller.getPlayerUpdate().toString();
            if(convert.contains("You used the matches to light the candle.\nThe illumination reveals a triangular amulet, this may come in handy.")) {
                setUnlit(false);
            }
            //if(convert.contains("You throw the blue stone at the beast...")) {
                //quitGame("quit");
                //endGameScreen("win");
            //}
            feedbackWrap.setText(convert.substring(1, convert.length() - 1));
        }
        else {
            setOldLocation(controller.showAreaDescription());
        }
        gameText.setText(getOldLocation());
//        gameText = new JTextArea(controller.showAreaDescription());
        gameText.setBounds(5, 555, 400, 200);
        gameText.setBackground(Color.black);
        gameText.setForeground(Color.red);
        gameText.setEditable(false);
        gameText.setLineWrap(true);
        gameText.setWrapStyleWord(true);
        gameText.setFont(new Font("Chiller", Font.PLAIN, 24));

        // this decides where in borderlayout they are positioned...
        panelRoomDescription.add(roomTitle, BorderLayout.PAGE_START);
        panelRoomDescription.add(labelRoomDescription);
        panelRoomDescription.add(gameText);
        panelRoomDescription.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN));

        // Text Feedback on actions panel
        panelTextFeedback = new JPanel();
        panelTextFeedback.setLayout(new BorderLayout());
        panelTextFeedback.setBackground(Color.BLACK);
        panelTextFeedback.setBounds(410, 555, 265, 200);

        String textFeedbackTitleString = feedbackTitleString;
        feedbackTitle = new JLabel(textFeedbackTitleString);
        feedbackTitle.setForeground(Color.GRAY);
        feedbackTitle.setFont(new Font("Chiller", Font.ITALIC, 24));
        feedbackTitle.setHorizontalAlignment(SwingConstants.CENTER);

        // FEEDBACK
        feedbackTitle.add(feedbackWrap);
        panelTextFeedback.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        panelTextFeedback.add(feedbackTitle, BorderLayout.PAGE_START);
        panelTextFeedback.add(feedbackWrap);

        // BUTTONs instantiate
        JButton settingsButton = new JButton("Settings");
        JButton helpButton = new JButton("Help");
        JButton quitButton = new JButton("Exit");

        // POP UP BUTTON
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
                        controller.SFXAccessOn();
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
                        controller.SFXAccessOff();
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
                        controller.musicAccessOn();
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
                        controller.musicAccessOff();
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


        // HELP Button
        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
        helpButton.addActionListener(e -> System.out.println("help"));
        helpButton.setPreferredSize(new Dimension(273, 40));
//        button.addActionListener(this);
        helpButton.setFocusable(false); // gets rid of box around button
        helpButton.setText("Help");
        //button.setIcon(icon);// adds pic to button
        helpButton.setHorizontalTextPosition(JButton.RIGHT);
        helpButton.setVerticalTextPosition(JButton.CENTER);
        helpButton.setFont(new Font("Chiller", Font.ITALIC, 40));
//        settingsButton.setIconTextGap(5);
        helpButton.setForeground(Color.red);  //(new Color(0x8A0303));
        helpButton.setBackground(Color.DARK_GRAY);
        helpButton.setBorder(BorderFactory.createEtchedBorder());

        // Section: POP UP for HELP, button click
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("hi");
                // The Frame
                JFrame frame = new JFrame("Help");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setBounds(300,300,400,250);
                //frame.setFont(new Font("Chiller", Font.PLAIN, 24));
                frame.setVisible(true);
                //ICON
                frame.setIconImage(icon.getImage()); // change icon in upper left

                // Section: The Panel
                JPanel panelPopUpSettings = new JPanel();
                panelPopUpSettings.setBackground(Color.BLACK);
                panelPopUpSettings.setForeground(Color.GREEN);
                panelPopUpSettings.setFont(new Font("Chiller", Font.PLAIN, 18));

                // ADD text
                String helpTitle = "                   Help with Point and Click";
                String helpMove = "\n\n\nMovement: Click on an arrow.";
                String helpItems = "\n\nItems: Right click to look or take or interact.";
                String helpPeople = "\n\nPeople: Right click to look or talk or interact.";
//                String roomDesc = helpMenu.help();
//
//                HelpMenu helpMenu = new HelpMenu();

                JTextArea gameText = new JTextArea(helpTitle+ helpMove + helpItems + helpPeople, 1, 20);
                gameText.setBounds(5, 555, 350, 350);
                gameText.setBackground(Color.black);
                gameText.setForeground(Color.green);
                gameText.setEditable(false);
                gameText.setLineWrap(true);
                gameText.setWrapStyleWord(true);
                gameText.setFont(new Font("SANS_SERIF", Font.PLAIN, 16));
                frame.add(panelPopUpSettings);
                panelPopUpSettings.add(gameText);

//                popUpSettings.add(textArea);
            }
        });

        //Quit/Exit
        quitButton.addActionListener(e -> {
            quitGame("quit");
        });
        quitButton.setPreferredSize(new Dimension(273, 40));
//        button.addActionListener(this);
        quitButton.setFocusable(false); // gets rid of box around button
        quitButton.setText("Exit");
        //button.setIcon(icon);// adds pic to button
        quitButton.setHorizontalTextPosition(JButton.RIGHT);
        quitButton.setVerticalTextPosition(JButton.CENTER);
        quitButton.setFont(new Font("Chiller", Font.ITALIC, 40));
//        settingsButton.setIconTextGap(5);
        quitButton.setForeground(Color.red);  //(new Color(0x8A0303));
        quitButton.setBackground(Color.DARK_GRAY);
        quitButton.setBorder(BorderFactory.createEtchedBorder());

        // Add items to panelButtons
        panelButtons.add(settingsButton);
        panelButtons.add(helpButton);
        panelButtons.add(quitButton);

        // Add items to frame
        frame.add(panelButtons);
        frame.add(panelMap);
        createDirectionButtons();
        frame.add(panelInventory);
        frame.add(panelRoomDescription);
        frame.add(panelTextFeedback);
    }
    public void quitGame(String endType) {
        panelVisual[1].setVisible(false);

        frame.getContentPane().removeAll();
        frame.repaint();
        //frame.add(panelVisual[1]);
        createQuitScreen(endType);
    }

    public void backgroundLayout(int visualNum, String bgImage) {

        panelVisual[visualNum] = new JPanel();
        panelVisual[visualNum].setBackground(Color.BLACK);
        panelVisual[visualNum].setSize(50, 300);
        panelVisual[visualNum].setBounds(5, 50, 670, 500);
        labelVisual[visualNum] = new JLabel();
        ImageIcon image = new ImageIcon(new ImageIcon(bgImage).getImage().getScaledInstance(670, 500, Image.SCALE_SMOOTH)); // sets frame to size of image

        labelVisual[visualNum].setIcon(image);
    }

    private void createNpc(String talk, String fight) {
        Map<String, String> showNPCs = controller.areaNPCs();
        JMenuItem[] dropMenu = new JMenuItem[4];
        String location = controller.playerLocation();

        if(showNPCs.size() > 0) {
            if (showNPCs.get("0") != null) {
                JPopupMenu npc1 = new JPopupMenu();
                dropMenu[1] = new JMenuItem(talk);
                dropMenu[1].addActionListener(e -> {
                    try {
                        controller.userPrompt(String.format("%s %s", talk, showNPCs.get("0")));
                        npc1.setVisible(false);
                        initialize();
                        createScreen();
                    }
                    catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                npc1.add(dropMenu[1]);
                dropMenu[2] = new JMenuItem(fight);
                dropMenu[2].addActionListener(e -> {
                    try {
                        controller.userPrompt(String.format("%s %s", fight, showNPCs.get("0")));
                        initialize();
                        createScreen();
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
                labelVisual[1].add(npc1Label);
            }
            if (showNPCs.get("1") != null) {
                JPopupMenu npc2 = new JPopupMenu();
                dropMenu[1] = new JMenuItem(talk);
                dropMenu[1].addActionListener(e -> {
                    try {
                        controller.userPrompt(String.format("%s %s", talk, showNPCs.get("1")));
                        npc2.setVisible(false);
                        initialize();
                        createScreen();
                    } catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                npc2.add(dropMenu[1]);
                dropMenu[2] = new JMenuItem(fight);
                dropMenu[2].addActionListener(e -> {
                    try {
                        controller.userPrompt(String.format("%s %s", fight, showNPCs.get("1")));
                        if (showNPCs.get("1").equals("images/catDemon.jpg")) {
                            quitGame("win");
                        }
                        else {
                            initialize();
                            createScreen();
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
                labelVisual[1].add(npc2Label);
            }
        }
    }

    public void createItemImage(String Choice1, String Choice2) {
        Map<String, String> showItems;
        showItems = controller.areaItems();
        String location = controller.playerLocation();
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
                            controller.userPrompt(String.format("%s %s", Choice1, showItems.get("0")));
                            item1.setVisible(false);
                            initialize();
                            createScreen();
                        } catch (IOException | UnsupportedAudioFileException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    dropMenu[1].addActionListener(e-> {
                        try {
                            controller.userPrompt(String.format("%s %s", Choice2, showItems.get("0")));
                            initialize();
                            createScreen();
                        } catch (IOException | UnsupportedAudioFileException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    item1.add(dropMenu[0]);
                    item1.add(dropMenu[1]);
                    item1Label.setBounds(400,200,100,100);
                }
                else if(location.equals("Church")){
                    if(isUnlit()) {
                        item1Label.setBounds(50, 255, 100, 100);
                        dropMenu[2] = new JMenuItem("Light");
                        dropMenu[2].addActionListener(e-> {
                            try {
                                controller.userPrompt(String.format("%s %s", "light", showItems.get("0")));
                                item1.setVisible(false);
                                initialize();
                                createScreen();
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
                            controller.userPrompt(String.format("%s %s", "search", showItems.get("0")));
                            item1.setVisible(false);
                            initialize();
                            createScreen();
                        } catch (IOException | UnsupportedAudioFileException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    dropMenu[3] = new JMenuItem();
                    dropMenu[3].setText("Use");
                    dropMenu[3].addActionListener(e-> {
                        try {
                            controller.userPrompt(String.format("%s %s", "use", "well"));
                            initialize();
                            createScreen();
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
                labelVisual[1].add(item1Label);
            }
            if(showItems.get("1") != null ) {
                JPopupMenu item2 = new JPopupMenu();
                dropMenu[1] = new JMenuItem(Choice1);
                dropMenu[1].addActionListener(e -> {
                    try {
                        controller.userPrompt(String.format("%s %s", Choice1, showItems.get("1")));
                        item2.setVisible(false);
                        initialize();
                        createScreen();
                    } catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item2.add(dropMenu[1]);
                dropMenu[2] = new JMenuItem(Choice2);
                dropMenu[2].addActionListener(e -> {
                    try {
                        controller.userPrompt(String.format("%s %s", Choice2, showItems.get("1")));
                        initialize();
                        createScreen();
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
                labelVisual[1].add(item2Label);
            }
            if(showItems.get("2") != null ) {
                JPopupMenu item3 = new JPopupMenu();
                dropMenu[1] = new JMenuItem(Choice1);
                dropMenu[1].addActionListener(e -> {
                    try {
                        controller.userPrompt(String.format("%s %s", Choice1, showItems.get("2")));
                        item3.setVisible(false);
                        initialize();
                        createScreen();
                    } catch (IOException | UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item3.add(dropMenu[1]);
                dropMenu[2] = new JMenuItem(Choice2);
                dropMenu[2].addActionListener(e -> {
                    try {
                        controller.userPrompt(String.format("%s %s", Choice2, showItems.get("2")));
                        initialize();
                        createScreen();
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
                labelVisual[1].add(item3Label);
            }
        }
    }

    public void createDirectionButtons() {
        panelNav = new JPanel();
        panelNav.setBackground(Color.BLACK);
        panelNav.setLayout(new BorderLayout());
        panelNav.setBounds(780, 585, 120, 120);
        labelNav = new JLabel("Navigation");
        panelNav.add(labelNav);
        NorthButton = new JButton("N");
        EastButton = new JButton("E");
        WestButton = new JButton("W");
        SouthButton = new JButton("S");
//        // Navigation Buttons
//        // NORTH
        NorthButton.addActionListener(e -> {
            try {
                controller.userPrompt("go north");
                initialize();
                createScreen();
            } catch (IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
        });
        NorthButton.setFocusable(false); // gets rid of box around button
        NorthButton.setText("N");
        NorthButton.setHorizontalTextPosition(JButton.CENTER);
        NorthButton.setVerticalTextPosition(JButton.CENTER);
        NorthButton.setFont(new Font("Chiller", Font.ITALIC, 40));
        NorthButton.setPreferredSize(new Dimension(40, 40));
        NorthButton.setSize(40, 40);
        NorthButton.setForeground(Color.red);  //(new Color(0x8A0303));
        NorthButton.setBackground(Color.DARK_GRAY);
        NorthButton.setBorder(BorderFactory.createEtchedBorder());

        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(40, 40));
        northPanel.add(NorthButton);
        northPanel.setBackground(Color.BLACK);

        // EAST
        EastButton.addActionListener(e -> {
            try {
                controller.userPrompt("go east");
                initialize();
                createScreen();
            } catch (IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
        });
        EastButton.setPreferredSize(new Dimension(40, 40));
        EastButton.setFocusable(false); // gets rid of box around button
        EastButton.setText("E");
        //button.setIcon(icon);// adds pic to button
        EastButton.setHorizontalTextPosition(JButton.CENTER);
        EastButton.setVerticalTextPosition(JButton.CENTER);
        EastButton.setFont(new Font("Chiller", Font.ITALIC, 40));
        EastButton.setSize(40, 40);
        EastButton.setForeground(Color.red);  //(new Color(0x8A0303));
        EastButton.setBackground(Color.DARK_GRAY);
        EastButton.setBorder(BorderFactory.createEtchedBorder());

        eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(40, 40));
        eastPanel.add(EastButton);
        eastPanel.setBackground(Color.BLACK);


        // WEST
        WestButton.addActionListener(e -> {
            try {
                controller.userPrompt("go west");
                initialize();
                createScreen();
            } catch (IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
        });
        WestButton.setPreferredSize(new Dimension(40, 40));
        WestButton.setFocusable(false); // gets rid of box around button
        WestButton.setText("W");
        //button.setIcon(icon);// adds pic to button
        WestButton.setHorizontalTextPosition(JButton.RIGHT);
        WestButton.setVerticalTextPosition(JButton.CENTER);
        WestButton.setFont(new Font("Chiller", Font.ITALIC, 40));
        WestButton.setForeground(Color.red);  //(new Color(0x8A0303));
        WestButton.setBackground(Color.DARK_GRAY);
        WestButton.setBorder(BorderFactory.createEtchedBorder());

        westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(40, 40));
        westPanel.add(WestButton);
        westPanel.setBackground(Color.BLACK);


        // South
        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
        SouthButton.addActionListener(e -> {
            try {
                controller.userPrompt("go south");
                initialize();
                createScreen();
            } catch (IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
        });
        SouthButton.setPreferredSize(new Dimension(40, 40));
        SouthButton.setFocusable(false); // gets rid of box around button
        SouthButton.setText("S");
        //button.setIcon(icon);// adds pic to button
        SouthButton.setHorizontalTextPosition(JButton.RIGHT);
        SouthButton.setVerticalTextPosition(JButton.CENTER);
        SouthButton.setFont(new Font("Chiller", Font.ITALIC, 40));
        SouthButton.setForeground(Color.red);  //(new Color(0x8A0303));
        SouthButton.setBackground(Color.DARK_GRAY);
        SouthButton.setBorder(BorderFactory.createEtchedBorder());

        southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(40, 40));
        southPanel.add(SouthButton);
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
        frame.add(panelNav);

    }
    public void createScreen() {
//        First Image Home
        backgroundLayout(1, controller.backgroundJpeg());
        createItemImage("take", "look");
        createNpc("talk", "fight");
        panelVisual[1].add(labelVisual[1], BorderLayout.PAGE_START);
        frame.add(panelVisual[1]);

    }


    public void endGameScreen(String imagePath) {
        end = new JLabel();
        ImageIcon image = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH)); // sets frame to size of image
        end.setBackground(Color.black); // set background color
        end.setIcon(image);

    }

    public void createQuitScreen(String endType) {
//      First Image Home
        if(endType.equals("quit")) {
            endGameScreen("./images/theend.jpg");
        }
        else if(endType.equals("win")) {
            endGameScreen( "images/demonDeath.png");
            end.setText("You killed the demon!");
            end.setForeground(new Color(0x8A0303));  // text color
            end.setOpaque(true); // display background color
            end.setFont(new Font("Chiller", Font.PLAIN, 110));
            end.setHorizontalTextPosition(JLabel.CENTER);
            end.setVerticalTextPosition(JLabel.CENTER);
            end.setVerticalAlignment(JLabel.CENTER);
            end.setHorizontalAlignment(JLabel.CENTER);
        }
        end.setVisible(true);
        frame.setContentPane(end);
        frame.setVisible(true);
    }

    public String getOldLocation() {
        return oldLocation;
    }

    public void setOldLocation(String oldLocation) {
        this.oldLocation = oldLocation;
    }

    public boolean isUnlit() {
        return unlit;
    }

    public void setUnlit(boolean unlit) {
        this.unlit = unlit;
    }
}