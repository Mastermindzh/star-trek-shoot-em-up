package com.android.StarTrek.Powerups;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import android.util.Log;

import com.android.StarTrek.Board;
import com.android.StarTrek.Ships.Ally;
import com.android.StarTrek.Ships.StarShip;

/**
 * Created by Rick van Lieshout on 4/2/15.
 * @author  Rick van Lieshout
 */
public class Invincibility extends PowerUp implements IAlarm {
    /**
     * This variable keeps track of the lifeTime of the object.
     */
    private Alarm lifeTime;
    /**
     * The ship that owns this object.
     */
    private Ally owner;
    /**
     * The board this object is on
     */
    private Board myGame;

    /**
     * A powerup is an item a player can collect to gain some kind of boost
     * @param b board this object is on
     */
    public Invincibility(Board b) {
        setSprite("invincibility");
        myGame = b;
        lifeTime = new Alarm(5,400, this);
        lifeTime.startAlarm();
    }

    /**
     * The action this object performs
     * @param a the ship to which it performs it's action
     */
    @Override
    public void doAction(Ally a) {
            a.setCurrentPowerUp(this);
            owner = a;
        }

    /**
     * This method goes off when the alarm goes off
     * @param alarmID id of the alarm to go of.
     */
    @Override
    public void triggerAlarm(int alarmID) {
            myGame.deleteGameObject(this);
    }

}
