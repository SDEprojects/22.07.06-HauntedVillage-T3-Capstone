package com.game.HauntedVillage;

import com.game.HauntedVillage.app.ArelysController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObjectHandler implements ActionListener {
    ArelysController arelysController;
    public ObjectHandler(ArelysController arelysController) {
        this.arelysController = arelysController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String userClick = e.getActionCommand();
        switch (userClick){
            case "TakeKnife":
                arelysController.itemsHandler.takeKnife();break;
            case "KnifeDesc":
                arelysController.itemsHandler.knifeDesc();break;
            case "takeMatches":
                arelysController.itemsHandler.takeMatches();break;
            case "matchesDesc":
                arelysController.itemsHandler.matchesDesc();break;
            case "takeCrucifix":
                arelysController.itemsHandler.takeCrucifix();break;
            case "crucifixDesc":
                arelysController.itemsHandler.crucifixDesc();break;

            case "goWest":
                arelysController.roomChanger.homeScene();break;
            case "goEast":
                arelysController.roomChanger.centerCourtyard();break;
            case "goNorth":
                arelysController.roomChanger.northernSquare();break;
            case "goSouth":
                arelysController.roomChanger.southernSquare();break;
//            case "goSouth":controller.roomChanger.farm();break;
        }
    }
//    public void actionPerformed(ActionEvent event) {
//
//        String userClick = event.getActionCommand();
//        switch (userClick){
//
//            case "South":controller.roomChanger.farm();break;
//        }
    }


