package com.android.StarTrek.Ships.Bosses;

import android.gameengine.icadroids.sound.GameSound;
import android.gameengine.icadroids.sound.MusicPlayer;

import com.android.StarTrek.Board;
import com.android.StarTrek.Bullets.Bullet;
import com.android.StarTrek.Bullets.PhaserBarrage;
import com.android.StarTrek.Bullets.Torpedo;
import com.android.StarTrek.Ships.Boss;

import java.util.Random;

/**
 * Created by Rick van Lieshout on 3/23/15.
 * @author Rick van Lieshout
 */
public class Cube extends Boss {

    /**
     * The cube is the third boss you will encounter.
     * It is a ultimate boss, it can use both special attacks.
     * @param game The board this object is displayed on
     */
    public Cube(Board game) {
        super(game, 500, 25, 85,150);
        setSprite("cube");
        setShipSpeed(4);
        MusicPlayer.play("borg", false);
    }

    /**
     * This method will do some form of special attack, each boss must override it.
     */
    @Override
    public void doSpecialAttack() {
        Random r = new Random();
        if(r.nextInt(2) == 0){
            shootPhaserBarrage();
        }else{
            fireTorpedo();
        }
        super.shoot();
    }
    /**
    * The special attack this boss has.
    * it fires a torpedo which follows the player untill it hits or is destroyed
    */
    private void fireTorpedo(){
        GameSound.playSound(1, 1);
        Bullet torpedo = new Torpedo(getMyGame(),this,getAttack()*2,getMyGame().getCurrentPlayer());
        float bullety = this.getCenterY() + torpedo.getFrameHeight() + 20;
        getMyGame().addGameObject(torpedo,Math.round(getCenterX()),Math.round(bullety));
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
