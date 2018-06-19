package com.android.StarTrek;

import android.gameengine.icadroids.dashboard.DashboardTextView;
import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.input.MotionSensor;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.input.TouchInput;
import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.sound.GameSound;
import android.gameengine.icadroids.sound.MusicPlayer;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;

import com.android.StarTrek.Bullets.Bullet;
import com.android.StarTrek.Menu.Button;
import com.android.StarTrek.Menu.Buttons.RaceButton;
import com.android.StarTrek.Menu.Buttons.exitButton;
import com.android.StarTrek.Menu.Buttons.highscoreButton;
import com.android.StarTrek.Menu.Buttons.startButton;
import com.android.StarTrek.Menu.Cursor;
import com.android.StarTrek.Ships.Allies.Enterprise;
import com.android.StarTrek.Ships.Allies.Klingon;
import com.android.StarTrek.Ships.Allies.Romulan;
import com.android.StarTrek.Ships.Allies.Species8472;
import com.android.StarTrek.Ships.Ally;
import com.android.StarTrek.Ships.Enemy;
import com.android.StarTrek.Ships.StarShip;

import java.util.Vector;

/**
 * Main class of the game 'Board'.
 * 
 * @author Rick van Lieshout , Wouter Schipperheijn
 *
 */
public class Board extends GameEngine {
	
	/**
	 * Dashboard for displaying the score
	 */
	private DashboardTextView stats;

    /**
     * left and right margin for any ship.
     */
    private int margin = 50;

    /**
     * The score a player has.
     */
    private int score;
    /**
     * The amount of killed opponents
     */
    private int killed;
    /**
     * The current player
     */
    private StarShip player;

    /**
     * The Controller that spawns new enemies
     */
    EnemyController ec;
	/**
	 * Initialize the game, create objects and level
	 * 
	 * @see android.gameengine.icadroids.engine.GameEngine#initialize()
	 */
	@Override
	protected void initialize() {
        score = 0;
        killed = 0;
        setButtons();       // configures buttons
        startMenu();
        GameSound.addSound(0, "phaser");
        GameSound.addSound(1, "torpedo");
        GameSound.addSound(2, "barrage");
	}

    /**
     * Clear the entire board.
     */
    public void clear(){
        Vector<GameObject> objects = this.items;
        for(GameObject g: objects){
            this.deleteGameObject(g);
            if(g instanceof Enemy){
                ((Enemy) g).getEsc().getTicker().pauseAlarm();
            }
        }
    }

    /**
     * Clear bullets and buttons (menu transition)
     */
    public void clearButtons(){
        Vector<GameObject> objects = this.items;
        for(GameObject g: objects){
            if(g instanceof Button || g instanceof Bullet){
                this.deleteGameObject(g);
            }
        }
    }

    /**
     * Start the highscore screen
     */
    public void startHighscore(){
        Log.d("Board", "StartHighscore");
    }
    /**
     * Start the Game over screen
     */
    public void startGameOver(){
        clear();
        ec.getTicker().pauseAlarm();
        this.stats.setWidgetX(this.getMapWidth() / 2 - stats.getWidth()/2);
        this.stats.setWidgetY(this.getMapHeight() / 2 - stats.getHeight());
    }
    /**
     * Start the starShip selection screen
     */
    public void startShipSelection(){
        clearButtons();
        setBackground("pickyourship");
        Button[] raceButtons = new Button[4];
        raceButtons[0] = new RaceButton(this, new Species8472(this),"race_species");
        raceButtons[1] = new RaceButton(this, new Enterprise(this),"race_federation");
        raceButtons[2] = new RaceButton(this, new Klingon(this),"race_klingon");
        raceButtons[3] = new RaceButton(this, new Romulan(this),"race_romulan");
        int buttonMargin = 20;
        for(int i = 0; i<raceButtons.length; i++){
            this.addGameObject(raceButtons[i],i*(buttonMargin+raceButtons[i].getFrameWidth()),10);
        }
    }

    /**
     * Start the main menu
     */
    public void startMenu(){
        MusicPlayer.play("menu",true);
        clear(); // clear the field so we can add new things
        StarShip cursor = new Cursor(this);
        addGameObject(cursor,cursor.getFrameWidth() + 10,getMapHeight() - (2*cursor.getFrameHeight()));
        setBackground("menu_background_blurred");
        int buttonYMargin = 20;
        Button start = new startButton(this);
        Button highscore = new highscoreButton(this);
        Button exit = new exitButton(this);
        //add buttons
        addGameObject(start, buttonYMargin,buttonYMargin);
        addGameObject(highscore, this.getMapWidth()/2 - highscore.getFrameWidth()/2,buttonYMargin);
        addGameObject(exit, this.getMapWidth() - exit.getFrameWidth()-buttonYMargin,buttonYMargin);
    }

    /**
     * This method starts the game with a given player
     * @param player player with wich to start the game
     */
    public void startGame(StarShip player){
        MusicPlayer.pauseAll();
        MusicPlayer.play("background",true);
        clear();
        this.player = player;
        setBackground("background");
        createDashboard();  // create a dashboard
        ec = new EnemyController(this);
        ec.triggerAlarm(1); //make sure the game starts with an enemy;
        addGameObject(this.player,getMapWidth()/2 - player.getFrameWidth() /2, getMapHeight() - (this.player.getFrameHeight()) + 20);
        //cheat line for assessment
       // addGameObject(new Cube(this),this.getMapWidth()/2,10);
    }

    /**
     * This method enables the on screen buttons
     */
    private void setButtons(){
        TouchInput.use = false;
        MotionSensor.use = false;
        OnScreenButtons.use = true;
    }

    /**
     * This method creates the little dashboard that holds a score.
     */
	private void createDashboard(){
        // Example of how to add a Dashboard to a game
        stats = new DashboardTextView(this);
        stats.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        stats.setTextColor(Color.YELLOW);
        addToDashboard(stats);
		this.stats.setWidgetHeight(350);
		this.stats.setWidgetX(5);
		this.stats.setWidgetY(5);

		// If you want to modify the layout of a dashboard widget,
		// you need to so so using its run method.
		this.stats.run(new Runnable() {
            public void run() {
                stats.setPadding(10, 10, 10, 10);
            }
        });
	}

    /**
     * This method adds a kill and increases the score
     * @param score amount the score has to increase
     */
    public void addKill(int score){
        this.killed++;
        addScore(score);
    }

    /**
     * This method changes the score.
     * @param score a positive or negative value that changes the score
     */
    public void addScore(int score){
        this.score+=score;
    }
	/**
	 * Update the game.
	 * 
	 * @see android.gameengine.icadroids.engine.GameEngine#update()
	 */
	@Override
	public void update() {
		super.update();

		if(! (this.stats == null)){
            String txt = "no";
            if(( (Ally) getCurrentPlayer()).getCurrentPowerUp() == null){
                 txt = "no";
            }else{
                 txt = "yes";
            }
            this.stats.setTextString(
                    "Score: " + String.valueOf(this.score) + "\n"
                            + "Killed: " + this.killed + "\n"
                            + "Health: " + getCurrentPlayer().getHealth() + "\n"
                            + "Attack: " + getCurrentPlayer().getAttack() +"\n"
                            + "Invincible: " + txt

            );
        }
	}

    /**
     * This method returns the current player.
     * @return StarShip currentPlayer
     */
    public StarShip getCurrentPlayer(){
        return this.player;
    }

    /**
     * This method returns the margin every ship / object has.
     * @return int , margin for ships
     */
    public int getMargin() {
        return margin;
    }
}