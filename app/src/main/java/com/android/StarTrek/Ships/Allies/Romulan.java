package com.android.StarTrek.Ships.Allies;

import com.android.StarTrek.Board;
import com.android.StarTrek.Ships.Ally;

/**
 * Enterprise is one of the player objects available.
 * The enterprise has a difficulty of ''
 *
 * @author Rick van Lieshout
 */
public class Romulan extends Ally {

    /**
     * The Romulan class represents the extreme difficulty.
     * @param game the board this object is on
     */

    public Romulan(Board game) {
        super(game, 200, 10, 92);
        setSprite("romulan");
		setFriction(0.05);
        setShipSpeed(20);
    }
}
