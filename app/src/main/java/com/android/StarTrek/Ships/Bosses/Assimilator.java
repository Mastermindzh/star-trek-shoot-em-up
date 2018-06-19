package com.android.StarTrek.Ships.Bosses;

import android.gameengine.icadroids.sound.GameSound;
import android.gameengine.icadroids.sound.MusicPlayer;
import android.util.Log;

import com.android.StarTrek.Board;
import com.android.StarTrek.Bullets.Bullet;
import com.android.StarTrek.Bullets.Torpedo;
import com.android.StarTrek.Ships.Ally;
import com.android.StarTrek.Ships.Boss;
import com.android.StarTrek.Ships.Enemy;

/**
 * Created by Rick van Lieshout on 3/24/15.
 * @author Rick van Lieshout
 */
public class Assimilator extends Boss {
    /**
     * Assimilator is the first boss you will encounter
     * @param game the board the boss spawns on
     */
    public Assimilator(Board game) {
        super(game, 200, 15, 75,50);
        setSprite("assimilator");
        setShipSpeed(2);
        setSpecialChance(2);

    }

    /**
     * This method will do some form of special attack, each boss must override it.
     */
    @Override
    public void doSpecialAttack() {
        fireTorpedo();
    }

    /**
     * The special attack this boss has.
     * it fires a torpedo which follows the player untill it hits or is destroyed
     */
    private void fireTorpedo(){
        GameSound.playSound(1,1);
        Bullet torpedo = new Torpedo(getMyGame(),this,getAttack()*2,getMyGame().getCurrentPlayer());
        float bullety = this.getCenterY() + torpedo.getFrameHeight() + 20;
        getMyGame().addGameObject(torpedo,Math.round(getCenterX()),Math.round(bullety));
    }
}
