package com.android.StarTrek.Ships.Allies;

import com.android.StarTrek.Board;
import com.android.StarTrek.Ships.Ally;

/**
 * Created by Rick van Lieshout on 3/23/15.
 * @author Rick van Lieshout
 */
public class Klingon extends Ally {
    /**
     * The Klingon class represents the hard difficulty.
     * @param game the board this object is on
     */
    public Klingon(Board game) {
        super(game, 300, 20, 93);
        setSprite("vorcha");
        setFriction(0.05);
        setShipSpeed(15);
    }
}
