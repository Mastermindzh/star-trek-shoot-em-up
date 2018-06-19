package com.android.StarTrek.Ships;

import android.gameengine.icadroids.input.MotionSensor;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.sound.GameSound;

import com.android.StarTrek.Board;
import com.android.StarTrek.Powerups.PowerUp;
import com.android.StarTrek.Powerups.Invincibility;
import com.android.StarTrek.ShootController;

/**
 * Created by Rick van Lieshout on 3/23/15.
 * @author Rick van Lieshout
 */
public abstract class Ally extends StarShip{
    /**
     * The canShoot field determines wether a ship can shoot or not.
     */
    private boolean canShoot;

    /**
     * An alarm which resets the canshoot variable.
     * Without the alarm a ship can fire only once.
     */
    private ShootController sc;

    /**
     *
     */
    private PowerUp currentPowerUp;
    /**
     * An Ally is a ship the player can control.
     * It can shoot and it can move around the field.
     *
     * @param game the board the ship is on
     * @param health the amount of health a ship has
     * @param attack the amount of attack a ship has
     * @param attackSpeed the speed with which the ship attacks
     */
    public Ally(Board game, int health, int attack, int attackSpeed) {
        super(game, health, attack, attackSpeed);
        sc = new ShootController(this);
        sc.triggerAlarm(2);

    }
    @Override
    public void takeDamage(int dmg){
        if(dmg>0){
            if(!(currentPowerUp instanceof Invincibility)){
                setHealth(getHealth()- dmg);
            }
        }
    }

    /**
     * This method occurs every game loop. It handles keyPresses and check wether the ship is offscreen
     */
    @Override
    public void update(){
        super.update();
        handleKeyPress();
        checkOffScreen();
    }

    /**
     * This method checks wether the ship is off the screen and resets it's position.
     * This will eliminate the drifting beyond the screens edge.
     */
    private void checkOffScreen(){
        if(this.getX() <= 0 + getMyGame().getMargin()){
            this.setX(getMyGame().getMargin());
        }else if(this.getX()+this.getFrameWidth() >= getMyGame().getMapWidth() - getMyGame().getMargin()){
            this.setX(getMyGame().getMapWidth()-getMyGame().getMargin()-this.getFrameWidth());
        }
    }

    /**
     * This method handles buttonpresses from the onscreen buttons.
     *
     */
    private void handleKeyPress(){
        // Handle input. Both on screen buttons and tilting are supported.
        // Buttons take precedence.
        boolean buttonPressed = false;
        if (OnScreenButtons.dPadUp || OnScreenButtons.dPadDown
                || OnScreenButtons.dPadLeft || OnScreenButtons.dPadRight)
        {
            buttonPressed = true;
        }

        if (OnScreenButtons.dPadRight
                || (MotionSensor.tiltRight && !buttonPressed))
        {
            if(this.getCenterX() + (this.getFrameWidth()/2) >= (getMyGame().getMapWidth()-getMyGame().getMargin())){
                setDirectionSpeed(90, 0);//set speed 0 so it stops moving
                setFrameNumber(0);
            }else{
                setDirectionSpeed(90, getShipSpeed());
                setFrameNumber(0);
            }
        }
        if (OnScreenButtons.dPadLeft
                || (MotionSensor.tiltLeft && !buttonPressed))
        {
            if(this.getCenterX() - (this.getFrameWidth()/2) <= getMyGame().getMargin()){
                setDirectionSpeed(270, 0);//set speed 0 so it stops moving
                setFrameNumber(0);
            }else{
                setDirectionSpeed(270, getShipSpeed());
                setFrameNumber(0);
            }
        }

        if(OnScreenButtons.buttonA){
            if(canShoot){
                shoot();

                GameSound.playSound(0,1);
            }
        }
    }

    /**
     * Ally overrides die because if it dies, the game is over.
     */
    @Override
    public void die(){
        getMyGame().startGameOver();
    }
    /**
     * This method sets the variable "canShoot"
     * @param b true or false.
     */
    public void setCanShoot(boolean b){
        canShoot = b;
    }

    public PowerUp getCurrentPowerUp() {
        return currentPowerUp;
    }

    public void setCurrentPowerUp(PowerUp currentPowerUp) {
        this.currentPowerUp = currentPowerUp;

    }
}


