package com.android.StarTrek.Bullets;

import com.android.StarTrek.Board;
import com.android.StarTrek.Ships.Ally;
import com.android.StarTrek.Ships.Enemy;
import com.android.StarTrek.Ships.StarShip;

/**
 * Created by Rick van Lieshout on 3/30/15.
 * @author  Rick van Lieshout
 */
public class PhaserBarrage extends Bullet{
    /**
     * A bullet is shot by an enemy or an allied ship.
     * The type (Enemy or Ally) of the owner variable will determine speed heading and colour of the bullet.
     *
     * @param mygame Keeps track of the game the bullet is currently on
     * @param owner  Keeps track of the ship that shot the bullet.
     * @param attack Amount of damage the bullet does when it hits it's target.
     */
    public PhaserBarrage(Board mygame, StarShip owner, int attack) {
        super(mygame, owner, attack);
        this.setSprite("barrage");
    }

    /**
     * The move method let's the bullet move downwards.
     * The phaser barrage moves at a slower speed compared to a regular bullet
     */
    @Override
    public void move(){
        if(getCenterY() < 0 - getFrameWidth()/2 || getCenterY() > getMygame().getMapHeight()){
            getMygame().deleteGameObject(this);
        }else{
            setDirectionSpeed(180, 5);
            setFrameNumber(1);
        }
    }
}
