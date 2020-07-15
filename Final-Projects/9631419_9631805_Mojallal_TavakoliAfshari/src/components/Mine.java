package components;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mine {

    private BufferedImage mineImage;

    private int x;
    private int y;

    private GameFrame game;

    /**
     * constructor for the Mine class
     * @param game game frame
     * @param x x
     * @param y y
     */
    public Mine(GameFrame game, int x, int y){
        this.game = game;
        this.x = x;
        this.y = y;

        try{
            mineImage = ImageIO.read(new File("src/pictures/mine.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for image
     * @return image
     */
    public BufferedImage getImage() {
        return mineImage;
    }

    /**
     * getter for bounds
     * @return bounds
     */
    public Rectangle getBounds(){
        return new Rectangle(x + 25, y + 25, 50, 50);
    }

    /**
     * checks if tank had collision with tank
     * @param state game state
     * @return boolean
     */
    public boolean collision(GameState state){
        return game.getTankObject().getBounds(state).intersects(getBounds());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
