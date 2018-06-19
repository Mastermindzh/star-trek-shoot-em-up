package com.android.StarTrek.Ships;

import android.gameengine.icadroids.sound.MusicPlayer;
import android.util.Log;

import com.android.StarTrek.Board;
import com.android.StarTrek.Powerups.Invincibility;
import com.android.StarTrek.Powerups.PowerUp;
import com.android.StarTrek.Powerups.attackDown;
import com.android.StarTrek.Powerups.attackUp;
import com.android.StarTrek.Powerups.healthUp;
import com.android.StarTrek.Ships.Bars.Bar;
import com.android.StarTrek.ShootController;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Rick van Lieshout on 3/23/15.
 * @author Rick van Lieshout
 */
public class Enemy extends StarShip {
    /**
     * The score field holds the value of an enemy.
     */
    private int score;

    /**
     * The timeCounter is used to make a ship "hang" in the air for a short period of time.
     */
    private int timeCounter;

    /**
     * An alarm which resets the canshoot variable.
     * Without the alarm a ship can fire only once.
     */
    private ShootController esc;

    /**
     * An enemy is a ship the player can NOT control.
     * It will do it's own thing and try and kill you.
     * @param game the board the ship is on
     * @param health the amount of health a ship has
     * @param attack the amount of attack a ship has
     * @param attackSpeed the speed with which the ship attacks
     * @param score The amount of points the player gets when he/she destroys this ship
     */
    public Enemy(Board game, int health, int attack, int attackSpeed, int score) {
        super(game, health, attack, attackSpeed);
        this.timeCounter = 0;
        esc = new ShootController(this);
        this.score = score;
        drawHP();
    }

    /**
     * Method is called every single gameloop.
     * Makes sure the enemy moves.
     */
    @Override
    public void update() {
        super.update();
        move();
    }

    /**
     * This method adds an hp bar for the enemy to the board.
     */
    private void drawHP(){
            getMyGame().addGameObject(new Bar(this,getMyGame()));
    }

    /**
     * This method gets called when an enemy dies.
     * it will add a kill and score to the player's score.
     * It will shut off music (if it has any)
     */
    public void die(){
        esc.getTicker().pauseAlarm();
        getMyGame().addKill(this.score);
        getMyGame().deleteGameObject(this);
        if(this instanceof Boss){
            MusicPlayer.pauseAll();
            MusicPlayer.play("background",true);
        }
        dropLoot();
    }

    /**
     * This method drops a powerup
     */
    public void dropLoot(){
        Random r = new Random();
        if(r.nextInt(6) == 0 ){
            ArrayList<PowerUp> powerups = new ArrayList();
            powerups.add(new Invincibility(getMyGame()));
            powerups.add(new healthUp(getMyGame()));
            powerups.add(new healthUp(getMyGame()));
            powerups.add(new healthUp(getMyGame()));
            powerups.add(new healthUp(getMyGame()));
            powerups.add(new attackDown(getMyGame()));
            powerups.add(new attackDown(getMyGame()));
            powerups.add(new attackUp(getMyGame()));
            powerups.add(new attackUp(getMyGame()));
            powerups.add(new attackUp(getMyGame()));
            powerups.add(new attackUp(getMyGame()));
            PowerUp p = powerups.get(r.nextInt(powerups.size()));
            getMyGame().addGameObject(p,Math.round(getCenterX()),Math.round(getCenterY()));
        }
    }
    /**
     * The move method controls the way a enemy moves.
     */
    public void move(){
        timeCounter++;
        if (timeCounter % 20 == 0) {//makes the ship hang in the air for a while.
            setDirectionSpeed(180, 2);//move down slowly
            setFrameNumber(1);

        }
        if(this.getCenterY() - this.getFrameHeight()/2 >= 1920){
            //if enemy is off screen
            getMyGame().addScore(-50);
            getMyGame().deleteGameObject(this);
        }
    }

    /**
     * This method returns the shootcontroller so it can be pause
     * @return alarm, shootcontroller
     */
    public ShootController getEsc() {
        return esc;
    }
}
