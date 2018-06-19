package com.android.StarTrek.Ships.Enemies;

import com.android.StarTrek.Board;
import com.android.StarTrek.Ships.Enemy;

/**
 * Created by Rick van Lieshout on 3/23/15.
 * @author Rick van Lieshout
 */
public class Interceptor extends Enemy {

    /**
     * Interceptor is another simple enemy,
     * @param game the board the interceptor lives on.
     */
    public Interceptor(Board game) {
        super(game, 30, 15, 65,12);
        setSprite("interceptor");
    }
}
