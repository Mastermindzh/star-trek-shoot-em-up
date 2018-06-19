package com.android.StarTrek.Ships;

import android.gameengine.icadroids.sound.MusicPlayer;

import com.android.StarTrek.Board;

import java.util.Random;

/**
 * Created by Rick van Lieshout on 3/23/15.
 * @author Rick van Lieshout
 */
public abstract class Boss extends Enemy {

    /**
     * The timeCounter is used to make a ship "hang" in the air for a short period of time.
     */
    int timeCounter;
    /**
     * The specialChance variable determines how often a boss shoots a special attack.
     */
    int specialChance;

    /**
     * The shipDirection variable keeps track of the direction the ship is headed.
     * A boss bounces from left to right.
     */
    int ShipDirection;

    /**
     * A boss is a special unit. it is usually much tougher than a regular enemy and always
     * has a special attack.
     *
     * @param game The board the boss is on
     * @param health The amount of health the boss has
     * @param attack The attack value of the bosses attacks
     * @param attackSpeed The speed with which the boss attacks
     * @param score
     */
    public Boss(Board game, int health, int attack, int attackSpeed, int score) {
        super(game, health, attack, attackSpeed, score);
        timeCounter =0;
        ShipDirection = 90;
        specialChance = 3;
        MusicPlayer.pauseAll();
        MusicPlayer.play("boss",true);
    }

    /**
     * The move method controls the way a boss moves.
     * This overrides the default enemy behaviour because a boss isn't supposed to move downwards.
     */
    @Override
    public void move(){
        timeCounter++;
        if (timeCounter % 20 == 0) {//makes the ship hang in the air for a while.
            //figure out if the ship has to turn
            if(this.getCenterX() + (this.getFrameWidth()/2) >= (getMyGame().getMapWidth()-getMyGame().getMargin())
                   || this.getCenterX() - (this.getFrameWidth()/2) <= getMyGame().getMargin()){
                turn(getShipDirection());
            }
            //let the ship move
            setDirectionSpeed(getShipDirection(), getShipSpeed());
            setFrameNumber(0);
        }
    }

    /**
     * This method turns the ship arround.
     * For example, if the ship is going to the right it will move to the left after calling this
     * method
     * @param currentDirection the currentDirection of the ship.
     */
    private void turn(int currentDirection){
        if(currentDirection == 90){
            setShipDirection(270);
        }else{
            setShipDirection(90);
        }
    }

    /**
     * The shoot methods determines wether the boss shoots a regular attack or a special attack.
     * It does so at random with a given chance.
     *
     * It has to override the regular shoot method since normal ships don't have a special attack.
     */
    @Override
    public void shoot(){
        Random r = new Random();
        if(r.nextInt(specialChance) == 0){
            doSpecialAttack();
        }else{
            super.shoot();
        }

    }
    /**
     * takeDamage overrides the starShip method because bosses have a shield
     * which has to be taken down first.
     * @param dmg, integer with the value of the dmg to be done
     */
    @Override
    public void takeDamage(int dmg){
        if(dmg>0){
            setHealth(getHealth()- dmg);
        }
    }

    public abstract void doSpecialAttack();

    /**
     * Returns timeCounter field
     * @return timeCounter field
     */
    public int getTimeCounter() {
        return timeCounter;
    }

    /**
     * This method gets the current ship direction.
     * @return ship's direction
     */
    public int getShipDirection() {
        return ShipDirection;
    }

    /**
     * This method sets a new ship direction
     * @param shipDirection new direction
     */
    public void setShipDirection(int shipDirection) {
        ShipDirection = shipDirection;
    }

    /**
     * This method sets the specialchance variable which determines how often a boss uses a
     * special attack.
     * @param specialChance integer
     */
    public void setSpecialChance(int specialChance) {
        this.specialChance = specialChance;
    }
}
