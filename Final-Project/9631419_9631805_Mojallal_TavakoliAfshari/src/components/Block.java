package components;

import game.GameFrame;
import game.GameState;
import main.AudioPlayer;
import main.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Block {

    private BufferedImage block1Image;
    private BufferedImage block2Image;
    private BufferedImage block3Image;
    private BufferedImage block4Image;

    private GameFrame game;

    private int health = 12;

    private int x;
    private int y;

    /**
     * constructor for the Block class
     * @param game game frame
     * @param x x
     * @param y y
     */
    public Block(GameFrame game, int x, int y){
        this.game = game;
        this.x = x;
        this.y = y;

        try {
            block1Image = ImageIO.read(new File("src/pictures/block1.png"));
            block2Image = ImageIO.read(new File("src/pictures/block2.png"));
            block3Image = ImageIO.read(new File("src/pictures/block3.png"));
            block4Image = ImageIO.read(new File("src/pictures/block4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for image
     * @return image
     */
    public BufferedImage getImage() {
        if(health <= 12 && health > 9){
            return block1Image;
        }
        if(health <= 9 && health > 6){
            return block2Image;
        }
        if(health <= 6 && health > 3){
            return block3Image;
        }
        if(health <= 3 && health > 0){
            return block4Image;
        }
        else return null;
    }

    /**
     * getter for bounds
     * @return rectangle
     */
    public Rectangle getBounds(){
        return new Rectangle(x, y, block1Image.getWidth(), block1Image.getHeight());
    }

    /**
     * checks if block had collision with our tank
     * @param state game state
     * @return boolean
     */
    public boolean tankCollision(GameState state){
        return game.getTankObject().getBounds(state).intersects(getBounds());
    }

    /**
     * checks if block had collision with our missiles
     * @param state game state
     */
    public void missileCollision(GameState state) {
        for (int i = 0; i < state.getMissiles().size(); i++) {
            if (state.getMissiles().get(i).getBounds().intersects(getBounds())) {
                state.getMissiles().remove(state.getMissiles().get(i));
                health -= 3;
                if(health <= 0){
                    Map.blocks.remove(this);
                }
                AudioPlayer wallCollisionSFX=new AudioPlayer("sound effects/softwall.wav",0);
            }
        }
    }

    /**
     * checks if block had collision with our bullets
     * @param state game state
     */
    public void bulletCollision(GameState state) {
        for (int i = 0; i < state.getBullets().size(); i++) {
            if (state.getBullets().get(i).getBounds().intersects(getBounds())) {
                state.getBullets().remove(state.getBullets().get(i));
                health -= 1;
                if(health <= 0){
                    Map.blocks.remove(this);
                }
            }
        }
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
