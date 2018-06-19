package com.android.StarTrek.Ships.Bars;

import android.gameengine.icadroids.objects.GameObject;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.android.StarTrek.Board;
import com.android.StarTrek.Ships.StarShip;

/**
 * Created by Rick van Lieshout on 3/24/15.
 * @author Rick van Lieshout
 */
public class Bar extends GameObject{
    /**
     * The parent field holds a reference to the StarShip that it belongs too.
     */
    private StarShip parent;

    /**
     * The myGame variable holds a reference to the board this bar is on
     */
    private Board myGame;

    /**
     * This field hols the value that has to be drawn
     */
    private int value;

    /**
     * This variable holds the max HP of the unit it belongs to
     */
    private int initialHealth;
    /**
     * A bar represent the amount of health an enemy still has.
     *
     * @param parent the StarShip this bar belongs too
     * @param myGame The Board this bar is drawn on
     */
    public Bar(StarShip parent, Board myGame) {
        this.parent = parent;
        this.myGame = myGame;
        initialHealth = getParent().getHealth();
    }

    /**
     * This method draws both the text and the hp bar visuals.
     * @param canvas The Android canvas. Use this provided object to
     * draw for example text, rectangles and colors. For the api see:
     */
    @Override
    public void drawGameObject(Canvas canvas) {
        super.drawGameObject(canvas);
        this.value = (getParent().getHealth());

        //drawBars
        Paint hp = new Paint();
        hp.setColor(Color.RED);
        hp.setStrokeWidth(12);
        hp.setTextSize(60);
        float startX = getParent().getCenterX() - getParent().getFrameWidth()/2;
        float startY = getParent().getCenterY() + getParent().getFrameHeight()/2 + 15;
        float endX;
        if(getParent().getHealth() <= 0){
            endX = startX;
        }else{
           endX = startX + (getParent().getFrameWidth() / initialHealth) * getParent().getHealth();
        }

        float stopY  = getParent().getCenterY() + getParent().getFrameHeight()/2 + 15;

        canvas.drawLine(startX,startY,endX,stopY, hp);
        canvas.drawText(getValueAsString(),getParent().getCenterX(),getParent().getCenterY() + getParent().getFrameHeight()/2 + 75,hp );
    }


    /**
     * This method checks wether the object needs to be removed (when hp is below or at 0)
     */
    @Override
    public void update(){
        if(getValue() <= 0){
            myGame.deleteGameObject(this);
        }
    }

    /**
     * This method gets the value of the current bar
     * @return bar's value
     */
    private int getValue() {
        return value;
    }

    /**
     * Returns the value of this bar as a string
     * @return string version of the value from this bar.
     */
    public String getValueAsString(){
        return ""+value;
    }
    /**
     * Returns parent field
     * @return Returns parent field
     */
    public StarShip getParent() {
        return parent;
    }
}
