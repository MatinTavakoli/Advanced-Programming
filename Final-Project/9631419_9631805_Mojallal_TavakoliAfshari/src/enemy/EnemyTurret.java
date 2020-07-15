package enemy;

import components.Destroyed;
import game.GameFrame;
import game.GameState;
import main.AudioPlayer;
import main.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyTurret {

    private BufferedImage enemyTurretImage;

    private int locX;
    private int locY;

    private int health;

    /**
     * constructor for the EnemyTurret class
     * @param locX x
     * @param locY y
     */
    public EnemyTurret(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;

        if(GameFrame.difficulty == 1){
            health = 3;
        }
        else if(GameFrame.difficulty == 2){
            health = 6;
        }
        else if (GameFrame.difficulty == 3){
            health = 9;
        }

        try {
            enemyTurretImage = ImageIO.read(new File("src/pictures/turret.png"));
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
     * paints the body of enemy turret
     * @param graphics2D graphic 2D
     * @param state game state
     */
    public void paintBody(Graphics2D graphics2D, GameState state) {
        AffineTransform transform = new AffineTransform();
        transform.setToTranslation(locX - xChange(state), locY - yChange(state));
        transform.rotate(Math.atan2(state.getTankLocY() - locY, state.getTankLocX() - locX));
        transform.rotate(4);
        transform.translate(-70, -70);

        graphics2D.drawImage(enemyTurretImage, transform,null);
    }

    /**
     * getter for bounds
     * @return bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(locX, locY, enemyTurretImage.getWidth(), enemyTurretImage.getHeight());
    }

    /**
     * checks if enemy Turret had collision with our missile
     * @param state game state
     */
    public void missileCollision(GameState state) {
        for (int i = 0; i < state.getMissiles().size(); i++) {
            if (state.getMissiles().get(i).getBounds().intersects(getBounds())) {
                state.getMissiles().remove(state.getMissiles().get(i));
                if(health > 0){
                    health = health - 3;
                }
                if(health <= 0){
                    state.getEnemyTurrets().remove(this);
                    AudioPlayer enemyDestroyed=new AudioPlayer("sound effects/enemydestroyed.wav",0);
                    Map.destroyedArrayList.add(new Destroyed(locX, locY));
                }
            }
        }
    }

    /**
     * checks if enemy turret had collision with our bullet
     * @param state game state
     */
    public void bulletCollision(GameState state){
        for (int i = 0; i < state.getBullets().size(); i++) {
            if (state.getBullets().get(i).getBounds().intersects(getBounds())) {
                state.getBullets().remove(state.getBullets().get(i));
                if(health > 0){
                    health = health - 1;
                }
                if(health <= 0){
                    state.getEnemyTurrets().remove(this);
                    AudioPlayer enemyDestroyed=new AudioPlayer("sound effects/enemydestroyed.wav",0);
                    Map.destroyedArrayList.add(new Destroyed(locX, locY));
                }
            }
        }
    }

    /**
     * checks if enemy turret had collision with our tank
     * @param game game frame
     * @param state game state
     * @return boolean
     */
    public boolean tankCollision(GameFrame game, GameState state) {
        return game.getTankObject().getBounds(state).intersects(getBounds());
    }

    public int getLocX() {
        return locX;
    }
    public int getLocY() {
        return locY;
    }
}
