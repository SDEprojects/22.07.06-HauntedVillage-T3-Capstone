package com.game.HauntedVillage;

import com.game.HauntedVillage.app.ArelysController;

public class RoomChanger {
    ArelysController arelysController;

    public RoomChanger(ArelysController arelysController) {
        this.arelysController = arelysController;
    }

    public void homeScene() {
        arelysController.gameFrame.panelVisual[1].setVisible(true);
        arelysController.gameFrame.panelVisual[2].setVisible(false);

    }

    public void centerCourtyard() {
        arelysController.gameFrame.panelVisual[1].setVisible(false);
        arelysController.gameFrame.panelVisual[2].setVisible(true);

    }

    public void northernSquare() {
        arelysController.gameFrame.panelVisual[1].setVisible(false);
        arelysController.gameFrame.panelVisual[2].setVisible(false);
        arelysController.gameFrame.panelVisual[3].setVisible(true);
    }
    public void southernSquare() {
        arelysController.gameFrame.panelVisual[1].setVisible(false);
        arelysController.gameFrame.panelVisual[2].setVisible(false);
        arelysController.gameFrame.panelVisual[3].setVisible(false);
        arelysController.gameFrame.panelVisual[4].setVisible(true);
    }
    public void farm(){
        arelysController.gameFrame.panelVisual[1].setVisible(false);
        arelysController.gameFrame.panelVisual[2].setVisible(false);
        arelysController.gameFrame.panelVisual[3].setVisible(false);
        arelysController.gameFrame.panelVisual[4].setVisible(false);
        arelysController.gameFrame.panelVisual[5].setVisible(true);
    }
}
