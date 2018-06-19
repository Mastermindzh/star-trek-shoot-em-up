package com.android.StarTrek.Menu.Buttons;

import android.util.Log;

import com.android.StarTrek.Board;
import com.android.StarTrek.Menu.Button;
import com.android.StarTrek.Ships.Allies.Enterprise;

/**
 * Created by Rick van Lieshout on 3/26/15.
 * @author Rick van Lieshout
 */
public class startButton extends Button{
    /**
     * The startButton class draws an object on the screen which acts as a button to start the game
     * @param game reference to the board the button is on.
     */
    public startButton(Board game) {
        super(game);
        this.setSprite("start");
    }

    /**
     * This method starts the game.
     */
    @Override
    public void doAction() {
     getMygame().startShipSelection();
    }
}
