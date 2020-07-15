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

public class EnemyWallTurret {

    private BufferedImage enemyWallTurret;

    private int locX;
    private int locY;

    private int health;

    /**
     * constructor for the EnemyWallTurret class
     * @param locX x
     * @param locY y
     */
    public EnemyWallTurret(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;

        if(GameFrame.difficulty == 1){
            health = 2;
        }
        else if(GameFrame.difficulty == 2){
            health = 4;
        }
        else if (GameFrame.difficulty == 3){
            health = 6;
        }

        try {
            enemyWallTurret = ImageIO.read(new File("src/pictures/wallTurret.png"));
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
     * paints the body of enemy wall turret
     * @param graphics2D graphic 2D
     * @param state game state
     */
    public void paintBody(Graphics2D graphics2D, GameState state) {
        AffineTransform transform = new AffineTransform();
        transform.setToTranslation(locX - xChange(state), locY - yChange(state));
        transform.rotate(Math.atan2(state.getTankLocY() - locY, state.getTankLocX() - locX));
        transform.translate(-50, -50);

        graphics2D.drawImage(enemyWallTurret, transform,null);
    }

    /**
     * getter for bounds
     * @return bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(locX, locY, enemyWallTurret.getWidth(), enemyWallTurret.getHeight());
    }

    /**
     * checks if enemy wall turret had collision with our missile
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
                    state.getEnemyWallTurrets().remove(this);
                    AudioPlayer enemyDestroyed=new AudioPlayer("sound effects/enemydestroyed.wav",0);
                    Map.destroyedArrayList.add(new Destroyed(locX, locY));
                }
            }
        }
    }

    /**
     * checks if enemy wall turret had collision with our bullet
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
                    state.getEnemyWallTurrets().remove(this);
                    AudioPlayer enemyDestroyed=new AudioPlayer("sound effects/enemydestroyed.wav",0);
                    Map.destroyedArrayList.add(new Destroyed(locX, locY));
                }
            }
        }
    }

    public int getLocX() {
        return locX;
    }
    public int getLocY() {
        return locY;
    }


}
