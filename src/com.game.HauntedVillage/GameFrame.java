package com.game.HauntedVillage;

import com.game.HauntedVillage.app.ArelysController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameFrame extends JFrame {

    ArelysController arelysController;
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


    public GameFrame(ArelysController arelysController) {
        this.arelysController = arelysController;
        TitleScreen();// was initialized

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
    }

    public void initialize() {

        boardGame.setVisible(false);
        label.setVisible(false);

//        frame = new JFrame();
        frame.setTitle("Haunted Village");

        // Define the frame
//        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        frame.setLayout(null);
//        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);  // 850 500
        frame.getContentPane().setBackground(Color.BLACK);
        //frame.setLocationRelativeTo(null); // centers on screen
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


        // VISUAL panel
//        panelVisual = new JPanel();
//        panelVisual.setBackground(Color.BLACK);
//        panelVisual.setSize(50,300);
//        panelVisual.setBounds(5,50,670,500);
//        labelVisual = new JLabel();
//        ImageIcon image = new ImageIcon(new ImageIcon("images/cabinedited.jpeg").getImage().getScaledInstance(670, 500, Image.SCALE_SMOOTH)); // sets frame to size of image
//
//
//        labelVisual.setIcon(image);
//        panelVisual.add(labelVisual, BorderLayout.PAGE_START);
//        GameBackground *************************************************************************


        // MAP panel
        panelMap = new JPanel();
        panelMap.setBackground(Color.BLACK);
        panelMap.setSize(50, 300);
        panelMap.setBounds(680, 50, 300, 300);
        JLabel labelMap = new JLabel("Map");
        labelMap.setForeground(Color.GREEN);
        labelMap.setFont(new Font("Chiller", Font.PLAIN, 24));
        panelMap.add(labelMap);
        panelMap.add(MapImage.guiMap());


        // INVENTORY panel
        panelInventory = new JPanel(new BorderLayout());
        panelInventory.setBackground(Color.BLACK);
//        panelInventory.setSize(50,300);
        panelInventory.setBounds(680, 355, 300, 195);
//        JLabel labelInventory = new JLabel("Inventory");
//        panelInventory.add(labelInventory, BorderLayout.CENTER );

        String inventoryTitleString = "Inventory";
        JLabel labelInventoryTitle = new JLabel(inventoryTitleString);
        labelInventoryTitle.setForeground(Color.GRAY);
        labelInventoryTitle.setFont(new Font("Chiller", Font.PLAIN, 24));
        labelInventoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
//        room.setForeground(Font);

        String inventoryItems = "\n\nmatches \n crucifix";
        JLabel labelInventoryItems = new JLabel(inventoryItems);
//        labelRoomDescription.setFont();
        labelInventoryItems.setForeground(Color.GRAY);
        // this decides where in borderlayout they are positioned...
        panelInventory.add(labelInventoryTitle, BorderLayout.PAGE_START);
        panelInventory.add(labelInventoryItems, BorderLayout.WEST);
        panelInventory.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));

        //this.getContentPane().setBackground(new Color(0,0,10));
        // NAVIGATION panel (N,E,W,S)
//        panelNav = new JPanel();
//        panelNav.setBackground(Color.BLACK);
//        panelNav.setLayout(new BorderLayout());
////        panelNav.setSize(50,100);
////        panelNav.setBounds(680,555,300,200); // original where the box fits perfect
//        panelNav.setBounds(780, 585, 120, 120);
//        JLabel labelNav = new JLabel("Navigation");
//        panelNav.add(labelNav);

        // ROOM DESCRIPTION panel
        panelRoomDescription = new JPanel();
        panelRoomDescription.setBackground(Color.BLACK);
        panelRoomDescription.setLayout(new BorderLayout());
        panelRoomDescription.setBounds(5, 555, 400, 200);

        String roomTitleString = "Old Church";
        JLabel roomTitle = new JLabel(roomTitleString);
        roomTitle.setForeground(Color.GREEN);
        roomTitle.setFont(new Font("Chiller", Font.PLAIN, 24));
        roomTitle.setHorizontalAlignment(SwingConstants.CENTER);
//        room.setForeground(Font);

        String roomDescription = "This room is empty and dark";
        JLabel labelRoomDescription = new JLabel(roomDescription);
//        labelRoomDescription.setFont();
        labelRoomDescription.setForeground(Color.GREEN);
        gameText = new JTextArea("Game TEXT");
        gameText.setBounds(5, 555, 400, 200);
        gameText.setBackground(Color.black);
        gameText.setForeground(Color.red);
        gameText.setEditable(false);
        gameText.setLineWrap(true);
        gameText.setWrapStyleWord(true);
        gameText.setFont(new Font("Chiller", Font.PLAIN, 30));
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

        // Title of Feedback
        String feedbackTitleString = "The wind seems to mutter...";
        //  "A voice in your head tells you....", "Something tells you that....", "A distance voice moans..."
        String textFeedbackTitleString = feedbackTitleString;
        JLabel feedbackTitle = new JLabel(textFeedbackTitleString);
        feedbackTitle.setForeground(Color.GRAY);
        feedbackTitle.setFont(new Font("Chiller", Font.ITALIC, 24));
        feedbackTitle.setHorizontalAlignment(SwingConstants.CENTER);
//        room.setForeground(Font);

        // FEEDBACK
        JLabel labelTextFeedback = new JLabel("You cannot go that direction...");
        labelTextFeedback.setForeground(Color.gray);
        labelTextFeedback.setHorizontalAlignment(SwingConstants.CENTER);
//        labelTextFeedback.add(labelRoomDescription);
        panelTextFeedback.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

        panelTextFeedback.add(feedbackTitle, BorderLayout.PAGE_START);
        panelTextFeedback.add(labelTextFeedback);


        // BUTTONs instantiate

        JButton settingsButton = new JButton("Settings");
        JButton helpButton = new JButton("Help");
        JButton quitButton = new JButton("Exit");

//        panelNav = new JPanel();
//        panelNav.setBackground(Color.BLACK);
//        panelNav.setLayout(new BorderLayout());
//        panelNav.setBounds(780,585,120,120);
//        JLabel labelNav = new JLabel("Navigation");
//        panelNav.add(labelNav);
//        NorthButton = new JButton("N");
//        EastButton = new JButton("E");
//        WestButton = new JButton("W");
//        SouthButton = new JButton("S");



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


        // Navigation Buttons
        // NORTH
        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
//        NorthButton.addActionListener(e -> System.out.println("North"));
////        button.addActionListener(this);
//        NorthButton.setFocusable(false); // gets rid of box around button
//        NorthButton.setText("N");
//        //button.setIcon(icon);// adds pic to button
//        NorthButton.setHorizontalTextPosition(JButton.CENTER);
//////        NorthButton.setHorizontalTextPosition(JButton.LEFT_ALIGNMENT= (2.0f));
////        NorthButton.setAlignmentX(1.0F);
//        NorthButton.setVerticalTextPosition(JButton.CENTER);
//        NorthButton.setFont(new Font("Chiller", Font.ITALIC, 40));
//        NorthButton.setPreferredSize(new Dimension(40, 40));
//        NorthButton.setSize(40, 40);
////        NorthButton.setAlignmentX(1);
////        settingsButton.setIconTextGap(5);
//        NorthButton.setForeground(Color.red);  //(new Color(0x8A0303));
//        NorthButton.setBackground(Color.DARK_GRAY);
//        NorthButton.setBorder(BorderFactory.createEtchedBorder());
//
//        JPanel northPanel = new JPanel();
//        northPanel.setPreferredSize(new Dimension(40, 40));
//        northPanel.add(NorthButton);
//        northPanel.setBackground(Color.BLACK);
//
//
//        // EAST
//        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
//        EastButton.addActionListener(e -> System.out.println("East"));
//        EastButton.setPreferredSize(new Dimension(40, 40));
////        button.addActionListener(this);
//        EastButton.setFocusable(false); // gets rid of box around button
//        EastButton.setText("E");
//        //button.setIcon(icon);// adds pic to button
//        EastButton.setHorizontalTextPosition(JButton.CENTER);
//        EastButton.setVerticalTextPosition(JButton.CENTER);
//        EastButton.setFont(new Font("Chiller", Font.ITALIC, 40));
//        EastButton.setSize(40, 40);
////        settingsButton.setIconTextGap(5);
//        EastButton.setForeground(Color.red);  //(new Color(0x8A0303));
//        EastButton.setBackground(Color.DARK_GRAY);
//        EastButton.setBorder(BorderFactory.createEtchedBorder());
//
//
//        JPanel eastPanel = new JPanel();
//        eastPanel.setPreferredSize(new Dimension(40, 40));
//        eastPanel.add(EastButton);
//        eastPanel.setBackground(Color.BLACK);
//
//
//        // WEST
//        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
//        WestButton.addActionListener(e -> System.out.println("West"));
//        WestButton.setPreferredSize(new Dimension(40, 40));
////        button.addActionListener(this);
//        WestButton.setFocusable(false); // gets rid of box around button
//        WestButton.setText("W");
//        //button.setIcon(icon);// adds pic to button
//        WestButton.setHorizontalTextPosition(JButton.RIGHT);
//        WestButton.setVerticalTextPosition(JButton.CENTER);
//        WestButton.setFont(new Font("Chiller", Font.ITALIC, 40));
////        settingsButton.setIconTextGap(5);
//        WestButton.setForeground(Color.red);  //(new Color(0x8A0303));
//        WestButton.setBackground(Color.DARK_GRAY);
//        WestButton.setBorder(BorderFactory.createEtchedBorder());
//
//        JPanel westPanel = new JPanel();
//        westPanel.setPreferredSize(new Dimension(40, 40));
//        westPanel.add(WestButton);
//        westPanel.setBackground(Color.BLACK);
//
//
//        // South
//        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
//        SouthButton.addActionListener(e -> System.out.println("South"));
//        SouthButton.setPreferredSize(new Dimension(40, 40));
////        button.addActionListener(this);
//        SouthButton.setFocusable(false); // gets rid of box around button
//        SouthButton.setText("S");
//        //button.setIcon(icon);// adds pic to button
//        SouthButton.setHorizontalTextPosition(JButton.RIGHT);
//        SouthButton.setVerticalTextPosition(JButton.CENTER);
//        SouthButton.setFont(new Font("Chiller", Font.ITALIC, 40));
////        settingsButton.setIconTextGap(5);
//        SouthButton.setForeground(Color.red);  //(new Color(0x8A0303));
//        SouthButton.setBackground(Color.DARK_GRAY);
//        SouthButton.setBorder(BorderFactory.createEtchedBorder());
//
//        JPanel southPanel = new JPanel();
//        southPanel.setPreferredSize(new Dimension(40, 40));
//        southPanel.add(SouthButton);
//        southPanel.setBackground(Color.BLACK);
//
//
//        // CENTER PANEL
//        JPanel centerPanel = new JPanel();
//        centerPanel.setPreferredSize(new Dimension(40, 40));
////        eastPanel.add(EastButton);
//        centerPanel.setBackground(Color.BLACK);

        //https://www.javaswingdev.com/2022/02/gradient-dropdown-menu-using-java-swing.html
//        GradientDropdownMenu menu = new GradientDropdownMenu();
//        menu.addItem("Home");
//        menu.addItem("Features", "Ticker New", "Featured Styles", "Content Blocks");
//        menu.apply(frame);

        // Add items to panelButtons
        panelButtons.add(settingsButton);
        panelButtons.add(helpButton);
        panelButtons.add(quitButton);
//        panelButtons.add(JDDM);

//        panelNav.add(northPanel, BorderLayout.PAGE_START);
//        panelNav.add(eastPanel, BorderLayout.EAST);
//        panelNav.add(westPanel, BorderLayout.WEST);
//        panelNav.add(southPanel, BorderLayout.PAGE_END);
//        panelNav.add(centerPanel);

        // Add items to frame
        frame.add(panelButtons);
//        frame.add(panelButtons, BorderLayout.PAGE_START);
//       frame.add(panelVisual[]);
        frame.add(panelMap);
//        frame.add(panelMap, BorderLayout.EAST);
//        frame.add(panelNav);
        createDirectionButtons();
        frame.add(panelInventory);
        frame.add(panelRoomDescription);
        frame.add(panelTextFeedback);

    }

    public void backgroundLayout(int visualNum, String backGroundImage) {

        panelVisual[visualNum] = new JPanel();
        panelVisual[visualNum].setBackground(Color.BLACK);
        panelVisual[visualNum].setSize(50, 300);
        panelVisual[visualNum].setBounds(5, 50, 670, 500);
        labelVisual[visualNum] = new JLabel();
        ImageIcon image = new ImageIcon(new ImageIcon(backGroundImage).getImage().getScaledInstance(670, 500, Image.SCALE_SMOOTH)); // sets frame to size of image


        labelVisual[visualNum].setIcon(image);
//        panelVisual[visualNum].add(labelVisual[visualNum], BorderLayout.PAGE_START);
        frame.add(panelVisual[visualNum]);
    }

    public void createObject(int visualNum, String Choice1, String Choice2) {
//        create dropdown menu
        inspectKnife = new JPopupMenu();
        inspectMatches = new JPopupMenu();
        inspectCrucifix = new JPopupMenu();

//        knifeObjLabel, matchesObjLabel, crucifixObjLabel;
//        create the options in the dropdown
        JMenuItem[] dropMenu = new JMenuItem[4];

        dropMenu[1] = new JMenuItem(Choice1);
        dropMenu[1].addActionListener(arelysController.objectsActions);
        dropMenu[1].setActionCommand("TakeKnife");
        inspectKnife.add(dropMenu[1]);


        dropMenu[2] = new JMenuItem(Choice2);
        dropMenu[2].addActionListener(arelysController.objectsActions);
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
        dropMenu[1].addActionListener(arelysController.objectsActions);
        dropMenu[1].setActionCommand("takeMatches");
        inspectMatches.add(dropMenu[1]);


        dropMenu[2] = new JMenuItem(Choice2);
        dropMenu[2].addActionListener(arelysController.objectsActions);
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
        dropMenu[1].addActionListener(arelysController.objectsActions);
        dropMenu[1].setActionCommand("takeCrucifix");
        inspectCrucifix.add(dropMenu[1]);


        dropMenu[2] = new JMenuItem(Choice2);
        dropMenu[2].addActionListener(arelysController.objectsActions);
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
        NorthButton.addActionListener(e -> System.out.println("North"));
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
        NorthButton.addActionListener(arelysController.objectsActions);
        NorthButton.setActionCommand("goNorth");
        NorthButton.setBorder(BorderFactory.createEtchedBorder());

        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(40, 40));
        northPanel.add(NorthButton);
        northPanel.setBackground(Color.BLACK);


        // EAST
        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
        EastButton.addActionListener(e -> System.out.println("East"));
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
        EastButton.addActionListener(arelysController.objectsActions);
        EastButton.setActionCommand("goEast");
        EastButton.setBorder(BorderFactory.createEtchedBorder());


        eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(40, 40));
        eastPanel.add(EastButton);
        eastPanel.setBackground(Color.BLACK);


        // WEST
        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
        WestButton.addActionListener(e -> System.out.println("West"));
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
        WestButton.addActionListener(arelysController.objectsActions);
        WestButton.setActionCommand("goWest");
        WestButton.setBorder(BorderFactory.createEtchedBorder());

        westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(40, 40));
        westPanel.add(WestButton);
        westPanel.setBackground(Color.BLACK);


        // South
        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size
        SouthButton.addActionListener(e -> System.out.println("South"));
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
        SouthButton.addActionListener(arelysController.objectsActions);
        SouthButton.setActionCommand("goSouth");
        SouthButton.setActionCommand("South");
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
        backgroundLayout(1, "images/cabinedited.jpg");
        createObject(1, "get", "Talk");
        panelVisual[1].add(labelVisual[1], BorderLayout.PAGE_START);

//        Second Image Center CourtYard
        backgroundLayout(2, "images/spookyVillageedited.jpg");
//        createObject(2, "get", "Talk");
        panelVisual[2].add(labelVisual[2], BorderLayout.PAGE_START);

//        Third Image Northern Square
        backgroundLayout(3, "images/northernSquare2.jpg");
//        createObject(3, "get", "Talk");
        panelVisual[3].add(labelVisual[3], BorderLayout.PAGE_START);

//        Fourth Image Southern Square
        backgroundLayout(4, "images/southernSquare.jpg");
//        createObject(3, "get", "Talk");
        panelVisual[4].add(labelVisual[4], BorderLayout.PAGE_START);

        //        Fifth Image Farm Square
        backgroundLayout(5, "images/farm.jpg");
//        createObject(3, "get", "Talk");
        panelVisual[5].add(labelVisual[5], BorderLayout.PAGE_START);


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