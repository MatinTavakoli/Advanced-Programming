package enemy;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyMissile {

    private BufferedImage enemyMissileImage;

    private GameFrame game;
    private EnemyTank enemyTank;

    private int locX;
    private int locY;

    private double missileRotation;
    private AffineTransform transform = new AffineTransform();

    private int speed;
    private double slope;

    /**
     * constructor of the EnemyMissile class
     * @param game game frame
     * @param state game state
     * @param speed speed
     * @param enemyTank enemyTank
     */
    public EnemyMissile(GameFrame game, GameState state, int speed, EnemyTank enemyTank) {
        this.game = game;
        this.speed = speed;
        this.enemyTank = enemyTank;

        locX = enemyTank.getLocX() + 50;
        locY = enemyTank.getLocY() + 50;

        missileRotation = Math.atan2(state.getTankLocY() - enemyTank.getLocY() - 50,
                state.getTankLocX() - enemyTank.getLocX() - 50);

        try {
            enemyMissileImage = ImageIO.read(new File("src/pictures/missile.png"));
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
     * calculates and sets the location of missile
     * @param state game state
     */
    public void missileDirection(GameState state) {
        int deltaY = locY - state.getTankLocY() + 50;
        int deltaX = state.getTankLocX() - locX + 50;

        slope = ((float)deltaY / deltaX);

        if(deltaX < 0) {
            speed *= -1;
        }
    }

    /**
     * calculates and sets the location of missile
     * @param state game state
     */
    public void calculate(GameState state) {
        setLocX((int) (locX + speed / (Math.sqrt(1 + (slope * slope)))));
        setLocY((int) (locY - speed * slope / (Math.sqrt(1 + (slope * slope)))));
    }

    /**
     * paints the enemy homing missile
     * @param graphics2D graphics 2D
     * @param state game state
     */
    public void paint(Graphics2D graphics2D, GameState state) {

        calculate(state);
        transform.setToTranslation(locX - xChange(state), locY - yChange(state));
        transform.rotate(missileRotation);

        graphics2D.drawImage(enemyMissileImage, transform, null);
    }

    /**
     * getter for bounds
     * @param state game state
     * @return bounds
     */
    public Rectangle getBounds(GameState state) {
        return new Rectangle(locX, locY, enemyMissileImage.getWidth(), enemyMissileImage.getHeight());
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
