package com.android.StarTrek.Ships.Bosses;

import android.gameengine.icadroids.sound.GameSound;

import com.android.StarTrek.Board;
import com.android.StarTrek.Bullets.Bullet;
import com.android.StarTrek.Bullets.PhaserBarrage;
import com.android.StarTrek.Ships.Boss;

/**
 * Created by Rick van Lieshout on 3/23/15.
 * @author Rick van Lieshout
 */
public class Tac_sphere extends Boss {
    /**
     * The cube is a boss you will encounter.
     * it has a signle special attack
     * @param game The board this object is displayed on
     */
    public Tac_sphere(Board game) {
        super(game, 400, 20, 80,100);
        setSprite("tac_sphere");
        setShipSpeed(3);
        setSpecialChance(3);
    }
    /**
     * The special attack this boss has.
     * it fires a torpedo which follows the player untill it hits or is destroyed
     */
    @Override
    public void doSpecialAttack() {
     shootPhaserBarrage();
    }

    /**
     * This method will shoot a phaser barrage which can't be shot.
     *
     */
    private void shootPhaserBarrage(){
        GameSound.playSound(2,1);
        Bullet barrage = new PhaserBarrage(getMyGame(),this,getAttack()*2);
        float bullety = this.getCenterY() + barrage.getFrameHeight() + 20;
        getMyGame().addGameObject(barrage,Math.round(getCenterX()),Math.round(bullety));
    }
}
