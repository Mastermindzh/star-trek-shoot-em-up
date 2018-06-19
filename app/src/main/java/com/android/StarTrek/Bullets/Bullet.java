package com.android.StarTrek.Bullets;

import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.MoveableGameObject;
import com.android.StarTrek.Board;
import com.android.StarTrek.Collision;
import com.android.StarTrek.Menu.Button;
import com.android.StarTrek.Powerups.PowerUp;
import com.android.StarTrek.Ships.Ally;
import com.android.StarTrek.Ships.Enemy;
import com.android.StarTrek.Ships.StarShip;

import java.util.ArrayList;

/**
 * Created by Rick van Lieshout on 3/24/15.
 * @author  Rick van Lieshout
 *
 *
 */
public class Bullet extends MoveableGameObject implements Collision {
    /**
     * The Board field holds a reference to the board this object is currently on
     */
    private Board       mygame;

    /**
     * The owner field holds a reference to the StarShip that shot the bullet
     */
    private StarShip    owner;

    /**
     * The attack fields holds a numeric value which represents the amount of damage the bullet
     * does on impact. It is important to keep this value in the bullet and not in the StarShip
     * so that any upgrades obtained after shooting the bullet will not be applied to bullets that
     * are already fired.
     */
    private int         attack;

    /**
     *  A bullet is shot by an enemy or an allied ship.
     *  The type (Enemy or Ally) of the owner variable will determine speed heading and colour of the bullet.
     *
     * @param mygame Keeps track of the game the bullet is currently on
     * @param owner  Keeps track of the ship that shot the bullet.
     * @param attack Amount of damage the bullet does when it hits it's target.
     */
    public Bullet(Board mygame,StarShip owner, int attack) {
        this.mygame = mygame;
        this.attack = attack;
        this.owner = owner;
        setBulletSprite();

    }

    /**
     * This method sets a sprite for the current object.
     * It will check wether the owner of the bullet is an ally
     * or an enemy and set the sprite accordingly
     */
    private void setBulletSprite(){
        if(isAlly()){
            setSprite("allied_phaser");
        }else{
            setSprite("enemy_phaser");
        }
    }

    /**
     * The move method checks wether the Bullet is an allied or Enemy bullet and moves the
     * bullet either up or down.
     *
     * It also checks wether the bullet is outside of the screen and if it is it will delete itself.
     */
    public void move(){
            if(getCenterY() < 0 - getFrameWidth()/2 || getCenterY() > getMygame().getMapHeight()){
                getMygame().deleteGameObject(this);
            }else{
                if(getOwner() instanceof Ally){
                    setDirectionSpeed(0, 30);
                }else if(getOwner() instanceof Enemy){
                    setDirectionSpeed(180, 10);
                }
                setFrameNumber(1);
            }
    }

    /**
     * The update method is called every game loop.
     * In this method we call the move method and the checkCollision method.
     */
    @Override
    public void update(){
        super.update();
        move();
        checkCollision();
    }

    /**
     * The checkCollision method checks wether the bullet has hit a (valid) target.
     * does it's thing and then destroys itself.
     */
    @Override
    public void checkCollision() {
        ArrayList<GameObject> collided = getCollidedObjects();
        if (collided != null) {
            for(GameObject g: collided){
                if(g instanceof StarShip){
                    if( ((isEnemy((StarShip) g)) && (isEnemy()))){
                        //do not hit ! friendly fire
                    }else{
                        ((StarShip) g).takeDamage(getAttack());
                        getMygame().deleteGameObject(this);
                    }
                }else if(g instanceof Button){
                    ((Button) g).doAction();
                    getMygame().deleteGameObject(this);
                }else if(g instanceof Bullet){ //check for collision with other bullets
                    if(getOwner() instanceof Ally){ // if owner of the bullet is an Ally
                        if(!(((Bullet) g).getOwner() instanceof Ally)){//if collided bullet is NOT from an allied ship
                            //if other bullet is not phaser barrage
                            if(! (g instanceof PhaserBarrage)){
                                //delete both bullets
                                getMygame().deleteGameObject(g);
                                getMygame().deleteGameObject(this);
                            }
                        }
                    }
                }else if(g instanceof PowerUp && getOwner() instanceof Ally){
                    ((PowerUp) g).doAction((Ally) getOwner());
                    getMygame().deleteGameObject(g);
                    getMygame().deleteGameObject(this);
                }
            }
        }
    }

    /**
     * Ask wether the starship is an Ally
     *
     * @param s give a reference to a StarShip object
     * @return true or false based on wether param s is an instance of Ally is
     */
    private boolean isAlly(StarShip s){
        return s instanceof Ally;
    }

    /**
     * Ask wether the starship is an Ally
     * @return true or false based on wether owner is an instance of Ally
     */
    public boolean isAlly(){
        return getOwner() instanceof Ally;
    }

    /**
     * Ask wether the starship is an enemy
     * @param s give a reference to a StarShip object
     * @return true or false based on wether param s is an instance of Enemy
     */
    public boolean isEnemy(StarShip s){
        return s instanceof Enemy;
    }

    /**
     * Ask wether the starship is an enemy
     * @return
     */
    public boolean isEnemy(){
        return getOwner() instanceof Enemy;
    }

    /**
     * This method will return the board the bullet is on
     * @return board the bullet is on
     */
    public Board getMygame(){
        return this.mygame;
    }

    /**
     * Getter for the attack value
     * @return objects attack value
     */
    public int getAttack() {
        return attack;
    }

    /**
     * This method returns the owner of the bullet
     * @return StarShip - ship that shot the bullet
     */
    public StarShip getOwner() {
        return owner;
    }
}
