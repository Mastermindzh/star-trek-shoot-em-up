package com.android.StarTrek.Menu.Buttons;

import android.util.Log;

import com.android.StarTrek.Board;
import com.android.StarTrek.Menu.Button;

/**
 * Created by Rick van Lieshout on 3/26/15.
 * @author Rick van Lieshout
 */

/**
 *
 */
public class exitButton extends Button {

    /**
     * The exit button class draws an object on the screen which acts as an exit button
     * @param game reference to the board the button is on.
     */
    public exitButton(Board game) {
        super(game);
        setSprite("exit");
    }

    /**
     * Whenever this method is called the app will exit completely.
     */
    @Override
    public void doAction() {
        System.exit(0);
    }
}
