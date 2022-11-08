package com.game.HauntedVillage.app;

import com.game.HauntedVillage.GameFrame;
import com.game.HauntedVillage.ItemHandler;
import com.game.HauntedVillage.ObjectHandler;
import com.game.HauntedVillage.RoomChanger;

public class ArelysController {

    // FIELDS
    public ObjectHandler objectsActions = new ObjectHandler(this);
    public GameFrame gameFrame = new GameFrame(this);
    public ItemHandler itemsHandler = new ItemHandler(this);
    public RoomChanger roomChanger = new RoomChanger(this);

    public static void main(String[] args) {
        new ArelysController();
    }

    // CONSTRUCTOR
    public ArelysController() {
    }

}
