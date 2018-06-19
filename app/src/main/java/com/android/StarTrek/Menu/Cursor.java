package com.android.StarTrek.Menu;

import com.android.StarTrek.Board;
import com.android.StarTrek.Ships.Ally;
import com.android.StarTrek.Ships.StarShip;

/**
 * Created by mastermindzh on 3/26/15.
 */
public class Cursor extends Ally {
    /**
     * Cursor is the simplest implementation of a StarShip you can possible imagine.
     * It has a high speed, low friction and it's damage doesn't matter.
     * @param game The board where the cursor is on.
     */
    public Cursor(Board game) {
        super(game, 400, 40, 95);
        setSprite("cursor");
        setFriction(0.05);
        setShipSpeed(35);
    }
}
