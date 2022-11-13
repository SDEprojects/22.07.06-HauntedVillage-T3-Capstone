package com.game.hauntedvillage.app;

import com.game.hauntedvillage.controller.Controller;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class InventoryPanel {
    private JPanel panelInventory;
    private Controller controllerInstance = Controller.getInstance();

    InventoryPanel() throws UnsupportedAudioFileException, IOException {
    }

    JPanel inventoryPanel() throws UnsupportedAudioFileException, IOException {
        // INVENTORY panel
        panelInventory = new JPanel(new BorderLayout());
        panelInventory.setBackground(Color.BLACK);
        panelInventory.setBounds(680, 355, 300, 195);

        String inventoryTitleString = "Inventory";
        JLabel labelInventoryTitle = new JLabel(inventoryTitleString);
        labelInventoryTitle.setForeground(Color.GREEN);
        labelInventoryTitle.setFont(new Font("Chiller", Font.PLAIN, 24));
        labelInventoryTitle.setHorizontalAlignment(SwingConstants.CENTER);

        if(controllerInstance.gameInventory().size() > 0 && controllerInstance.gameInventory().get(0) != null) {
            ImageIcon inventoryItem1 = new ImageIcon(controllerInstance.gameInventory().get(0));
            JLabel item1Label = new JLabel(inventoryItem1);
            item1Label.setBounds(10, 30, 60, 100);

            panelInventory.add(item1Label);
        }
        if(controllerInstance.gameInventory().size() > 1 && controllerInstance.gameInventory().get(1) != null) {
            ImageIcon inventoryItem2 = new ImageIcon(controllerInstance.gameInventory().get(1));
            JLabel item2Label = new JLabel(inventoryItem2);
            item2Label.setBounds(70, 30, 60, 100);

            panelInventory.add(item2Label);
        }
        if(controllerInstance.gameInventory().size() > 2 && controllerInstance.gameInventory().get(2) != null) {
            ImageIcon inventoryItem3 = new ImageIcon(controllerInstance.gameInventory().get(2));
            JLabel item3Label = new JLabel(inventoryItem3);
            item3Label.setBounds(170, 30, 60, 100);

            panelInventory.add(item3Label);
        }
        if(controllerInstance.gameInventory().size() > 3 && controllerInstance.gameInventory().get(3) != null) {
            ImageIcon inventoryItem4 = new ImageIcon(controllerInstance.gameInventory().get(3));
            JLabel item4Label = new JLabel(inventoryItem4);
            item4Label.setBounds(230, 30, 60, 100);

            panelInventory.add(item4Label);
        }
        if(controllerInstance.gameInventory().size() > 4 && controllerInstance.gameInventory().get(4) != null) {
            ImageIcon inventoryItem5 = new ImageIcon(controllerInstance.gameInventory().get(4));
            JLabel item5Label = new JLabel(inventoryItem5);
            item5Label.setBounds(10, 120, 60, 100);

            panelInventory.add(item5Label);
        }
        if(controllerInstance.gameInventory().size() > 5 && controllerInstance.gameInventory().get(5) != null) {
            ImageIcon inventoryItem6 = new ImageIcon(controllerInstance.gameInventory().get(5));
            JLabel item6Label = new JLabel(inventoryItem6);

            panelInventory.add(item6Label);
        }
        if(controllerInstance.gameInventory().size() > 6 && controllerInstance.gameInventory().get(6) != null) {
            ImageIcon inventoryItem7 = new ImageIcon(controllerInstance.gameInventory().get(6));
            JLabel item7Label = new JLabel(inventoryItem7);

            panelInventory.add(item7Label);
        }
        panelInventory.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
        panelInventory.add(labelInventoryTitle, BorderLayout.PAGE_START);

        return panelInventory;
    }
}