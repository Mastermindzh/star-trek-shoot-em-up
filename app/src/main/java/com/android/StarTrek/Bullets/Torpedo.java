package com.android.StarTrek.Bullets;

import com.android.StarTrek.Board;
import com.android.StarTrek.Ships.StarShip;

/**
 * Created by mastermindzh on 3/30/15.
 */
public class Torpedo extends Bullet {

    private StarShip target;

    /**
     * A bullet is shot by an enemy or an allied ship.
     * The type (Enemy or Ally) of the owner variable will determine speed heading and colour of the bullet.
     *
     * @param mygame Keeps track of the game the bullet is currently on
     * @param owner  Keeps track of the ship that shot the bullet.
     * @param attack Amount of damage the bullet does when it hits it's target.
     */
    public Torpedo(Board mygame, StarShip owner, int attack, StarShip target) {
        super(mygame, owner, attack);
        this.target = target;
        this.setSprite("torpedo");
    }

    /**
     * This method makes the torpedo move.
     * It will move towards the current players position
     */
    @Override
    public void move(){
        setDirectionSpeed(180, 7);
        this.moveTowardsAPoint(target.getCenterX(), target.getCenterY());
    }

}
