package com.android.StarTrek.Ships.Allies;

import com.android.StarTrek.Board;
import com.android.StarTrek.Ships.Ally;

/**
 * Enterprise is one of the player objects available.
 * The enterprise has a difficulty of ''
 *
 * @author Rick van Lieshout
 */
public class Species8472 extends Ally {

    /**
     * The Species8472 class represents the easy difficulty.
     * @param game the board this object is on
     */

    public Species8472(Board game) {
        super(game, 500, 50, 100);
        setSprite("species8472");
		setFriction(0.05);
		setShipSpeed(30);
    }
}
