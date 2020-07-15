package enemy;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyWallTurretMissile {

    private BufferedImage enemyWallTurretMissileImage;

    private GameFrame game;
    private EnemyWallTurret enemyWallTurret;

    private int locX;
    private int locY;

    private double missileRotation;
    private AffineTransform transform = new AffineTransform();

    private int speed;
    private double slope;

    /**
     * constructor for the EnemyWallTurretMissile
     * @param game game frame
     * @param state game state
     * @param speed speed
     * @param enemyWallTurret enemyWallTurret
     */
    public EnemyWallTurretMissile(GameFrame game, GameState state, int speed, EnemyWallTurret enemyWallTurret) {
        this.game = game;
        this.speed = speed;
        this.enemyWallTurret = enemyWallTurret;

        this.locX = enemyWallTurret.getLocX();
        this.locY = enemyWallTurret.getLocY();

        missileRotation = Math.atan2(state.getTankLocY() - locY - 50,
                state.getTankLocX() - locX - 50);

        try {
            enemyWallTurretMissileImage = ImageIO.read(new File("src/pictures/missile.png"));
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
        int deltaY = locY - state.getTankLocY();
        int deltaX = state.getTankLocX() - locX;

        slope = ((float)deltaY / deltaX);

        if(deltaX < 0) {
            speed *= -1;
        }
    }

    /**
     * calculates and sets the enemies missile location
     * @param state game state
     */
    public void calculate(GameState state) {
        setLocX((int) (locX + speed / (Math.sqrt(1 + (slope * slope)))));
        setLocY((int) (locY - speed * slope / (Math.sqrt(1 + (slope * slope)))));
    }

    /**
     * paints the missile
     * @param graphics2D graphic 2D
     * @param state game state
     */
    public void paint(Graphics2D graphics2D, GameState state) {

        calculate(state);
        transform.setToTranslation(locX - xChange(state), locY - yChange(state));
        transform.rotate(missileRotation);

        graphics2D.drawImage(enemyWallTurretMissileImage, transform, null);
    }

    /**
     * getter for bounds
     * @param state game state
     * @return bounds
     */
    public Rectangle getBounds(GameState state) {
        return new Rectangle(locX, locY, enemyWallTurretMissileImage.getWidth(), enemyWallTurretMissileImage.getHeight());
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

