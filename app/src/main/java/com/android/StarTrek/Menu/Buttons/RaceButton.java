package com.android.StarTrek.Menu.Buttons;

import com.android.StarTrek.Board;
import com.android.StarTrek.Menu.Button;
import com.android.StarTrek.Ships.StarShip;

/**
 * Created by Rick van Lieshout on 3/26/15.
 * @author Rick van Lieshout
 */
public class RaceButton extends Button{
    /**
     * myShip holds a reference to the ship that will be used to play.
     */
    private StarShip myShip;

    /**
     * The raceButton class is used to create buttons which represent a race.
     *
     * @param game the board the button is on
     * @param player the ship that will be chosen
     * @param sprite The image for the button
     */
    public RaceButton(Board game, StarShip player, String sprite) {
        super(game);
        this.myShip = player;
        setSprite(sprite);
    }

    /**
     * This method starts the game with the ship of your choice.
     */
    @Override
    public void doAction() {
        getMygame().startGame(myShip);
    }
}
