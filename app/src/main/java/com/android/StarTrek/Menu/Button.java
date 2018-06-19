package com.android.StarTrek.Menu;

import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.MoveableGameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;
import android.util.Log;

import com.android.StarTrek.Board;
import com.android.StarTrek.Collision;
import com.android.StarTrek.Ships.StarShip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rick van Lieshout on 3/26/15.
 * @author Rick van Lieshout
 */

/**
 * The button class is used to display an object which functions as a button.
 */
public abstract class Button extends GameObject {

    /**
     * The health variable contains the amount of health the button has.
     * This will always be 1.
     */
    private int health;

    /**
     * The mygame variable holds a reference to the Board this button is currently on.
     */
    private Board mygame;

    /**
     * The button class draws an object on the screen which acts as a button
     * @param game reference to the board the button is on.
     */
    public Button(Board game){
        this.mygame = game;
        this.health = 1;
    }

    /**
     * Abstract method doAction, every button has to do something.
     */
    public abstract void doAction();

    /**
     * Getter to get the board this button is on.
     * @return Board this button is on.
     */
    public Board getMygame(){
        return mygame;
    }
}
