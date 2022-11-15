package com.game.hauntedvillage.app;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import com.game.hauntedvillage.controller.Controller;

public class GameFrame extends JFrame {

    private JFrame frame;
    private final InventoryPanel inventory = new InventoryPanel();
    private final RoomDesPanel roomDes = new RoomDesPanel();
    private final FeedbackPanel feedback = new FeedbackPanel();
    private final SettingsButton settings = new SettingsButton();
    private final HelpButton helpButton = new HelpButton();
    private final MapPanel mapPanel = new MapPanel();
    private final QuitButton quitButton = new QuitButton();
    private final ItemImage itemImage = new ItemImage();
    private final NpcImage npcImage = new NpcImage();
    private JLabel label, end;
    private JPanel boardGame;
    private final JLabel[] labelVisual = new JLabel[10];
    private final JPanel[] panelVisual = new JPanel[10];
    private boolean unlit = true;
    private final DirectionalPanel panelNav = new DirectionalPanel();
    private static GameFrame instance;

    public GameFrame() throws IOException, UnsupportedAudioFileException {
    }

    public static GameFrame getInstance() throws UnsupportedAudioFileException, IOException {
        if(instance != null) {
            return instance;
        }
        return instance = new GameFrame();
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
        ImageIcon icon = new ImageIcon("images/eyes.png"); // change icon in upper left
        frame.setIconImage(icon.getImage()); // change icon in upper left

        label.setText("Haunted Village");
        label.setForeground(new Color(0x8A0303));  // text color
        label.setBackground(Color.black); // set background color
        label.setOpaque(true); // display background color
        label.setFont(new Font("Courier", Font.PLAIN, 110));

        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        boardGame = new JPanel();
        boardGame.setLayout(new GridLayout(1, 2));
        boardGame.setBackground(Color.black);
        boardGame.setBounds(350, 650, 300, 45);
        setFont(new Font("Courier", Font.ITALIC, 40));

        // BOARD START BUTTON
        JButton playButton = new JButton("START GAME");
        boardGame.add(playButton);
        playButton.setFont(new Font("Courier", Font.ITALIC, 20));
        playButton.setForeground(Color.BLACK);
        playButton.setOpaque(true);
        playButton.setBackground(Color.DARK_GRAY);
        playButton.setBorder(BorderFactory.createEtchedBorder());
        playButton.addActionListener(e -> {
            try {
                initialize();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                createScreen();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        playButton.setFocusPainted(false);

        JButton endButton = new JButton("Quit");
        endButton.setFont(new Font("Courier", Font.ITALIC, 20));
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
    }
    void quitMainScreen(){
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel quitLabel = new JLabel();
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon image = new ImageIcon(new ImageIcon(classLoader.getResource("images/thankyouforplaying.jpg")).getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH)); // sets frame to size of image
        quitLabel.setBackground(Color.black); // set background color
        quitLabel.setIcon(image);

        frame.add(quitLabel);
        frame.setVisible(true);
    }

    void initialize() throws UnsupportedAudioFileException, IOException {
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
        JPanel panelButtons;
        panelButtons = new JPanel(new FlowLayout());
        panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 5));
        panelButtons.setBackground(Color.BLACK);
        panelButtons.setSize(1000, 50);

        panelButtons.add(settings.settingsButton(icon));
        panelButtons.add(helpButton.helpButton(icon));
        panelButtons.add(quitButton.quitButton());

        // Add items to frame
        frame.add(panelButtons);
        frame.add(mapPanel.mapPanel());
        frame.add(panelNav.createDirectionButtons());
        frame.add(inventory.inventoryPanel());
        frame.add(roomDes.roomDesPanel());
        frame.add(feedback.feedbackPanel());
    }
    void quitGame(String endType) {
        panelVisual[1].setVisible(false);

        frame.getContentPane().removeAll();
        frame.repaint();
        //frame.add(panelVisual[1]);
        createQuitScreen(endType);
    }

    void backgroundLayout(int visualNum, String bgImage) {

        panelVisual[visualNum] = new JPanel();
        panelVisual[visualNum].setBackground(Color.BLACK);
        panelVisual[visualNum].setSize(50, 300);
        panelVisual[visualNum].setBounds(5, 50, 670, 500);
        labelVisual[visualNum] = new JLabel();
        ImageIcon image = new ImageIcon(new ImageIcon(bgImage).getImage().getScaledInstance(670, 500, Image.SCALE_SMOOTH)); // sets frame to size of image

        labelVisual[visualNum].setIcon(image);
    }

    void createScreen() throws UnsupportedAudioFileException, IOException {
//        First Image Home
        backgroundLayout(1, Controller.getInstance().backgroundJpeg());
        itemImage.createItemImage("take", "look");
        npcImage.createNpc("talk", "fight");
        panelVisual[1].add(labelVisual[1], BorderLayout.PAGE_START);
        frame.add(panelVisual[1]);
    }

    void createQuitScreen(String endType) {
//      First Image Home
        if(endType.equals("quit")) {
            endGameScreen("./images/theend.jpg");
        }
        else if(endType.equals("win")) {
            endGameScreen( "images/demonDeath.png");

            end.setText("It's trapped in The Void...");
            end.setForeground(new Color(0x8A0303));  // text color
            end.setOpaque(true); // display background color
            end.setFont(new Font("Courier", Font.PLAIN, 80));
            end.setHorizontalTextPosition(JLabel.CENTER);
            end.setVerticalTextPosition(JLabel.CENTER);
            end.setVerticalAlignment(JLabel.CENTER);
            end.setHorizontalAlignment(JLabel.CENTER);
        }

        end.setVisible(true);
        frame.setContentPane(end);
        frame.setVisible(true);
    }

    void endGameScreen(String imagePath) {
        end = new JLabel();
        ImageIcon image = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH)); // sets frame to size of image
        end.setBackground(Color.black); // set background color
        end.setIcon(image);
    }

    void addPanel(JLabel jLabel) {
        labelVisual[1].add(jLabel);
    }

    boolean isUnlit() {
        return unlit;
    }

    void setUnlit(boolean unlit) {
        this.unlit = unlit;
    }
}