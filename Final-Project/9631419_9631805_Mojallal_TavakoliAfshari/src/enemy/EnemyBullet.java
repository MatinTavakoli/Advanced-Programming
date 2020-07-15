package enemy;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyBullet {

    private BufferedImage enemyBulletImage;

    private GameFrame game;
    private EnemyCar enemyCar;

    private int locX;
    private int locY;

    private double bulletRotation;
    private AffineTransform transform = new AffineTransform();

    private int speed;
    private double slope;

    /**
     * constructor for the EnemyBullet class
     * @param game game frame
     * @param state game state
     * @param speed speed
     * @param enemyCar enemyCar
     */
    public EnemyBullet(GameFrame game, GameState state, int speed, EnemyCar enemyCar) {
        this.game = game;
        this.speed = speed;
        this.enemyCar = enemyCar;

        locX = enemyCar.getLocX() + 50;
        locY = enemyCar.getLocY() + 50;

        bulletRotation = Math.atan2(state.getTankLocY() - enemyCar.getLocY() - 50,
                state.getTankLocX() - enemyCar.getLocX() - 50);

        try {
            enemyBulletImage = ImageIO.read(new File("src/pictures/enemyBullet.png"));
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
        int deltaY = locY - state.getTankLocY() + 25;
        int deltaX = state.getTankLocX() - locX + 25;

        slope = ((float)deltaY / deltaX);

        if(deltaX < 0) {
            speed *= -1;
        }
    }

    /**
     * calculates and sets the loc of missile
     * @param state game state
     */
    public void calculate(GameState state) {
        setLocX((int) (locX + speed / (Math.sqrt(1 + (slope * slope)))));
        setLocY((int) (locY - speed * slope / (Math.sqrt(1 + (slope * slope)))));
    }

    /**
     * paints the bullet
     * @param graphics2D graphics 2D
     * @param state game state
     */
    public void paint(Graphics2D graphics2D, GameState state) {

        calculate(state);
        transform.setToTranslation(locX - xChange(state), locY - yChange(state));
        transform.rotate(bulletRotation);

        graphics2D.drawImage(enemyBulletImage, transform, null);
    }

    /**
     * getter for bounds
     * @param state game state
     * @return bounds
     */
    public Rectangle getBounds(GameState state) {
        return new Rectangle(locX, locY, enemyBulletImage.getWidth(), enemyBulletImage.getHeight());
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
