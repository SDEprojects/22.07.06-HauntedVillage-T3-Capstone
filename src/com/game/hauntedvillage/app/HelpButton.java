package com.game.hauntedvillage.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HelpButton {
    JButton helpButton(ImageIcon icon) {
        JButton helpButton = new JButton("Help");
        helpButton.addActionListener(e -> System.out.println("help"));
        // HELP Button
        //settingsButton.setBounds(0,10,150,50);  // button locatoin and button size

        helpButton.setPreferredSize(new Dimension(273, 40));
//        button.addActionListener(this);
        helpButton.setFocusable(false); // gets rid of box around button
        helpButton.setText("Help");
        //button.setIcon(icon);// adds pic to button
        helpButton.setHorizontalTextPosition(JButton.RIGHT);
        helpButton.setVerticalTextPosition(JButton.CENTER);
        helpButton.setFont(new Font("Courier", Font.ITALIC, 40));
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

                frame.setVisible(true);
                //ICON
                frame.setIconImage(icon.getImage()); // change icon in upper left

                // Section: The Panel
                JPanel panelPopUpSettings = new JPanel();
                panelPopUpSettings.setBackground(Color.BLACK);
                panelPopUpSettings.setForeground(Color.GREEN);
                panelPopUpSettings.setFont(new Font("Courier", Font.PLAIN, 18));

                // ADD text
                String helpTitle = "                   Help with Point and Click";
                String helpMove = "\n\n\nMovement: Click on an arrow.";
                String helpItems = "\n\nItems: Right click to look or take or interact.";
                String helpPeople = "\n\nPeople: Right click to look or talk or interact.";

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
        return helpButton;
    }
}