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

public class EnemyTank {

    private GameFrame game;
    private GameState state;

    private BufferedImage enemyTankBody1Image;
    private BufferedImage enemyTankBody2Image;
    private BufferedImage enemyTankGunImage;
    private BufferedImage destroyed;

    private int health;

    private int locX;
    private int locY;

    private int startX;
    private int startY;

    private String type;
    private int length;

    private int distance = 2;

    /**
     * constructor for the Enemy Tank class
     * @param game game frame
     * @param state game state
     * @param startX starting point
     * @param startY starting point
     * @param type horizontal or vertical
     * @param length length of moving
     */
    public EnemyTank(GameFrame game, GameState state, int startX, int startY, String type, int length) {
        this.game = game;
        this.state = state;

        this.startX = startX;
        this.startY = startY;
        this.type = type;
        this.length = length;

        this.locX = startX;
        this.locY = startY;

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
            enemyTankBody1Image = ImageIO.read(new File("src/pictures/enemyTank1.png"));
            enemyTankBody2Image = ImageIO.read(new File("src/pictures/enemyTank2.png"));
            enemyTankGunImage = ImageIO.read(new File("src/pictures/enemyTankGun.png"));
            destroyed = ImageIO.read(new File("src/pictures/destroyed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * updates enemy location
     */
    public void update() {
        if(!tankCollision(game, state)){
            if(type.equals("h")){
                if(distance > 0){
                    if(locX <= startX + length){
                        locX += distance;
                    }
                }
                if(distance < 0){
                    if(locX >= startX){
                        locX += distance;
                    }
                }
                if(distance > 0){
                    if(locX == startX + length){
                        distance = -distance;
                    }
                }
                if(distance < 0){
                    if(locX == startX){
                        distance = -distance;
                    }
                }
            }
            if(type.equals("v")){
                if(distance > 0){
                    if(locY <= startY + length){
                        locY += distance;
                    }
                }
                if(distance < 0){
                    if(locY >= startY){
                        locY += distance;
                    }
                }
                if(distance > 0){
                    if(locY == startY + length){
                        distance = -distance;
                    }
                }
                if(distance < 0){
                    if(locY == startY){
                        distance = -distance;
                    }
                }
            }
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
     * paints the enemy tank body
     * @param graphics2D graphic 2D
     * @param state game state
     */
    public void paintBody(Graphics2D graphics2D, GameState state) {
        update();
        if(type.equals("h")){
            graphics2D.drawImage(enemyTankBody1Image, locX - xChange(state), locY - yChange(state), null);
        }
        if(type.equals("v")){
            graphics2D.drawImage(enemyTankBody2Image, locX - xChange(state), locY - yChange(state), null);
        }
    }

    /**
     * paints enemy tanks gun
     * @param graphics2D graphic 2D
     * @param state game state
     */
    public void paintGun1(Graphics2D graphics2D, GameState state) {
        AffineTransform transform = new AffineTransform();
        transform.setToTranslation(locX + 50 - xChange(state), locY + 50 - yChange(state));
        transform.rotate(Math.atan2(state.getTankLocY() - locY, state.getTankLocX() - locX));
        transform.translate(-20, -20);

        graphics2D.drawImage(enemyTankGunImage, transform, null);
    }

    /**
     * getter for bounds
     * @return bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(locX, locY, enemyTankBody1Image.getWidth(), enemyTankBody1Image.getHeight());
    }

    /**
     * checks if enemy tank had collision with our missile
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
                    state.getEnemyTanks().remove(this);
                    AudioPlayer enemyDestroyed=new AudioPlayer("sound effects/enemydestroyed.wav",0);
                    Map.destroyedArrayList.add(new Destroyed(locX, locY));
                }
            }
        }
    }

    /**
     * checks if enemy tank had collision with our bullet
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
                    state.getEnemyTanks().remove(this);
                    AudioPlayer enemyDestroyed=new AudioPlayer("sound effects/enemydestroyed.wav",0);
                    Map.destroyedArrayList.add(new Destroyed(locX, locY));
                }
            }
        }
    }

    /**
     * checks if enemy tank had collision with our tank
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
