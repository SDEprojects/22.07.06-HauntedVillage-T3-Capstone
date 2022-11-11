package com.game.hauntedvillage.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.game.hauntedvillage.controller.Controller;

public class GameFrame extends JFrame {

    private JFrame frame;
    private JPanel panelButtons;
    private JPanel panelInventory;
    private JPanel panelNav;
    private JPanel panelMap;
    private JPanel panelRoomDescription;
    private JPanel panelTextFeedback;
    private JLabel label, gameObjLabel;
    private ImageIcon icon, gameIcon;
    private JPanel boardGame;
    private JButton playButton, quitButton, exitButton;
    private JPopupMenu inspectKnife, inspectMatches, inspectCrucifix;
    public JLabel knifeObjLabel, matchesObjLabel, crucifixObjLabel;
    private JPopupMenu createMenu;
    private JMenuItem[] menuOptions;
    private JLabel[] labelVisual = new JLabel[10];
    public JPanel[] panelVisual = new JPanel[10];
    JTextArea gameText;
    JPanel centerPanel, southPanel, westPanel, eastPanel, northPanel;
    JButton NorthButton, EastButton, WestButton, SouthButton;
    Controller controller = new Controller();
    private String oldLocation = "";

    public GameFrame() throws IOException {

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
//        boardGame.setOpaque(false);
        boardGame.setBounds(300, 600, 300, 45);
        setFont(new Font("Chiller", Font.ITALIC, 40));

        // BOARD START BUTTON

        playButton = new JButton("START GAME");

        boardGame.add(playButton);
//        playButton.setBackground(Color.BLACK);
        playButton.setFont(new Font("Chiller Bold", Font.ITALIC, 15));
        playButton.setForeground(Color.red);
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.addActionListener(e -> {
            initialize();
            createScreen();
        });

        playButton.setFocusPainted(false);

        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Chiller Bold", Font.ITALIC, 15));
        quitButton.setForeground(Color.red);
        quitButton.setOpaque(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
        boardGame.add(quitButton);

        frame.add(boardGame);
        frame.add(label);
        frame.pack();
        setOldLocation(controller.showAreaDescription());
    }

    public void initialize() {

        boardGame.setVisible(false);
        label.setVisible(false);

//        frame = new JFrame();
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

        // this decides where in borderlayout they are positioned...
//        String inventory = controller.gameInventory().toString();
//        JTextArea inventoryText = new JTextArea();
//        inventoryText.setFont(new Font("Chiller", Font.PLAIN, 20));
//        inventoryText.setForeground(Color.RED);
//        inventoryText.setBackground(Color.black);
//        inventoryText.setText(inventory.substring(1, inventory.length() - 1));
//        inventoryText.setLineWrap(true);
//        inventoryText.setWrapStyleWord(true);
//        labelInventoryTitle.add(inventoryText);

        if(controller.gameInventory().size() > 0 && controller.gameInventory().get(0) != null) {
            ImageIcon inventoryItem1 = new ImageIcon(controller.gameInventory().get(0));
            JLabel item1Label = new JLabel(inventoryItem1);
            item1Label.setBounds(10, 30, 60, 60);

            panelInventory.add(item1Label);
        }
        if(controller.gameInventory().size() > 1 && controller.gameInventory().get(1) != null) {
            ImageIcon inventoryItem2 = new ImageIcon(controller.gameInventory().get(1));
            JLabel item2Label = new JLabel(inventoryItem2);
            item2Label.setBounds(70, 30, 60, 60);

            panelInventory.add(item2Label);
        }
        if(controller.gameInventory().size() > 2 && controller.gameInventory().get(2) != null) {
            ImageIcon inventoryItem3 = new ImageIcon(controller.gameInventory().get(2));
            JLabel item3Label = new JLabel(inventoryItem3);
            item3Label.setBounds(130, 30, 60, 60);

            panelInventory.add(item3Label);
        }
        if(controller.gameInventory().size() > 3 && controller.gameInventory().get(3) != null) {
            ImageIcon inventoryItem4 = new ImageIcon(controller.gameInventory().get(3));
            JLabel item4Label = new JLabel(inventoryItem4);

            panelInventory.add(item4Label);
        }
        if(controller.gameInventory().size() > 4 && controller.gameInventory().get(4) != null) {
            ImageIcon inventoryItem5 = new ImageIcon(controller.gameInventory().get(4));
            JLabel item5Label = new JLabel(inventoryItem5);

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
//        panelInventory.add(inventoryText);

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
        JTextArea feedbackWrap = new JTextArea();
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
            feedbackWrap.setText(convert.substring(1, convert.length() - 1));
            gameText.setText(getOldLocation());
        }
        else {
            setOldLocation(controller.showAreaDescription());
            gameText.setText(getOldLocation());
        }
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
        JLabel feedbackTitle = new JLabel(textFeedbackTitleString);
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
        exitButton = new JButton("Exit");

        // POP UP BUTTON
        // SETTINGS Button
        //settingsButton.setBounds(0,10,150,50);  // button location and button size
        settingsButton.addActionListener(e -> System.out.println("foo"));
        settingsButton.setPreferredSize(new Dimension(273, 40));
//        button.addActionListener(this);
        settingsButton.setFocusable(false); // gets rid of box around button
        settingsButton.setText("Settings");
        //button.setIcon(icon);// adds pic to button
        settingsButton.setHorizontalTextPosition(JButton.RIGHT);
        settingsButton.setVerticalTextPosition(JButton.CENTER);
        settingsButton.setFont(new Font("Chiller", Font.ITALIC, 40));
//        settingsButton.setIconTextGap(5);
        settingsButton.setForeground(Color.red);  //(new Color(0x8A0303));
        settingsButton.setBackground(Color.DARK_GRAY);
        settingsButton.setBorder(BorderFactory.createEtchedBorder());

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

        //Quit/Exit
        exitButton.addActionListener(e ->quitGame());
        exitButton.setPreferredSize(new Dimension(273, 40));
//        button.addActionListener(this);
        exitButton.setFocusable(false); // gets rid of box around button
        exitButton.setText("Exit");
        //button.setIcon(icon);// adds pic to button
        exitButton.setHorizontalTextPosition(JButton.RIGHT);
        exitButton.setVerticalTextPosition(JButton.CENTER);
        exitButton.setFont(new Font("Chiller", Font.ITALIC, 40));
//        settingsButton.setIconTextGap(5);
        exitButton.setForeground(Color.red);  //(new Color(0x8A0303));
        exitButton.setBackground(Color.DARK_GRAY);
        exitButton.setBorder(BorderFactory.createEtchedBorder());

        // Add items to panelButtons
        panelButtons.add(settingsButton);
        panelButtons.add(helpButton);
        panelButtons.add(exitButton);

        // Add items to frame
        frame.add(panelButtons);
        frame.add(panelMap);
        createDirectionButtons();
        frame.add(panelInventory);
        frame.add(panelRoomDescription);
        frame.add(panelTextFeedback);
    }
    public void quitGame() {
//        panelVisual[1].setVisible(false);
//        panelNav.setVisible(false);
//        panelMap.setVisible(false);
//        panelInventory.setVisible(false);
//        panelRoomDescription.setVisible(false);
//        panelTextFeedback.setVisible(false);
//        panelButtons.setVisible(false);
//        panelVisual[2].setVisible(true);
//
//        frame.wait();
//        createScreen();
        System.exit(0);
    }

    public void backgroundLayout(int visualNum, String bgImage) {

        panelVisual[visualNum] = new JPanel();
        panelVisual[visualNum].setBackground(Color.BLACK);
        panelVisual[visualNum].setSize(50, 300);
        panelVisual[visualNum].setBounds(5, 50, 670, 500);
        labelVisual[visualNum] = new JLabel();
        System.out.println(controller.backgroundJpeg());
        ImageIcon image = new ImageIcon(new ImageIcon(bgImage).getImage().getScaledInstance(670, 500, Image.SCALE_SMOOTH)); // sets frame to size of image

        labelVisual[visualNum].setIcon(image);
    }

    public void createObject(int visualNum, String Choice1, String Choice2) {
        Map<String, String> showItems;
        showItems = controller.areaItems();
        JMenuItem[] dropMenu = new JMenuItem[4];

        if(showItems.size() > 0) {
            if(showItems.get("0") != null ) {
                JPopupMenu item1 = new JPopupMenu();
                dropMenu[1] = new JMenuItem(Choice1);
                dropMenu[1].addActionListener(e-> {
                    try {
                        controller.userPrompt(String.format("%s %s", Choice1, showItems.get("0")));
                        item1.setVisible(false);
                        initialize();
                        createScreen();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item1.add(dropMenu[1]);
                dropMenu[2] = new JMenuItem(Choice2);
                dropMenu[2].addActionListener(e-> {
                    try {
                        controller.userPrompt(String.format("%s %s", Choice2, showItems.get("0")));
                        initialize();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item1.add(dropMenu[2]);
                ImageIcon item1Icon = new ImageIcon(showItems.get("0"));
                JLabel item1Label = new JLabel();
                item1Label.setBounds(400,200,100,100);
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
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item2.add(dropMenu[1]);
                dropMenu[2] = new JMenuItem(Choice2);
                dropMenu[2].addActionListener(e -> {
                    try {
                        controller.userPrompt(String.format("%s %s", Choice2, showItems.get("1")));
                        initialize();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item2.add(dropMenu[2]);
                ImageIcon item2Icon = new ImageIcon(showItems.get("1"));
                JLabel item2Label = new JLabel();
                item2Label.setBounds(50,150,100,100);
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
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item3.add(dropMenu[1]);
                dropMenu[2] = new JMenuItem(Choice2);
                dropMenu[2].addActionListener(e -> {
                    try {
                        controller.userPrompt(String.format("%s %s", Choice2, showItems.get("2")));
                        initialize();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                item3.add(dropMenu[2]);
                ImageIcon item3Icon = new ImageIcon(showItems.get("2"));
                JLabel item3Label = new JLabel();
//            knifeObjLabel.setBounds(175, 350, 100, 100);
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
        JLabel labelNav = new JLabel("Navigation");
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
            } catch (IOException ex) {
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
            } catch (IOException ex) {
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
            } catch (IOException ex) {
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
            } catch (IOException ex) {
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
        createObject(1, "take", "look");
        panelVisual[1].add(labelVisual[1], BorderLayout.PAGE_START);
        frame.add(panelVisual[1]);

//        Second Image Center CourtYard
//        backgroundLayout(2, "images/theend.jpg");
//        panelVisual[2].add(labelVisual[2], BorderLayout.PAGE_START);
//        frame.add(panelVisual[2]);
//
    }
//
//    public JPanel getPanelButtons() {
//        return panelButtons;
//    }
//
//    public void setPanelButtons(JPanel panelButtons) {
//        this.panelButtons = panelButtons;
//    }
//
//    public JPanel getPanelInventory() {
//        return panelInventory;
//    }
//
//    public void setPanelInventory(JPanel panelInventory) {
//        this.panelInventory = panelInventory;
//    }
//
//    public JPanel getPanelNav() {
//        return panelNav;
//    }
//
//    public void setPanelNav(JPanel panelNav) {
//        this.panelNav = panelNav;
//    }
//
//    public JPanel getPanelMap() {
//        return panelMap;
//    }
//
//    public void setPanelMap(JPanel panelMap) {
//        this.panelMap = panelMap;
//    }
//
//    public JPanel[] getPanelVisual() {
//        return panelVisual;
//    }
//
//    public void setPanelVisual(JPanel[] panelVisual) {
//        this.panelVisual = panelVisual;
//    }
//
//    public JLabel[] getLabelVisual() {
//        return labelVisual;
//    }
//
//    public void setLabelVisual(JLabel[] labelVisual) {
//        this.labelVisual = labelVisual;
//    }
//
//    public JPanel getPanelRoomDescription() {
//        return panelRoomDescription;
//    }
//
//    public void setPanelRoomDescription(JPanel panelRoomDescription) {
//        this.panelRoomDescription = panelRoomDescription;
//    }
//
//    public JPanel getPanelTextFeedback() {
//        return panelTextFeedback;
//    }
//
//    public void setPanelTextFeedback(JPanel panelTextFeedback) {
//        this.panelTextFeedback = panelTextFeedback;
//    }

    public String getOldLocation() {
        return oldLocation;
    }

    public void setOldLocation(String oldLocation) {
        this.oldLocation = oldLocation;
    }
}