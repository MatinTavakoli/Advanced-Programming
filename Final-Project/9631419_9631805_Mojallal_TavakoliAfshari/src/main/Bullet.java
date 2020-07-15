package main;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet {

    private BufferedImage bulletImage;

    private int locX;
    private int locY;

    private double bulletRotation;

    private AffineTransform transform = new AffineTransform();

    private int speed;
    private double slope;

    /**
     * constructor for the bullet class
     * @param state game state
     * @param speed speed
     */
    public Bullet(GameState state, int speed){
        this.speed = speed;

        locX = state.getTankLocX() + 50;
        locY = state.getTankLocY() + 50;

        bulletRotation = Math.atan2(state.getMouseY() - state.getTankLocY() - 50 + yChange(state),
                state.getMouseX() - state.getTankLocX() - 50 + xChange(state));

        try {
            bulletImage = ImageIO.read(new File("src/pictures/bullet.png"));
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
     * calculates the direction of the bullet
     * @param state game state
     */
    public void bulletDirection(GameState state) {
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
        transform.rotate(bulletRotation);

        graphics2D.drawImage(bulletImage, transform, null);
    }

    /**
     * getter for bounds
     * @return bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(locX, locY, bulletImage.getWidth(), bulletImage.getHeight());
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
