package main;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Missile {

    private BufferedImage missileImage;

    private int locX;
    private int locY;

    private double missileRotation;

    private AffineTransform transform = new AffineTransform();

    private int speed;
    private double slope;

    /**
     * constructor for the missile class
     * @param state game state
     * @param speed speed
     */
    public Missile(GameState state, int speed) {
        this.speed = speed;

        locX = state.getTankLocX() + 50;
        locY = state.getTankLocY() + 50;

        missileRotation = Math.atan2(state.getMouseY() - state.getTankLocY() - 50 + yChange(state),
                state.getMouseX() - state.getTankLocX() - 50 + xChange(state));

        try {
            missileImage = ImageIO.read(new File("src/pictures/missile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * tank distance from starting point
     * @param state game state
     * @return yChange
     */
    public int yChange(GameState state){
        return state.getTankLocY() - GameFrame.tankYStart;
    }

    /**
     * tank distance from starting point
     * @param state game state
     * @return xChange
     */
    public int xChange(GameState state){
        return  state.getTankLocX() - GameFrame.tankXStart;
    }

    /**
     * calculates the direction of the missile
     * @param state game state
     */
    public void missileDirection(GameState state) {
        int deltaY = locY - state.getMouseY() - yChange(state);
        int deltaX = state.getMouseX() - locX + xChange(state);

        slope = ((float)deltaY / deltaX);

        if(deltaX < 0) {
            speed *= -1;
        }
    }

    /**
     * calculates and sets the location of the missile
     */
    public void calculate() {
        setLocX((int) (locX + speed / (Math.sqrt(1 + (slope * slope)))));
        setLocY((int) (locY - speed * slope / (Math.sqrt(1 + (slope * slope)))));
    }

    /**
     * paints the missile
     * @param graphics2D graphics2D
     * @param state game state
     */
    public void paint(Graphics2D graphics2D, GameState state) {

        calculate();
        transform.setToTranslation(locX - xChange(state), locY - yChange(state));
        transform.rotate(missileRotation);

        graphics2D.drawImage(missileImage, transform, null);
    }

    /**
     * getter for bounds
     * @return bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(locX, locY, missileImage.getWidth(), missileImage.getHeight());
    }

    public int getLocX() {
        return locX;
    }
    public void setLocX(int locX) {
        this.locX = locX;
    }
    public int getLocY() {
        return locY;
    }
    public void setLocY(int locY) {
        this.locY = locY;
    }
}
