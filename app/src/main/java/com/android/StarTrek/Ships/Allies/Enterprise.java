package com.android.StarTrek.Ships.Allies;

import com.android.StarTrek.Board;
import com.android.StarTrek.Ships.Ally;

/**
 * Enterprise is one of the player objects available.
 * The enterprise has a difficulty of ''
 *
 * @author Rick van Lieshout
 */
public class Enterprise extends Ally {

    /**
     * The enterprise class represents the medium difficulty.
     * @param game the board this object is on
     */
    public Enterprise(Board game) {
        super(game, 400, 25, 95);
        setSprite("enterprise");
		setFriction(0.1);
		setShipSpeed(12);
    }
}
