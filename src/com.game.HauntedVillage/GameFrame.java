package com.game.HauntedVillage;

import com.game.HauntedVillage.app.Print;
import com.game.HauntedVillage.controller.ArelysController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

import com.game.HauntedVillage.controller.Controller;

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
    private JButton playButton, quitButton;
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

    public void TitleScreen() {

        frame = new JFrame();
        label = new JLabel();  // create label
        ImageIcon image = new ImageIcon(new ImageIcon("images/spookyVillageedited.jpg").getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH)); // sets frame to size of image

        // JFrame
        frame.setTitle("Haunted Village"); //sets title of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
        frame.setSize(1000, 800); //sets the x-dimension, and y-dimension of frame
//        this.setResizable(false); // prevent from being resized
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); //make frame visible


        //ICON
        icon = new ImageIcon("resources/eyes.jpeg"); // change icon in upper left
        frame.setIconImage(icon.getImage()); // change icon in upper left
        //this.getContentPane().setBackground(new Color(0,0,10));


// TODO works to here

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
        //label.setBounds(0,0, 2000,1600); // set x,y position w/in frame as well as dimensions

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
        oldLocation = controller.showAreaDescription();
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
        ImageIcon icon = new ImageIcon("images/MicrosoftTeams-image.png"); // change icon in upper left
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
        String inventory = controller.gameInventory().toString();
        JTextArea inventoryText = new JTextArea();
        inventoryText.setFont(new Font("Chiller", Font.PLAIN, 20));
        inventoryText.setForeground(Color.RED);
        inventoryText.setBackground(Color.black);
        inventoryText.setText(inventory.substring(1, inventory.length() - 1));
        inventoryText.setLineWrap(true);
        inventoryText.setWrapStyleWord(true);
        labelInventoryTitle.add(inventoryText);

        panelInventory.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
        panelInventory.add(labelInventoryTitle, BorderLayout.PAGE_START);
        panelInventory.add(inventoryText);

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
//        room.setForeground(Font);

        String roomDescription = "This room is empty and dark";
//        String roomDescription = controller.showAreaDescription();
        JLabel labelRoomDescription = new JLabel(roomDescription);
//        labelRoomDescription.setFont();
        labelRoomDescription.setForeground(Color.GREEN);
        gameText = new JTextArea();
        // Title of Feedback
        String feedbackTitleString = "";
//        JLabel labelTextFeedback = new JLabel();
        JTextArea feedbackWrap = new JTextArea();
        feedbackWrap.setFont(new Font("Chiller", Font.PLAIN, 20));
        feedbackWrap.setForeground(Color.RED);
        feedbackWrap.setBackground(Color.black);
        feedbackWrap.setLineWrap(true);
        feedbackWrap.setWrapStyleWord(true);
        if(controller.getPlayerUpdate().size() > 0) {
            feedbackTitleString = "The wind seems to mutter...";
            String convert = controller.getPlayerUpdate().toString();
            feedbackWrap.setText(convert.substring(1, convert.length() - 1));
            gameText.setText(oldLocation);
        }
        else {
            oldLocation = controller.showAreaDescription();
            gameText.setText(oldLocation);
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
//        panelNav.setSize(50,100);
        panelTextFeedback.setBounds(410, 555, 265, 200);

        //  "A voice in your head tells you....", "Something tells you that....", "A distance voice moans..."
        String textFeedbackTitleString = feedbackTitleString;
        JLabel feedbackTitle = new JLabel(textFeedbackTitleString);
        feedbackTitle.setForeground(Color.GRAY);
        feedbackTitle.setFont(new Font("Chiller", Font.ITALIC, 24));
        feedbackTitle.setHorizontalAlignment(SwingConstants.CENTER);
//        room.setForeground(Font);

        // FEEDBACK

//        labelTextFeedback.setForeground(Color.red);
//        labelTextFeedback.setHorizontalAlignment(SwingConstants.CENTER);
//        labelTextFeedback.add(labelRoomDescription);
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
        quitButton.addActionListener(e -> System.out.println("quit"));
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
//        create dropdown menu
        inspectKnife = new JPopupMenu();
        inspectMatches = new JPopupMenu();
        inspectCrucifix = new JPopupMenu();

        JMenuItem[] dropMenu = new JMenuItem[4];

        dropMenu[1] = new JMenuItem(Choice1);
        dropMenu[1].setActionCommand("TakeKnife");
        inspectKnife.add(dropMenu[1]);


        dropMenu[2] = new JMenuItem(Choice2);
        dropMenu[2].setActionCommand("KnifeDesc");
        inspectKnife.add(dropMenu[2]);

        knifeObjLabel = new JLabel();
        knifeObjLabel.setBounds(175, 350, 100, 100);

        gameIcon = new ImageIcon("images/knife.jpg");
        knifeObjLabel.setIcon(gameIcon);

        knifeObjLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {

            }

            @Override
            public void mousePressed(MouseEvent event) {
                if (SwingUtilities.isRightMouseButton(event)) {
                    inspectKnife.show(knifeObjLabel, event.getX(), event.getY());
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
        inspectMatches = new JPopupMenu();


        dropMenu[1] = new JMenuItem(Choice1);
//        dropMenu[1].addActionListener(arelysController.objectsActions);
        dropMenu[1].setActionCommand("takeMatches");
        inspectMatches.add(dropMenu[1]);


        dropMenu[2] = new JMenuItem(Choice2);
//        dropMenu[2].addActionListener(arelysController.objectsActions);
        dropMenu[2].setActionCommand("matchesDesc");
        inspectMatches.add(dropMenu[2]);

        matchesObjLabel = new JLabel();
        matchesObjLabel.setBounds(400,200,100,100);

        gameIcon = new ImageIcon("images/matches.jpg");
        matchesObjLabel.setIcon(gameIcon);

        matchesObjLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {

            }

            @Override
            public void mousePressed(MouseEvent event) {
                if (SwingUtilities.isRightMouseButton(event)) {
                    inspectMatches.show(matchesObjLabel, event.getX(), event.getY());
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

        dropMenu[1] = new JMenuItem(Choice1);
//        dropMenu[1].addActionListener(arelysController.objectsActions);
        dropMenu[1].setActionCommand("takeCrucifix");
        inspectCrucifix.add(dropMenu[1]);


        dropMenu[2] = new JMenuItem(Choice2);
//        dropMenu[2].addActionListener(arelysController.objectsActions);
        dropMenu[2].setActionCommand("crucifixDesc");
        inspectCrucifix.add(dropMenu[2]);

        crucifixObjLabel = new JLabel();
        crucifixObjLabel.setBounds(50,150,100,100);

        gameIcon = new ImageIcon("images/crucifix.jpg");
        crucifixObjLabel.setIcon(gameIcon);

        crucifixObjLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {

            }

            @Override
            public void mousePressed(MouseEvent event) {
                if (SwingUtilities.isRightMouseButton(event)) {
                    inspectCrucifix.show(crucifixObjLabel, event.getX(), event.getY());
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

        labelVisual[1].add(knifeObjLabel);
        labelVisual[1].add(matchesObjLabel);
        labelVisual[1].add(crucifixObjLabel);
//        panelVisual[visualNum].add(labelVisual[visualNum], BorderLayout.PAGE_START);


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
//        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
        NorthButton.addActionListener(e -> {
            try {
                controller.userPrompt("go north");
                initialize();
                createScreen();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
//        button.addActionListener(this);
        NorthButton.setFocusable(false); // gets rid of box around button
        NorthButton.setText("N");
        //button.setIcon(icon);// adds pic to button
        NorthButton.setHorizontalTextPosition(JButton.CENTER);
////        NorthButton.setHorizontalTextPosition(JButton.LEFT_ALIGNMENT= (2.0f));
//        NorthButton.setAlignmentX(1.0F);
        NorthButton.setVerticalTextPosition(JButton.CENTER);
        NorthButton.setFont(new Font("Chiller", Font.ITALIC, 40));
        NorthButton.setPreferredSize(new Dimension(40, 40));
        NorthButton.setSize(40, 40);
//        NorthButton.setAlignmentX(1);
//        settingsButton.setIconTextGap(5);
        NorthButton.setForeground(Color.red);  //(new Color(0x8A0303));
        NorthButton.setBackground(Color.DARK_GRAY);
//        NorthButton.addActionListener(arelysController.objectsActions);
//        NorthButton.setActionCommand("goNorth");
        NorthButton.setBorder(BorderFactory.createEtchedBorder());

        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(40, 40));
        northPanel.add(NorthButton);
        northPanel.setBackground(Color.BLACK);


        // EAST
        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
//        EastButton.addActionListener(e -> System.out.println("East"));
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
//        button.addActionListener(this);
        EastButton.setFocusable(false); // gets rid of box around button
        EastButton.setText("E");
        //button.setIcon(icon);// adds pic to button
        EastButton.setHorizontalTextPosition(JButton.CENTER);
        EastButton.setVerticalTextPosition(JButton.CENTER);
        EastButton.setFont(new Font("Chiller", Font.ITALIC, 40));
        EastButton.setSize(40, 40);
//        settingsButton.setIconTextGap(5);
        EastButton.setForeground(Color.red);  //(new Color(0x8A0303));
        EastButton.setBackground(Color.DARK_GRAY);
//        EastButton.addActionListener(arelysController.objectsActions);
//        EastButton.addActionListener(controller.userPrompt("go east"));
//        EastButton.setActionCommand("goEast");
        EastButton.setBorder(BorderFactory.createEtchedBorder());


        eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(40, 40));
        eastPanel.add(EastButton);
        eastPanel.setBackground(Color.BLACK);


        // WEST
        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
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
//        button.addActionListener(this);
        WestButton.setFocusable(false); // gets rid of box around button
        WestButton.setText("W");
        //button.setIcon(icon);// adds pic to button
        WestButton.setHorizontalTextPosition(JButton.RIGHT);
        WestButton.setVerticalTextPosition(JButton.CENTER);
        WestButton.setFont(new Font("Chiller", Font.ITALIC, 40));
//        settingsButton.setIconTextGap(5);
        WestButton.setForeground(Color.red);  //(new Color(0x8A0303));
        WestButton.setBackground(Color.DARK_GRAY);
//        WestButton.addActionListener(arelysController.objectsActions);
//        WestButton.setActionCommand("goWest");
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
//        button.addActionListener(this);
        SouthButton.setFocusable(false); // gets rid of box around button
        SouthButton.setText("S");
        //button.setIcon(icon);// adds pic to button
        SouthButton.setHorizontalTextPosition(JButton.RIGHT);
        SouthButton.setVerticalTextPosition(JButton.CENTER);
        SouthButton.setFont(new Font("Chiller", Font.ITALIC, 40));
//        settingsButton.setIconTextGap(5);
        SouthButton.setForeground(Color.red);  //(new Color(0x8A0303));
        SouthButton.setBackground(Color.DARK_GRAY);
//        SouthButton.addActionListener(arelysController.objectsActions);
//        SouthButton.setActionCommand("goSouth");
//        SouthButton.setActionCommand("South");
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
        createObject(1, "get", "Talk");
        panelVisual[1].add(labelVisual[1], BorderLayout.PAGE_START);
        frame.add(panelVisual[1]);

//        Second Image Center CourtYard
//        backgroundLayout(2);
////        createObject(2, "get", "Talk");
//        panelVisual[2].add(labelVisual[2], BorderLayout.PAGE_START);
//
////        Third Image Northern Square
//        backgroundLayout(3);
////        createObject(3, "get", "Talk");
//        panelVisual[3].add(labelVisual[3], BorderLayout.PAGE_START);
//
////        Fourth Image Southern Square
//        backgroundLayout(4);
////        createObject(3, "get", "Talk");
//        panelVisual[4].add(labelVisual[4], BorderLayout.PAGE_START);
//
//        //        Fifth Image Farm Square
//        backgroundLayout(5);
////        createObject(3, "get", "Talk");
//        panelVisual[5].add(labelVisual[5], BorderLayout.PAGE_START);


    }


    public JPanel getPanelButtons() {
        return panelButtons;
    }

    public void setPanelButtons(JPanel panelButtons) {
        this.panelButtons = panelButtons;
    }

    public JPanel getPanelInventory() {
        return panelInventory;
    }

    public void setPanelInventory(JPanel panelInventory) {
        this.panelInventory = panelInventory;
    }

    public JPanel getPanelNav() {
        return panelNav;
    }

    public void setPanelNav(JPanel panelNav) {
        this.panelNav = panelNav;
    }

    public JPanel getPanelMap() {
        return panelMap;
    }

    public void setPanelMap(JPanel panelMap) {
        this.panelMap = panelMap;
    }

    public JPanel[] getPanelVisual() {
        return panelVisual;
    }

    public void setPanelVisual(JPanel[] panelVisual) {
        this.panelVisual = panelVisual;
    }

    public JLabel[] getLabelVisual() {
        return labelVisual;
    }

    public void setLabelVisual(JLabel[] labelVisual) {
        this.labelVisual = labelVisual;
    }

    public JPanel getPanelRoomDescription() {
        return panelRoomDescription;
    }

    public void setPanelRoomDescription(JPanel panelRoomDescription) {
        this.panelRoomDescription = panelRoomDescription;
    }

    public JPanel getPanelTextFeedback() {
        return panelTextFeedback;
    }

    public void setPanelTextFeedback(JPanel panelTextFeedback) {
        this.panelTextFeedback = panelTextFeedback;
    }
}