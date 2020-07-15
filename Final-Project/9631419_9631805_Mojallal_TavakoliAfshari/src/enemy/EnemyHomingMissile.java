package enemy;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyHomingMissile {

    private BufferedImage enemyHomingMissileImage;

    private GameFrame game;
    private EnemyTurret enemyTurret;

    private int locX;
    private int locY;

    private double missileRotation;
    private AffineTransform transform = new AffineTransform();

    private int speed;
    private double slope;

    /**
     * constructor of the EnemyHomingMissile class
     * @param game game frame
     * @param state game state
     * @param speed speed
     * @param enemyTurret enemyTurret
     */
    public EnemyHomingMissile(GameFrame game, GameState state, int speed, EnemyTurret enemyTurret) {
        this.game = game;
        this.speed = speed;
        this.enemyTurret = enemyTurret;

        locX = enemyTurret.getLocX();
        locY = enemyTurret.getLocY();

        missileRotation = Math.atan2(state.getTankLocY() - enemyTurret.getLocY() - 50,
                state.getTankLocX() - enemyTurret.getLocX() - 50);

        try {
            enemyHomingMissileImage = ImageIO.read(new File("src/pictures/homingMissile.png"));
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

        graphics2D.drawImage(enemyHomingMissileImage, transform, null);
    }

    /**
     * getter for bounds
     * @param state game state
     * @return bounds
     */
    public Rectangle getBounds(GameState state) {
        return new Rectangle(locX, locY, enemyHomingMissileImage.getWidth(), enemyHomingMissileImage.getHeight());
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
