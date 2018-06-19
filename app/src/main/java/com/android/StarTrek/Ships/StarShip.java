package com.android.StarTrek.Ships;

import android.gameengine.icadroids.objects.MoveableGameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;
import android.util.Log;

import com.android.StarTrek.Board;
import com.android.StarTrek.Bullets.Bullet;
import com.android.StarTrek.Ships.Bars.Bar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rick van Lieshout on 3/22/15.
 * @author Rick van Lieshout
 */
public abstract class StarShip extends MoveableGameObject {


    /**
     * this field holds a reference to the board this ship is on.
     */
    private Board mygame;
    /**
     * This field holds the current health of this object
     */
    private int health;
    /**
     * This field holds the current attack power this object has
     */
    private int attack;
    /**
     * This field holds the current speed this object has
     */
    private int shipSpeed;
    /**
     * This field holds the current attack speed this object has
     */
    private int attackSpeed;

    /**
     *
     * The constructor of a basic starship.
     * Each and every starship has these properties and must set them when created.
     *
     * @param game
     * @param health
     * @param attack
     * @param attackSpeed
     */
    public StarShip(Board game, int health, int attack, int attackSpeed){
        this.mygame = game;
        this.health = health;
        this.attack = attack;
        this.attackSpeed = attackSpeed;
    }

    /**
     * The takeDamage method decreases the health of a StarShip by a given amount (param dmg)
     * @param dmg
     */
    public void takeDamage(int dmg){
        if(dmg>0){
            setHealth(getHealth()- dmg);
        }
    }

    /**
     * In the update method we can write code which will be executed every game loop.
     * We could for example check wether the StarShip has enough health to exist.
     */
    @Override
    public void update()
    {
        super.update();
        if(getHealth() <= 0){
            die();
        }
    }

    /**
     * This method gets called when a starShip has no more health
     */
    public void die(){
        getMyGame().deleteGameObject(this);
    }
    /**
     * Every starship must implement some kind of shoot method.
     *
     */
    public void shoot(){
        Bullet bullet = new Bullet(getMyGame(),this,getAttack());
        float bullety;
        if(this instanceof Ally){
            bullety = this.getCenterY() - bullet.getFrameHeight() - getFrameHeight()/2;

        }else if(this instanceof Enemy){
            bullety = this.getCenterY() + bullet.getFrameHeight() + getFrameHeight()/2;
        }else{
            bullety = 0;
        }
        getMyGame().addGameObject(bullet,Math.round(getCenterX()),Math.round(bullety));
        if(this instanceof Ally){
            ((Ally) this).setCanShoot(false);
        }
    };

    /**
     * This getter returns the board this object is on.
     * @return Board mygame
     */
    public Board getMyGame(){
        return this.mygame;
    }

    /**
     * This getter returns the health of this object
     * @return integer value of the health this object currently has
     */
    public int getHealth() {
        return health;
    }
    /**
     * This getter returns the attack of this object
     * @return integer value of the attack this object currently has
     */
    public int getAttack() {
        return attack;
    }
    /**
     * This getter returns the attack speed of this object
     * @return integer value of the attack speed this object currently has
     */
    public int getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * This setter sets a new health for the current object.
     * @param health the new health to be set.
     */
    public void setHealth(int health) {
        this.health = health;
    }
    /**
     * This setter sets a new attack for the current object.
     * @param attack the new attack to be set.
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }
    /**
     * This setter sets a new attack speed for the current object.
     * @param attackSpeed the new attack speed to be set.
     */
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * This getter return the current objects move speed
     * @return integer value shipSpeed
     */
    public int getShipSpeed() {
        return shipSpeed;
    }
    /**
     * This setter sets a new speed for the current object.
     * @param shipSpeed the new speed to be set.
     */
    public void setShipSpeed(int shipSpeed) {
        this.shipSpeed = shipSpeed;
    }
}
