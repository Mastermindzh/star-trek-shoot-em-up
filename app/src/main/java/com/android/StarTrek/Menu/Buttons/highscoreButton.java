package com.android.StarTrek.Menu.Buttons;

import android.util.Log;

import com.android.StarTrek.Board;
import com.android.StarTrek.Menu.Button;

/**
 * Created by Rick van Lieshout on 3/26/15.
 * @author Rick van Lieshout
 */
public class highscoreButton extends Button{

    /**
     * The button class draws an object on the screen which acts as a button
     * @param game reference to the board the button is on.
     */
    public highscoreButton(Board game) {
        super(game);
        setSprite("highscore");
    }


    /**
     * this methods loads the highscore menu
     */
    @Override
    public void doAction() {
        getMygame().startHighscore();
    }

}
