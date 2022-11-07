//package com.game.HauntedVillage;
//
//import javax.swing.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//public class ObjectsGame {
//
//
//    ObjectsGame(){
//
//        createScreen();
//
//    }
//
//
//
//    public void createObject(String Choice1, String Choice2, String Choice3){
//
//        JPopupMenu inspectSword = new JPopupMenu();
//
//        JMenuItem  [] swordMenu = new JMenuItem[4];
//
//        swordMenu[1] = new JMenuItem(Choice1);
//        inspectSword.add(swordMenu[1]);
//
//        swordMenu[2] = new JMenuItem(Choice2);
//        inspectSword.add(swordMenu[2]);
//        swordMenu[3] = new JMenuItem(Choice3);
//        inspectSword.add(swordMenu[3]);
//
//        JLabel swordObject = new JLabel();
//        swordObject.setBounds(20,20,75,75);
//
//        ImageIcon swordIcon = new ImageIcon("images/piercing-sword.jpg");
//        swordObject.setIcon(swordIcon);
//
//        swordObject.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent event) {
//                if(SwingUtilities.isRightMouseButton(event)){
//                    inspectSword.show(swordObject,event.getX(), event.getY());
//                }
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
//
//        new GameFrame().getPanelVisual().add(swordObject);
////        backGroundPanel[1].add(backGroundLabel[1]);
//
//
//    }
//    public void createScreen(){
//        createObject("look", "get", "Talk");
//
//    }
//
//}
