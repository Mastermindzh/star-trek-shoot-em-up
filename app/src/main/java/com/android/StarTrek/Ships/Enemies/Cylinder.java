package com.android.StarTrek.Ships.Enemies;

import com.android.StarTrek.Board;
import com.android.StarTrek.Ships.Bars.Bar;
import com.android.StarTrek.Ships.Enemy;

/**
 * Created by Rick van Lieshout on 3/23/15.
 * @author  Rick van Lieshout
 */
public class Cylinder extends Enemy {

    /**
     * The cylinder is the basic enemy.
     * @param game the board that holds this object
     */
    public Cylinder(Board game) {
        super(game, 25, 15, 60,10);
        setSprite("cylinder");

    }
}
