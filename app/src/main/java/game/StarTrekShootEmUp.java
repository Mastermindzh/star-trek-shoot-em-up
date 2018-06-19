package game;

import com.android.StarTrek.Board;

import android.content.Intent;
import android.gameengine.icadroids.engine.GameEngine;


public class StarTrekShootEmUp extends GameEngine {
	
@Override
protected void initialize() {
    // start game with the board class.
	Intent intent = new Intent(this, Board.class);
	startActivity(intent);
}

@Override
	public void update() {
		super.update();
	}

}
