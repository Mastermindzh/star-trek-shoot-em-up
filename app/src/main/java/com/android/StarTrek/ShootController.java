package com.android.StarTrek;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;

import com.android.StarTrek.Ships.Ally;
import com.android.StarTrek.Ships.StarShip;

/**
 * Created by Rick van Lieshout on 3/27/15.
 * @author Rick van Lieshout
 */
public class ShootController implements IAlarm {
    /**
     * The owner fields holds a reference to the starShip it belongs too
     */
    private StarShip owner;
    /**
     * Ticker is an alarm that we can start and stop.
     */
    private Alarm ticker;

    /**
     * the ShootController class controls how many times a starShip can shoot.
     * @param owner The starShip that this controller belongs to.
     */
    public ShootController(StarShip owner) {
        this.owner = owner;
        int speed = (100 - owner.getAttackSpeed()) * 3;
        ticker = new Alarm(2,speed,this);
        ticker.startAlarm();
    }

    /**
     * This method gets called when the alarm goes off.
     * @param alarmID the id of the alarm that got triggered
     */
    @Override
    public void triggerAlarm(int alarmID) {
            if( owner instanceof Ally){
                ((Ally) owner).setCanShoot(true);
            }else{
                owner.shoot();
            }
            ticker.restartAlarm();
    }

    /**
     * Returns the alarm
     * @return Alarm, ticker in this object.
     */
    public Alarm getTicker(){
        return this.ticker;
    }

}
