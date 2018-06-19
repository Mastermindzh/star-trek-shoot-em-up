package com.android.StarTrek;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import android.util.Log;

import com.android.StarTrek.Ships.Bosses.Assimilator;
import com.android.StarTrek.Ships.Bosses.Cube;
import com.android.StarTrek.Ships.Bosses.Tac_sphere;
import com.android.StarTrek.Ships.Enemies.Cylinder;
import com.android.StarTrek.Ships.Enemies.Interceptor;
import com.android.StarTrek.Ships.StarShip;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Rick van Lieshout on 3/27/15.
 * @author Rick van Lieshout
 */
public class EnemyController implements IAlarm {
    /**
     * The myGame fields holds a reference to the board it needs to spawn the enemies on.
     */
    private Board myGame;
    private Alarm ticker;
    /**
     * This field holds the number of enemies that have spawned in.
     * Will be used later to calculate when a boss needs to be summoned.
     */
    private int   nrSpawned;

    public EnemyController(Board myGame) {
        this.myGame = myGame;
        ticker = new Alarm(1,62,this);
        ticker.startAlarm();
        nrSpawned = 0;
    }

    /**
     * This method gets called when the alarm goes off
     * @param alarmID id of the alarm that goes off
     */
    @Override
    public void triggerAlarm(int alarmID) {
        ArrayList<String> enemies = new ArrayList();
        Log.d("Spawner",""+ getNrSpawned() % 50);
        if(getNrSpawned() != 0){
            if(getNrSpawned() % 50 == 0){
                enemies.add("assimilator");
            }
            if (getNrSpawned() % 75 == 0){
                enemies.add("sphere");
            }
            if (getNrSpawned() % 110 == 0){
                enemies.add("cube");
            }
        }
        //if no bosses have been added add both of the regular units.
        if(enemies.size() == 0){
            enemies.add("cylinder");
            enemies.add("interceptor");
        }

        Random r = new Random();
        StarShip s;

        switch(enemies.get(r.nextInt(enemies.size()))){
            case "cylinder":
                s = new Cylinder(getMyGame());
                break;

            case "interceptor":
                s = new Interceptor(getMyGame());
                break;

            case "assimilator":
                s = new Assimilator(getMyGame());
                break;

            case "sphere":
                s = new Tac_sphere(getMyGame());
                break;

            case "cube":
                s = new Cube(getMyGame());
                break;
            default:
                s = new Cylinder(getMyGame());
                break;
        }
        //calculate starting positions (x and y)
        int y = 10;
        int x = r.nextInt((getMyGame().getMapWidth() - getMyGame().getMargin() - s.getFrameWidth() - getMyGame().getMargin()) + 1) + getMyGame().getMargin();

        getMyGame().addGameObject(s,x,y);
        nrSpawned++;
        ticker.restartAlarm();//restart the alarm

    }

    /**
     * Get the number of spawned enemies
     * @return Number of spawned enemies
     */
    public int getNrSpawned() {
        return nrSpawned;
    }

    /**
     * Get the game where this controller works on
     * @return Board , game where this controller works on.
     */
    public Board getMyGame() {
        return myGame;
    }

    /**
     * Returns the alarm so other classes can pause it
     * @return Alarm object.
     */
    public Alarm getTicker(){
        return this.ticker;
    }
}
