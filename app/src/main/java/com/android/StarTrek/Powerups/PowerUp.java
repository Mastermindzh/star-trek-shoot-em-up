package com.android.StarTrek.Powerups;

import android.gameengine.icadroids.objects.GameObject;

import com.android.StarTrek.Ships.Ally;
import com.android.StarTrek.Ships.StarShip;

/**
 * Created by Rick van Lieshout on 4/2/15.
 * @author Rick van Lieshout
 */
public abstract class PowerUp extends GameObject{
    /**
     * Number of hit points a powerup has.
     */
    int health;

    /**
     * A powerup is an item a player can collect to gain some kind of boost
     */
    public PowerUp() {
        health = 1;
    }

    /**
     * Every child class has to have an action.
     * This method forces an implementation
     */
    public abstract void doAction(Ally a);
}
